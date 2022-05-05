package com.example.cookmaster.ui.classes;

public class Receptes {
    private String nom;
    private String ingredients;
    private String preparacio;
    private int imgId;

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
    public String getIngredients() {
        return ingredients;
    }
    public String getPreparacio(){return preparacio;}
    public int getImgId() {
        return imgId;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }
    public void setPreparacio(String preparacio){this.preparacio = preparacio; }
    public void setImgId(int imgId) {
        this.imgId = imgId;
    }

}
