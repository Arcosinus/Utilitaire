package com.example.utilitaire.objet;

import java.util.ArrayList;
import java.util.List;

public class General {
    String name;
    List<Soldat> troupe = new ArrayList<>();
    public void Rang(){
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
    public void General(String n, List<Soldat> assign){
        name=n;
        troupe=assign;
    }
    public void rename(String newname){
        name=newname;
    }
    public void renameSoldier(String newname, int index){
        troupe.get(index).rename(newname);
    }
    public void fired(int index){
        troupe.remove(index);
    }
    public void setHealth(int health, int index){
        troupe.get(index).medicalcheck(health);
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
