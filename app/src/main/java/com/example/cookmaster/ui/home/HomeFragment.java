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
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;

import com.example.cookmaster.MainActivity;
import com.example.cookmaster.R;
import com.example.cookmaster.databinding.FragmentHomeBinding;
import com.example.cookmaster.ui.classes.Receptes;

public class HomeFragment extends Fragment implements View.OnClickListener, View.OnLongClickListener {

    private AppBarConfiguration appBarConfiguration;

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    private FragmentHomeBinding binding;
    private HomeViewModel homeViewModel;

    boolean args;
    int id;
    String recepta;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);

        fragmentManager = getParentFragmentManager();

        recepta = null;
        if(getArguments() != null){
            args = true;
            if(getArguments().containsKey("dia")){this.id = getArguments().getInt("id");}
            if(getArguments().containsKey("nomRecepta")){ this.recepta = getArguments().getString("nomRecepta");}
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
            homeViewModel.gestio(id, ((MainActivity) requireActivity()).receptesDB.get(recepta).getNom());
        }

    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v){
        String recepta = "";
        int id = v.getId();

        if (id != R.id.Boto_Share && id != R.id.Boto_millora){
            Bundle bundle = new Bundle();
            recepta = homeViewModel.get(id);
            if(recepta.equals("")){
                bundle.putBoolean("select", true);
                bundle.putInt("dia", id);
                Navigation.findNavController(v).navigate(R.id.nav_receptes, bundle);
            }else {
                bundle.putString("nomRecepta", homeViewModel.get(id));
                Navigation.findNavController(v).navigate(R.id.nav_recepta, bundle);
            }
        }
    }


    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onLongClick(View view) {
        Context context = getContext();
        int id = view.getId();
        boolean state = false;
        CharSequence error = "S'ha produit un error";
        CharSequence done = "S'ha eliminat correctament el\n";
        System.out.println("2");
        state = homeViewModel.delete(id);
        if(homeViewModel.get(id).equals("")) {
            Toast.makeText(context,"null" , Toast.LENGTH_SHORT).show();
        }

        view.findViewById(view.getId());

        return true;
    }

    public void updateButton(){

    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}