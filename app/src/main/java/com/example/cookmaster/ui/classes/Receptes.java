package com.example.cookmaster.ui.classes;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import java.util.HashMap;
import java.util.Map;

public class Receptes {
    //front
    private String nom;
    private String ingredients;
    private String preparacio;
    private String calories;
    private ImageView imgId;

    //back
    private String userID;
    private String imageUrl;
    private String id;

    public Receptes(String nom, String ingredients, String preparacio, ImageView imgId, String calories, String uid, String url){
        this.nom = nom;
        this.ingredients = ingredients;
        this.preparacio = preparacio;
        this.imgId = imgId;
        this.calories = calories;
        this.userID = uid;
        this.imageUrl = url;
        this.id = nom + uid;

    }

    public Receptes(String nom, String ingredients, String preparacio, String imgIdInt){
        this.nom = nom;
        this.ingredients = ingredients;
        this.preparacio = preparacio;
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

    public int getImgIdInt() { return Integer.parseInt("1");}

    public boolean getFetaUser(){ return true;}

    public void setCalories(String caloria){ this.calories = caloria;}

    public String getCalories(){return calories;}

    public void setUrl(String url){
        this.imageUrl = url;
    }

    public String getId(){return id;}
    @Override
    public String toString(){
        return getNom() + ";" + getIngredients() + ";" + getPreparacio() + ";" + imageUrl;
    }

    public Map<String, Object> toMap(){
        Map<String, Object> recepta = new HashMap<>();

        //recepta.put("ID", id);
        recepta.put("Nom", nom);
        recepta.put("Ingredients", ingredients);
        recepta.put("Preparacio", preparacio);
        recepta.put("Creador",userID);
        recepta.put("URI",imgId);

        return recepta;
    }
}