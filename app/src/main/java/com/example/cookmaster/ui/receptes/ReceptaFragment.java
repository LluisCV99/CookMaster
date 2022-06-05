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
import android.widget.Toast;

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
        TextView calories = view.findViewById(R.id.calories_fill);
        ImageView img = view.findViewById(R.id.image_recepta);
        if(recepta.getFetaUser()){
            //img.setImageDrawable(recepta.getImgId());
        }else {

            switch (recepta.getImgIdInt()) {

                case 1:
                    img.setImageResource(R.drawable.id1);
                    break;

                case 2:
                    img.setImageResource(R.drawable.id2);
                    break;

                case 3:
                    img.setImageResource(R.drawable.id3);
                    break;

                case 4:
                    img.setImageResource(R.drawable.id4);
                    break;

                case 5:
                    img.setImageResource(R.drawable.id5);
                    break;

                case 6:
                    img.setImageResource(R.drawable.id6);
                    break;

                case 7:
                    img.setImageResource(R.drawable.id7);
                    break;

                case 8:
                    img.setImageResource(R.drawable.id8);
                    break;

                case 9:
                    img.setImageResource(R.drawable.id9);
                    break;

                case 10:
                    img.setImageResource(R.drawable.id10);
                    break;

                case 11:
                    img.setImageResource(R.drawable.id11);
                    break;

                default:

                    break;
            }
        }

        nom.setText(recepta.getNom());
        ingredients.setText(recepta.getIngredients());
        preparacio.setText(recepta.getPreparacio());
        calories.setText(recepta.getCalories());


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}