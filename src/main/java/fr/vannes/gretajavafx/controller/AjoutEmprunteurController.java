package fr.vannes.gretajavafx.controller;


import fr.vannes.gretajavafx.dao.DAOFactory;
import fr.vannes.gretajavafx.dao.emprunteur.EmprunteurDAO;
import fr.vannes.gretajavafx.dao.emprunteur.EmprunteurDAOImpl;
import fr.vannes.gretajavafx.model.Emprunteur;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.time.LocalDate;

public class AjoutEmprunteurController {

    @FXML
    private TextField nomField;

    @FXML
    private TextField prenomField;

    @FXML
    private DatePicker dateNaissancePicker;

    @FXML
    private Button ajouterButton;

    private EmprunteurDAO emprunteurDAO;

    public AjoutEmprunteurController() {
        DAOFactory daoFactory = DAOFactory.getInstance();
        this.emprunteurDAO = EmprunteurDAOImpl.get_instance(daoFactory);
    }

    @FXML
    public void initialize() {
    }

    @FXML
    private void ajouterEmprunteur() {
        String nom = nomField.getText();
        String prenom = prenomField.getText();
        LocalDate dateNaissance = dateNaissancePicker.getValue();

        if (nom.isEmpty() || prenom.isEmpty() || dateNaissance == null) {
            afficherAlerte("Champs manquants", "Veuillez remplir tous les champs.");
            return;
        }

        Emprunteur nouvelEmprunteur = new Emprunteur(nom, prenom, dateNaissance, null);

        emprunteurDAO.ajouterEmprunteur(nouvelEmprunteur);
        // TODO MODIFIER L AFFICHAGE EN CAS DE SUCCESS
        afficherAlerte("Succès", "L'emprunteur a été ajouté avec succès.");

        reinitialiserChamps();
    }

    private void reinitialiserChamps() {
        nomField.clear();
        prenomField.clear();
        dateNaissancePicker.setValue(null);
    }

    private void afficherAlerte(String titre, String message) {
        Alert alerte = new Alert(Alert.AlertType.INFORMATION);
        alerte.setTitle(titre);
        alerte.setHeaderText(null);
        alerte.setContentText(message);
        alerte.showAndWait();
    }
}

