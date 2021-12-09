package com.example.utilitaire.controller;

import com.example.utilitaire.objet.General;
import com.example.utilitaire.objet.Soldat;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ArmadaController implements Initializable {
    List<String> dico = new ArrayList<>();
    List<General> armada = new ArrayList<>();
    List<Soldat> green = new ArrayList<>();
    TreeItem<String> armadatree = new TreeItem<>("Armée");
    @FXML
    private TreeView treemada;
    @FXML
    private MenuItem createg;
    @FXML
    private MenuItem create;
    public void treeRefresh(){
        treemada.setRoot(null);
        TreeItem<String> armadatree = new TreeItem<>("Armée");
        for (int j = 0; j < armada.size(); j++) {
            TreeItem<String> generaltree = new TreeItem<>(armada.get(j).Matricule());
            if (armada.get(j).haveTroupe()) {
                for (int i = 0; i < armada.get(j).numTroupe(); i++) {
                    TreeItem<String> soldattree = new TreeItem<>(armada.get(j).troupeAssign(i));
                    generaltree.getChildren().add(soldattree);
                }
            }
            armadatree.getChildren().add(generaltree);
        }
        treemada.setRoot(armadatree);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dico.add("Jacque Chirac");
        dico.add("George Pompidou");
        dico.add("Pétain");
        dico.add("Emmanuel Macron");
        dico.add("Sarcozie");
        General general = new General();
        for (int i = 1; i <= 5; i++) {
            Soldat recrue = new Soldat();
            recrue.Soldat(dico.get(i-1));
            green.add(recrue);
        }
        general.General("Charle de Gaulle",green);
        armada.add(general);
        treeRefresh();
        create.setOnAction(create -> {
            General selectedgeneral = new General();
            MultipleSelectionModel msm = treemada.getSelectionModel();
            System.out.println(msm.getSelectedItem());
            int i;
            for (i = 0; i < armada.size(); i++) {
                if (msm.getSelectedItem() + "" == "TreeItem [ value: " + armada.get(i).Matricule() + " ]"){
                    selectedgeneral = armada.get(i);
                    break;
                }
            }
            Soldat recrue = new Soldat();
            recrue.Soldat("Soldat");
            selectedgeneral.addSoldat(recrue);
            armada.set(i-1,selectedgeneral);
            treeRefresh();
        });
        createg.setOnAction(createg -> {
            General recrue = new General();
            recrue.General("Général",null);
            armada.add(recrue);
            treeRefresh();
        });
    }
}
