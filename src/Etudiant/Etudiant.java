package Etudiant;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Etudiant {
    private String nomPrenom;
    private ArrayList<Tentative> tentatives;



    public Etudiant(String nomprenom){
        this.nomPrenom = nomprenom;
        this.tentatives = new ArrayList<Tentative>();
    }

    /**
     * return Integer.MAX_VALUE si le nom n'existe pas
     *
     * @param etudiants
     * @return
     */
    public static int getIndexNom(ArrayList<Etudiant> etudiants, String nomPrenom){
        if(etudiants != null) {
            for (int i = 0; i < etudiants.size(); i++) {
                if (etudiants.get(i).getNomPrenom().contains(nomPrenom) || nomPrenom.contains(etudiants.get(i).getNomPrenom())) {
                    return i;
                }
            }
        }

        return Integer.MAX_VALUE;
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


    public void addTentative(String tentative,String date,String heure) {
        this.tentatives.add(new Tentative(tentative,date,heure,false));
    }

    public void addReussite(String tentative,String date,String heure) {
        this.tentatives.add(new Tentative(tentative,date,heure,true));
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

    public String stringWriteFile(Tentative tentative){
        String nomprenom = nomPrenom;

        return nomprenom + tentative.getDateString() + " " + tentative.getHeure() + " \n" + tentative.getTentative();
    }

    public void writeFile() {
        BufferedWriter bw = null;
        try {

            File file = new File("Data\\blop.txt");

            //créer fichier si il existe pas
            if (!file.exists()){
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
            bw = new BufferedWriter(fw);
            for (Tentative tentative : tentatives) {
                String content = stringWriteFile(tentative);

                bw.write(content);
                bw.newLine();

            }
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            try {
                assert bw != null;
                bw.close();
            }catch (IOException e){
                e.printStackTrace();
            }

        }
    }


}
