package fr.vannes.gretajavafx.controller;

import fr.vannes.gretajavafx.Main;
import fr.vannes.gretajavafx.model.Emprunteur;
import fr.vannes.gretajavafx.dao.emprunteur.EmprunteurDAO;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
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
    private StackPane rootPane;
    @FXML
    private MenuBar menuBar;
    @FXML
    private Button minimize;

    private final ObservableList<Emprunteur> obsPersonne = FXCollections.observableArrayList();
    private EmprunteurDAO emprunteurDAO; // DAO pour interagir avec les emprunteurs
    private Stage stage;
    private double xOffset = 0;
    private double yOffset = 0;

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

            this.stage = (Stage) w;

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("home.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root, 800, 600);

            stage.setTitle("Accueil");
            stage.setScene(scene);
            stage.setResizable(true);
            stage.show();


        } catch (Exception e) {
            e.printStackTrace();
        }
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

    /**
     * Méthode appelée à l'initialisation de la liste
     *
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        app = this;
    }

    /**
     * Chargement de la liste de noms fictifs
     */
    public void loadListe() {
        String[] names = {"Julien", "Theo", "Pascal"};
        maListe.getItems().addAll(names);
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
    private void FXMLLoader(String location) throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource(location));
        Parent root = loader.load();

        rootPane.getChildren().clear();
        rootPane.getChildren().add(root);
    }

    public void ajouterEmprunteur() {
        try {
            this.FXMLLoader("ajoutEmprunteur.fxml");

        } catch (IOException e) {
            errorAlert("Erreur", "Impossible de charger l'interface d'ajout d'emprunteur : " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void ajouterMedia() {
        try {
            this.FXMLLoader("ajoutMedia.fxml");

        } catch (IOException e) {
            errorAlert("Erreur", "Impossible de charger l'interface d'ajout de media : " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void ajouterEmprunt() {
        try {
            this.FXMLLoader("ajoutEmprunt.fxml");

        } catch (IOException e) {
            errorAlert("Erreur", "Impossible de charger l'interface d'ajout d'emprunteur : " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void afficherEmprunteur() {
        try {
            this.FXMLLoader("affichageEmprunteur.fxml");

        } catch (IOException e) {
            errorAlert("Erreur", "Impossible de charger l'interface d'affichage d'emprunteur : " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void afficherMedia() {
        try {
            this.FXMLLoader("affichageMedia.fxml");

        } catch (IOException e) {
            errorAlert("Erreur", "Impossible de charger l'interface d'affichage d'emprunteur : " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void afficherEmprunt() {
        try {
            this.FXMLLoader("affichageEmprunt.fxml");


        } catch (IOException e) {
            errorAlert("Erreur", "Impossible de charger l'interface d'affichage d'emprunt : " + e.getMessage());
            e.printStackTrace();
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

    public void onCloseClick()
    {
        Platform.exit();
    }

}

