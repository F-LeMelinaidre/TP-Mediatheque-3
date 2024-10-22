package fr.vannes.gretajavafx.dao.media;

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
    Category getCategory(ResultSet rs) throws SQLException;
    SubCategory getSubCategory(ResultSet rs) throws SQLException;
}
