package fr.vannes.gretajavafx.model;

import java.util.ArrayList;

public class Categorie {

    private int categorieId;
    private String label;

    private ArrayList<SousCategorie> sousCategorieList = new ArrayList<>();

    public Categorie(int categorieId, String label) {
        this.categorieId = categorieId;
        this.label       = label;
    }
    public Categorie(String label) {
        this.label = label;
    }

    public int getIdCategorie() {
        return categorieId;
    }

    public void setIdCategorie(int categorieId) {
        this.categorieId = categorieId;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void addSousCategorie(SousCategorie sousCategorie) {
        this.sousCategorieList.add(sousCategorie);
    }

    public ArrayList<SousCategorie> getSousCategories() {
        return sousCategorieList;
    }

    @Override
    public String toString() {
        return label;
    }

}