package com.example.cookmaster.ui.home;

import com.example.cookmaster.ui.classes.Receptes;

import java.util.HashMap;

public class GestorHomeReceptes {
    private static GestorHomeReceptes gestorHomeReceptes;
    private final HashMap<String, Receptes> receptes;


    public static GestorHomeReceptes getInstance(){
        if(gestorHomeReceptes == null){
            gestorHomeReceptes = new GestorHomeReceptes();
        }
        return gestorHomeReceptes;
    }
    private GestorHomeReceptes(){
        this.receptes = new HashMap<>();
    }

    public GestorHomeReceptes(HashMap receptes){
        this.receptes = receptes;
    }

    public HashMap<String, Receptes> getReceptes(){return receptes;}

    public void setRecepta(String apat, Receptes recepta){
        receptes.put(apat, recepta);
    }

    public Receptes getRecepta(String apat){
        return receptes.get(apat);
    }

    public boolean dayCheck(String apat){
        return receptes.containsKey(apat);
    }

    public void deleteRecepta(String apat){
        receptes.remove(apat);
    }
}
