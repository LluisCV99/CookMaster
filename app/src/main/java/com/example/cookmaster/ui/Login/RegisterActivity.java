package com.example.cookmaster.ui.Login;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.cookmaster.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class RegisterActivity extends AppCompatActivity{

    private LoginViewModel loginViewModel;
    private LlistaUsuaris llista = new LlistaUsuaris();

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

        try (FileInputStream fis = openFileInput("users");
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            llista = (LlistaUsuaris) ois.readObject();
        }
        catch (IOException | ClassNotFoundException e) {
            Toast.makeText(getApplicationContext(), "errorio", Toast.LENGTH_LONG).show();
        }

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(loginViewModel.isNewPasswordValid(passwordEditText.getText().toString(),
                        password2EditText.getText().toString()) &&
                        loginViewModel.isPasswordValid(passwordEditText.getText().toString())){
                    Usuari user = new Usuari(usernameEditText.getText().toString(),
                            correuEditText.getText().toString(),passwordEditText.getText().toString());
                    try{
                        for(int i = 0; i < llista.getSize(); i++) {
                            String[] credencials = llista.getUsers(llista.getAt(i)).split(",");
                            if(usernameEditText.getText().toString().equals(credencials[0])){
                                Toast.makeText(getApplicationContext(), "l'usuari ja esta en us", Toast.LENGTH_LONG).show();
                                break;
                            }
                            if(correuEditText.getText().toString().equals(credencials[1])){
                                Toast.makeText(getApplicationContext(), "El correu ja esta en us", Toast.LENGTH_LONG).show();
                                break;
                            }
                        }
                        llista.afegir(user);
                    } catch(Exception e){
                        Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_LONG).show();
                    }
                    try{
                        FileOutputStream fout = openFileOutput("users", Context.MODE_PRIVATE);
                        ObjectOutputStream oos = new ObjectOutputStream(fout);
                        oos.writeObject(llista);
                        oos.close();
                    }
                    catch (IOException e) {
                        Toast.makeText(getApplicationContext(), "errorio", Toast.LENGTH_LONG).show();
                    }
                    finish();
                }
                else{
                    Toast.makeText(getApplicationContext(), getString(R.string.contrasenya_inc), Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}

