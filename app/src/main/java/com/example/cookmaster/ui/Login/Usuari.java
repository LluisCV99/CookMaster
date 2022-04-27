package com.example.cookmaster.ui.Login;

import java.io.Serializable;

public class Usuari implements Serializable {
    String usuari, correu, contrasenya;

    public Usuari(String usuari, String correu, String contrasenya) {
        this.usuari = usuari;
        this.correu = correu;
        this.contrasenya = contrasenya;
    }

    public String getUsuari() {
        return usuari;
    }

    public void setUsuari(String usuari) {
        this.usuari = usuari;
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
        return (this.getUsuari()+","+this.getCorreu()+","+this.getContrasenya());
    }
}
