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

    @FXML
    private TableColumn<Media, String> identifiantColumn;
    @FXML
    private TableColumn<Media, String> titreColumn;
    @FXML
    private TableColumn<Media, String> categorieColumn;
    @FXML
    private TableColumn<Media, String> sousCategorieColumn;


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

            this.identifiantColumn.setCellValueFactory(new PropertyValueFactory<>("mediaId"));

            this.titreColumn.setCellValueFactory(new PropertyValueFactory<>("titre"));

            this.categorieColumn.setCellValueFactory(new PropertyValueFactory<>("categorieLabel"));

            this.sousCategorieColumn.setCellValueFactory(new PropertyValueFactory<>("sousCategorieLabel"));

            mediaTableView.setItems(medias);

        } catch (Exception e) {
            System.out.println("Erreur Impossible de récupérer les medias.");
        }
    }
}
