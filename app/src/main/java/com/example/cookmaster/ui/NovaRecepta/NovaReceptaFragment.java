package com.example.cookmaster.ui.NovaRecepta;

import static android.app.Activity.RESULT_OK;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;


import com.example.cookmaster.MainActivity;
import com.example.cookmaster.R;
import com.example.cookmaster.data.dataStore;
import com.example.cookmaster.databinding.NovaReceptaBinding;
import com.example.cookmaster.ui.classes.Receptes;
import com.example.cookmaster.ui.receptes.GestorReceptes;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.IOException;


public class NovaReceptaFragment extends Fragment {

    private NovaReceptaBinding binding;
    private NovaReceptaViewModel novaReceptaViewModel;
    private final int GALLERY_REQ_CODE = 1000;
    GestorReceptes llista;
    ImageView imgGallery;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        novaReceptaViewModel = new ViewModelProvider(this).get(NovaReceptaViewModel.class);

        binding = NovaReceptaBinding.inflate(inflater, container, false);

        llista = ((MainActivity) requireActivity()).receptesDB;
        return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        EditText nomText = view.findViewById(R.id.nom_text);
        EditText ingredientsText = view.findViewById(R.id.ingredients_text);
        EditText preparacioText = view.findViewById(R.id.preparacio_text);

        imgGallery = view.findViewById(R.id.image_recepta);
        Button btnGallery = view.findViewById(R.id.btnGallery);

        btnGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent galeria = new Intent(Intent.ACTION_PICK);
                galeria.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galeria, GALLERY_REQ_CODE);
            }
        });


        FloatingActionButton confirma = view.findViewById(R.id.floatingActionButton2);
        try {
        confirma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nomRecepta = nomText.getText().toString();
                String ingredientsRecepta = ingredientsText.getText().toString();
                String preparacioRecepta = preparacioText.getText().toString();
                Receptes recepta = new Receptes(nomRecepta,
                        ingredientsRecepta, preparacioRecepta, imgGallery);
                llista.add(recepta);
                Toast.makeText(getContext(), "S'ha desat correctament!", Toast.LENGTH_LONG).show();
            }
        });
        }catch (Exception e){
            Toast.makeText(getContext(), "ha petat " + e.getMessage(), Toast.LENGTH_LONG).show();
        }


    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK){


            if (requestCode == GALLERY_REQ_CODE){

                imgGallery.setImageURI(data.getData());
            }
        }

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}