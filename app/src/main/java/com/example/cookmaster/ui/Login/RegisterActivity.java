package com.example.cookmaster.ui.Login;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.cookmaster.CookMaster;
import com.example.cookmaster.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity{

    private EditText username;
    private EditText email;
    private EditText password;
    private EditText passwordRep;
    private Button registerButton;

    private CookMaster cookMaster;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        username = findViewById(R.id.usuari);
        email = findViewById(R.id.correu);
        password = findViewById(R.id.new_password);
        passwordRep = findViewById(R.id.new_password_rep);
        registerButton = findViewById(R.id.Signup);

        cookMaster = CookMaster.getInstance();

    }

    public void RegisterButtonListener(View v) {
        String userT = username.getText().toString();
        String emailT = email.getText().toString();
        String passwordT = password.getText().toString();
        String passwordRepT = passwordRep.getText().toString();
        if(check(passwordT, passwordRepT)) cookMaster.signIn(this, userT, emailT, passwordT);

    }

    private boolean check(String p1, String p2){

        Pattern uppercase = Pattern.compile("[A-Z]");
        Pattern lowercase = Pattern.compile("[a-z]");
        Pattern digit = Pattern.compile("[0-9]");

        if(p1.equals(p2)){

            if(p1.length() < 6){
                Toast.makeText(this, "Password too short", Toast.LENGTH_SHORT).show();
                return false;
            }

            if(!uppercase.matcher(p1).find()){
                Toast.makeText(this, "Password must contain one uppercase character", Toast.LENGTH_SHORT).show();
                return false;
            }

            if(!lowercase.matcher(p1).find()){
                Toast.makeText(this, "Password must contain one lowercase character", Toast.LENGTH_SHORT).show();
                return false;
            }

            if(!digit.matcher(p1).find()){
                Toast.makeText(this, "Password must contain one digit character", Toast.LENGTH_SHORT).show();
                return false;
            }

            return true;
        }else{
            Toast.makeText(this, "Passwords don't match", Toast.LENGTH_SHORT).show();
            return false;
        }
    }
    public void goLogin(){
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }
}

