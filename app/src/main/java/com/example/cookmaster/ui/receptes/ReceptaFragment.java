package com.example.cookmaster.ui.receptes;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ImageView;

import com.example.cookmaster.MainActivity;
import com.example.cookmaster.R;
import com.example.cookmaster.databinding.ReceptaFragmentBinding;
import com.example.cookmaster.ui.classes.Receptes;

public class ReceptaFragment extends ReceptesFragment {

    private ReceptaViewModel receptaViewModel;
    private ReceptaFragmentBinding binding;
    private Receptes recepta;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        receptaViewModel = new ViewModelProvider(this).get(ReceptaViewModel.class);

        recepta = ((MainActivity) requireActivity()).receptesDB.get(getArguments().getString("nomRecepta"));

        binding = ReceptaFragmentBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //Toast.makeText(getContext(), recepta.getImgIdString(), Toast.LENGTH_LONG).show();
        TextView nom = view.findViewById(R.id.nom);
        TextView ingredients = view.findViewById(R.id.ingredients_fill);
        TextView preparacio = view.findViewById(R.id.preparacio_fill);
        ImageView img = view.findViewById(R.id.image_recepta);

        if (recepta.getImgIdInt()==0){
            img.setImageDrawable(recepta.getImgId());

        }else{
            img.setImageResource(recepta.getImgIdInt());
        }

        nom.setText(recepta.getNom());
        ingredients.setText(recepta.getIngredients());
        preparacio.setText(recepta.getPreparacio());


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}