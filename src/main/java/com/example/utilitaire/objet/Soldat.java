package com.example.utilitaire.objet;

import java.util.List;

public class Soldat {
    String name;
    public void Soldat(String n){
        name=n;
    }
    public String Matricule(){
        return name;
    }
}
