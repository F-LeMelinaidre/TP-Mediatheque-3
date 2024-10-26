package fr.vannes.gretajavafx.controller;


import fr.vannes.gretajavafx.dao.DAOFactory;
import fr.vannes.gretajavafx.dao.emprunt.EmpruntDAO;
import fr.vannes.gretajavafx.dao.emprunt.EmpruntDAOImpl;
import fr.vannes.gretajavafx.model.Emprunt;
import fr.vannes.gretajavafx.model.Media;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;
import java.util.List;

public class AffichageEmpruntController
{

    @FXML
    private TableView<Emprunt> empruntTableView;
    @FXML
    private TableColumn<Emprunt, LocalDate> dateEmpruntColumn;
    @FXML
    private TableColumn<Emprunt, LocalDate> dateRetourEmpruntColumn;
    @FXML
    private TableColumn<Emprunt, Number> identifiantEmprunteurColumn;
    @FXML
    private TableColumn<Emprunt, String> identiteEmprunteurColumn;
    @FXML
    private TableColumn<Emprunt, Media> idMediaColumn;
    @FXML
    private TableColumn<Emprunt, String> titreMediaColumn;
    @FXML
    private TableColumn<Emprunt, String> categorieMediaColumn;


    private EmpruntDAO empruntDAO;
    private ObservableList<Emprunt> emprunts = FXCollections.observableArrayList();

    public AffichageEmpruntController()
    {
        DAOFactory daoFactory = DAOFactory.getInstance();
        this.empruntDAO = EmpruntDAOImpl.get_instance(daoFactory);
    }

    @FXML
    public void initialize()
    {
        afficherEmprunts();
    }

    private void afficherEmprunts()
    {
        try {
            List<Emprunt> empruntsList = empruntDAO.getTousLesEmprunts();
            emprunts.addAll(empruntsList);

            this.dateEmpruntColumn.setCellValueFactory(new PropertyValueFactory<>("dateEmprunt"));
            this.dateRetourEmpruntColumn.setCellValueFactory(new PropertyValueFactory<>("dateRetour"));
            this.identifiantEmprunteurColumn.setCellValueFactory(
                    cellData -> new SimpleIntegerProperty(cellData.getValue().getEmprunteur().getEmprunteurId()));
            this.identiteEmprunteurColumn.setCellValueFactory(
                    cellData -> new SimpleStringProperty(cellData.getValue().getEmprunteur().getNom() + " " + cellData.getValue().getEmprunteur().getPrenom()));
            this.idMediaColumn.setCellValueFactory(new PropertyValueFactory<>("mediaId"));
            this.titreMediaColumn.setCellValueFactory(
                    cellData -> new SimpleStringProperty(cellData.getValue().getMedia().getTitre()));
            this.categorieMediaColumn.setCellValueFactory(
                    cellData -> new SimpleStringProperty(cellData.getValue().getMedia().getCategorie().getLabel()));

            empruntTableView.setItems(emprunts);
        } catch (Exception e) {
            afficherAlerte("Erreur",
                           "Une erreur s'est produite lors de la récupération des emprunts : " + e.getMessage());
        }
    }

    private void afficherAlerte(String titre, String message)
    {
        Alert alerte = new Alert(Alert.AlertType.INFORMATION);
        alerte.setTitle(titre);
        alerte.setHeaderText(null);
        alerte.setContentText(message);
        alerte.showAndWait();
    }
}

