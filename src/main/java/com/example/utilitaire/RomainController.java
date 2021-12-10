package com.example.utilitaire;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

public class RomainController implements Initializable {


    @FXML
    private TextField inputChiffresRomains;

    @FXML
    private TextField inputNombre;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    //On refresh La valeure du textifield chiffres Romains a chaque lettres tapé dans chiffres
        inputNombre.setOnKeyTyped(CtoR -> {

            List<Character> chiffres = Arrays.asList('0','1', '2', '3', '4', '5', '6', '7', '8', '9');

            //on fait en sorte que l'utilisateur ne puisse rentrer que des caractères numériques
            //dans le textfield chiffre.Si le craractère rentré n'est pas dans la liste chiffres il est
            //retiré
            for (int i = 0; i < inputNombre.getLength() ; i++) {
                if (!chiffres.contains(inputNombre.getText().charAt(i)))
                {
                    inputNombre.setText(inputNombre.getText().replace(inputNombre.getText().substring(i, i+1), ""));
                }
            }
            //On ne fait le calcul dans le textfield Chiffres romain que si l'input n'est pas nul
            if (inputNombre.getText().length() > 0) {
                try {
                    inputChiffresRomains.setText(ChiffresToRomain(Integer.parseInt(inputNombre.getText())));
                }
                catch (Exception e){
                    inputNombre.setText("Incorrect roman number");
                }
            }
            //Sinon le textfield Chiffres romain est set à nul
            else {
                inputChiffresRomains.setText("");
            }
        });

        //Idem pour le textfield Chiffres Romains
        inputChiffresRomains.setOnKeyTyped(RtoC -> {
            List<Character> chiffresRomains = Arrays.asList('I', 'V', 'X', 'L', 'C', 'D', 'M');

            for (int i = 0; i < inputChiffresRomains.getLength() ; i++) {
                if (!chiffresRomains.contains(inputChiffresRomains.getText().charAt(i)))
                {
                    inputChiffresRomains.setText(inputChiffresRomains.getText().replace(inputChiffresRomains.getText().substring(i, i+1), ""));
                }
            }
            if (inputChiffresRomains.getText().length()>0 ){
            inputNombre.setText(RomainToChiffres(inputChiffresRomains.getText()));}
            else {
                inputNombre.setText("");
            }
        });


    }





    public String RomainToChiffres(String processedChiffresRomains){

        //initialization de deux tableaux contenant la liste des chiffres romain par ordre croissant
        //et des valeures conrespondantes en base 10 avec index concordants (I et 1 sont tout deux à l'index 0).


        List<Integer> chiffres = Arrays.asList(1, 5, 10, 50, 100, 500, 1000);
        List<String> chiffresRomains = Arrays.asList("I", "V", "X", "L", "C", "D", "M");


        //On définit le niveau du premier chiffre romain de l'input c'est à dire son index (ind) dans le tableau des chiffres
        //romains.Par exemple si on a input="VII" pour V level=1 car c'est l'indexe de V dans chiffresRomains
        int level = chiffresRomains.indexOf(processedChiffresRomains.substring(0, 1));
        //nextlevel contient le niveau correspondant au prochain chiffre romain de l'input.
        //dans l'exemple précédent le deuxième chiffre est I dont l'index est 0 dans chiffresRomains donc nextLevel = 0
        int nextLevel = 0;
        //variable de stockage des opérations aboutissant à la conversion
        int convertion = 0;

        //boucle pour chaque Lettres (chiffres romains) que contient input


        for (int i = 0; i < processedChiffresRomains.length(); i++) {
            //on stock le niveau de la lettre situé à l'index i de input
            level = chiffresRomains.indexOf(processedChiffresRomains.substring(i, i + 1));

            //si il existe un chiffre après l'index i on assigne à nextlevel le niveau de la prochaine lettre
            if (!(i == processedChiffresRomains.length() - 1)) {
                nextLevel = chiffresRomains.indexOf(processedChiffresRomains.substring(i + 1, i + 2));
            }

            //si le niveau du prochain chiffre romain est est plus grand de 1 du niveau du Chiffre actuel
            //nous somme dans une configuration de type "IV" et l'on fait l'opération 5 - 1
            // soit V(next level)-I(level)
            //on incrémente i une deuxième fois car nous avons utiliser 2 chiffre (dans l'exemple I et V)
            if (nextLevel == level + 1) {
                convertion = convertion + chiffres.get(nextLevel) - chiffres.get(level);
                i++;
            }

            //dans tous les autres cas on ajoute simplement la valeure du chiffre courant a la variable conversion
            //la configuration est de type "LX" on va simplement faire L + X soit 50+10
            else {
                convertion = convertion + chiffres.get(level);
            }
        }
        if (ChiffresToRomain(convertion).equals(processedChiffresRomains)){
        return Integer.toString(convertion);}
        else {
            return "incorrect roman number";
        }
    }

    public String ChiffresToRomain(int processedNombre){
        List<Integer> chiffres = Arrays.asList(1, 5, 10, 50, 100, 500, 1000);
        List<String> chiffresRomains = Arrays.asList("I", "V", "X", "L", "C", "D", "M");
        //On reverse les listes utilisées précedement pour faciliter l'intération par ordre Decroissant
        Collections.reverse(chiffres);
        Collections.reverse(chiffresRomains);


        int level = 0;
        String convertion = "";


        if (processedNombre <= 3999) {



            //tant que l'input est supèrieur à 0 on lui enlève le plus grand chiffre romain qu'il contient et on ajoute
            //la lettre correspondante à convertion
            //si on prend 223 on fait 223 - C - C - X - X -I -I -I =0
            while (processedNombre > 0) {
                int incr = 0;
                while ((double) processedNombre / chiffres.get(level) >= 1) {
                    convertion = convertion + chiffresRomains.get(level);
                    processedNombre = processedNombre - chiffres.get(level);
                    incr++;

                    //on incrémente à chanque fois que l'on rajoute le même chiffre romain plusieurs fois
                    //arrivé à 4 on remplace cette suit de 4 par une écriture correcte en chiffre romain
                    //IIII devient IV
                    if (incr == 4) {
                        String occu = chiffresRomains.get(level);
                        convertion = convertion.replace(occu + occu + occu + occu, occu + chiffresRomains.get(level - 1));

                    }

                }

                System.out.println(convertion);
                //On incrémente level car si on est arrivé ici c'est que la valeure du chiffre correspondant
                // est plus élévé que l'input
                level++;
            }


        }
        else {
            convertion = "Number is too large ";
        }

        return convertion;
    }

}