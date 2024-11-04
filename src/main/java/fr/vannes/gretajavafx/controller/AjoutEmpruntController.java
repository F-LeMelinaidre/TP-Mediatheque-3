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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

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
    private VBox emprunteurDetail;
    @FXML
    private Label emprunteurMessageLabel;
    @FXML
    private VBox mediaDetail;
    @FXML
    private Label mediaMessageLabel;

    private EmpruntDAO empruntDAO;
    private EmprunteurDAO emprunteurDAO;
    private MediaDAO mediaDAO;

    public AjoutEmpruntController() {
        DAOFactory daoFactory = DAOFactory.getInstance();
        this.empruntDAO = EmpruntDAOImpl.get_instance(daoFactory);
        this.emprunteurDAO = EmprunteurDAOImpl.get_instance(daoFactory);
        this.mediaDAO = MediaDAOImpl.get_instance(daoFactory);
    }

    // TODO Penser à vider les éléments des FXML details
    @FXML
    public void initialize() {

        emprunteurDetail.setVisible(false);
        emprunteurMessageLabel.setVisible(false);
        mediaDetail.setVisible(false);
        mediaMessageLabel.setVisible(false);

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

        dateEmpruntField.setDayCellFactory(new Callback<DatePicker, DateCell>() {
            @Override
            public DateCell call(DatePicker datePicker) {
                return new DateCell() {
                    @Override
                    public void updateItem(LocalDate item, boolean empty) {
                        super.updateItem(item, empty);
                        // Désactiver les cellules de date inférieures à aujourd'hui
                        if (item != null && item.isBefore(LocalDate.now())) {
                            setDisable(true);
                            setStyle("-fx-background-color: #ffc0cb;"); // Optionnel: Change la couleur de fond
                        }
                    }
                };
            }
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

    //TODO Ajouter: Si l'emprunteur n existe pas, ne pas afficher emprunteurDetail (laisser/mettre setVisible(false)) et afficher un message
    // Ajouter le control du type en entrée
    private void afficherEmprunteur() throws IOException
    {
        int emprunteurId = (emprunteurIdField.getText().isEmpty())? 0 : Integer.parseInt(emprunteurIdField.getText());

        if (emprunteurId != 0) {

            Emprunteur emprunteur = emprunteurDAO.getEmprunteurById(emprunteurId, true);

            if (emprunteur != null) {
                Label nomLabel = (Label) emprunteurDetail.lookup("#nomLabel");
                nomLabel.setText(emprunteur.getNom());
                Label prenomLabel = (Label) emprunteurDetail.lookup("#prenomLabel");
                prenomLabel.setText(emprunteur.getPrenom());

                List<Emprunt> empruntList = emprunteur.getEmpruntsNonRendus();
                ObservableList<Emprunt> emprunts = FXCollections.observableArrayList(empruntList);
                ListView empruntsListView = (ListView) emprunteurDetail.lookup("#empruntList");
                empruntsListView.setItems(emprunts);

                emprunteurMessageLabel.setVisible(false);
                emprunteurDetail.setVisible(true);
            } else {
                emprunteurDetail.setVisible(false);
                emprunteurMessageLabel.setText("Aucun emprunteur trouvé avec cet identifiant!");
                emprunteurMessageLabel.setVisible(true);
            }


        }
    }

    //TODO Ajouter: Si le média n existe pas, ne pas afficher mediaDetail (laisser/mettre setVisible(false)) et afficher un message
    // Ajouter le control du type en entrée
    private void afficherMedia() throws IOException
    {
        String mediaId = mediaIdField.getText();

        if (!mediaId.isEmpty()) {

            Media media = mediaDAO.getMediaWithDisponibilite(mediaId);

            if (media != null) {
                Label disponibiliteLabel = (Label) mediaDetail.lookup("#disponibiliteLabel");
                disponibiliteLabel.setText(media.estDisponibleText());

                Label titreLabel = (Label) mediaDetail.lookup("#titreLabel");
                titreLabel.setText(media.getTitre());

                Label categorieLabel = (Label) mediaDetail.lookup("#categorieLabel");
                categorieLabel.setText(media.getCategorieLabel());
                Label sousCategorieLabel = (Label) mediaDetail.lookup("#sousCategorieLabel");
                sousCategorieLabel.setText(media.getSousCategorieLabel());

                mediaMessageLabel.setVisible(false);
                mediaDetail.setVisible(true);
            } else {
                mediaDetail.setVisible(false);
                mediaMessageLabel.setText("Aucun média trouvé avec cet identifiant!");
                mediaMessageLabel.setVisible(true);
            }
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
    }
}
