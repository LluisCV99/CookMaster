package com.example.cookmaster.ui.Login;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.view.View;

import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cookmaster.CookMaster;
import com.example.cookmaster.MainActivity;
import com.example.cookmaster.R;


public class LoginActivity extends AppCompatActivity {

    private EditText password;
    private EditText email;
    private CookMaster cookMaster;
    int count;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        password = findViewById(R.id.password);
        email = findViewById(R.id.username);

        cookMaster = CookMaster.getInstance();

        count = 0;

    }

    public void LogInButtonListener(View v){
        String emailT = email.getText().toString();
        String passwordT = password.getText().toString();
        if(emailT.isEmpty() && passwordT.isEmpty()){
            Toast.makeText(this, "Fill Email and Password", Toast.LENGTH_SHORT).show();
        }else if(emailT.isEmpty()){
            Toast.makeText(this, "Fill Email", Toast.LENGTH_SHORT).show();
        }else if(passwordT.isEmpty()){
            Toast.makeText(this, "Fill Password", Toast.LENGTH_SHORT).show();
        }else{
            cookMaster.logIn(this, emailT, passwordT);
        }
    }
    public void onBackPressed(){
        count++;
        if(count > 2) {
            finishAffinity();
        }
    }

    public void RunMain(String uid, String dn) {
        Intent newAct = new Intent(this, MainActivity.class);
        SharedPreferences settings = getSharedPreferences("USER", 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean("login", true);
        editor.putString("user", uid);
        editor.putString("dn", dn);
        editor.apply();
        startActivity(newAct);
        this.finish();
    }


    public void RegisterActListener(View v) {
        Intent newAct = new Intent(this, RegisterActivity.class);
        startActivity(newAct);
        finish();
    }
}
