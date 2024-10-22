package fr.vannes.gretajavafx.controller;

import fr.vannes.gretajavafx.Main;
import fr.vannes.gretajavafx.model.Emprunteur;
import fr.vannes.gretajavafx.dao.emprunteur.EmprunteurDAO;
import fr.vannes.gretajavafx.dao.emprunteur.EmprunteurDAOImpl;
import fr.vannes.gretajavafx.dao.DAOFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
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
        DAOFactory daoFactory = DAOFactory.getInstance();
        this.emprunteurDAO = new EmprunteurDAOImpl(daoFactory); // Initialiser le DAO
        loadListe();
        loadTable();
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
}

