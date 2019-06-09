package Etudiant;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Vector;

public class Etudiant {
    private String nomPrenom;
    private ArrayList<Tentative> tentatives;



    public Etudiant(String nomprenom){
        this.nomPrenom = nomprenom;
        this.tentatives = new ArrayList<Tentative>();
    }

    /**
     * Retourne la classe etudiant correspondant au nomPrenom si il existe,
     * retourne null sinon.
     *
     * @param etudiants
     * @param nomPrenom
     * @return
     */
    public static Etudiant getEtudiantByNomPrenom(ArrayList<Etudiant> etudiants, String nomPrenom){
        if(etudiants != null && nomPrenom != null && nomPrenom != "") {
            for (int i = 0; i < etudiants.size(); i++) {
                if (etudiants.get(i).getNomPrenom().contains(nomPrenom) || nomPrenom.contains(etudiants.get(i).getNomPrenom())) {
                    return etudiants.get(i);
                }
            }
        }
        return null;
    }

    public static void printEtudiants(ArrayList<Etudiant> etudiants){

        for(int i = 0;i<etudiants.size();i++){
            System.out.println(etudiants.get(i).toString());
        }

        System.out.println("\nNombre d'etudiants : " + etudiants.size());
    }



    public String getNomPrenom() {
        return nomPrenom;
    }


    public void addTentative(String tentative,String date,String heure,Vector testUnitaires, boolean reussite) {
        this.tentatives.add(new Tentative(tentative,date,heure, testUnitaires, reussite));
    }

    public ArrayList<Tentative> getTentatives() {
        return tentatives;
    }

    public Tentative getTentativeIndex(int index){
        return tentatives.get(index);
    }


    public void sortByDate(){
        Collections.sort(tentatives);
    }



    @Override
    public String toString() {
        String tostring = "\nEtudiant{" +
                "nomPrenom='" + nomPrenom + '\'' +
                ",";

        for(int i = 0;i < tentatives.size();i++){
            tostring = tostring + "\ntentative " + i + " = ";
            tostring = tostring + tentatives.get(i).toString();
        }
        tostring = tostring +'}';

        return tostring;
    }


}
