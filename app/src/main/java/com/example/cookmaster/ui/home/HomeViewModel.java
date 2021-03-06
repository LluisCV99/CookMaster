package com.example.cookmaster.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.cookmaster.MainActivity;
import com.example.cookmaster.ui.classes.Receptes;
import com.example.cookmaster.ui.receptes.GestorReceptes;

import java.util.Map;

public class HomeViewModel extends ViewModel {

    private static final MutableLiveData<GestorHomeReceptes> gestorLive = new MutableLiveData<>();
    private static GestorHomeReceptes gestor;

    public HomeViewModel() {
        gestor = GestorHomeReceptes.getInstance();
    }
    public HomeViewModel(GestorHomeReceptes gestor){ HomeViewModel.gestor = gestor;}

    LiveData<GestorHomeReceptes> getGestorLive(){return gestorLive;}


    public Receptes gestio(String data, GestorReceptes receptesDB){
        Receptes recepta;
        if(gestor.dayCheck(data)){
            recepta = receptesDB.get(gestor.getRecepta(data));
            return recepta;
        }else{
            return null;
        }
    }

    public void gestio(String dia, Receptes recepta){
        gestor.afegeixRecepta(dia, recepta);
        gestorLive.setValue(gestor);
    }

    public String delete(String data){
        gestor.deleteRecepta(data);
        gestorLive.setValue(gestor);
        if(gestor.dayCheck(data)){
            return "false";
        }
        String ret;
        if(data.charAt(0) == 'd'){ ret = "dinar ";}
        else{ ret = "sopar ";}
        switch (data.charAt(1)){
            case 1: ret += "dilluns"; break;
            case 2: ret += "dimarts"; break;
            case 3: ret += "dimecres"; break;
            case 4: ret += "dijous"; break;
            case 5: ret += "divendres"; break;
            case 6: ret += "dissabte"; break;
            case 7: ret += "diumenge"; break;
        }
        return ret;
    }

    public GestorHomeReceptes getGestor() {
        return gestor;
    }

    public String getCalories(GestorReceptes receptesDB) {
        int sum = 0;
        Map<String, String> receptes = gestor.getReceptes();
        for(String r : receptes.values()){
            sum += Integer.parseInt(receptesDB.get(r).getCalories());
        }
        String ret = "" + sum;
        return ret;
    }
}