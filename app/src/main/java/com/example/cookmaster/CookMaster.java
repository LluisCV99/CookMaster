package com.example.cookmaster;

import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.cookmaster.ui.Login.LoginActivity;
import com.example.cookmaster.ui.Login.RegisterActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

import java.util.regex.Pattern;

public class CookMaster {
    private static CookMaster cookMaster;
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    
    public static CookMaster getInstance(){
        if(cookMaster == null) {
            cookMaster = new CookMaster();
        }
        return cookMaster;
    }

    public void logIn(LoginActivity loginActivity, String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
          if(task.isSuccessful()){
              loginActivity.RunMain(mAuth.getCurrentUser().getUid(), mAuth.getCurrentUser().getDisplayName());
          }else{
              Toast.makeText(loginActivity, "Error: " + task.getException().getMessage() , Toast.LENGTH_SHORT).show();
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
                            registerActivity.goLogin();
                        } else {
                            Toast.makeText(registerActivity, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }


    public String check(String p1, String p2){
        Pattern uppercase = Pattern.compile("[A-Z]");
        Pattern lowercase = Pattern.compile("[a-z]");
        Pattern digit = Pattern.compile("[0-9]");

        if(p1.equals(p2)){
            if(p1.length() < 6){
                return "Password too short";
            }

            if(!uppercase.matcher(p1).find()){
                return "Password must contain one uppercase character";
            }

            if(!lowercase.matcher(p1).find()){
                return "Password must contain one lowercase character";
            }

            if(!digit.matcher(p1).find()){
                return "Password must contain one digit character";
            }

            return "";
        }else{
            return "Passwords don't match";
        }
    }
}
