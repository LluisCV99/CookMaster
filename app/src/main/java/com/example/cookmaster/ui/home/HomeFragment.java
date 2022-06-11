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
import androidx.navigation.Navigation;

import com.example.cookmaster.MainActivity;
import com.example.cookmaster.R;
import com.example.cookmaster.data.dataLoad;
import com.example.cookmaster.data.dataStore;
import com.example.cookmaster.databinding.FragmentHomeBinding;
import com.example.cookmaster.ui.classes.Receptes;
import com.example.cookmaster.ui.receptes.GestorReceptes;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HomeFragment extends Fragment implements View.OnClickListener, View.OnLongClickListener {

    FragmentManager fragmentManager;

    private FragmentHomeBinding binding;
    public static HomeViewModel homeViewModel;

    private String dia;
    private String recepta;
    private boolean args;
    private int caloriesTotals=0;

    private static Button dinar0;
    private static Button dinar1;
    private static Button dinar2;
    private static Button dinar3;
    private static Button dinar4;
    private static Button dinar5;
    private static Button dinar6;
    private static Button sopar0;
    private static Button sopar1;
    private static Button sopar2;
    private static Button sopar3;
    private static Button sopar4;
    private static Button sopar5;
    private static Button sopar6;
    private static Button valoracio;
    private static ImageButton share;


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

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

        dinar0 = view.findViewById(R.id.dinar_dill);
        dinar0.setOnClickListener(this);
        dinar0.setOnLongClickListener(this);
        dinar1 = view.findViewById(R.id.dinar_dim);
        dinar1.setOnClickListener(this);
        dinar1.setOnLongClickListener(this);
        dinar2 = view.findViewById(R.id.dinar_dime);
        dinar2.setOnClickListener(this);
        dinar2.setOnLongClickListener(this);
        dinar3 = view.findViewById(R.id.dinar_dij);
        dinar3.setOnClickListener(this);
        dinar3.setOnLongClickListener(this);
        dinar4 = view.findViewById(R.id.dinar_div);
        dinar4.setOnClickListener(this);
        dinar4.setOnLongClickListener(this);
        dinar5 = view.findViewById(R.id.dinar_dis);
        dinar5.setOnClickListener(this);
        dinar5.setOnLongClickListener(this);
        dinar6 = view.findViewById(R.id.dinar_dium);
        dinar6.setOnClickListener(this);
        dinar6.setOnLongClickListener(this);

        sopar0 = view.findViewById(R.id.sopar_dill);
        sopar0.setOnClickListener(this);
        sopar0.setOnLongClickListener(this);
        sopar1 = view.findViewById(R.id.sopar_dim);
        sopar1.setOnClickListener(this);
        sopar1.setOnLongClickListener(this);
        sopar2 = view.findViewById(R.id.sopar_dime);
        sopar2.setOnClickListener(this);
        sopar2.setOnLongClickListener(this);
        sopar3 = view.findViewById(R.id.sopar_dij);
        sopar3.setOnClickListener(this);
        sopar3.setOnLongClickListener(this);
        sopar4 = view.findViewById(R.id.sopar_div);
        sopar4.setOnClickListener(this);
        sopar4.setOnLongClickListener(this);
        sopar5 = view.findViewById(R.id.sopar_dis);
        sopar5.setOnClickListener(this);
        sopar5.setOnLongClickListener(this);
        sopar6 = view.findViewById(R.id.sopar_dium);
        sopar6.setOnClickListener(this);
        sopar6.setOnLongClickListener(this);

        valoracio = view.findViewById(R.id.Boto_millora);
        valoracio.setOnClickListener(this);
        share = view.findViewById(R.id.Boto_Share);
        share.setOnClickListener(this);

        setClickable(homeViewModel != null);
        if(args){
            Receptes rep = ((MainActivity) requireActivity()).receptesDB.get(recepta);
            homeViewModel.gestio(dia, rep);
            this.dia = null;
            this.recepta = null;
        }

    }

    @Override
    public void onResume() {
        super.onResume();
        if(homeViewModel == null){
            loadCalendar(getContext(),FirebaseAuth.getInstance().getUid());
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
            case R.id.Boto_millora:
                rep = false;
                break;
        }
        if (rep){
            GestorReceptes receptesDB = ((MainActivity) requireActivity()).receptesDB;
            recepta = homeViewModel.gestio(data, receptesDB);
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
            Toast.makeText(this.getContext(), caloriesTotals, Toast.LENGTH_SHORT).show();
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

    public static void loadCalendar(Context context, String uid){
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("calendaris");

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot post : snapshot.getChildren()){
                    if(post.getKey().equals(uid)){

                        GestorHomeReceptes ghr = new GestorHomeReceptes();
                        String apat;
                        String recepta;

                        for(DataSnapshot calendari : post.getChildren()){
                            for(DataSnapshot entrada : calendari.getChildren()){
                                apat = entrada.getKey();
                                recepta = (String) entrada.getValue();
                                ghr.afegeixRecepta(apat, recepta);
                            }
                        }
                        homeViewModel = new HomeViewModel(ghr);
                        break;
                    }


                }
                if(homeViewModel == null) homeViewModel = new HomeViewModel();
                setClickable(true);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(context, "Error " + error.getMessage(), Toast.LENGTH_SHORT).show();
                homeViewModel = new HomeViewModel();
            }
        });
    }

    public static void setClickable(boolean set){
        dinar0.setClickable(set);
        dinar1.setClickable(set);
        dinar2.setClickable(set);
        dinar3.setClickable(set);
        dinar4.setClickable(set);
        dinar5.setClickable(set);
        dinar6.setClickable(set);

        sopar0.setClickable(set);
        sopar1.setClickable(set);
        sopar2.setClickable(set);
        sopar3.setClickable(set);
        sopar4.setClickable(set);
        sopar5.setClickable(set);
        sopar6.setClickable(set);

        valoracio.setClickable(set);
        share.setClickable(set);
    }


}