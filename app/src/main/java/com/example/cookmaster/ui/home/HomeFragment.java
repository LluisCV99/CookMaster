package com.example.cookmaster.ui.home;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.ui.AppBarConfiguration;

import com.example.cookmaster.R;
import com.example.cookmaster.databinding.FragmentHomeBinding;
import com.example.cookmaster.ui.classes.Receptes;
import com.example.cookmaster.ui.receptes.ReceptaFragment;
import com.example.cookmaster.ui.slideshow.SlideshowFragment;

public class HomeFragment extends Fragment implements View.OnClickListener, View.OnLongClickListener {

    private AppBarConfiguration appBarConfiguration;

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    private FragmentHomeBinding binding;
    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);

        fragmentManager = getParentFragmentManager();

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


    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v){
        Receptes recepta = null;
        switch (v.getId()){
            case R.id.dinar_dill: recepta = homeViewModel.gestio(0, 0); break;
            case R.id.dinar_dim: recepta = homeViewModel.gestio(1,0); break;
            case R.id.dinar_dime: recepta = homeViewModel.gestio(2,0); break;
            case R.id.dinar_dij: recepta = homeViewModel.gestio(3,0); break;
            case R.id.dinar_div: recepta = homeViewModel.gestio(4, 0); break;
            case R.id.dinar_dis: recepta = homeViewModel.gestio(5,0); break;
            case R.id.dinar_dium: recepta = homeViewModel.gestio(6,0); break;

            case R.id.sopar_dill: recepta = homeViewModel.gestio(0,1); break;
            case R.id.sopar_dim: recepta = homeViewModel.gestio(1,1); break;
            case R.id.sopar_dime: recepta = homeViewModel.gestio(2, 1); break;
            case R.id.sopar_dij: recepta = homeViewModel.gestio(3,1); break;
            case R.id.sopar_div: recepta = homeViewModel.gestio(4,1); break;
            case R.id.sopar_dis: recepta = homeViewModel.gestio(5,1); break;
            case R.id.sopar_dium: recepta = homeViewModel.gestio(6,1); break;

            case R.id.Boto_Share:
                //Compartir
                break;
            case R.id.Boto_millora:
                //Valoracio i millora
                break;
        }
        Context context = getContext();
        CharSequence vuida = "vuida";
        CharSequence plena = "P[LENA";
        if(recepta == null){
            fragmentTransaction = fragmentManager.beginTransaction();
        }else{

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