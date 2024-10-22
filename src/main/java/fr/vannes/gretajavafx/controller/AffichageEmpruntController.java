package fr.vannes.gretajavafx.controller;


import fr.vannes.gretajavafx.dao.DAOFactory;
import fr.vannes.gretajavafx.dao.emprunt.EmpruntDAO;
import fr.vannes.gretajavafx.dao.emprunt.EmpruntDAOImpl;
import fr.vannes.gretajavafx.model.Emprunt;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;

import java.util.List;

public class AffichageEmpruntController {

    @FXML
    private ListView<Emprunt> listViewEmprunts; // ListView pour afficher les emprunts

    private EmpruntDAO empruntDAO; // DAO pour interagir avec la base de données

    public AffichageEmpruntController() {
        DAOFactory daoFactory = DAOFactory.getInstance();
        this.empruntDAO = new EmpruntDAOImpl(daoFactory);
    }

    @FXML
    public void initialize() {
        // Appeler la méthode pour charger les emprunts lors de l'initialisation
        afficherEmprunts();
    }

    private void afficherEmprunts() {
        try {
            List<Emprunt> emprunts = empruntDAO.getTousLesEmprunts();
            listViewEmprunts.getItems().clear(); // Vider la ListView avant de la remplir

            for (Emprunt emprunt : emprunts) {
                listViewEmprunts.getItems().add(emprunt); // Ajouter chaque emprunt à la ListView
            }
        } catch (Exception e) {
            afficherAlerte("Erreur", "Une erreur s'est produite lors de la récupération des emprunts : " + e.getMessage());
        }
    }

    private void afficherAlerte(String titre, String message) {
        // Méthode pour afficher une alerte d'erreur ou d'information
        Alert alerte = new Alert(Alert.AlertType.INFORMATION);
        alerte.setTitle(titre);
        alerte.setHeaderText(null);
        alerte.setContentText(message);
        alerte.showAndWait();
    }
}

