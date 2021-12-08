package com.example.utilitaire.controller;

import com.example.utilitaire.objet.D2BinaryHexaConverter;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class ConverterController implements Initializable {

    //Controller of the scene Converter

    @FXML
    private TextField txtDecimalB;
    @FXML
    private TextField txtDecimalH;
    @FXML
    private TextField txtBinary;
    @FXML
    private TextField txtHexadecimal;

    public void initialize(URL url, ResourceBundle resourceBundle) {
        txtDecimalB.setOnKeyTyped(test -> {
            txtBinary.setText(D2BinaryHexaConverter.dec2bin(txtDecimalB.getText()));
        });
        txtDecimalH.setOnKeyTyped(test -> {
            txtHexadecimal.setText(D2BinaryHexaConverter.dec2hexa(txtDecimalH.getText()));
        });
        txtBinary.setOnKeyTyped(test -> {
            txtDecimalB.setText(D2BinaryHexaConverter.bin2dec(txtBinary.getText()));
        });
        txtHexadecimal.setOnKeyTyped(test -> {
            txtDecimalH.setText(D2BinaryHexaConverter.hexa2dec(txtHexadecimal.getText()));
        });

    }

}
