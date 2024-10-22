package fr.vannes.gretajavafx.controller;


import fr.vannes.gretajavafx.dao.DAOFactory;
import fr.vannes.gretajavafx.dao.emprunteur.EmprunteurDAO;
import fr.vannes.gretajavafx.dao.emprunteur.EmprunteurDAOImpl;
import fr.vannes.gretajavafx.model.Emprunteur;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class RechercheEmprunteurController {

    @FXML
    private TextField idField; // Assurez-vous que ce champ est bien défini dans le FXML

    @FXML
    private Button rechercherButton;

    @FXML
    private Label resultLabel;

    private EmprunteurDAO emprunteurDAO;

    public RechercheEmprunteurController() {
        DAOFactory daoFactory = DAOFactory.getInstance();
        this.emprunteurDAO = new EmprunteurDAOImpl(daoFactory);
    }

    @FXML
    public void initialize() {
        // Initialisation si nécessaire
    }

    @FXML
    private void rechercherEmprunteur() {
        String idText = idField.getText(); // Récupère la valeur entrée comme texte

        // Vérifier que l'utilisateur a entré quelque chose
        if (idText.isEmpty()) {
            afficherAlerte("Champs manquants", "Veuillez entrer un ID.");
            return;
        }

        int emprunteurId;
        try {
            // Convertir le texte en entier
            emprunteurId = Integer.parseInt(idText);
        } catch (NumberFormatException e) {
            // Si l'entrée n'est pas un nombre, afficher une erreur
            afficherAlerte("Erreur de saisie", "L'ID doit être un nombre.");
            return;
        }

        try {
            // Rechercher l'emprunteur par ID
            Emprunteur emprunteur = emprunteurDAO.getEmprunteurById(emprunteurId);

            if (emprunteur != null) {
                // Afficher les informations de l'emprunteur trouvé
                resultLabel.setText("Emprunteur trouvé : " + emprunteur.getNom() + " " + emprunteur.getPrenom() +
                        ", Né(e) le : " + emprunteur.getDate_naissance());
            } else {
                // Aucun emprunteur trouvé avec cet ID
                resultLabel.setText("Aucun emprunteur trouvé avec cet ID.");
            }

        } catch (Exception e) {
            afficherAlerte("Erreur", "Une erreur s'est produite lors de la recherche.");
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

