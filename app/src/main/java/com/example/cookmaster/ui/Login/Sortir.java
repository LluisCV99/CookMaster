package com.example.cookmaster.ui.Login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cookmaster.R;
public class Sortir extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_sortir);

        final Button deleteButton = findViewById(R.id.bsortir);


        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences settings = getSharedPreferences("USER", 0);
                SharedPreferences.Editor editor = settings.edit();
                editor.clear().apply();
                Intent newAct = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(newAct);
                finish();
            }
        });
    }
}
