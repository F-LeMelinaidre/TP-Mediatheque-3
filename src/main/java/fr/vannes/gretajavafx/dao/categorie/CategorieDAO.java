package fr.vannes.gretajavafx.dao.categorie;

import java.util.ArrayList;

public interface CategorieDAO<Categorie> {


    Boolean create(Categorie categorie);

    Categorie findByID(int id);
    ArrayList<Categorie> findAll();
    Categorie update(Categorie categorie);
    Boolean delete(Categorie categorie);

    ArrayList findAll(boolean sousCategories);
}
