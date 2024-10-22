package fr.vannes.gretajavafx.dao.emprunteur;

import fr.vannes.gretajavafx.model.Emprunteur;

import java.util.List;

public interface EmprunteurDAO {

    // CREATE : Ajouter un nouvel emprunteur
    void ajouterEmprunteur(Emprunteur emprunteur);

    // READ : Récupérer un emprunteur par son ID
    Emprunteur getEmprunteurById(int id);

    // READ : Récupérer tous les emprunteurs
    List<Emprunteur> getAllEmprunteurs();

    // UPDATE : Mettre à jour un emprunteur existant
    void updateEmprunteur(Emprunteur emprunteur);

    // DELETE : Supprimer un emprunteur par son ID
    void supprimerEmprunteur(int id);
}
