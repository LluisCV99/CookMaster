package com.example.cookmaster;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.cookmaster.data.dataStore;
import com.example.cookmaster.data.*;
import com.example.cookmaster.ui.Login.LoginActivity;
import com.example.cookmaster.ui.Login.RegisterActivity;
import com.example.cookmaster.ui.Login.Settings;
import com.example.cookmaster.ui.Login.Sortir;
import com.example.cookmaster.ui.classes.Receptes;
import com.example.cookmaster.ui.receptes.GestorReceptes;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;


import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cookmaster.databinding.ActivityMainBinding;

import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;
    private Integer pos;
    public GestorReceptes receptesDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        try{
            receptesDB = dataLoad.loadReceptes(getApplicationContext());
        } catch (IOException | ClassNotFoundException ex) {
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_LONG).show();
            receptesDB = new GestorReceptes();
        }

        Intent switchActivityIntent = new Intent(getApplicationContext(), LoginActivity.class);

        startActivity(switchActivityIntent);

        setSupportActionBar(binding.appBarMain.toolbar);


        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_receptes, R.id.nav_novarecepta)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item ) {
        switch (item.getItemId()){
            case R.id.action_settings:
                Intent switchActivityIntent = new Intent(getApplicationContext(), Settings.class);
                switchActivityIntent.putExtra("numero", pos);
                startActivity(switchActivityIntent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            pos = data.getIntExtra("numero",0);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        try {
            String temp = dataStore.saveReceptes(receptesDB, getApplicationContext());
            //Toast.makeText(this, temp, Toast.LENGTH_SHORT).show();
        } catch (IOException ex) {
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
    }
