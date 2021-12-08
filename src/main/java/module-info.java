module com.example.utilitaire {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;

    opens com.example.utilitaire to javafx.fxml;
    exports com.example.utilitaire;
}