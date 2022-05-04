package com.example.cookmaster.ui.receptes;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.cookmaster.R;
import com.example.cookmaster.databinding.ReceptaFragmentBinding;
import com.example.cookmaster.ui.adapters.ReceptesAdapter;
import com.example.cookmaster.ui.classes.Receptes;

public class ReceptaFragment extends ReceptesFragment {

    public static ReceptaFragment newInstance() {
        return new ReceptaFragment();
    }

    private ReceptaFragmentBinding binding;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        ReceptaViewModel receptaViewModel =
                new ViewModelProvider(this).get(ReceptaViewModel.class);
        View vista=inflater.inflate(R.layout.recepta_fragment, container, false);


        binding = ReceptaFragmentBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        final TextView textView = binding.nom;
        final TextView textView1 = binding.ingredients;
        final TextView textView2 = binding.preparacio;

        receptaViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        receptaViewModel.getText().observe(getViewLifecycleOwner(), textView1::setText);
        receptaViewModel.getText().observe(getViewLifecycleOwner(), textView2::setText);

        return vista;

    }

    /*
    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState){
        Receptes recepta = requireArguments().getParcelable("te")
    }

     */
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}