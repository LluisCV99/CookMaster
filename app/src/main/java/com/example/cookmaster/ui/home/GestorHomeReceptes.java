package com.example.cookmaster.ui.home;

import com.example.cookmaster.ui.classes.Receptes;

public class GestorHomeReceptes {
    private final Receptes[][] receptes;

    public GestorHomeReceptes(){
        this.receptes = new Receptes[2][7];
    }

    public GestorHomeReceptes(Receptes[][] receptes){
        this.receptes = receptes;
    }

    public Receptes[][] getReceptes(){return receptes;}

    public void setRecepta(int dia, int apat, Receptes recepta){
        receptes[apat][dia] = recepta;
    }

    public Receptes getRecepta(int dia, int apat) {
        return receptes[apat][dia];
    }

    public boolean dayCheck(int dia, int apat){
        return !(receptes[apat][dia] == null);
    }

    public void deleteRecepta(int dia, int apat){
        receptes[apat][dia] = null;
    }
}
