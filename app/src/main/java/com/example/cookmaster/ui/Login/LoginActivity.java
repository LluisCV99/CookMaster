package com.example.cookmaster.ui.Login;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.cookmaster.R;
import com.example.cookmaster.databinding.ActivityLoginBinding;
import com.example.cookmaster.ui.Login.LoginViewModel;
import com.example.cookmaster.ui.Login.LoginViewModelFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class LoginActivity extends AppCompatActivity {

    private LoginViewModel loginViewModel;
    private LlistaUsuaris llista = new LlistaUsuaris();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        loginViewModel = new ViewModelProvider(this, new LoginViewModelFactory())
                .get(LoginViewModel.class);
        try (FileInputStream fis = openFileInput("users");
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            llista = (LlistaUsuaris) ois.readObject();
        }
        catch (IOException | ClassNotFoundException e) {
            Toast.makeText(getApplicationContext(), "errorio", Toast.LENGTH_LONG).show();
        }
        final TextView regi = findViewById(R.id.registre);
        final EditText usernameEditText = findViewById(R.id.username);
        final EditText passwordEditText = findViewById(R.id.password);
        final Button loginButton = findViewById(R.id.login);
        final ProgressBar loadingProgressBar = findViewById(R.id.loading);

        loginViewModel.getLoginFormState().observe(this, new Observer<LoginFormState>() {
            @Override
            public void onChanged(@Nullable LoginFormState loginFormState) {
                if (loginFormState == null) {
                    return;
                }
                loginButton.setEnabled(loginFormState.isDataValid());
                if (loginFormState.getUsernameError() != null) {
                    usernameEditText.setError(getString(loginFormState.getUsernameError()));
                }
                if (loginFormState.getPasswordError() != null) {
                    passwordEditText.setError(getString(loginFormState.getPasswordError()));
                }
            }
        });

        loginViewModel.getLoginResult().observe(this, new Observer<LoginResult>() {
            @Override
            public void onChanged(@Nullable LoginResult loginResult) {
                if (loginResult == null) {
                    return;
                }
                loadingProgressBar.setVisibility(View.GONE);
                if (loginResult.getError() != null) {
                    showLoginFailed(loginResult.getError());
                }
                if (loginResult.getSuccess() != null) {
                    updateUiWithUser(loginResult.getSuccess());
                    for(int i = 0; i < llista.getSize(); i++){
                        String[] credencials = llista.getInfo(i);
                        if(usernameEditText.getText().toString().equals(credencials[0]) || usernameEditText.getText().toString().equals(credencials[1])){
                            Intent intent = new Intent();
                            intent.putExtra("numero", i);
                            setResult(RESULT_OK);
                            finish();
                        }
                    }
                }
                //Complete and destroy login activity once successful
            }
        });

        TextWatcher afterTextChangedListener = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // ignore
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // ignore
            }

            @Override
            public void afterTextChanged(Editable s) {
                loginViewModel.loginDataChanged(usernameEditText.getText().toString(),
                        passwordEditText.getText().toString());
            }
        };
        usernameEditText.addTextChangedListener(afterTextChangedListener);
        passwordEditText.addTextChangedListener(afterTextChangedListener);
        passwordEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    try (FileInputStream fis = openFileInput("users");
                         ObjectInputStream ois = new ObjectInputStream(fis)) {
                        llista = (LlistaUsuaris) ois.readObject();
                    }
                    catch (IOException | ClassNotFoundException e) {
                        Toast.makeText(getApplicationContext(), "errorio", Toast.LENGTH_LONG).show();
                    }
                    loginViewModel.login(usernameEditText.getText().toString(),
                            passwordEditText.getText().toString(), llista);
                }
                return false;
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingProgressBar.setVisibility(View.VISIBLE);
                try (FileInputStream fis = openFileInput("users");
                     ObjectInputStream ois = new ObjectInputStream(fis)) {
                    llista = (LlistaUsuaris) ois.readObject();
                }
                catch (IOException | ClassNotFoundException e) {
                    Toast.makeText(getApplicationContext(), "errorio", Toast.LENGTH_LONG).show();
                }
                loginViewModel.login(usernameEditText.getText().toString(),
                        passwordEditText.getText().toString(), llista);
            }
        });

        regi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent switchActivityIntent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(switchActivityIntent);
            }
        });
    }
    private void updateUiWithUser (LoggedInUserView model){
        String welcome = getString(R.string.welcome) + model.getDisplayName();
        // TODO : initiate successful logged in experience
        Toast.makeText(getApplicationContext(), welcome, Toast.LENGTH_LONG).show();
    }

    private void showLoginFailed (@StringRes Integer errorString){
        Toast.makeText(getApplicationContext(), errorString, Toast.LENGTH_SHORT).show();
    }
}