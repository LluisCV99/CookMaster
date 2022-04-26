package com.example.cookmaster.ui.Login;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.os.Process;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cookmaster.R;
public class Sortir extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_sortir);

        final EditText newusernameEditText = findViewById(R.id.missatgeSortida);
        final Button deleteButton = findViewById(R.id.bsortir);

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishAffinity();
            }
        });
    }
}
