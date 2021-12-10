package com.example.utilitaire.controller;

import com.example.utilitaire.objet.General;
import com.example.utilitaire.objet.Soldat;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static java.lang.Integer.parseInt;

public class ArmadaController implements Initializable {
    List<General> armada = new ArrayList<>();
    @FXML
    private Label erreur;
    @FXML
    private TextField nameSelect;
    @FXML
    private TextField hpSelect;
    @FXML
    private TreeView treemada;
    @FXML
    private MenuItem suppr;
    @FXML
    private MenuItem createg;
    @FXML
    private MenuItem create;
    public void treeRefresh(){
        //Affiche les nouveaux Generaux et Soldats ayant été ajouté ou modifié, sert aussi à initialisé la racine de l'arbre
        treemada.setRoot(null);
        TreeItem<String> armadatree = new TreeItem<>("Armée");
        for (int j = 0; j < armada.size(); j++) {
            armada.get(j).Rang();
            TreeItem<String> generaltree = new TreeItem<>("Â-"+armada.get(j).Matricule());
            if (armada.get(j).haveTroupe()) {
                for (int i = 0; i < armada.get(j).numTroupe(); i++) {
                    TreeItem<String> soldattree = new TreeItem<>("^-"+armada.get(j).troupeAssign(i));
                    generaltree.getChildren().add(soldattree);
                }
            }
            armadatree.getChildren().add(generaltree);
        }
        treemada.setRoot(armadatree);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        treeRefresh();
        create.setOnAction(create -> {
            //Ajoute un Soldat dans la liste des Soldats du General selectionné, ne fonctionne pas si l'armée ou un soldat est selectionné
            General selectedgeneral = new General();
            MultipleSelectionModel msm = treemada.getSelectionModel();
            int selecte = -1;
            for (int i = 0; i < armada.size(); i++) {
                if ((msm.getSelectedItem() + "").equals("TreeItem [ value: Â-" + armada.get(i).Matricule() + " ]")){
                    selecte=i;
                }
            }
            if (selecte != -1) {
                selectedgeneral.General(armada.get(selecte).Matricule(), armada.get(selecte).getTroupe());
                Soldat recrue = new Soldat();
                recrue.Soldat("Soldat" + (selectedgeneral.numTroupe() + 1),10);
                selectedgeneral.addSoldat(recrue);
                armada.set(selecte, selectedgeneral);
                treeRefresh();
            }
            erreur.setText("");
        });
        createg.setOnAction(createg -> {
            //Ajoute un General dans la liste des Generaux
            General recrueg = new General();
            List<Soldat> troupe = new ArrayList<>();
            recrueg.General("Général"+(armada.size()+1),troupe);
            armada.add(recrueg);
            treeRefresh();
            erreur.setText("");
        });
        treemada.setOnMouseClicked(selection ->{
            //Affiche le nom du General selectionné ou le nom et l'état de santé du Soldat selectionné
            nameSelect.clear();
            hpSelect.clear();
            MultipleSelectionModel msm = treemada.getSelectionModel();
            int selecte = -1;
            int selectesol = -1;
            for (int i = 0; i < armada.size(); i++) {
                if ((msm.getSelectedItem() + "").equals("TreeItem [ value: Â-" + armada.get(i).Matricule() + " ]")){
                    selecte=i;
                    i=armada.size();
                }
            }
            if (selecte == -1){
                for (int i = 0; i < armada.size(); i++) {
                    for (int j = 0; j < (armada.get(i).numTroupe()); j++) {
                        if ((msm.getSelectedItem() + "").equals("TreeItem [ value: ^-" + armada.get(i).troupeAssign(j) + " ]")){
                            selecte=i;
                            selectesol=j;
                            break;
                        }
                    }
                }
            }
            if (selectesol == -1 && selecte != -1) {
                nameSelect.setText(armada.get(selecte).Matricule());
            }
            if (selectesol != -1 && selecte != -1) {
                nameSelect.setText(armada.get(selecte).troupeAssign(selectesol));
                hpSelect.setText(armada.get(selecte).troupeAssignStatut(selectesol)+"");
            }
            erreur.setText("");
        });
        nameSelect.setOnKeyPressed(changeName ->{
            //Donne au General selectionné le nom donné dans le textfield ou donne au Soldat selectionné le nom ainsi que l'état de santé donné dans les textfiels en appuyant sur Entrée
            if (changeName.getCode() == KeyCode.ENTER) {
                MultipleSelectionModel msm = treemada.getSelectionModel();
                int selecte = -1;
                int selectesol = -1;
                for (int i = 0; i < armada.size(); i++) {
                    if ((msm.getSelectedItem() + "").equals("TreeItem [ value: Â-" + armada.get(i).Matricule() + " ]")) {
                        selecte = i;
                        i = armada.size();
                    }
                }
                if (selecte == -1) {
                    for (int i = 0; i < armada.size(); i++) {
                        for (int j = 0; j < (armada.get(i).numTroupe()); j++) {
                            if ((msm.getSelectedItem() + "").equals("TreeItem [ value: ^-" + armada.get(i).troupeAssign(j) + " ]")) {
                                selecte = i;
                                selectesol = j;
                                break;
                            }
                        }
                    }
                }
                if (selectesol == -1 && selecte != -1) {
                    armada.get(selecte).rename(nameSelect.getText());
                    erreur.setText("");
                }
                boolean valeurcorrect = false;
                if (selectesol != -1 && selecte != -1) {
                    try {
                        parseInt(hpSelect.getText());
                        valeurcorrect = true;
                    } catch (Exception e) {
                        valeurcorrect = false;
                        erreur.setText("Valeur de point de vie incorrect");
                    }
                    if (valeurcorrect){
                        armada.get(selecte).renameSoldier(nameSelect.getText(), selectesol);
                        armada.get(selecte).setHealth(parseInt(hpSelect.getText()), selectesol);
                        erreur.setText("");
                    }
                }
                treeRefresh();
                nameSelect.clear();
                hpSelect.clear();
            }
        });
        hpSelect.setOnKeyPressed(changeHp ->{
            //Donne au Soldat selectionné le nom ainsi que l'état de santé donné dans les textfiels en appuyant sur Entrée
            if (changeHp.getCode() == KeyCode.ENTER) {
                MultipleSelectionModel msm = treemada.getSelectionModel();
                int selecte = -1;
                int selectesol = -1;
                for (int i = 0; i < armada.size(); i++) {
                    for (int j = 0; j < (armada.get(i).numTroupe()); j++) {
                        if ((msm.getSelectedItem() + "").equals("TreeItem [ value: ^-" + armada.get(i).troupeAssign(j) + " ]")) {
                            selecte = i;
                            selectesol = j;
                            break;
                        }
                    }
                }
                boolean valeurcorrect;
                try{
                    parseInt(hpSelect.getText());
                    valeurcorrect = true;
                } catch (Exception e) {
                    valeurcorrect = false;
                    erreur.setText("Valeur de point de vie incorrect");
                }
                if (selectesol != -1 && selecte != -1 && valeurcorrect) {
                    armada.get(selecte).renameSoldier(nameSelect.getText(), selectesol);
                    armada.get(selecte).setHealth(parseInt(hpSelect.getText()), selectesol);
                    erreur.setText("");
                }
                treeRefresh();
                nameSelect.clear();
                hpSelect.clear();
            }
        });
        suppr.setOnAction(suppression ->{
            //Supprime le Soldat selectionné ou le General selectionné et l'ensemble de ses soldats
            MultipleSelectionModel msm = treemada.getSelectionModel();
            int selecte = -1;
            int selectesol = -1;
            for (int i = 0; i < armada.size(); i++) {
                if ((msm.getSelectedItem() + "").equals("TreeItem [ value: Â-" + armada.get(i).Matricule() + " ]")) {
                    selecte = i;
                    i = armada.size();
                }
            }
            if (selecte == -1) {
                for (int i = 0; i < armada.size(); i++) {
                    for (int j = 0; j < (armada.get(i).numTroupe()); j++) {
                        if ((msm.getSelectedItem() + "").equals("TreeItem [ value: ^-" + armada.get(i).troupeAssign(j) + " ]")) {
                            selecte = i;
                            selectesol = j;
                            break;
                        }
                    }
                }
            }
            if (selectesol == -1 && selecte != -1) {
                armada.remove(selecte);
            }
            if (selectesol != -1 && selecte != -1) {
                armada.get(selecte).fired(selectesol);
            }
            treeRefresh();
            erreur.setText("");
        });
    }
}
