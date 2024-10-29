package fr.vannes.gretajavafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import java.io.IOException;

public class Main extends Application {

    private static Main _instance;
    @Override
    public void start(Stage stage) throws IOException {
        Font.loadFont(getClass().getResourceAsStream("/fonts/BioRhyme/BioRhyme-SemiBold.ttf"), 14);
        Font.loadFont(getClass().getResourceAsStream("/fonts/BioRhyme/BioRhyme-Bold.ttf"), 14);
        Font.loadFont(getClass().getResourceAsStream("/fonts/Roboto/RobotoSlab-Regular.ttf"), 14);
        Font.loadFont(getClass().getResourceAsStream("/fonts/Roboto/RobotoSlab-SemiBold.ttf"), 14);
        Font.loadFont(getClass().getResourceAsStream("/fonts/Ubuntu_Sans/UbuntuSans_Condensed-Medium.ttf"), 14);
        Font.loadFont(getClass().getResourceAsStream("/fonts/Ubuntu_Sans/UbuntuSans_Condensed-SemiBold.ttf"), 14);
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("auth.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 360, 380);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {
        launch();
    }
}