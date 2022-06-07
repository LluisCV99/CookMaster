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

    private String dia;
    private String recepta;
    private boolean args;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        homeViewModel = new HomeViewModel();

        binding = FragmentHomeBinding.inflate(inflater, container, false);

        fragmentManager = getParentFragmentManager();

        if(getArguments() != null){
            this.args = true;
            this.dia = getArguments().getString("dia");
            this.recepta = getArguments().getString("idRecepta");
        }else{
            args = false;
        }

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
            Receptes rep = ((MainActivity) requireActivity()).receptesDB.get(recepta);
            homeViewModel.gestio(dia, rep);
            this.dia = null;
            this.recepta = null;
        }

    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v){
        Receptes recepta = null;
        boolean rep = true;
        String data = "";
        switch (v.getId()){
            //Apat dia
            case R.id.dinar_dill: data = "d1"; break;
            case R.id.dinar_dim: data = "d2"; break;
            case R.id.dinar_dime: data = "d3";  break;
            case R.id.dinar_dij: data = "d4"; break;
            case R.id.dinar_div: data = "d5"; break;
            case R.id.dinar_dis: data = "d6"; break;
            case R.id.dinar_dium: data = "d7"; break;

            case R.id.sopar_dill: data = "s1"; break;
            case R.id.sopar_dim: data = "s2";  break;
            case R.id.sopar_dime: data = "s3"; break;
            case R.id.sopar_dij: data = "s4"; break;
            case R.id.sopar_div: data = "s5"; break;
            case R.id.sopar_dis: data = "s6"; break;
            case R.id.sopar_dium: data = "s7"; break;

            case R.id.Boto_Share:
                rep = false;
                break;
            case R.id.Boto_millora:
                rep = false;
                break;
        }
        if (rep){


            recepta = homeViewModel.gestio(data);
            Bundle bundle = new Bundle();
            if(recepta == null){
                bundle.putBoolean("recepta", false);
                bundle.putString("dia", data);
                Navigation.findNavController(v).navigate(R.id.nav_receptes, bundle);
            }else {
                bundle.putString("idRecepta", recepta.getId());
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
            case R.id.dinar_dill: state = homeViewModel.delete("d1"); break;
            case R.id.dinar_dim: state = homeViewModel.delete("d2"); break;
            case R.id.dinar_dime: state = homeViewModel.delete("d3"); break;
            case R.id.dinar_dij: state = homeViewModel.delete("d4"); break;
            case R.id.dinar_div: state = homeViewModel.delete("d5"); break;
            case R.id.dinar_dis: state = homeViewModel.delete("d6"); break;
            case R.id.dinar_dium: state = homeViewModel.delete("d7"); break;

            case R.id.sopar_dill: state = homeViewModel.delete("s1"); break;
            case R.id.sopar_dim: state = homeViewModel.delete("s2"); break;
            case R.id.sopar_dime: state = homeViewModel.delete("s3"); break;
            case R.id.sopar_dij: state = homeViewModel.delete("s4"); break;
            case R.id.sopar_div: state = homeViewModel.delete("s5"); break;
            case R.id.sopar_dis: state = homeViewModel.delete("s6"); break;
            case R.id.sopar_dium: state = homeViewModel.delete("s7"); break;
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