package com.example.cookmaster.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.cookmaster.ui.classes.Receptes;

public class HomeViewModel extends ViewModel {

    private static final MutableLiveData<GestorReceptes> gestorLive = new MutableLiveData<>();
    private static GestorReceptes gestor;

    public HomeViewModel() {
        gestor = new GestorReceptes();
    }
    public HomeViewModel(GestorReceptes gestor){ this.gestor = gestor;}

    LiveData<GestorReceptes> getGestorLive(){return gestorLive;}

    public Receptes gestio(int dia, int apat){
        Receptes recepta;
        if(gestor.dayCheck(dia, apat)){
            recepta = gestor.getRecepta(dia, apat);
            return recepta;
        }else{
            /*
            // TODO: Canviar el fragment a SlideshowFragment (modificar per que es pugui sel·leccionar una recepta)
            gestor.setRecepta(dia, apat, recepta);
            gestorLive.setValue(gestor);
             */
            return null;
        }
    }

    public void gestio(int dia, int apat, Receptes recepta){
        gestor.setRecepta(dia, apat, recepta);
        gestorLive.setValue(gestor);
    }

    public String delete(int dia, int apat){
        gestor.deleteRecepta(dia, apat);
        gestorLive.setValue(gestor);
        if(!(gestor.dayCheck(dia, apat))){
            return "false";
        }
        String ret;
        if(apat == 0){ ret = "dinar ";}
        else{ ret = "sopar ";}
        switch (dia){
            case 0: ret += "dilluns"; break;
            case 1: ret += "dimarts"; break;
            case 2: ret += "dimecres"; break;
            case 3: ret += "dijous"; break;
            case 4: ret += "divendres"; break;
            case 5: ret += "dissabte"; break;
            case 6: ret += "diumenge"; break;
        }
        return ret;
    }

}