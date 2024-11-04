package fr.vannes.gretajavafx.controller;

import fr.vannes.gretajavafx.dao.DAOFactory;
import fr.vannes.gretajavafx.dao.categorie.CategorieDAO;
import fr.vannes.gretajavafx.dao.categorie.CategorieDAOImpl;
import fr.vannes.gretajavafx.model.Categorie;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import java.time.LocalDate;
import java.util.List;

public class AjoutMediaController {

    @FXML
    private TextField titreField;
    @FXML
    private ComboBox<Categorie> categorieBox;
    @FXML
    private ComboBox<String> sousCategorieBox;
    @FXML
    private TextArea descriptionArea;
    @FXML
    private Button effacerButton;
    @FXML
    private Button enregistrerButton;

    private DAOFactory daoFactory;
    private CategorieDAO categorieDAO;

    public AjoutMediaController() {
        this.daoFactory = DAOFactory.getInstance();
        this.categorieDAO = CategorieDAOImpl.get_instance(daoFactory);
    }

    @FXML
    public void initialize() {

        List<Categorie> categories = this.categorieDAO.findAll();
        categorieBox.getItems().addAll(categories);

        categorieBox.setValue(categories.get(0));

        categorieBox.setOnAction(event -> {
            Categorie selectedCategorie = categorieBox.getValue(); // Correction ici
            if (selectedCategorie != null) {
                // Mise à jour du ComboBox des sous-catégories
                sousCategorieBox.getItems().setAll(selectedCategorie.getSousCategories().values());
                sousCategorieBox.getSelectionModel().clearSelection();
            }
        });
    }

    @FXML
    private void ajouterMedia() {

        String titre = titreField.getText();
        int categorie = categorieBox.getSelectionModel().getSelectedIndex();
        int sousCategorie = sousCategorieBox.getSelectionModel().getSelectedIndex();
        String description = descriptionArea.getText();


    }
}
