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

    //back
    private String userID;
    private String imageUrl;
    private String id;

    public Receptes(){}

    public Receptes(String nom, String ingredients, String preparacio, String calories, String uid, String url, String id){
        this.nom = nom;
        this.ingredients = ingredients;
        this.preparacio = preparacio;
        this.calories = calories;
        this.userID = uid;
        this.imageUrl = url;
        if(id.trim().isEmpty()){this.id = nom + uid;}
        else this.id = id;

    }

    /* Getters */
    public String getNom(){return nom;}
    public String getIngredients(){return ingredients;}
    public String getPreparacio(){return preparacio.trim();}
    public String getCalories(){return calories.trim();}
    public int getCaloriesInt(){return Integer.parseInt(getCalories());}

    public String getUserId(){return userID;}
    public String getImageUrl(){return imageUrl;}
    public String getId(){return id;}



    /* Setters */
    public void setNom(String nom){this.nom = nom;}
    public void setIngredients(String ingredients){this.ingredients = ingredients;}
    public void setPreparacio(String preparacio){this.preparacio = preparacio;}
    public void setCalories(String calories){ this.calories = calories;}

    public void setUserId(String userId){this.userID = userId;}
    public void setImageUrl(String url){this.imageUrl = url;}
    public void setId(String id){this.id = id;}



    /* Checker */
    public boolean fetaPerUser(Context context){
        SharedPreferences settings = context.getSharedPreferences("USER", 0);
        String uid = settings.getString("user",null);
        return uid.equals(userID);
    }
}