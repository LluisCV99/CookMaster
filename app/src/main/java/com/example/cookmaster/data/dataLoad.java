package com.example.cookmaster.data;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;

import com.example.cookmaster.MainActivity;
import com.example.cookmaster.ui.classes.Receptes;
import com.example.cookmaster.ui.home.GestorHomeReceptes;
import com.example.cookmaster.ui.home.HomeFragment;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

public class dataLoad {

    public static void loadReceptes(Context context, MainActivity activity){
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("receptes");
        ArrayList<Receptes> receptesList = new ArrayList<>();

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot post : snapshot.getChildren()){
                    Receptes receptes = post.getValue(Receptes.class);
                    receptesList.add(receptes);
                }
                activity.receptesDB.setReceptes(receptesList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(context, "Error " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }


}
