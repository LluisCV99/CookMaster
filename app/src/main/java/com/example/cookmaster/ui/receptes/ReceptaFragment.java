package com.example.cookmaster.ui.receptes;

import androidx.lifecycle.ViewModelProvider;

import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.cookmaster.MainActivity;
import com.example.cookmaster.R;
import com.example.cookmaster.databinding.ReceptaFragmentBinding;
import com.example.cookmaster.ui.classes.Receptes;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class ReceptaFragment extends ReceptesFragment {

    private ReceptaViewModel receptaViewModel;
    private ReceptaFragmentBinding binding;
    private Receptes recepta;

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

    }

    private void setImage(Uri uri){
        Glide.with(getContext()).load(uri).into(img);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}