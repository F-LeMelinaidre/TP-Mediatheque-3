package fr.vannes.gretajavafx.controller;

import fr.vannes.gretajavafx.dao.DAOFactory;
import fr.vannes.gretajavafx.dao.emprunt.EmpruntDAO;
import fr.vannes.gretajavafx.dao.emprunt.EmpruntDAOImpl;
import fr.vannes.gretajavafx.model.Emprunt;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.time.LocalDate;

public class AjoutEmpruntController {

    @FXML
    private TextField emprunteurIdField; // Champ pour ID de l'emprunteur

    @FXML
    private TextField mediaIdField; // Champ pour ID du média

    @FXML
    private DatePicker dateEmpruntField; // Champ pour la date d'emprunt

    @FXML
    private DatePicker dateRetourField; // Champ pour la date de retour

    @FXML
    private Button ajouterButton; // Bouton pour ajouter l'emprunt

    private EmpruntDAO empruntDAO; // DAO pour interagir avec la base de données

    public AjoutEmpruntController() {
        DAOFactory daoFactory = DAOFactory.getInstance();
        this.empruntDAO = new EmpruntDAOImpl(daoFactory);
    }

    @FXML
    public void initialize() {
        // Initialisation si nécessaire
    }

    @FXML
    private void ajouterEmprunt() {
        String emprunteurIdText = emprunteurIdField.getText();
        String mediaIdText = mediaIdField.getText();
        LocalDate dateEmprunt = dateEmpruntField.getValue();
        LocalDate dateRetour = dateRetourField.getValue();

        // Vérification des champs obligatoires
        if (emprunteurIdText.isEmpty() || mediaIdText.isEmpty() || dateEmprunt == null || dateRetour == null) {
            afficherAlerte("Champs manquants", "Veuillez remplir tous les champs.");
            return;
        }

        int emprunteurId;
        int mediaId;

        try {
            // Convertir les textes en entiers
            emprunteurId = Integer.parseInt(emprunteurIdText);
            mediaId = Integer.parseInt(mediaIdText);
        } catch (NumberFormatException e) {
            afficherAlerte("Erreur de saisie", "Les IDs doivent être des nombres entiers.");
            return;
        }

        // Créer l'objet Emprunt
        Emprunt nouvelEmprunt = new Emprunt(emprunteurId, mediaId, dateEmprunt, dateRetour);

        try {
            // Ajouter l'emprunt à la base de données
            empruntDAO.ajouterEmprunt(nouvelEmprunt);
            afficherAlerte("Succès", "Emprunt ajouté avec succès !");
            resetFields(); // Réinitialiser les champs après l'ajout
        } catch (Exception e) {
            afficherAlerte("Erreur", "Une erreur s'est produite lors de l'ajout de l'emprunt : " + e.getMessage());
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

    private void resetFields() {
        // Méthode pour réinitialiser les champs de saisie
        emprunteurIdField.clear();
        mediaIdField.clear();
        dateEmpruntField.setValue(null);
        dateRetourField.setValue(null);
    }
}
