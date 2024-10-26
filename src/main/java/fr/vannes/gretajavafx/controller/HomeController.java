package fr.vannes.gretajavafx.controller;

import fr.vannes.gretajavafx.Main;
import fr.vannes.gretajavafx.model.Emprunteur;
import fr.vannes.gretajavafx.dao.emprunteur.EmprunteurDAO;
import fr.vannes.gretajavafx.dao.emprunteur.EmprunteurDAOImpl;
import fr.vannes.gretajavafx.dao.DAOFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Classe Controller pour la page d'Accueil
 */
public class HomeController implements Initializable {

    /**
     * Instance d'HomeController
     */
    private static HomeController app = null;

    @FXML
    private ListView<String> maListe; // Typage de la ListView
    @FXML
    private TableView<Emprunteur> monTab; // Typage de la TableView
    @FXML
    private TableColumn<Emprunteur, String> prenom, nom;

    @FXML
    private AnchorPane rootPane;

    private final ObservableList<Emprunteur> obsPersonne = FXCollections.observableArrayList();
    private EmprunteurDAO emprunteurDAO; // DAO pour interagir avec les emprunteurs

    /**
     * Instance unique d'AppController
     *
     * @return l'instance de l'AppController
     */
    public static HomeController getInstance() {
        return app;
    }

    /**
     * Méthode pour l'affichage de la page Accueil
     *
     * @param w Window
     */
    public void HomeScene(Window w) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("home.fxml"));

            Scene scene = new Scene(loader.load(), 640, 400);
            Stage stage = (Stage) w;
            stage.setTitle("Accueil");
            stage.setScene(scene);

            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Chargement de la liste de noms fictifs
     */
    public void loadListe() {
        String[] names = {"Julien", "Theo", "Pascal"};
        maListe.getItems().addAll(names);
    }

    /**
     * Méthode appelée à l'initialisation de la liste
     *
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        app = this; // Initialisation de l'instance
        /*DAOFactory daoFactory = DAOFactory.getInstance();
        this.emprunteurDAO = new EmprunteurDAOImpl(daoFactory); // Initialiser le DAO
        loadListe();
        loadTable();*/
    }

    /**
     * Charger les emprunteurs dans la table
     */
    public void loadTable() {
        try {
            // Récupérer tous les emprunteurs de la base de données
            List<Emprunteur> emprunteurs = emprunteurDAO.getAllEmprunteurs();
            obsPersonne.clear(); // Vider la liste avant de la remplir

            obsPersonne.addAll(emprunteurs); // Ajouter tous les emprunteurs à l'ObservableList

            // Cablage des colonnes
            nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
            prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
            monTab.setItems(obsPersonne);
        } catch (Exception e) {
            errorAlert("Erreur", "Impossible de charger les emprunteurs : " + e.getMessage());
        }
    }

    /**
     * Dialog de succès
     *
     * @param successMessage header message
     * @param contentMessage main content message
     */
    public void successAlert(String successMessage, String contentMessage) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Succès");
        alert.setHeaderText(successMessage);
        alert.setContentText(contentMessage);
        alert.showAndWait();
    }

    /**
     * Dialog d'erreur
     *
     * @param errMessage     header message
     * @param contentMessage main content message
     */
    public void errorAlert(String errMessage, String contentMessage) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(errMessage);
        alert.setContentText(contentMessage);
        alert.showAndWait();
    }

    @FXML
    private void toSendMail() throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("mail.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("Envoi courriel");
        stage.setScene(scene);
        stage.show();
    }

    public void ajouterEmprunteur() {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("ajoutEmprunteur.fxml"));
            Parent ajoutEmprunteurPane = loader.load();


            rootPane.getChildren().clear();
            rootPane.getChildren().add(ajoutEmprunteurPane);

        } catch (IOException e) {
            errorAlert("Erreur", "Impossible de charger l'interface d'ajout d'emprunteur : " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void ajouterMedia() {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("ajoutMedia.fxml"));
            Parent ajoutMediaPane = loader.load();

            rootPane.getChildren().clear();
            rootPane.getChildren().add(ajoutMediaPane); // Ajouter la nouvelle vue

        } catch (IOException e) {
            errorAlert("Erreur", "Impossible de charger l'interface d'ajout de media : " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void ajouterEmprunt() {
        try {
            // Charger le FXML pour la vue "ajoutEmprunteur"
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("ajoutEmprunt.fxml"));
            Parent ajoutEmpruntPane = loader.load();


            rootPane.getChildren().clear();
            rootPane.getChildren().add(ajoutEmpruntPane);

        } catch (IOException e) {
            errorAlert("Erreur", "Impossible de charger l'interface d'ajout d'emprunteur : " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void afficherEmprunteur() {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("affichageEmprunteur.fxml"));
            Parent afficherEmprunteurPane = loader.load();

            rootPane.getChildren().clear();
            rootPane.getChildren().add(afficherEmprunteurPane);

        } catch (IOException e) {
            errorAlert("Erreur", "Impossible de charger l'interface d'affichage d'emprunteur : " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void afficherMedia() {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("affichageMedia.fxml"));
            Parent afficherMediaPane = loader.load();

            rootPane.getChildren().clear();
            rootPane.getChildren().add(afficherMediaPane);

            AnchorPane.setBottomAnchor(afficherMediaPane, 0.0);
            AnchorPane.setLeftAnchor(afficherMediaPane, 0.0);
            AnchorPane.setRightAnchor(afficherMediaPane, 0.0);
            AnchorPane.setTopAnchor(afficherMediaPane, 0.0);

        } catch (IOException e) {
            errorAlert("Erreur", "Impossible de charger l'interface d'affichage d'emprunteur : " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void afficherEmprunt() {
        try {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("affichageEmprunt.fxml"));
        Parent afficherEmpruntPane = loader.load();

        rootPane.getChildren().clear();
        rootPane.getChildren().add(afficherEmpruntPane);
        AnchorPane.setBottomAnchor(afficherEmpruntPane, 0.0);
        AnchorPane.setLeftAnchor(afficherEmpruntPane, 0.0);
        AnchorPane.setRightAnchor(afficherEmpruntPane, 0.0);
        AnchorPane.setTopAnchor(afficherEmpruntPane, 0.0);

        } catch (IOException e) {
            errorAlert("Erreur", "Impossible de charger l'interface d'affichage d'emprunt : " + e.getMessage());
            e.printStackTrace();
        }
    }

}

