package com.example.cookmaster.ui.receptes;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cookmaster.MainActivity;
import com.example.cookmaster.R;
import com.example.cookmaster.databinding.ReceptaFragmentBinding;
import com.example.cookmaster.ui.adapters.ReceptesAdapter;
import com.example.cookmaster.ui.classes.Receptes;

import org.jetbrains.annotations.Contract;
import org.w3c.dom.Text;

import java.util.ArrayList;

public class ReceptaFragment extends ReceptesFragment {

    private ReceptaViewModel receptaViewModel;
    private ReceptaFragmentBinding binding;
    private Receptes recepta;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        receptaViewModel = new ViewModelProvider(this).get(ReceptaViewModel.class);

        // Arguments per saber quina recepta tractem
        if(getArguments() != null) {

            if(getArguments().containsKey("nomRecepta")) {
                recepta = ((MainActivity) requireActivity()).receptesDB.get(getArguments().getString("nomRecepta"));
            }else if(getArguments().containsKey("recepta")){
                recepta = (Receptes) getArguments().get("recepta");
            }

            else {
                Toast.makeText(getContext(), "Error en rebre recepta", Toast.LENGTH_SHORT).show();
                return null;
            }
        }

        else{
            Toast.makeText(getContext(), "Error en rebre recepta", Toast.LENGTH_SHORT).show();
            return null;
        }

        binding = ReceptaFragmentBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView nom = view.findViewById(R.id.nom);
        TextView ingredients = view.findViewById(R.id.ingredients_fill);
        TextView preparacio = view.findViewById(R.id.preparacio_fill);
        ImageView img = view.findViewById(R.id.image_recepta);

        nom.setText(recepta.getNom());
        ingredients.setText(recepta.getIngredients());
        preparacio.setText(recepta.getPreparacio());
        img.setImageAlpha(recepta.getImgId().getImageAlpha());


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}