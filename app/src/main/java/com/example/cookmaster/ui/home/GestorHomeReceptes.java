package com.example.cookmaster.ui.home;

import com.example.cookmaster.ui.classes.Receptes;

import java.util.HashMap;

public class GestorHomeReceptes {
    private final HashMap<Integer, String> receptes;

    public GestorHomeReceptes(){
        this.receptes = new HashMap<>();
    }

    public GestorHomeReceptes(HashMap<Integer, String> receptes){
        this.receptes = receptes;
    }

    public void getReceptes(){}

    public void setRecepta(int id, String recepta){
        receptes.put(id, recepta);
    }

    public String getRecepta(int id) {
        return receptes.get(id);
    }

    //true: Hi ha recepta
    public boolean dayCheck(int id){
        return receptes.containsKey(id);
    }

    public void deleteRecepta(int id){
        receptes.remove(id);
    }
}
