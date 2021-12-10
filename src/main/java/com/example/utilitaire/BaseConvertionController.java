package com.example.utilitaire;

import com.example.utilitaire.objet.Bases;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.util.StringConverter;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class BaseConvertionController implements Initializable {
    @FXML
    private ComboBox<Bases> baseConverted;

    @FXML
    private ComboBox<Bases> baseToConvert;

    @FXML
    private TextField inputConverted;

    @FXML
    private TextField inputToConvert;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //création des list de valeure et instanciation des objets bases
        List<Character> hexaList = List.of('0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F');
        List<Character> binaryList = List.of('0', '1');
        List<Character> decaList = List.of('0', '1', '2', '3', '4', '5', '6', '7', '8', '9');

        Bases deca = new Bases("Base 10", decaList);
        Bases hexa = new Bases("hexadecimal", hexaList);
        Bases binary = new Bases("binary", binaryList);

        //ajout des objets aux combobox
        baseConverted.getItems().addAll(deca, hexa, binary);
        baseToConvert.getItems().addAll(deca, hexa, binary);

        baseToConvert.setPromptText("Convert from");
        baseConverted.setPromptText("Convert to");

        //Le converter permet d'afficher les objets par leur nom dans la combobox
        StringConverter baseDisplay = new StringConverter<Bases>() {
            public String toString(Bases bases) {
                return bases.getName();
            }

            public Bases fromString(String s) {
                return baseToConvert.getItems().stream().filter(bases -> bases.getName().equals(s)).findFirst().orElse(null);
            }
        };

        //on associe le converteur aux combobox
        baseConverted.setConverter(baseDisplay);
        baseToConvert.setConverter(baseDisplay);

        //On refresh les valeures quand on tape les valeures et quand on change de choix dans les combobox
        baseConverted.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
            Refresh(baseConverted, baseToConvert, inputConverted, inputToConvert);
        });

        baseToConvert.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
            Refresh(baseToConvert, baseConverted, inputToConvert, inputConverted);
        });


        inputToConvert.setOnKeyTyped(CtoR -> {
            Refresh(baseToConvert, baseConverted, inputToConvert, inputConverted);

        });

        inputConverted.setOnKeyTyped(CtoR -> {
            Refresh(baseConverted, baseToConvert, inputConverted, inputToConvert);
        });

    }

    //fonction refresh qui appel la fonction convertion avec les bons paramètres et vérifie les inputs
    public static void Refresh(ComboBox<Bases> baseToConvert, ComboBox<Bases> baseConverted, TextField inputToConvert, TextField inputConverted) {
        if (inputToConvert.getText().equals("")) {
            inputConverted.setText("");
        }

        for (int i = 0; i < inputToConvert.getLength() ; i++) {
            if (!baseToConvert.getValue().getValues().contains(inputToConvert.getText().charAt(i)))
            {
                inputToConvert.setText(inputToConvert.getText().replace(inputToConvert.getText().substring(i, i+1), ""));
            }
        }




        if (inputToConvert.getText().length() > 0) {
            inputConverted.setText(Converter(inputToConvert.getText(), baseToConvert.getValue().getValues(), baseConverted.getValue().getValues()));
        }
    }

    //Fonction de convertion de Base à base quelle quelle soit
    public static String Converter(String inputNombre, List<Character> baseToConvertValues, List<Character> wantedBaseValues) {
        System.out.println(inputNombre);
        int reste;
        String result = "";
        int resultBase = wantedBaseValues.size();
        int startBase = baseToConvertValues.size();
        double nombre = 0;
        for (int i = 0; i < inputNombre.length(); i++) {
            if (!baseToConvertValues.contains(inputNombre.charAt(i))) {
                return "01";
            }
            nombre = nombre + baseToConvertValues.indexOf(inputNombre.charAt(i)) * Math.pow(startBase, (inputNombre.length() - i - 1));
        }
        if (nombre == 0) {
            return "0";
        }
        if (wantedBaseValues.size() == 10) {

            return Integer.toString((int) nombre);
        }

        ArrayList<String> baseConvertionResultTable = new ArrayList<>();

        while (nombre != 0) {
            reste = (int) nombre % resultBase;
            nombre = (int) nombre / resultBase;
            System.out.println(reste);
            baseConvertionResultTable.add(0, Integer.toString(reste));
        }

        for (String s : baseConvertionResultTable) {
            result = result + wantedBaseValues.get(Integer.parseInt(s));
        }

        return result;
    }
}
