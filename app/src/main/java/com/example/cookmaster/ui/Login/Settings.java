package com.example.cookmaster.ui.Login;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cookmaster.R;

public class Settings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.settings);

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
                finish();
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
