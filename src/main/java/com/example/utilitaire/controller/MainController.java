package com.example.utilitaire.controller;

import com.example.utilitaire.objet.Book;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @FXML
    private Menu menuIMCcalc;
    @FXML
    private Menu menuLib;
    @FXML
    private MenuItem Yes;
    @FXML
    private AnchorPane main;
    @FXML
    private HBox formIMCcalc;
    @FXML
    private VBox formLib;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        main.getChildren().removeAll(formIMCcalc,formLib);

        menuIMCcalc.setOnMenuValidation(launchbinary ->{
            main.getChildren().removeAll(formIMCcalc,formLib);
            main.getChildren().add(formIMCcalc);
        });

        menuLib.setOnMenuValidation(launchLibrary -> {
            main.getChildren().removeAll(formLib,formIMCcalc);
            main.getChildren().add(formLib);
        });

        Yes.setOnAction(exit ->{
            Platform.exit();
        });
    }
}