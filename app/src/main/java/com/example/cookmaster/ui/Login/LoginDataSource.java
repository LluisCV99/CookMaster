package com.example.cookmaster.ui.Login;

import android.widget.Toast;

import com.example.cookmaster.MainActivity;
import com.example.cookmaster.ui.Login.LoggedInUser;
import android.content.Context;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class LoginDataSource {

    public Result<LoggedInUser> login(String username, String password, LlistaUsuaris llista) {
        try {
            for(int i = 0; i < llista.getSize(); i++){
                String[] credencials = llista.getUsers(llista.getAt(i)).split(",");
                if(username.equals(credencials[0]) || username.equals(credencials[1])){
                    if(password.equals(credencials[2])){
                        LoggedInUser fakeUser =
                                new LoggedInUser(
                                        java.util.UUID.randomUUID().toString(),
                                        username);
                        return new com.example.cookmaster.ui.Login.Result.Success<>(fakeUser);
                    }
                }
            }
        } catch (Exception e) {
            return new com.example.cookmaster.ui.Login.Result.Error(new IOException("Error logging in", e));
        }
        return new com.example.cookmaster.ui.Login.Result.Error(new IOException("Error logging in"));
    }

    public void logout() {
        // TODO: revoke authentication
    }
}