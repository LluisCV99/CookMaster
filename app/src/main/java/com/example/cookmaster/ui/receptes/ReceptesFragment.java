package com.example.cookmaster.ui.receptes;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cookmaster.MainActivity;
import com.example.cookmaster.R;
import com.example.cookmaster.databinding.FragmentReceptesBinding;
import com.example.cookmaster.ui.adapters.ReceptesAdapter;
import com.example.cookmaster.ui.classes.Receptes;
import java.util.ArrayList;
import java.util.Objects;


public class ReceptesFragment extends Fragment implements View.OnClickListener{


    private FragmentReceptesBinding binding;

    RecyclerView recyclerView;
    ArrayList<Receptes> llistaReceptes;



    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        /*
         * Monta el fragment
         */
        ReceptesViewModel receptesViewModel = new ViewModelProvider(this).get(ReceptesViewModel.class);
        View vista = inflater.inflate(R.layout.fragment_receptes, container, false);
        binding = FragmentReceptesBinding.inflate(inflater, container, false);

        /*
         * Monta l'escroller
         */
        recyclerView = (RecyclerView) vista.findViewById(R.id.recyclerId);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        llistaReceptes = ((MainActivity) requireActivity()).receptesDB.getAll();
        ReceptesAdapter adapter = new ReceptesAdapter(llistaReceptes, savedInstanceState);
        recyclerView.setAdapter(adapter);

        final TextView textView = binding.textReceptes;
        receptesViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return vista;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onClick(View view) {

    }
}