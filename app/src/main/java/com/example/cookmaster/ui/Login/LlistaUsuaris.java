package com.example.cookmaster.ui.Login;

import java.io.Serializable;
import java.util.ArrayList;

public class LlistaUsuaris <Usuari> implements Serializable {
    protected ArrayList<Usuari> llista;

    public LlistaUsuaris() {
        llista = new ArrayList<>();
    }

    public int getSize() {
        return llista.size();
    }

    public void afegir(Usuari usuari) throws Exception {
        if (llista.contains(usuari)){
            throw new Exception("Ja existeix");
        }
        else{
            llista.add(usuari);
        }
    }

    public void esborrar(Usuari usuari) {
        llista.remove(usuari);
    }

    public Usuari getAt(int position) {
        return llista.get(position);
    }

    public void clear() {
        llista.clear();
    }

    public boolean isEmpty() {
        return (llista.isEmpty());
    }

    public ArrayList<Usuari> getArrayList() {
        ArrayList<Usuari> arrlist = new ArrayList<>(llista);
        return arrlist;
    }
}