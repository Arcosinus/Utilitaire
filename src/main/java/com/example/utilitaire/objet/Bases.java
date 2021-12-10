package com.example.utilitaire.objet;

import java.util.List;
//objet base qui défini 2 caractéristique le nom de la base et son tableaux de valeures possibles

public class Bases {
    String name;
    List<Character> values;



    public String getName() {
        return name;
    }


    public List<Character> getValues() {
        return values;
    }



    public Bases(String name, List<Character> values) {
        this.values = values;
        this.name = name;
    }


}
