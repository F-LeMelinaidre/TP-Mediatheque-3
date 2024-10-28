package fr.vannes.gretajavafx.dao.emprunt;


import fr.vannes.gretajavafx.model.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface EmpruntDAO {

    // Méthode pour ajouter un emprunt
    void ajouterEmprunt(Emprunt emprunt) throws Exception;

    // Méthode pour mettre à jour un emprunt
    void mettreAJourEmprunt(Emprunt emprunt) throws Exception;

    // Méthode pour supprimer un emprunt
    void supprimerEmprunt(int emprunteur_id, String media_id) throws Exception;

    // Méthode pour récupérer un emprunt par emprunteur_id et media_id
    Emprunt getEmpruntById(int emprunteur_id, String media_id) throws Exception;

    // Méthode pour récupérer tous les emprunts
    List<Emprunt> getTousLesEmprunts() throws Exception;

    Emprunteur getEmprunteur(ResultSet rs) throws SQLException;

    Media getMedia(ResultSet rs, Categorie categorie, SousCategorie sousCategorie) throws SQLException;

    Categorie getCategorie(ResultSet rs) throws SQLException;

    SousCategorie getSousCategorie(ResultSet rs) throws SQLException;
}
