package com.example.cookmaster.ui.receptes;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cookmaster.R;
import com.example.cookmaster.databinding.FragmentReceptesBinding;
import com.example.cookmaster.ui.adapters.ReceptesAdapter;
import com.example.cookmaster.ui.classes.Receptes;

import java.util.ArrayList;

public class ReceptesFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;

    private FragmentReceptesBinding binding;

    RecyclerView recyclerView;
    ArrayList<Receptes> llistaReceptes;

    public ReceptesFragment() {
    }

    public static ReceptesFragment newInstance(String param1, String param2) {
        ReceptesFragment fragment = new ReceptesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ReceptesViewModel receptesViewModel =
                new ViewModelProvider(this).get(ReceptesViewModel.class);
        View vista=inflater.inflate(R.layout.fragment_receptes, container, false);


        binding = FragmentReceptesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        llistaReceptes=new ArrayList<>();
        recyclerView= (RecyclerView) vista.findViewById(R.id.recyclerId);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        llistaReceptes.add(new Receptes("Amanida de cigrons"));
        llistaReceptes.add(new Receptes("Amanida de llenties"));
        llistaReceptes.add(new Receptes("Amanida de pasta"));
        llistaReceptes.add(new Receptes("Arros al curry amb pollastre"));
        llistaReceptes.add(new Receptes("Canelons d'espinacs"));
        llistaReceptes.add(new Receptes("Ensaladilla russa"));
        llistaReceptes.add(new Receptes("Pizza vegetariana"));
        llistaReceptes.add(new Receptes("Lasanya"));
        llistaReceptes.add(new Receptes("Sopa de tortuga"));
        llistaReceptes.add(new Receptes("Macarerons boloñesa"));
        llistaReceptes.add(new Receptes("Sopa de ceba tendra silvestre"));
        llistaReceptes.add(new Receptes("Nuguets de pullastra"));
        llistaReceptes.add(new Receptes("Gambas al allet"));
        llistaReceptes.add(new Receptes("Vistek"));
        llistaReceptes.add(new Receptes("Ansaladiya ukrainesa"));
        llistaReceptes.add(new Receptes("Vietnamita a la planxa"));







        ReceptesAdapter adapter=new ReceptesAdapter(llistaReceptes);
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



}