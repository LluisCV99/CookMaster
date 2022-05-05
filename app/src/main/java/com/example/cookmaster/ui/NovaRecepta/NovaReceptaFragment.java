package com.example.cookmaster.ui.NovaRecepta;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
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
    GestorReceptes llista;

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
        FloatingActionButton confirma = view.findViewById(R.id.floatingActionButton2);
        try {
        confirma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nomRecepta = nomText.getText().toString();
                String ingredientsRecepta = ingredientsText.getText().toString();
                String preparacioRecepta = preparacioText.getText().toString();
                Receptes recepta = new Receptes(nomRecepta,
                        ingredientsRecepta, preparacioRecepta, 1);
                llista.add(recepta);
                Toast.makeText(getContext(), nomRecepta, Toast.LENGTH_LONG).show();
            }
        });
        }catch (Exception e){
            Toast.makeText(getContext(), "ha petat " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}