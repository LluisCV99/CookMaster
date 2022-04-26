package com.example.cookmaster.ui.Login;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.cookmaster.R;

public class RegisterActivity extends AppCompatActivity{

    private LoginViewModel loginViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_register);

        loginViewModel = new ViewModelProvider(this, new LoginViewModelFactory())
                .get(LoginViewModel.class);
        final EditText usernameEditText = findViewById(R.id.usuari);
        final EditText passwordEditText = findViewById(R.id.new_password2);
        final EditText correuEditText = findViewById(R.id.correu);
        final EditText password2EditText = findViewById(R.id.new_password);
        final Button registerButton = findViewById(R.id.Signup);


        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(loginViewModel.isNewPasswordValid(passwordEditText.getText().toString(),
                        password2EditText.getText().toString()) &&
                        loginViewModel.isPasswordValid(passwordEditText.getText().toString())){
                    finish();
                }
                else{
                    Toast.makeText(getApplicationContext(), getString(R.string.contrasenya_inc), Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}

