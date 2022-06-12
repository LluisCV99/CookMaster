package com.example.cookmaster.ui.receptes;

import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.cookmaster.MainActivity;
import com.example.cookmaster.R;
import com.example.cookmaster.databinding.ReceptaFragmentBinding;
import com.example.cookmaster.ui.classes.Receptes;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class ReceptaFragment extends ReceptesFragment {

    private ReceptaViewModel receptaViewModel;
    private ReceptaFragmentBinding binding;
    private static Receptes recepta;
    private ImageButton elimina;

    private ImageView img;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        receptaViewModel = new ViewModelProvider(this).get(ReceptaViewModel.class);

        recepta = ((MainActivity) requireActivity()).receptesDB.get(getArguments().getString("idRecepta"));

        binding = ReceptaFragmentBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        StorageReference gsReference;
        //Toast.makeText(getContext(), recepta.getImgIdString(), Toast.LENGTH_LONG).show();
        TextView nom = view.findViewById(R.id.nom);
        TextView ingredients = view.findViewById(R.id.ingredients_fill);
        TextView preparacio = view.findViewById(R.id.preparacio_fill);
        TextView calories = view.findViewById(R.id.calories_fill);
        img = view.findViewById(R.id.image_recepta);
        elimina = view.findViewById(R.id.buttonElimina);

        nom.setText(recepta.getNom());
        ingredients.setText(recepta.getIngredients());
        preparacio.setText(recepta.getPreparacio());
        calories.setText(recepta.getCalories()+"\n\n\n");

        gsReference = FirebaseStorage.getInstance().getReference();

        gsReference.child(recepta.getImageUrl()).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                setImage(uri);
            }
        });

        elimina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteRecepta(view);
            }
        });

    }

    private void setImage(Uri uri){
        Glide.with(getContext()).load(uri).into(img);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void deleteRecepta(View view) {
        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        StorageReference refImg = FirebaseStorage.getInstance().getReference().child("imatges");
        String rid = recepta.getUserId();

        if(uid.equals(rid)){

            //Pot ser que necesitem el nom de la imatge, enlloc de l'url
            //caldria modificar la recepta per guardar el nom de l'imatge
            String img = recepta.getImageUrl();
            if(!img.equals("imatges/1654696473902.jpg")) {
                refImg = refImg.child(img);

                refImg.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(view.getContext(), "Error " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }

            FirebaseDatabase db = FirebaseDatabase.getInstance();
            DatabaseReference ref = db.getReference("receptes");

            ref = ref.child(recepta.getId());
            ref.removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void unused) {
                    receptaEliminada();
                    FragmentManager fm = getActivity().getSupportFragmentManager();
                    fm.popBackStack();
                    Toast.makeText(view.getContext(), "Recepta eliminada correctament.", Toast.LENGTH_SHORT).show();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(view.getContext(), "Error " + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

        }else{
            Toast.makeText(view.getContext(),"Només pots eliminar les teves receptes.", Toast.LENGTH_SHORT).show();
        }
    }

    private void receptaEliminada(){
        ((MainActivity) requireActivity()).receptesDB.remove(recepta);
    }
}