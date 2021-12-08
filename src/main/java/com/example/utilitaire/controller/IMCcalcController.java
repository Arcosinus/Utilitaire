package com.example.utilitaire.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

import static java.lang.Integer.parseInt;

public class IMCcalcController implements Initializable {
    @FXML
    private Label Resultat;
    @FXML
    private TextField InputTaille;
    @FXML
    private TextField InputPoids;
    @FXML
    private Button btnCalcul;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btnCalcul.setOnMouseClicked(calculIMC -> {
            float calcul;
            boolean possible = true;
            try {
                calcul = Float.parseFloat(InputPoids.getText())/(Float.parseFloat(InputTaille.getText())*Float.parseFloat(InputTaille.getText()));
            } catch (Exception e){
                Resultat.setText("Valeur incorrecte");
                possible = false;
            }
            if (possible){
                calcul = Float.parseFloat(InputPoids.getText())/(Float.parseFloat(InputTaille.getText())*Float.parseFloat(InputTaille.getText()));
                if (calcul<16.5){
                    Resultat.setText(calcul+" dénutrition");
                } else if (calcul<18.5){
                    Resultat.setText(calcul+" maigreur");
                } else if (calcul<25){
                    Resultat.setText(calcul+" poids normal");
                } else if (calcul<30){
                    Resultat.setText(calcul+" surpoids");
                } else if (calcul<35){
                    Resultat.setText(calcul+" obésité modéré");
                } else if (calcul<40){
                    Resultat.setText(calcul+" obésité sévère");
                } else if (calcul>40){
                    Resultat.setText(calcul+" obésité massive");
                }
            }
        });
    }
}