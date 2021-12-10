package com.example.utilitaire.controller;

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
    private Menu menuBiblio;
    @FXML
    private Menu menuRomain;
    @FXML
    private Menu menuArmada;
    @FXML
    private Menu menuBHconverter;
    @FXML
    private MenuItem Yes;
    @FXML
    private AnchorPane main;
    @FXML
    private HBox formArmada;
    @FXML
    private HBox formRomain;
    @FXML
    private VBox formLibrary;
    @FXML
    private HBox formIMCcalc;
    @FXML
    private HBox formBHconverter;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        main.getChildren().removeAll(formArmada,formIMCcalc,formRomain,formLibrary,formBHconverter);
        menuArmada.setOnMenuValidation(launcharmada ->{
            main.getChildren().removeAll(formArmada,formIMCcalc,formRomain,formLibrary,formBHconverter);
            main.getChildren().add(formArmada);
        });
        menuIMCcalc.setOnMenuValidation(launchIMC ->{
            main.getChildren().removeAll(formArmada,formIMCcalc,formRomain,formLibrary,formBHconverter);
            main.getChildren().add(formIMCcalc);
        });
        menuRomain.setOnMenuValidation(launchromain ->{
            main.getChildren().removeAll(formArmada,formIMCcalc,formRomain,formLibrary,formBHconverter);
            main.getChildren().add(formRomain);
        });
        menuBiblio.setOnMenuValidation(launchbiblio ->{
            main.getChildren().removeAll(formArmada,formIMCcalc,formRomain,formLibrary,formBHconverter);
            main.getChildren().add(formLibrary);
        });
        menuBHconverter.setOnMenuValidation(launchBHconverter ->{
            main.getChildren().removeAll(formArmada,formIMCcalc,formRomain,formLibrary,formBHconverter);
            main.getChildren().add(formBHconverter);
        });
        Yes.setOnAction(exit ->{
            Platform.exit();
        });
    }
}