package com.example.cookmaster.ui.Login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

import com.example.cookmaster.CookMaster;
import com.example.cookmaster.MainActivity;
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

        passwordRep.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if(i == EditorInfo.IME_ACTION_GO){
                    InputMethodManager inputMethodManager = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
                    inputMethodManager.hideSoftInputFromWindow(passwordRep.getWindowToken(),0);
                    return true;
                }
                return false;
            }
        });

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

    public void toMain(String uid, String dn){
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

    public void onBackPressed(){
        count++;
        if(count > 2) {
            finishAffinity();
        }
    }
}
