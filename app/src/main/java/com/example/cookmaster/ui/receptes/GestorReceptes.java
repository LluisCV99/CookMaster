package com.example.cookmaster.ui.receptes;

import android.graphics.Bitmap;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.cookmaster.ui.classes.Receptes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class GestorReceptes implements Serializable {
    private HashMap<String,Receptes> llista;
    public GestorReceptes(){
        llista = new HashMap<>();
        inici();
    }

    public HashMap<String, String> get(){
        HashMap<String, String> map = new HashMap<>();
        for(Receptes rep : llista.values()){
            map.put(rep.getNom(), rep.toString());
        }
        return map;
    }

    public void setReceptes(HashMap<String, String> map){
        String[] cont;
        for(String s : map.values()){
            cont = s.split(";");
            this.add(new Receptes(cont[0], cont[1], cont[2], Integer.parseInt(cont[3])));
        }
    }

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

        add(new Receptes("Amanida de cigrons", "—70g de cigró sec o 200g de cigró cuit per persona\n" +
                "—2 tomàquets del tipus que us agradi: de la pera, raf, canari, cirerol, de branca… És millor que sigui de carn consistent, per poder tallar-lo a bocins petits sense que es desfaci\n" +
                "—1 pebrot vermell\n" +
                "—1 pebrot verd del tipus italià\n" +
                "—1 llauna de tonyina en oli d’oliva\n" +
                "—1 llauna d’olives farcides d’anxova o un grapat d’olives negres d’Aragó","Renteu totes les hortalisses i eixugueu-les.\n" +
                "\n" +
                "Agafeu el pebrot vermell, obriu-lo pel mig i traieu-ne les llavors. Si el pebrot és gros, en tindreu prou amb la meitat i podeu desar el que no feu servir. El talleu a la juliana, en tires no gaire gruixudes però no gaire primes, i després talleu les tires a daus petits.\n" +
                "\n" +
                "Feu igual amb el pebrot verd. Com que acostumen a ser més petits i de polpa menys carnosa, segurament el tallareu sencer.\n" +
                "\n" +
                "Feu igual amb els tomàquets. És important que no siguin massa madurs perquè no deixin anar massa suc.\n" +
                "\n" +
                "Barregeu-ho tot amb els cigrons i afegiu-hi una mica de sal.\n" +
                "\n" +
                "Poseu-hi les olives que hàgiu triat.\n" +
                "\n" +
                "Aboqueu-hi la tonyina i poseu-hi la quantitat que vulgueu de l’oli d’oliva de la llauna, que li donarà més gust i olor.", "res/drawable/id1.jpg"));
        add(new Receptes("Amanida de llenties", "150g llenties, 75g tomatic, 75g sal","Juntar tots els ingredients i punto", (ImageView) null));
        add(new Receptes("Amanida de pasta", "150g pasta, 75g tomatic, 75g sal","Juntar tots els ingredients i punto",(ImageView) null));
        add(new Receptes("Arros al curry amb pollastre", "150g arros, 75g pollastre, 75g curry","Juntar tots els ingredients i punto",(ImageView) null));
        add(new Receptes("Canelons d'espinacs", "150g espinacs, 75g plaques, 75g llet","Juntar tots els ingredients i punto",(ImageView) null));
        add(new Receptes("Ensaladilla russa"));
        add(new Receptes("Pizza vegetariana"));
        add(new Receptes("Lasanya"));
        add(new Receptes("Sopa de peix"));
        add(new Receptes("Macarrons boloñesa"));
        add(new Receptes("Sopa de ceba"));
        add(new Receptes("Ensaladilla rusa"));
    }
}
