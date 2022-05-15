package com.example.cookmaster.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.cookmaster.ui.classes.Receptes;

public class HomeViewModel extends ViewModel {

    private static final MutableLiveData<GestorHomeReceptes> gestorLive = new MutableLiveData<>();
    private static GestorHomeReceptes gestor;

    public HomeViewModel() {
        gestor = new GestorHomeReceptes();
    }
    public HomeViewModel(GestorHomeReceptes gestor){ this.gestor = gestor;}

    LiveData<GestorHomeReceptes> getGestorLive(){return gestorLive;}

    public String get(int id){
        return gestor.dayCheck(id) ? gestor.getRecepta(id) : "";
    }

    public void gestio(int id, String recepta){
        gestor.setRecepta(id, recepta);
        gestorLive.setValue(gestor);
    }

    public boolean delete(int id){
        gestor.deleteRecepta(id);
        gestorLive.setValue(gestor);
        return gestor.dayCheck(id);
    }

}