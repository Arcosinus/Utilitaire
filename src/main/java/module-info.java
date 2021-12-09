module com.example.utilitaire {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires javafx.base;

    opens com.example.utilitaire to javafx.fxml, javafx.base;
    exports com.example.utilitaire;
    exports com.example.utilitaire.controller;
    opens com.example.utilitaire.objet to javafx.base;
    opens com.example.utilitaire.controller to javafx.base, javafx.fxml;
}