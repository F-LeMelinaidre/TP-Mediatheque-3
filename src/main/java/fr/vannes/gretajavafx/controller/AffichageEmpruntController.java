package fr.vannes.gretajavafx.controller;


import fr.vannes.gretajavafx.dao.DAOFactory;
import fr.vannes.gretajavafx.dao.emprunt.EmpruntDAO;
import fr.vannes.gretajavafx.dao.emprunt.EmpruntDAOImpl;
import fr.vannes.gretajavafx.model.Emprunt;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.Date;
import java.util.List;

public class AffichageEmpruntController {

    @FXML
    private TableView<Emprunt> empruntTableView;
    @FXML
    private TableColumn<Emprunt, Integer> idEmpruntColumn;
    @FXML
    private TableColumn<Emprunt, String> titreMediaColumn;
    @FXML
    private TableColumn<Emprunt, String> categorieMediaColumn;
    @FXML
    private TableColumn<Emprunt, Date> dateEmpruntColumn;
    @FXML
    private TableColumn<Emprunt, Date> dateRetourEmpruntColumn;


    private EmpruntDAO empruntDAO;

    public AffichageEmpruntController() {
        DAOFactory daoFactory = DAOFactory.getInstance();
        this.empruntDAO = EmpruntDAOImpl.get_instance(daoFactory);
    }

    @FXML
    public void initialize() {
        afficherEmprunts();
    }

    private void afficherEmprunts() {
        try {
            List<Emprunt> emprunts = empruntDAO.getTousLesEmprunts();
            this.idEmpruntColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        } catch (Exception e) {
            afficherAlerte("Erreur",
                           "Une erreur s'est produite lors de la récupération des emprunts : " + e.getMessage());
        }
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

