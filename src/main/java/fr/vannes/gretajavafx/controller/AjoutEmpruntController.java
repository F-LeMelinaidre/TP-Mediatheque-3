package fr.vannes.gretajavafx.controller;

import fr.vannes.gretajavafx.Main;
import fr.vannes.gretajavafx.dao.DAOFactory;
import fr.vannes.gretajavafx.dao.emprunt.EmpruntDAO;
import fr.vannes.gretajavafx.dao.emprunt.EmpruntDAOImpl;
import fr.vannes.gretajavafx.dao.emprunteur.EmprunteurDAO;
import fr.vannes.gretajavafx.dao.emprunteur.EmprunteurDAOImpl;
import fr.vannes.gretajavafx.dao.media.MediaDAO;
import fr.vannes.gretajavafx.dao.media.MediaDAOImpl;
import fr.vannes.gretajavafx.model.Emprunt;
import fr.vannes.gretajavafx.model.Emprunteur;
import fr.vannes.gretajavafx.model.Media;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import java.io.IOException;
import java.time.LocalDate;

public class AjoutEmpruntController {

    @FXML
    private VBox ajoutEmprunt;
    @FXML
    private FlowPane searchBar;
    @FXML
    private TextField emprunteurIdField;
    @FXML
    private TextField mediaIdField;
    @FXML
    private DatePicker dateEmpruntField;
    @FXML
    private Button ajouterButton;
    @FXML
    private Button resetButton;
    @FXML
    private FlowPane empruntDetail;

    @FXML
    private VBox emprunteurDetail;
    private EmpruntDAO empruntDAO;
    private EmprunteurDAO emprunteurDAO;
    private MediaDAO mediaDAO;

    public AjoutEmpruntController() {
        DAOFactory daoFactory = DAOFactory.getInstance();
        this.empruntDAO = EmpruntDAOImpl.get_instance(daoFactory);
        this.emprunteurDAO = EmprunteurDAOImpl.get_instance(daoFactory);
        this.mediaDAO = MediaDAOImpl.get_instance(daoFactory);
    }

    @FXML
    public void initialize() {
        emprunteurIdField.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                try {
                    afficherEmprunteur();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
        mediaIdField.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                try {
                    afficherMedia();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });

        ajoutEmprunt.widthProperty().addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {

            

        });
    }

    @FXML
    private void ajouterEmprunt() {
        String emprunteurIdText = emprunteurIdField.getText();
        String mediaId = mediaIdField.getText();
        LocalDate dateEmprunt = dateEmpruntField.getValue();

        if (emprunteurIdText.isEmpty() || mediaId.isEmpty() || dateEmprunt == null) {
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

        Emprunt nouvelEmprunt = new Emprunt(emprunteurId, mediaId, dateEmprunt);

        try {
            empruntDAO.ajouterEmprunt(nouvelEmprunt);
            afficherAlerte("Succès", "Emprunt ajouté avec succès !");
            resetFields();
        } catch (Exception e) {
            afficherAlerte("Erreur", "Une erreur s'est produite lors de l'ajout de l'emprunt : " + e.getMessage());
        }
    }

    private void afficherEmprunteur() throws IOException
    {
        int emprunteurId = Integer.parseInt(emprunteurIdField.getText());

        if (emprunteurId != 0) {
            Emprunteur emprunteur = emprunteurDAO.getEmprunteurById(emprunteurId);
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("emprunteurDetail.fxml"));

            emprunteurDetail = loader.load();

            empruntDetail.getChildren().clear();
            empruntDetail.getChildren().add(emprunteurDetail);

            Label nomLabel = (Label) emprunteurDetail.lookup("#nomLabel");
            nomLabel.setText(emprunteur.getNom());
            Label prenomLabel = (Label) emprunteurDetail.lookup("#prenomLabel");
            prenomLabel.setText(emprunteur.getPrenom());

        }
    }
    private void afficherMedia() throws IOException
    {
        String mediaId = mediaIdField.getText();

        if (!mediaId.isEmpty()) {
            Media media = mediaDAO.getMediaById(mediaId);
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("mediaDetail.fxml"));
            Parent root = loader.load();

        }
    }

    private void afficherAlerte(String titre, String message) {
        Alert alerte = new Alert(Alert.AlertType.INFORMATION);
        alerte.setTitle(titre);
        alerte.setHeaderText(null);
        alerte.setContentText(message);
        alerte.showAndWait();
    }
    @FXML
    private void resetFields() {
        emprunteurIdField.clear();
        mediaIdField.clear();
        dateEmpruntField.setValue(null);
        empruntDetail.getChildren().clear();
    }
}
