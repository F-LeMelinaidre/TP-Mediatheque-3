package fr.vannes.gretajavafx.model;

import fr.vannes.gretajavafx.dao.categorie.CategorieDAOImpl;

import java.util.ArrayList;
import java.util.Map;

public class Categorie {

    private int categorieId;
    private String label;

    private static ArrayList<Categorie> categoriesList;
    private Map<Integer, SousCategorie> sousCategorie;

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
    public static ArrayList<Categorie> getList() {

        if(categoriesList == null) {
            categoriesList = CategorieDAOImpl.get_instance().findAll();
        }

        return categoriesList;
    }

    @Override
    public String toString() {
        return label;
    }

}