package fr.vannes.gretajavafx.controller;

import fr.vannes.gretajavafx.dao.DAOFactory;
import fr.vannes.gretajavafx.dao.media.MediaDAOImpl;
import fr.vannes.gretajavafx.model.Media;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;

public class AffichageMediaController
{


    @FXML
    private TableView<Media> mediaTableView;

    private MediaDAOImpl mediaDAO;
    private ObservableList<Media> medias = FXCollections.observableArrayList();


    public AffichageMediaController()
    {
        DAOFactory daoFactory = DAOFactory.getInstance();
        this.mediaDAO = MediaDAOImpl.get_instance(daoFactory);
    }

    @FXML
    public void initialize() {
        afficherTouslesMedias();
    }

    @FXML
    public void afficherTouslesMedias()
    {
        try {

            ArrayList<Media> mediaList = this.mediaDAO.findAll();
            medias.addAll(mediaList);

            TableColumn<Media, String> mediaIdColonne = new TableColumn<>("Identifiant");
            mediaIdColonne.setCellValueFactory(new PropertyValueFactory<>("mediaId"));

            TableColumn<Media, String> titreColonne = new TableColumn<>("Titre");
            titreColonne.setCellValueFactory(new PropertyValueFactory<>("titre"));

            TableColumn<Media, String> categorieColonne = new TableColumn<>("Catégorie");
            categorieColonne.setCellValueFactory(new PropertyValueFactory<>("categorieLabel"));

            TableColumn<Media, String> sousCategorieColonne = new TableColumn<>("Sous-catégorie");
            sousCategorieColonne.setCellValueFactory(new PropertyValueFactory<>("sousCategorieLabel"));

            mediaTableView.getColumns().addAll(mediaIdColonne, titreColonne, categorieColonne, sousCategorieColonne);

            mediaTableView.setItems(medias);

        } catch (Exception e) {
            System.out.println("Erreur Impossible de récupérer les medias.");
        }
    }
}
