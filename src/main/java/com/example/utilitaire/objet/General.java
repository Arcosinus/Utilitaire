package com.example.utilitaire.objet;

import java.util.ArrayList;
import java.util.List;

public class General {
    String name;
    List<Soldat> troupe = new ArrayList<>();
    public void General(String n, List<Soldat> assign){
        //Constructeur de l'objet General
        name=n;
        troupe=assign;
    }
    public void Rang(){
        //Tri de la liste d'objet Soldat assigné au General via création d'un liste ordonné qui écrasera la liste originale
        List<Soldat> rang = new ArrayList<>();
        int initial = troupe.size();
        for (int j = 0; j < initial; j++) {
            int MostAlive = -1;
            int indexmost = -1;
            for (int i = 0; i < troupe.size(); i++) {
                if (troupeAssignStatut(i)>MostAlive){
                    MostAlive=troupeAssignStatut(i);
                    indexmost=i;
                }
            }
            if (indexmost != -1) {
                rang.add(troupe.get(indexmost));
                troupe.remove(indexmost);
            }
        }
        troupe=rang;
    }
    public void rename(String newname){
        //Renommage du General
        name=newname;
    }
    public void renameSoldier(String newname, int index){
        //Appel la fonction de renommage du Soldat correspondant à l'index fournit
        troupe.get(index).rename(newname);
    }
    public void fired(int index){
        //Vire le Soldat de la liste correspondant à l'index donné
        troupe.remove(index);
    }
    public void setHealth(int health, int index){
        //Appelle la fonction qui change l'état de santé du Soldat de la liste correspondant à l'index
        troupe.get(index).medicalcheck(health);
    }
    public String Matricule(){
        //Donne le nom du General
        return name;
    }
    public String troupeAssign(int index){
        //Appelle la fonction qui donne le nom du Soldat de la liste correspondant à l'index
        return troupe.get(index).Matricule();
    }
    public int troupeAssignStatut(int index){
        //Appelle la fonction qui donne l'état de santé du Soldat de la liste correspondant à l'index
        return troupe.get(index).statut();
    }
    public List<Soldat> getTroupe(){
        //Renvoie la liste des Soldats assignés au General
        return troupe;
    }
    public void addSoldat(Soldat green){
        //Recrute un nouveau Soldat qui s'ajoute à la liste
        troupe.add(green);
    }
    public int numTroupe(){
        //Donne le nombre de Soldat dirigé par le General
        return troupe.size();
    }
    public boolean haveTroupe(){
        //Vérifie si le General possèdent des Soldats
        if (troupe == null){
            return false;
        }
        return true;
    }
}
