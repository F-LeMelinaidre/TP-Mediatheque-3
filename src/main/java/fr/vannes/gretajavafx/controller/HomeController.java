package fr.vannes.gretajavafx.controller;

import fr.vannes.gretajavafx.Main;
import fr.vannes.gretajavafx.model.Personne;
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
import java.util.ResourceBundle;


/**
 * classe Controller pour la page d'Accueil
 */
public class HomeController implements Initializable {

    /**
     * Instance d'HomeController
     */
    private static HomeController app = null;
    @FXML
    private ListView maListe;
    @FXML
    private TableView monTab;
    @FXML
    private TableColumn<Personne, String> prenom,nom;

    private final ObservableList<Personne> obsPersonne= FXCollections.observableArrayList();

    /**
     * Instance unique d'AppController
     *
     * @return l'instance de l'AppController
     */
    public static HomeController getInstance() {
        return app;
    }

    /**
     * Method epour l'affichage de la page Accueil
     *
     * @param w Window
     */
    public void HomeScene(Window w) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.
                    getResource("home.fxml"));

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
     * chargement de la liste
     */
    public void loadListe() {
       String [] names = {"Julien","Theo","Pascal"};

        maListe.getItems().addAll(names);


    }

    /**
     * méthode appelée à l'initialisation de la liste
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadListe();
        loadTable();
    }

    public void loadTable(){
       obsPersonne.add(new Personne("Dupont", "Jacques"));
      obsPersonne.add(new Personne("Martin", "Jean"));
    //cablage des colonnes
     nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
     prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
      monTab.setItems(obsPersonne);
    }

    /**
     * dialog de succès
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
     * dialog d'erreur
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
