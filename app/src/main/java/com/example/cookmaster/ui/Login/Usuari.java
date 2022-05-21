package com.example.cookmaster.ui.Login;

import java.io.Serializable;

public class Usuari implements Serializable {
    String username;
    String correu;
    String contrasenya;
    String id;

    public Usuari(String username, String correu, String contrasenya, String id) {
        this.username = username;
        this.correu = correu;
        this.contrasenya = contrasenya;
        this.id = id;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCorreu() {
        return correu;
    }

    public void setCorreu(String correu) {
        this.correu = correu;
    }

    public String getContrasenya() {
        return contrasenya;
    }

    public void setContrasenya(String contrasenya) {
        this.contrasenya = contrasenya;
    }

    @Override
    public String toString(){
        return (id + ", " + username + ", " + contrasenya + "," + correu);
    }
}
