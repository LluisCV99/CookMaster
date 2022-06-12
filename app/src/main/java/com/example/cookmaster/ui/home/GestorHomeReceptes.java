package com.example.cookmaster.ui.home;

import com.example.cookmaster.ui.classes.Receptes;

import java.util.HashMap;

public class GestorHomeReceptes {
    private static GestorHomeReceptes gestorHomeReceptes;
    private HashMap<String, String> receptes;


    public static GestorHomeReceptes getInstance(){
        if(gestorHomeReceptes == null){
            gestorHomeReceptes = new GestorHomeReceptes();
        }
        return gestorHomeReceptes;
    }
    public GestorHomeReceptes(){
        this.receptes = new HashMap<>();
    }


    public HashMap<String, String> getReceptes(){return receptes;}

    public void setReceptes(String rep){
        String s = rep.toString();
    }


    public void afegeixRecepta(String apat, Receptes recepta){
        receptes.put(apat, recepta.getId());
    }

    public void afegeixRecepta(String apat, String recepta){
        receptes.put(apat, recepta);
    }



    public String getRecepta(String apat){
        return receptes.get(apat);
    }

    public boolean dayCheck(String apat){
        return receptes.containsKey(apat);
    }

    public void deleteRecepta(String apat){
        receptes.remove(apat);
    }

}
