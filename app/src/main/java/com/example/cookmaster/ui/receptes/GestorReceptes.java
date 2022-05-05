package com.example.cookmaster.ui.receptes;

import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.cookmaster.ui.classes.Receptes;

import java.io.FileOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class GestorReceptes implements Serializable {
    private HashMap<String,Receptes> llista;
    public GestorReceptes(){
        llista = new HashMap<>();
        inici();
    }

    public HashMap<String, Receptes> get(){return llista;}
    public void set(HashMap<String, Receptes> map){this.llista = map;}

    public int getSize(){
        return llista.size();
    }

    public void clear(){
        llista.clear();
    }
    public boolean has(@NonNull Receptes recepta){
        return llista.containsKey(recepta.getNom());
    }
    public boolean has(String recepta){
        return llista.containsKey(recepta);
    }

    public boolean add(Receptes recepta){
        if(has(recepta)){return false; }
        llista.put(recepta.getNom(), recepta);
        return true;
    }

    public void remove(@NonNull Receptes recepta){
        llista.remove(recepta.getNom());
    }
    public void remove(String recepta){
        llista.remove(recepta);
    }

    public Receptes get(String recepta){
        if(has(recepta)){
            return llista.get(recepta);
        }
        return null;
    }

    public ArrayList<Receptes> getAll(){
        return new ArrayList<>(llista.values());
    }


    private void inici(){
        add(new Receptes("Amanida de cigrons", "150g Cigrons, 75g tomatic, 75g sal","Juntar tots els ingredients i punto",null));
        add(new Receptes("Amanida de llenties", "150g llenties, 75g tomatic, 75g sal","Juntar tots els ingredients i punto",null));
        add(new Receptes("Amanida de pasta", "150g pasta, 75g tomatic, 75g sal","Juntar tots els ingredients i punto",null));
        add(new Receptes("Arros al curry amb pollastre", "150g arros, 75g pollastre, 75g curry","Juntar tots els ingredients i punto",null));
        add(new Receptes("Canelons d'espinacs", "150g espinacs, 75g plaques, 75g llet","Juntar tots els ingredients i punto",null));
        add(new Receptes("Ensaladilla russa"));
        add(new Receptes("Pizza vegetariana"));
        add(new Receptes("Lasanya"));
        add(new Receptes("Sopa de tortuga"));
        add(new Receptes("Macarerons boloñesa"));
        add(new Receptes("Sopa de ceba tendra silvestre"));
        add(new Receptes("Nuguets de pullastra"));
        add(new Receptes("Gambas al allet"));
        add(new Receptes("Vistek"));
        add(new Receptes("Ansaladiya ukrainesa"));
        add(new Receptes("Vietnamita a la planxa"));
    }
}
