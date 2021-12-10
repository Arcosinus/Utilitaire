package com.example.utilitaire.objet;

import java.util.List;

public class Bases {
    String name;
    List<Character> values;



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Character> getValues() {
        return values;
    }

    public void setValues(List<Character> values) {
        this.values = values;
    }




    public Bases(String name, List<Character> values) {
        this.values = values;
        this.name = name;
    }


}
