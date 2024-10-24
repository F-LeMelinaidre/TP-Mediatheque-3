package fr.vannes.gretajavafx.dao.sousCategorie;

import fr.vannes.gretajavafx.model.Categorie;
import fr.vannes.gretajavafx.model.SousCategorie;

import java.util.ArrayList;

public interface SousCategorieDAO<SousCategorie> {
    Boolean create(SousCategorie sousCategorie);
    SousCategorie find(String id);
    ArrayList<SousCategorie> findAll();
    ArrayList<SousCategorie> findAllByCategory(Categorie categorie);
    SousCategorie update(SousCategorie sousCategorie);
    Boolean delete(SousCategorie sousCategorie);
}
