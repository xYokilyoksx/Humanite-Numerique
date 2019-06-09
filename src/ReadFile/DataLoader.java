package ReadFile;

import Etudiant.Etudiant;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataLoader {
    private String dateRegex = ".*([0-9]{4})-(0[1-9]|1[0-2])-(0[1-9]|1[0-9]|2[0-9]|3[0-1]).*";
    private String heureRegex = ".*(0[0-9]|1[0-9]|2[0-3]):([0-5][0-9]):([0-5][0-9]).*";

    ArrayList<Etudiant> etudiants = new ArrayList<Etudiant>();

    public DataLoader(){}

    public DataLoader(File fileTentatives){

        loadFileTentatives(fileTentatives);


        Etudiant.printEtudiants(etudiants);
    }

    public DataLoader(File fileTentatives,File fileReussites){

        loadFileTentatives(fileTentatives);
        loadFileReussites(fileReussites);

        sortEtudiantsByDate();

        Etudiant.printEtudiants(etudiants);
    }


    public void loadFileTentatives(File fileTentatives){
        BufferedReader reader = null;
        String line,date,heure,nom;
        int currentStudent = 0;
        int end = 0,i = 0,j=0;
        String tentative;

        try {

            reader = new BufferedReader(new FileReader(fileTentatives));
            line = reader.readLine();


            while(line != null){
                if(isNewTentative(line)){

                    date = getDateInString(line);
                    heure = getHeureInString(line);
                    nom = getNomPrenom(line,date,heure);
                    currentStudent = Etudiant.getIndexNom(etudiants,nom);

                    if(currentStudent == Integer.MAX_VALUE){
                        etudiants.add(0,new Etudiant(nom));
                        currentStudent = 0;
                    }

                    tentative = "";
                    while((line = reader.readLine()) != null && !isNewTentative(line)){
                        tentative = tentative + line;
                    }
                    etudiants.get(currentStudent).addTentative(tentative,date , heure);

                }else {
                    line = reader.readLine();
                }
            }





        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }


    public void loadFileReussites(File fileTentatives){
        BufferedReader reader = null;
        String line,date,heure,nom;
        int currentStudent = 0;
        int end = 0,i = 0,j=0;
        String tentative;




        try {

            reader = new BufferedReader(new FileReader(fileTentatives));
            line = reader.readLine();


            while(line != null){
                if(isNewTentative(line)){

                    date = getDateInString(line);
                    heure = getHeureInString(line);
                    nom = getNomPrenom(line,date,heure);
                    currentStudent = Etudiant.getIndexNom(etudiants,nom);

                    if(currentStudent == Integer.MAX_VALUE){
                        etudiants.add(0,new Etudiant(nom));
                        currentStudent = 0;
                    }

                    tentative = "";
                    while((line = reader.readLine()) != null && !isNewTentative(line)){
                        tentative = tentative + line;
                    }
                    etudiants.get(currentStudent).addReussite(tentative,date , heure);

                }else {
                    line = reader.readLine();
                }
            }


        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

    public void sortEtudiantsByDate(){
        for(int i = 0; i < etudiants.size();i++){
            etudiants.get(i).sortByDate();
        }
    }

    public boolean isNewTentative(String line){
        if(isValidDate(line) && isValidHeure(line)){
            return true;
        }
        return false;
    }


    public boolean isValidDate(String line) {
        if(line != null) {
            Pattern pattern = Pattern.compile(dateRegex, Pattern.DOTALL);
            Matcher matcher = pattern.matcher(line);

            return matcher.find();
        }
        return false;
    }


    public String getDateInString(String line){

        if(line != null) {
            Pattern pattern = Pattern.compile(dateRegex, Pattern.DOTALL);
            Matcher matcher = pattern.matcher(line);
            String heure,minute,seconde;

            if(matcher.find()) {
                return matcher.group(1) + "-" + matcher.group(2) + "-" + matcher.group(3);
            }
        }
        return null;
    }


    public String getNomPrenom(String line,String date,String heure){
        return line.replace(date,"").replace(heure,"");
    }

    public boolean isValidHeure(String line) {
        if(line != null) {
            Pattern pattern = Pattern.compile(heureRegex, Pattern.DOTALL);
            Matcher matcher = pattern.matcher(line);

            return matcher.find();
        }
        return false;
    }

    public String getHeureInString(String line){

        if(line != null) {
            Pattern pattern = Pattern.compile(heureRegex, Pattern.DOTALL);
            Matcher matcher = pattern.matcher(line);
            String heure,minute,seconde;

            if(matcher.find()) {
                return matcher.group(1) + ":" + matcher.group(2) + ":" + matcher.group(3);
            }
        }
        return null;
    }


    public ArrayList<Etudiant> getEtudiants() {
        return etudiants;
    }
}
