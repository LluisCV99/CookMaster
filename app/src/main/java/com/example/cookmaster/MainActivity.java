package com.example.cookmaster;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.cookmaster.data.dataStore;
import com.example.cookmaster.data.*;
import com.example.cookmaster.ui.Login.LoginActivity;
import com.example.cookmaster.ui.Login.Settings;
import com.example.cookmaster.ui.receptes.GestorReceptes;
import com.google.android.material.navigation.NavigationView;


import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cookmaster.databinding.ActivityMainBinding;
import java.io.IOException;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;
    private Integer pos;
    private CookMaster cookMaster;
    public GestorReceptes receptesDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        receptesDB = new GestorReceptes();
        cookMaster = CookMaster.getInstance();

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
    public void onStart() {
        super.onStart();
        try{
            HashMap<String, String> map = dataLoad.loadReceptes(getApplicationContext());
            if(map != null) receptesDB.setReceptes(map);

        } catch (IOException | ClassNotFoundException ex) {
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_LONG).show();
        }
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
           dataStore.saveReceptes(receptesDB, getApplicationContext());
        } catch (IOException ex) {
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}
