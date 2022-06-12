package com.example.cookmaster.ui.Login;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cookmaster.CookMaster;
import com.example.cookmaster.MainActivity;
import com.example.cookmaster.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Settings extends AppCompatActivity {

    private CookMaster cookMaster;
    private FirebaseAuth mAuth;

    private EditText user;
    private EditText password;
    private EditText passwordRep;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);

        user = findViewById(R.id.nouUsuari);
        password = findViewById(R.id.new_password3);
        passwordRep = findViewById(R.id.new_password4);

        cookMaster = CookMaster.getInstance();
        mAuth = FirebaseAuth.getInstance();
    }

    // Change username listener
    public void UsernameListener(View v){
        String nUsername = user.getText().toString();
        if(!nUsername.isEmpty()) {
            String title = "Change username";
            String message = "Are you sure you want to change your username to " + nUsername + "?";
            buildAlert(title, message, 0, "");
        }else{
            Toast.makeText(this, "Fill empty field", Toast.LENGTH_SHORT).show();
        }
    }

    // Change password listener
    public void PasswordListener(View v){
        String p1 = password.getText().toString();
        String p2 = passwordRep.getText().toString();
        String res = cookMaster.check(p1, p2);

        String title = "Change password";
        String message = "Write your current password: ";

        if(res.isEmpty()){
            buildAlert(title, message, 1, "");
        }else{
            Toast.makeText(this, res, Toast.LENGTH_SHORT).show();
        }
    }

    // Delete user listener
    public void deleteUserListener(View v){
        String title = "Delete user";
        String message = "Are you sure you want to delete your account?";
        buildAlert(title, message, 3, "");
    }



    // Login check true for login, false for error
    private boolean check(String pw){
        final boolean[] bool = {false};
        FirebaseUser fUser = mAuth.getCurrentUser();
        AuthCredential credential = EmailAuthProvider.getCredential(fUser.getEmail(), pw);
        fUser.reauthenticate(credential).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    bool[0] = true;
                }else{
                    Toast.makeText(Settings.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
        return bool[0];
    }



    // Changes username
    private void changeUserName(){
        String nUsername = user.getText().toString();
        FirebaseUser fUser = mAuth.getCurrentUser();
        UserProfileChangeRequest cr = new UserProfileChangeRequest.Builder().setDisplayName(nUsername).build();
        fUser.updateProfile(cr).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "New username " + nUsername, Toast.LENGTH_SHORT).show();
                    SharedPreferences settings = getSharedPreferences("USER", 0);
                    SharedPreferences.Editor editor = settings.edit();
                    editor.remove("dn");
                    editor.putString("dn", nUsername);
                    editor.apply();
                }else{
                    Toast.makeText(getApplicationContext(), "Error " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    // Changes password from user
    private void changePasswd(String pw){
        String p1 = password.getText().toString();
        FirebaseUser fUser = mAuth.getCurrentUser();

        AuthCredential credential = EmailAuthProvider.getCredential(fUser.getEmail(), pw);
        fUser.reauthenticate(credential).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    fUser.updatePassword(p1).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> taskP) {
                            if(task.isSuccessful()){
                                Toast.makeText(Settings.this, "Password changed", Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(Settings.this, "Update Error: " + taskP.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }else{
                    Toast.makeText(Settings.this, "Credential Error: " +  task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    // Deletes user
    private void delete(String pw){
        FirebaseUser fUser = mAuth.getCurrentUser();
        AuthCredential credential = EmailAuthProvider.getCredential(fUser.getEmail(), pw);

        FirebaseDatabase db = FirebaseDatabase.getInstance();
        DatabaseReference ref = db.getReference("calendaris");
        ref = ref.child(fUser.getUid());

        ref.removeValue().addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Settings.this, "Error " + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        fUser.reauthenticate(credential).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                fUser.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Intent intent = new Intent(getApplicationContext(), Sortir.class);
                            startActivity(intent);
                            finish();
                        }else{
                            Toast.makeText(Settings.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Settings.this, "Error " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }


    /**
     * Alert builder and launcher
     * @param title Alert title
     * @param message Alert message
     * @param func Alert function selector{
     *             0: Change username
     *             1: Ask and check user password
     *             2: Change password
     *             3: Delete user
     *             4: Delete check
     * }
     * @param pw Password input
     */
    private void buildAlert(String title, String message, int func, String pw){
        String yes = "Yes";
        EditText editText = new EditText(this);
        editText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        editText.setTransformationMethod(PasswordTransformationMethod.getInstance());

        AlertDialog.Builder alert = new AlertDialog.Builder(Settings.this);
        alert.setTitle(title);
        alert.setMessage(message);
        if(func == 1 || func == 4){
            yes = "Done";
            alert.setView(editText);
        }else {
            alert.setCancelable(false);
        }

        alert.setPositiveButton(yes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                switch (func){
                    case 0:
                        changeUserName();
                        break;
                    case 1:
                        if(check(editText.getText().toString())){
                            String title1 = "Change password";
                            String message1 = "Are you sure you want to change your password?";
                            buildAlert(title1, message1, 2, editText.getText().toString());
                        }
                        break;
                    case 2:
                        changePasswd(pw);
                        break;
                    case 3:
                        String title = "Delete user";
                        String message = "Write your current password: ";
                        buildAlert(title, message, 4, "");
                        break;
                    case 4:
                        delete(editText.getText().toString());
                }
                dialogInterface.dismiss();
            }
        });

        if(func == 0 || func == 2 || func == 3) alert.setNegativeButton("No", null);

        alert.create();
        alert.show();
    }


    // Goes back to main
    public void goMain(View v){
        Intent newAct = new Intent(this, MainActivity.class);
        startActivity(newAct);
        this.finish();
    }

    public void logOutListener(View v){
        SharedPreferences settings = getSharedPreferences("USER", 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.clear().apply();
        Intent newAct = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(newAct);
        finish();
    }
}