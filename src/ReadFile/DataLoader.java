package ReadFile;

import Etudiant.Etudiant;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataLoader {
    private static final String dateRegex = ".*([0-9]{4})-(0[1-9]|1[0-2])-(0[1-9]|1[0-9]|2[0-9]|3[0-1]).*";
    private static final String heureRegex = ".*(0[0-9]|1[0-9]|2[0-3]):([0-5][0-9]):([0-5][0-9]).*";

    private ArrayList<Etudiant> etudiants = new ArrayList<Etudiant>();

    public DataLoader(File fileTentatives,File fileReussites){

        loadFileTentatives(fileTentatives,false);
        loadFileTentatives(fileReussites, true);
        sortEtudiantsByDate();
        Etudiant.printEtudiants(etudiants);
    }


    public void loadFileTentatives(File fileTentatives, boolean reussite){
        BufferedReader reader = null;
        String line,date,heure,nomPrenom;
        Etudiant currentEtudiant;
        String tentative;
        Vector testUnitaires = new Vector();

        try {
            reader = new BufferedReader(new FileReader(fileTentatives));
            line = reader.readLine();

            while(line != null){
                if(isNewTentative(line)){

                    date = getDateInString(line);
                    heure = getHeureInString(line);
                    nomPrenom = getNomPrenom(line,date,heure);

                    //Retrouve l'étudiant puis l'affecte a currentEtudiant si il existe, sinon le créer puis l'affecte a currentEtudiant
                    if((currentEtudiant = Etudiant.getEtudiantByNomPrenom(etudiants,nomPrenom)) == null){
                        currentEtudiant = new Etudiant(nomPrenom);
                        etudiants.add(currentEtudiant);
                    }

                    if((line = reader.readLine()) != null){
                        System.out.println(line.toCharArray());
                    }

                    tentative = "";
                    while((line = reader.readLine()) != null && !isNewTentative(line)){
                        tentative = tentative + line;
                    }

                    //Insertion des tentatives dans la classe Etudiant correspondant
                    currentEtudiant.addTentative(tentative, date , heure, testUnitaires, reussite);

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


    private void sortEtudiantsByDate(){
        for(int i = 0; i < etudiants.size();i++){
            etudiants.get(i).sortByDate();
        }
    }

    private boolean isNewTentative(String line){
        if(isValidDate(line) && isValidHeure(line)){
            return true;
        }
        return false;
    }


    private boolean isValidDate(String line) {
        if(line != null) {
            Pattern pattern = Pattern.compile(dateRegex, Pattern.DOTALL);
            Matcher matcher = pattern.matcher(line);

            return matcher.find();
        }
        return false;
    }


    private String getDateInString(String line){

        if(line != null) {
            Pattern pattern = Pattern.compile(dateRegex, Pattern.DOTALL);
            Matcher matcher = pattern.matcher(line);

            if(matcher.find()) {
                return matcher.group(1) + "-" + matcher.group(2) + "-" + matcher.group(3);
            }
        }
        return null;
    }

    /**
     *
     *
     * @param line
     * @return
     */
    private Vector lineToVector(String line){
        line.toCharArray();
        System.out.println(line.toCharArray());
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
