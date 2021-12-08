package com.example.utilitaire.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    private Menu menuIMCcalc;
    @FXML
    private HBox formIMCcalc;

    @FXML
    private Menu menuBHConverter;
    @FXML
    private HBox formBHConverter;

    @FXML
    private Menu menuLib;
    @FXML
    private HBox formLib;

    @FXML
    private Menu menuArmee;
    @FXML
    private HBox formArmee;

    @FXML
    private Menu menuRomain;
    @FXML
    private HBox formRomain;


    @FXML
    private MenuItem Close;
    @FXML
    private VBox main;
    @FXML
    private AnchorPane mainAnchor;

//ConversionRomain RomainControlleur formRomain,

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        mainAnchor.getChildren().removeAll(formBHConverter/**, formIMCcalc, formLib, formArmee, formRomain**/);

        menuIMCcalc.setOnMenuValidation(launchbinary ->{
            main.getChildren().removeAll(formIMCcalc,formLib,formBHConverter/**,formRomain,formArmee**/);
            main.getChildren().add(formIMCcalc);
        });

        menuLib.setOnMenuValidation(launchLibrary -> {
            main.getChildren().removeAll(formIMCcalc,formLib,formBHConverter/**,formRomain,formArmee**/);
            main.getChildren().add(formLib);
        });

        menuBHConverter.setOnMenuValidation(launchBHconverter ->{
            main.getChildren().removeAll(formIMCcalc,formLib,formBHConverter/**,formRomain,formArmee**/);
            main.getChildren().add(formBHConverter);
        });

        //menuArmee.setOnMenuValidation(launchArmee ->{
        //    main.getChildren().removeAll(formIMCcalc,formLib,formBHConverter/**,formRomain,formArmee**/)
        //    main.getChildren().add(formArmee);
        //});

        //menuRomain.setOnMenuValidation(launchRomain ->{
        //    main.getChildren().removeAll(formIMCcalc,formLib,formBHConverter/**,formRomain,formArmee**/)
        //    main.getChildren().add(formRomain);
        //});

        Close.setOnAction(event -> {
            Platform.exit();
        });
    }
}