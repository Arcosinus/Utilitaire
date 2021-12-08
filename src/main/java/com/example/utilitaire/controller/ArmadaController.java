package com.example.utilitaire.controller;

import com.example.utilitaire.objet.General;
import com.example.utilitaire.objet.Soldat;
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
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ArmadaController implements Initializable {
    List<General> armada = new ArrayList<>();
    List<Soldat> green = new ArrayList<>();
    @FXML
    private TreeView treemada;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        TreeItem<String> armadatree = new TreeItem<>("Armée");
        General general = new General();
        for (int i = 1; i <= 5; i++) {
            Soldat recrue = new Soldat();
            recrue.Soldat("Soldat" + i);
            green.add(recrue);
        }
        general.General("Charle de Gaulle",green);
        armada.add(general);
        TreeItem<String> generaltree = new TreeItem<>(armada.get(0).Matricule());
        System.out.println(armada.get(0).troupeAssign(0));
        for (int i = 0; i < armada.get(0).numTroupe(); i++) {
            TreeItem<String> soldattree = new TreeItem<>(armada.get(0).troupeAssign(i));
            generaltree.getChildren().add(soldattree);
        }
        armadatree.getChildren().add(generaltree);
        treemada.setRoot(armadatree);
    }
}
