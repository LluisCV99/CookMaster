package com.example.cookmaster.ui.Login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

import com.example.cookmaster.CookMaster;
import com.example.cookmaster.R;


public class RegisterActivity extends AppCompatActivity {

    private EditText username;
    private EditText email;
    private EditText password;
    private EditText passwordRep;

    private CookMaster cookMaster;
    int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        username = findViewById(R.id.usuari);
        email = findViewById(R.id.correu);
        password = findViewById(R.id.new_password);
        passwordRep = findViewById(R.id.new_password_rep);

        cookMaster = CookMaster.getInstance();

        count = 0;

    }

    public void RegisterButtonListener(View v) {
        String userT = username.getText().toString();
        String emailT = email.getText().toString();
        String passwordT = password.getText().toString();
        String passwordRepT = passwordRep.getText().toString();
        String check = cookMaster.check(passwordT, passwordRepT);
        if (check.isEmpty()) {
            cookMaster.signIn(this, userT, emailT, passwordT);
        }else{
            Toast.makeText(this, check, Toast.LENGTH_SHORT).show();
        }
    }

    public void goLogin(){
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }

    public void onBackPressed(){
        count++;
        if(count > 2) {
            finishAffinity();
        }
    }
}

