package com.example.cookmaster.ui.Login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.cookmaster.R;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Settings extends AppCompatActivity {

    private LoginViewModel loginViewModel;
    private LlistaUsuaris llista = new LlistaUsuaris();
    private int pos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.settings);

        Intent data = getIntent();
        pos = data.getIntExtra("numero",0);

        loginViewModel = new ViewModelProvider(this, new LoginViewModelFactory())
                .get(LoginViewModel.class);

        try (FileInputStream fis = openFileInput("users");
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            llista = (LlistaUsuaris) ois.readObject();
        }
        catch (IOException | ClassNotFoundException e) {
            Toast.makeText(getApplicationContext(), "errorio", Toast.LENGTH_LONG).show();
        }

        final EditText newusernameEditText = findViewById(R.id.nouUsuari);
        final EditText newpasswordEditText = findViewById(R.id.new_password3);
        final EditText newpassword2EditText = findViewById(R.id.new_password4);
        final Button backButton = findViewById(R.id.back);
        final Button applyButton = findViewById(R.id.accepta);
        final Button deleteButton = findViewById(R.id.elimina);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        applyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(loginViewModel.isNewPasswordValid(newpasswordEditText.getText().toString(),
                        newpassword2EditText.getText().toString()) &&
                        loginViewModel.isPasswordValid(newpasswordEditText.getText().toString())){
                    ((Usuari) llista.getAt(pos)).setContrasenya(newpasswordEditText.getText().toString());
                    try{
                        FileOutputStream fout = openFileOutput("users", Context.MODE_PRIVATE);
                        ObjectOutputStream oos = new ObjectOutputStream(fout);
                        oos.writeObject(llista);
                        oos.close();
                        Toast.makeText(getApplicationContext(), getString(R.string.canvis), Toast.LENGTH_LONG).show();
                    }
                    catch (IOException e) {
                        Toast.makeText(getApplicationContext(), "errorio", Toast.LENGTH_LONG).show();
                    }
                }
                else{
                    Toast.makeText(getApplicationContext(), getString(R.string.contrasenya_inc), Toast.LENGTH_LONG).show();
                }
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                llista.esborrar(((Usuari)llista.getAt(pos)));
                try{
                    FileOutputStream fout = openFileOutput("users", Context.MODE_PRIVATE);
                    ObjectOutputStream oos = new ObjectOutputStream(fout);
                    oos.writeObject(llista);
                    oos.close();
                    Toast.makeText(getApplicationContext(), getString(R.string.canvis), Toast.LENGTH_LONG).show();
                }
                catch (IOException e) {
                    Toast.makeText(getApplicationContext(), "errorio", Toast.LENGTH_LONG).show();
                }
                Intent switchActivityIntent = new Intent(getApplicationContext(), Sortir.class);
                startActivity(switchActivityIntent);
            }
        });
    }
}
