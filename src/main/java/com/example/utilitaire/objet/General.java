package com.example.utilitaire.objet;

import java.util.ArrayList;
import java.util.List;

public class General {
    String name;
    List<Soldat> troupe = new ArrayList<>();
    public void General(String n, List<Soldat> assign){
        name=n;
        troupe=assign;
    }
    public String Matricule(){
        return name;
    }
    public String troupeAssign(int index){
        return troupe.get(index).Matricule();
    }
    public int troupeAssignStatut(int index){
        return troupe.get(index).statut();
    }
    public List<Soldat> getTroupe(){
        return troupe;
    }
    public void addSoldat(Soldat green){
        troupe.add(green);
    }
    public int numTroupe(){
        return troupe.size();
    }
    public boolean haveTroupe(){
        if (troupe == null){
            return false;
        }
        return true;
    }
}
