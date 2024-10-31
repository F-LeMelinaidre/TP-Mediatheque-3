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
    private TextField emprunteurIdField;

    @FXML
    private TextField mediaIdField;

    @FXML
    private DatePicker dateEmpruntField;

    @FXML
    private DatePicker dateRetourField;

    @FXML
    private Button ajouterButton;

    private EmpruntDAO empruntDAO;

    public AjoutEmpruntController() {
        DAOFactory daoFactory = DAOFactory.getInstance();
        this.empruntDAO = EmpruntDAOImpl.get_instance(daoFactory);
    }

    @FXML
    public void initialize() {

    }

    @FXML
    private void ajouterEmprunt() {
        // TODO MODIFIER LA VALIDATION VERIFIER L EXISTANCE DES ID EMPRUNTEUR ET MEDIA
        String emprunteurIdText = emprunteurIdField.getText();
        String mediaId = mediaIdField.getText();
        LocalDate dateEmprunt = dateEmpruntField.getValue();
        LocalDate dateRetour = dateRetourField.getValue();

        if (emprunteurIdText.isEmpty() || mediaId.isEmpty() || dateEmprunt == null || dateRetour == null) {
            afficherAlerte("Champs manquants", "Veuillez remplir tous les champs.");
            return;
        }

        int emprunteurId;

        try {
            emprunteurId = Integer.parseInt(emprunteurIdText);
        } catch (NumberFormatException e) {
            afficherAlerte("Erreur de saisie", "Les IDs doivent être des nombres entiers.");
            return;
        }

        Emprunt nouvelEmprunt = new Emprunt(emprunteurId, mediaId, dateEmprunt, dateRetour);

        try {
            empruntDAO.ajouterEmprunt(nouvelEmprunt);
            
            afficherAlerte("Succès", "Emprunt ajouté avec succès !");
            resetFields();
        } catch (Exception e) {
            afficherAlerte("Erreur", "Une erreur s'est produite lors de l'ajout de l'emprunt : " + e.getMessage());
        }
    }

    private void afficherAlerte(String titre, String message) {
        Alert alerte = new Alert(Alert.AlertType.INFORMATION);
        alerte.setTitle(titre);
        alerte.setHeaderText(null);
        alerte.setContentText(message);
        alerte.showAndWait();
    }

    private void resetFields() {
        emprunteurIdField.clear();
        mediaIdField.clear();
        dateEmpruntField.setValue(null);
        dateRetourField.setValue(null);
    }
}
