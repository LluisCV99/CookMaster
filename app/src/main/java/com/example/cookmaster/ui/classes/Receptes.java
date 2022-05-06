package com.example.cookmaster.ui.classes;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;

public class Receptes {
    private String nom;
    private String ingredients;
    private String preparacio;
    private String imgIdInt="0";
    private boolean fetaPerUser = false;

    private ImageView imgId=null;

    public Receptes(String nom, String ingredients, String preparacio, ImageView imgId){
        this.nom = nom;
        this.ingredients = ingredients;
        this.preparacio = preparacio;
        this.imgId = imgId;
        this.fetaPerUser = true;
    }

    public Receptes(String nom, String ingredients, String preparacio, String imgIdInt){
        this.nom = nom;
        this.ingredients = ingredients;
        this.preparacio = preparacio;
        this.imgIdInt = imgIdInt;
    }

    public Receptes(String nom) {
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    public String getIngredients() {
        return ingredients;
    }

    public String getPreparacio(){return preparacio;}

    public Drawable getImgId() {
        return imgId.getDrawable();
    }

    public int getImgIdInt() { return Integer.parseInt(imgIdInt);}

    public boolean getFetaUser(){ return fetaPerUser;}

    @Override
    public String toString(){
        return nom + ";" + ingredients + ";" + preparacio + ";" + imgIdInt;
    }


}