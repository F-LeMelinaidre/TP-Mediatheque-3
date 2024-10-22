package fr.vannes.gretajavafx.controller;

import fr.vannes.gretajavafx.util.SendMail;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.regex.Pattern;

public class MailController {
    @FXML
    private TextField tfRecipient;
    @FXML
    private Button btnFermer;

    @FXML
    private void sendMail() throws InterruptedException {

        // on récupère le mail entré par l'input
        String recipient = tfRecipient.getText();
        // on check le format du mail entré
        if (Pattern.compile("^(?=.{1,64}@)[\\w]+(\\.[\\w]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$").matcher(recipient).matches()) {
            // on effectue l'envoi de mail dans un thread
            SendMail sm = new SendMail(recipient);
            Thread thread = new Thread(sm);
            thread.start();
            // on attend la fin du thread, un timeout est placé par défaut
            thread.join(5000);
            // on ferme la fenêtre lorsque le thread est terminé
            Stage stage = (Stage) btnFermer.getScene().getWindow();
            stage.close();
        } else {
            // dialog d'erreur de format de mail
            HomeController.getInstance().errorAlert("L'envoi a échoué","Le format du mail est invalide, veuillez réessayer.");
        }
    }

    @FXML
    private void fermerMail() {
        Stage stage = (Stage) btnFermer.getScene().getWindow();
        stage.close();
    }
}
