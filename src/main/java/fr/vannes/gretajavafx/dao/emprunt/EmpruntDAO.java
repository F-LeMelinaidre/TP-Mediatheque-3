package fr.vannes.gretajavafx.dao.emprunt;


import fr.vannes.gretajavafx.model.Emprunt;
import java.util.List;

public interface EmpruntDAO {

    // Méthode pour ajouter un emprunt
    void ajouterEmprunt(Emprunt emprunt) throws Exception;

    // Méthode pour mettre à jour un emprunt
    void mettreAJourEmprunt(Emprunt emprunt) throws Exception;

    // Méthode pour supprimer un emprunt
    void supprimerEmprunt(int emprunteur_id, int media_id) throws Exception;

    // Méthode pour récupérer un emprunt par emprunteur_id et media_id
    Emprunt getEmpruntById(int emprunteur_id, int media_id) throws Exception;

    // Méthode pour récupérer tous les emprunts
    List<Emprunt> getTousLesEmprunts() throws Exception;
}
