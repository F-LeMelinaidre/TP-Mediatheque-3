package fr.vannes.gretajavafx.model;

import fr.vannes.gretajavafx.dao.sousCategorie.SousCategorieDAOImpl;

import java.util.ArrayList;

public class SousCategorie {
    private int id_subcategory;
    private String label;
    private static ArrayList<SousCategorie> subCategoriesList;

    public SousCategorie(int id_subcategory, String label) {
        this.id_subcategory = id_subcategory;
        this.label = label;
    }
    public SousCategorie(String label) {
        this.label = label;
    }

    public int getIdSousCategorie() {
        return id_subcategory;
    }

    public void setIdSousCategorie(int id_subcategory) {
        this.id_subcategory = id_subcategory;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public static ArrayList<SousCategorie> getList(Categorie category) {

        if (subCategoriesList != null) {
            subCategoriesList.clear();
        }
        subCategoriesList = SousCategorieDAOImpl.getInstance().findAllByCategory(category);
        return subCategoriesList;
    }

    @Override
    public String toString() {
        return label;
    }
}
