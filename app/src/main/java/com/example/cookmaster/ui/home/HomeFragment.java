package com.example.cookmaster.ui.home;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.cookmaster.MainActivity;
import com.example.cookmaster.R;
import com.example.cookmaster.databinding.FragmentHomeBinding;
import com.example.cookmaster.ui.classes.Receptes;

public class HomeFragment extends Fragment implements View.OnClickListener, View.OnLongClickListener {

    FragmentManager fragmentManager;

    private FragmentHomeBinding binding;
    private HomeViewModel homeViewModel;

    private int[] dia;
    private String recepta;
    private boolean args;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);

        fragmentManager = getParentFragmentManager();

        if(getArguments() != null){
            this.args = true;
            this.dia = getArguments().getIntArray("dia");
            this.recepta = getArguments().getString("nomRecepta");
        }else args = false;

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button dinar0 = view.findViewById(R.id.dinar_dill);
        dinar0.setOnClickListener(this);
        dinar0.setOnLongClickListener(this);
        Button dinar1 = view.findViewById(R.id.dinar_dim);
        dinar1.setOnClickListener(this);
        dinar1.setOnLongClickListener(this);
        Button dinar2 = view.findViewById(R.id.dinar_dime);
        dinar2.setOnClickListener(this);
        dinar2.setOnLongClickListener(this);
        Button dinar3 = view.findViewById(R.id.dinar_dij);
        dinar3.setOnClickListener(this);
        dinar3.setOnLongClickListener(this);
        Button dinar4 = view.findViewById(R.id.dinar_div);
        dinar4.setOnClickListener(this);
        dinar4.setOnLongClickListener(this);
        Button dinar5 = view.findViewById(R.id.dinar_dis);
        dinar5.setOnClickListener(this);
        dinar5.setOnLongClickListener(this);
        Button dinar6 = view.findViewById(R.id.dinar_dium);
        dinar6.setOnClickListener(this);
        dinar6.setOnLongClickListener(this);

        Button sopar0 = view.findViewById(R.id.sopar_dill);
        sopar0.setOnClickListener(this);
        sopar0.setOnLongClickListener(this);
        Button sopar1 = view.findViewById(R.id.sopar_dim);
        sopar1.setOnClickListener(this);
        sopar1.setOnLongClickListener(this);
        Button sopar2 = view.findViewById(R.id.sopar_dime);
        sopar2.setOnClickListener(this);
        sopar2.setOnLongClickListener(this);
        Button sopar3 = view.findViewById(R.id.sopar_dij);
        sopar3.setOnClickListener(this);
        sopar3.setOnLongClickListener(this);
        Button sopar4 = view.findViewById(R.id.sopar_div);
        sopar4.setOnClickListener(this);
        sopar4.setOnLongClickListener(this);
        Button sopar5 = view.findViewById(R.id.sopar_dis);
        sopar5.setOnClickListener(this);
        sopar5.setOnLongClickListener(this);
        Button sopar6 = view.findViewById(R.id.sopar_dium);
        sopar6.setOnClickListener(this);
        sopar6.setOnLongClickListener(this);

        Button valoracio = view.findViewById(R.id.Boto_millora);
        valoracio.setOnClickListener(this);
        ImageButton share = view.findViewById(R.id.Boto_Share);
        share.setOnClickListener(this);

        if(args){
            homeViewModel.gestio(dia[0], dia[1], ((MainActivity) requireActivity()).receptesDB.get(recepta));
            this.dia = null;
            this.recepta = null;
        }

    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v){
        Receptes recepta = null;
        boolean rep = false;
        int[] dia = new int[2];
        switch (v.getId()){
            //Apat dia
            case R.id.dinar_dill: rep = true; break;
            case R.id.dinar_dim: dia[1] = 1; rep = true; break;
            case R.id.dinar_dime: dia[1] = 2; rep = true; break;
            case R.id.dinar_dij: dia[1] = 3; rep = true; break;
            case R.id.dinar_div: dia[1] = 4; rep = true; break;
            case R.id.dinar_dis: dia[1] = 5; rep = true; break;
            case R.id.dinar_dium: dia[1] = 6; rep = true; break;

            case R.id.sopar_dill: dia[0] = 1; rep = true; break;
            case R.id.sopar_dim: dia[0] = 1; dia[1] = 1; rep = true; break;
            case R.id.sopar_dime: dia[0] = 1; dia[1] = 2; rep = true; break;
            case R.id.sopar_dij: dia[0] = 1; dia[1] = 3; rep = true; break;
            case R.id.sopar_div: dia[0] = 1; dia[1] = 4; rep = true; break;
            case R.id.sopar_dis: dia[0] = 1; dia[1] = 5; rep = true; break;
            case R.id.sopar_dium: dia[0] = 1; dia[1] = 6; rep = true; break;

            case R.id.Boto_Share:
                break;
            case R.id.Boto_millora:
                break;
        }
        if (rep){
            recepta = homeViewModel.gestio(dia[0], dia[1]);
            Bundle bundle = new Bundle();
            if(recepta == null){
                bundle.putBoolean("recepta", false);
                bundle.putIntArray("dia", dia);
                Navigation.findNavController(v).navigate(R.id.nav_receptes, bundle);
            }else {
                bundle.putString("nomRecepta", recepta.getNom());
                Navigation.findNavController(v).navigate(R.id.nav_recepta, bundle);
            }
        }else{
            Toast.makeText(this.getContext(), "avam", Toast.LENGTH_SHORT).show();
        }
    }


    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onLongClick(View view) {
        Context context = getContext();
        String state = "false";
        CharSequence error = "S'ha produit un error";
        CharSequence done = "S'ha eliminat correctament el\n";
        switch (view.getId()){
            case R.id.dinar_dill: state = homeViewModel.delete(0, 0); break;
            case R.id.dinar_dim: state = homeViewModel.delete(1,0); break;
            case R.id.dinar_dime: state = homeViewModel.delete(2,0); break;
            case R.id.dinar_dij: state = homeViewModel.delete(3,0); break;
            case R.id.dinar_div: state = homeViewModel.delete(4, 0); break;
            case R.id.dinar_dis: state = homeViewModel.delete(5,0); break;
            case R.id.dinar_dium: state = homeViewModel.delete(6,0); break;

            case R.id.sopar_dill: state = homeViewModel.delete(0,1); break;
            case R.id.sopar_dim: state = homeViewModel.delete(1,1); break;
            case R.id.sopar_dime: state = homeViewModel.delete(2, 1); break;
            case R.id.sopar_dij: state = homeViewModel.delete(3,1); break;
            case R.id.sopar_div: state = homeViewModel.delete(4,1); break;
            case R.id.sopar_dis: state = homeViewModel.delete(5,1); break;
            case R.id.sopar_dium: state = homeViewModel.delete(6,1); break;
        }

        if(!state.equals("false")){
            done += state;
            Toast.makeText(context, done, Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(context, error, Toast.LENGTH_LONG).show();
        }

        view.findViewById(view.getId());
        return true;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}