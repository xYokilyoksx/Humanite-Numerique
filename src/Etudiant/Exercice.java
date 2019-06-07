package Etudiant;

import java.util.ArrayList;

public class Exercice {
    private ArrayList<Tentative> tentatives;

    public Exercice(){
        tentatives = new ArrayList<Tentative>();
        tentatives = null;
    }


    public void addTentatives(Tentative tentative){
        tentatives.add(tentative);
    }
}
