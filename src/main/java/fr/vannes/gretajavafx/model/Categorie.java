package fr.vannes.gretajavafx.model;

import fr.vannes.gretajavafx.dao.categorie.CategorieDAOImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Categorie {

    private int categorieId;
    private String label;

    private Map<Integer, SousCategorie> sousCategorieMap = new HashMap<Integer, SousCategorie>();

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
        sousCategorieMap.put(sousCategorie.getIdSousCategorie(), sousCategorie);
    }

    public Map<Integer, SousCategorie> getSousCategories() {
        return sousCategorieMap;
    }

    @Override
    public String toString() {
        return label;
    }

}