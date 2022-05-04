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

        recyclerView= (RecyclerView) vista.findViewById(R.id.recyclerId);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        llistaReceptes = ((MainActivity) Objects.requireNonNull(getActivity())).receptesDB.getAll();

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

    @Override
    public void onClick(View view) {

    }
}