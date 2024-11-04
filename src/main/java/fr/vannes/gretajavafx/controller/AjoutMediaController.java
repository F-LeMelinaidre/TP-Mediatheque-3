package fr.vannes.gretajavafx.controller;

import fr.vannes.gretajavafx.dao.DAOFactory;
import fr.vannes.gretajavafx.dao.categorie.CategorieDAO;
import fr.vannes.gretajavafx.dao.categorie.CategorieDAOImpl;
import fr.vannes.gretajavafx.dao.media.MediaDAOImpl;
import fr.vannes.gretajavafx.model.Categorie;
import fr.vannes.gretajavafx.model.Media;
import fr.vannes.gretajavafx.model.SousCategorie;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import java.time.LocalDate;
import java.util.List;

public class AjoutMediaController {
    @FXML
    private GridPane mediaGrid;
    @FXML
    private TextField titreField;
    @FXML
    private ComboBox<Categorie> categorieBox;
    @FXML
    private ComboBox<SousCategorie> sousCategorieBox;
    @FXML
    private TextArea descriptionArea;
    @FXML
    private Button effacerButton;
    @FXML
    private Button enregistrerButton;

    private DAOFactory daoFactory;
    private CategorieDAOImpl categorieDAO;
    private final MediaDAOImpl mediaDAO;
    private final Categorie categorieDefaut = new Categorie(-1, "Sélectionnez une catégorie");
    private final SousCategorie sousCategorieDefaut = new SousCategorie(-1, "Sélectionnez un genre");

    public AjoutMediaController()
    {
        this.daoFactory = DAOFactory.getInstance();
        this.categorieDAO = CategorieDAOImpl.get_instance(daoFactory);
        this.mediaDAO = MediaDAOImpl.get_instance(daoFactory);
    }

    @FXML
    public void initialize()
    {
        List<Categorie> categories = categorieDAO.findAll(true);
        System.out.println(categories);
        this.categorieBox.getItems().add(categorieDefaut);

        if (categories != null) {
            this.categorieBox.getItems().addAll(categories);
        }

        this.categorieBox.setValue(categorieDefaut);

        this.categorieBox.setOnAction(event -> categorieSelectionne());
    }

    @FXML
    private void categorieSelectionne()
    {
        System.out.println(categorieBox.getValue());

        Categorie selectedCategorie = this.categorieBox.getValue();
        if (selectedCategorie != null) {
            this.sousCategorieBox.getItems().clear();

System.out.println(selectedCategorie.getSousCategories());

            this.sousCategorieBox.getItems().add(this.sousCategorieDefaut);
            this.sousCategorieBox.getItems().addAll(selectedCategorie.getSousCategories());

            this.sousCategorieBox.setValue(this.sousCategorieDefaut);
        }
    }

    @FXML
    private void ajouterMedia()
    {
        boolean valid = true;

        String titre = titreField.getText();
        Categorie categorie = categorieBox.getValue();
        SousCategorie sousCategorie = sousCategorieBox.getValue();
        String description = descriptionArea.getText();

        /*valid &= validerChamp(titre.isEmpty(), titreField, "Champ obligatoire!");
        valid &= validerChamp(categorie.getCategorieId() > 0, categorieBox, "Champ obligatoire!");
        valid &= validerChamp(sousCategorie.getSousCategorieId() > 0, sousCategorieBox, "Champ obligatoire!");
        valid &= validerChamp(description.isEmpty(), descriptionArea, "Champ obligatoire!");*/

        if(!valid) {
            // TODO AJOUTER MESSAGE ERREUR
        } else {
            Media media = new Media(titre, description, categorie, sousCategorie);
            media.generateId();
            this.mediaDAO.create(media);
            // TODO CHANGER L AFFICHAGE EN CAS DE SUCCES DE L'ENREGISTREMENT
            //  SUPPRIMER LE FORMULAIRE, AFFICHER UN RECAP OU LA VUE DU MEDIA
        }
    }

    @FXML
    private void viderFormulaire()
    {
        titreField.clear();
        this.categorieBox.setValue(categorieDefaut);
        sousCategorieBox.setValue(null); // Désélectionne la sous-catégorie sélectionnée
        descriptionArea.clear();
    }

    private boolean validerChamp(boolean condition, Control element, String message)
    {
        return !condition;
    }
}
