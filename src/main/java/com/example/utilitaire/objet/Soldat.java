package com.example.utilitaire.objet;

import java.util.List;

public class Soldat {
    String name;
    int hp;
    public void Soldat(String n, int s){
        //Constructeur de l'objet Soldat
        name=n;
        hp=s;
    }
    public void rename(String newname){
        //Renommage du Soldat
        name=newname;
    }
    public void medicalcheck(int health){
        //Donne un nouvel état de santé au Soldat
        hp=health;
    }
    public String Matricule(){
        //Donne le nom du Soldat
        return name;
    }
    public int statut(){
        //Donne l'état de santé du Soldat
        return hp;
    }
}
