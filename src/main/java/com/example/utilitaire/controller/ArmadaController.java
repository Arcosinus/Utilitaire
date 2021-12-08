package com.example.utilitaire.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ArmadaController implements Initializable {
    @FXML
    private TreeView treemada;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        TreeItem<String> armada = new TreeItem<>("Armée");
        TreeItem<String> general = new TreeItem<>("GénéralA");
        TreeItem<String> soldat = new TreeItem<>("SoldatA");
        general.getChildren().add(soldat);
        armada.getChildren().add(general);
        treemada.setRoot(armada);
    }
}
