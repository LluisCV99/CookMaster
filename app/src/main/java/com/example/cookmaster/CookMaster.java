package com.example.cookmaster;

import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.cookmaster.ui.Login.LoginActivity;
import com.example.cookmaster.ui.Login.RegisterActivity;
import com.example.cookmaster.ui.Login.Usuari;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

import java.util.Objects;
import java.util.concurrent.Executor;

public class CookMaster {
    private static CookMaster cookMaster;
    private Usuari usuari;
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    
    public static CookMaster getInstance(){
        if(cookMaster == null) return new CookMaster();
        return cookMaster;
    }


    public void logIn(LoginActivity loginActivity, String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
          if(task.isSuccessful()){
              setUser(mAuth.getCurrentUser(), email, password);
              loginActivity.RunMain();
          }else{
              Toast.makeText(loginActivity, "Error: " + task.getResult().toString() , Toast.LENGTH_SHORT).show();
          }
        });
    }

    public void signIn(RegisterActivity registerActivity, String username, String email, String password){
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();
                            UserProfileChangeRequest cr = new UserProfileChangeRequest.Builder().setDisplayName(username).build();
                            user.updateProfile(cr);
                            setUser(user, email, password);
                        } else {

                            Toast.makeText(registerActivity, "Authentication failed.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void setUser(FirebaseUser user, String email, String password){
        String username = user.getDisplayName();
        String id = user.getUid();
        usuari = new Usuari(username, email, password, id);
    }
}
