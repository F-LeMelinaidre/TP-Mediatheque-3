package fr.vannes.gretajavafx.dao.media;

import fr.vannes.gretajavafx.model.Categorie;
import fr.vannes.gretajavafx.model.SousCategorie;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface MediaDAO<Media> {

    int count();
    Boolean create(Media media);
    Media findById(String id);
    ArrayList<Media> findAll();
    Media update(Media media);
    Boolean delete(String id);
    Categorie getCategorie(ResultSet rs) throws SQLException;
    SousCategorie getSousCategorie(ResultSet rs) throws SQLException;
}
