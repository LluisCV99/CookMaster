package com.example.cookmaster.ui.classes;

public class Receptes {
    public String nom;
    public String ingredients;
    public String preparacio;
    public int imgId;

    public Receptes(String nom, String ingredients, String preparacio, int imgId){
        this.nom = nom;
        this.ingredients = ingredients;
        this.preparacio = preparacio;
        this.imgId = imgId;
    }

    public Receptes(String nom) {
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    public void setNombre(String nom) {
        this.nom = nom;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredientso) {
        this.ingredients = ingredients;
    }

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }

}
