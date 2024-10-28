package fr.vannes.gretajavafx.controller;


import fr.vannes.gretajavafx.dao.DAOFactory;
import fr.vannes.gretajavafx.dao.emprunteur.EmprunteurDAO;
import fr.vannes.gretajavafx.dao.emprunteur.EmprunteurDAOImpl;
import fr.vannes.gretajavafx.model.Emprunteur;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;

import java.util.List;

public class AffichageEmprunteurController {

    @FXML
    private ListView<String> emprunteurListView; // Liste pour afficher les emprunteurs

    @FXML
    private Button rafraichirButton; // Bouton pour rafraîchir la liste

    private EmprunteurDAO emprunteurDAO;

    public AffichageEmprunteurController() {
        DAOFactory daoFactory = DAOFactory.getInstance();
        this.emprunteurDAO = EmprunteurDAOImpl.get_instance(daoFactory);
    }

    @FXML
    public void initialize() {
        // Initialisation et chargement initial de la liste des emprunteurs
        afficherTousLesEmprunteurs();
    }

    @FXML
    private void afficherTousLesEmprunteurs() {
        try {
            // Récupérer la liste des emprunteurs à partir de la base de données
            List<Emprunteur> emprunteurs = emprunteurDAO.getAllEmprunteurs();

            // Transformer la liste en ObservableList pour JavaFX
            ObservableList<String> emprunteurNoms = FXCollections.observableArrayList();
            for (Emprunteur emprunteur : emprunteurs) {
                emprunteurNoms.add(emprunteur.getNom() + " " + emprunteur.getPrenom());
            }
            // Mettre à jour la ListView avec les noms des emprunteurs
            emprunteurListView.setItems(emprunteurNoms);

        } catch (Exception e) {
            afficherAlerte("Erreur", "Impossible de récupérer les emprunteurs.");
        }
    }

    @FXML
    private void rafraichirListe() {
        // Méthode pour rafraîchir la liste des emprunteurs
        afficherTousLesEmprunteurs();
    }

    private void afficherAlerte(String titre, String message) {
        // Méthode pour afficher une alerte d'erreur ou d'information
        Alert alerte = new Alert(Alert.AlertType.INFORMATION);
        alerte.setTitle(titre);
        alerte.setHeaderText(null);
        alerte.setContentText(message);
        alerte.showAndWait();
    }
}
