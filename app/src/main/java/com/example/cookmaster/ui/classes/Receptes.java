package com.example.cookmaster.ui.classes;

import android.content.Context;
import android.content.SharedPreferences;
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
    private String imgId;

    //back
    private String userID;
    private String imageUrl;
    private String id;

    public Receptes(String nom, String ingredients, String preparacio, String imgId, String calories, String uid, String url){
        this.nom = nom;
        this.ingredients = ingredients;
        this.preparacio = preparacio;
        this.imgId = imgId;
        this.calories = calories;
        this.userID = uid;
        this.imageUrl = url;
        this.id = nom + uid;

    }

    /* Getters */
    public String getNom(){return nom;}
    public String getIngredients(){return ingredients;}
    public String getPreparacio(){return preparacio + "\n\n\n";}
    public String getCalories(){return calories;}
    public String getImgId() {return "";} //imgId.getDrawable();    Temp

    public String getUserId(){return userID;}
    public String getImageUrl(){return imageUrl;}
    public String getId(){return id;}

    public int getImgIdInt() { return Integer.parseInt("1");} // Temp


    /* Setters */
    public void setCalories(String caloria){ this.calories = caloria;}
    public void setUrl(String url){this.imageUrl = url;}


    /* Converters */
    @Override
    public String toString(){
        return getNom() + ";" + getIngredients() + ";" + getPreparacio() + ";" + getImgId() + ";"
                + getCalories() + ";" + getUserId() + ";" + getImageUrl() + ";" + getId();
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

    /* Checker */
    public boolean fetaPerUser(Context context){
        SharedPreferences settings = context.getSharedPreferences("USER", 0);
        String uid = settings.getString("user",null);
        return uid.equals(userID);
    }
}