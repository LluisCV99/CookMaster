package com.example.cookmaster.ui.receptes;

import androidx.annotation.NonNull;

import com.example.cookmaster.ui.classes.Receptes;
import com.google.firebase.auth.FirebaseAuth;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class GestorReceptes implements Serializable {
    private HashMap<String,Receptes> llistaNom;
    private HashMap<String,Receptes> llistaId;
    public GestorReceptes(){
        llistaNom = new HashMap<>();
        llistaId = new HashMap<>();
    }

    public void setReceptes(ArrayList<Receptes> list){

        for (Receptes r : list){
            llistaNom.put(r.getNom(), r);
            llistaId.put(r.getId(), r);
        }

    }

    public void set(HashMap<String, Receptes> map){
        ArrayList<Receptes> temp = new ArrayList<>(map.values());
        if(map.containsKey(temp.get(0).getId())){
            llistaId = map;

            for(Receptes r : temp){
                llistaNom.put(r.getNom(), r);
            }
        }else if(map.containsKey(temp.get(0).getNom())){
            llistaNom = map;

            for(Receptes r : temp){
                llistaId.put(r.getId(), r);
            }
        }
    }

    public int getSize(){
        return llistaNom.size();
    }

    public void clear(){
        llistaNom.clear();
        llistaId.clear();
    }
    public boolean has(@NonNull Receptes recepta){
        return llistaNom.containsKey(recepta.getNom());
    }
    public boolean has(String recepta){
        return llistaNom.containsKey(recepta) || llistaId.containsKey(recepta);
    }

    public boolean add(Receptes recepta){
        if(has(recepta)){return false; }
        llistaNom.put(recepta.getNom(), recepta);
        llistaId.put(recepta.getId(), recepta);
        return true;
    }

    public void remove(@NonNull Receptes recepta){
        llistaNom.remove(recepta.getNom());
        llistaId.remove(recepta.getId());
    }
    public void remove(String recepta){
        if(llistaId.containsKey(recepta)){
            llistaNom.remove(llistaId.get(recepta).getNom());
            llistaId.remove(recepta);
        }else if(llistaNom.containsKey(recepta)){
            llistaId.remove(llistaNom.get(recepta).getId());
            llistaNom.remove(recepta);
        }
    }

    public Receptes get(String recepta){
        if(llistaNom.containsKey(recepta)){
            return llistaNom.get(recepta);
        }else if(llistaId.containsKey(recepta)){
            return llistaId.get(recepta);
        }
        return null;
    }

    public ArrayList<Receptes> getAll(){
        return new ArrayList<>(llistaNom.values());
    }
}
