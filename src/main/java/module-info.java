module fr.vannes.gretajavafx {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires sib.api.v3.sdk;

    opens fr.vannes.gretajavafx to javafx.fxml;
    exports fr.vannes.gretajavafx;
    exports fr.vannes.gretajavafx.controller;
    exports fr.vannes.gretajavafx.model;
    opens fr.vannes.gretajavafx.controller to javafx.fxml;
}