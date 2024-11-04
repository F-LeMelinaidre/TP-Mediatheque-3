package fr.vannes.gretajavafx;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    private Stage stage;
    private double xOffset = 0;
    private double yOffset = 0;


    public static void main(String[] args) {

        Font.loadFont(Main.class.getResourceAsStream("/fonts/Ubuntu_Sans/UbuntuSans_Bold.ttf"), 14);
        Font.loadFont(Main.class.getResourceAsStream("/fonts/BioRhyme/BioRhyme-SemiBold.ttf"), 14);
        Font.loadFont(Main.class.getResourceAsStream("/fonts/BioRhyme/BioRhyme-Bold.ttf"), 14);
        Font.loadFont(Main.class.getResourceAsStream("/fonts/Roboto/RobotoSlab-Regular.ttf"), 14);
        Font.loadFont(Main.class.getResourceAsStream("/fonts/Roboto/RobotoSlab-SemiBold.ttf"), 14);
        Font.loadFont(Main.class.getResourceAsStream("/fonts/Ubuntu_Sans/UbuntuSans_Condensed-Medium.ttf"), 14);
        Font.loadFont(Main.class.getResourceAsStream("/fonts/Ubuntu_Sans/UbuntuSans_Condensed-SemiBold.ttf"), 14);
        Font.loadFont(Main.class.getResourceAsStream("/fonts/Kaushan/KaushanScript-Regular.ttf"), 14);

        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        this.stage = stage;


        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("auth.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root, 240, 390);

            this.stage.setScene(scene);
            this.stage.setTitle(null);
            this.stage.setResizable(false);
            this.stage.show();

            this.stage.setOnCloseRequest(event -> {
                Platform.exit();
                System.exit(0);
            });


        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}