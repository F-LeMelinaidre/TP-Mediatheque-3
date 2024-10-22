package fr.vannes.gretajavafx.controller;

import fr.vannes.gretajavafx.Main;
import fr.vannes.gretajavafx.util.CryptPWD;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

/**
 * Classe Controller pour la partie authentification
 */
public class AuthController {
    @FXML
    private TextField login;

    @FXML
    private PasswordField pwd;
    @FXML
    private Label error;
    @FXML
    private Button valider;

    /**
     * mis à blanc des champs
     */
    public void onClearClick() {
        login.clear();
        pwd.clear();
    }

    /**
     * Fermeture de l'app
     */
    public void onCloseClick() {
        Platform.exit();
    }

    /**
     * Gestion de l'authentification
     */
    public void onValidateClick() {
        if (login.getText().equals(Configuration.login)
                && CryptPWD.getSHA512SecurePassword(pwd.getText()).equals(Configuration.password)) {
            try {
                HomeController homeController=new HomeController();
               homeController.HomeScene(valider.getScene().getWindow());
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            Alert alerte = new Alert(Alert.AlertType.ERROR);
            alerte.setTitle("Erreur Login ou Pwd!!");
            alerte.setHeaderText("Erreur Login ou Pwd!");
            alerte.showAndWait();
            error.setText("Erreur de saisie!" +
                    "Login ou password");
            onClearClick();
        }
    }

}