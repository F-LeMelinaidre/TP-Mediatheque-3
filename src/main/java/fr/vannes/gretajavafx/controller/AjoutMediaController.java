package fr.vannes.gretajavafx.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import java.time.LocalDate;

public class AjoutMediaController {

    @FXML
    private TextField titreField;
    @FXML
    private ComboBox<String> categorieBox;
    @FXML
    private ComboBox<String> sousCategorieBox;
    @FXML
    private TextArea descriptionArea;
    @FXML
    private Button effacerButton;
    @FXML
    private Button enregistrerButton;

    @FXML
    public void initialize() {

    }

    @FXML
    private void ajouterMedia() {
        String titre = titreField.getText();
        int categorie = categorieBox.getSelectionModel().getSelectedIndex();
        int sousCategorie = sousCategorieBox.getSelectionModel().getSelectedIndex();
        String description = descriptionArea.getText();


    }
}
