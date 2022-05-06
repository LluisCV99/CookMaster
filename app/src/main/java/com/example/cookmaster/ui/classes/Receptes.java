package com.example.cookmaster.ui.classes;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.content.res.ResourcesCompat;

import com.example.cookmaster.R;

import javax.xml.transform.Source;

public class Receptes {
    private String nom;
    private String ingredients;
    private String preparacio;
    private String imgIdString;

    private ImageView imgId=null;

    public Receptes(String nom, String ingredients, String preparacio, ImageView imgId){
        this.nom = nom;
        this.ingredients = ingredients;
        this.preparacio = preparacio;
        this.imgId = imgId;
    }

    public Receptes(String nom, String ingredients, String preparacio, String imgIdString){
        this.nom = nom;
        this.ingredients = ingredients;
        this.preparacio = preparacio;
        this.imgIdString = imgIdString;
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

    public Bitmap getImgIdString() {
        return Bitmap.createBitmap((Source) imgIdString);

    }


}