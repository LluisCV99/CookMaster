package com.example.cookmaster.ui.classes;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;

public class Receptes {
    private String nom;
    private String ingredients;
    private String preparacio;
    private String imgIdInt = "0";
    private String calories;
    private boolean fetaPerUser = false;

    private ImageView imgId=null;

    public Receptes(String nom, String ingredients, String preparacio, ImageView imgId, String calories){
        this.nom = nom;
        this.ingredients = ingredients;
        this.preparacio = preparacio;
        this.imgId = imgId;
        this.calories = calories;
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

    public String getPreparacio(){return preparacio + "\n\n\n";}

    public Drawable getImgId() {
        return imgId.getDrawable();
    }

    public int getImgIdInt() { return Integer.parseInt(imgIdInt);}

    public boolean getFetaUser(){ return fetaPerUser;}

    public void setCalories(String caloria){ this.calories = caloria;}

    public String getCalories(){return calories;}

    @Override
    public String toString(){
        return getNom() + ";" + getIngredients() + ";" + getPreparacio() + ";" + imgIdInt;
    }


}