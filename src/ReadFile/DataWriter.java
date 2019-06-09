package ReadFile;

import Etudiant.Etudiant;

import java.io.IOException;
import java.util.ArrayList;

public class DataWriter {

    public DataWriter(ArrayList<Etudiant> etudiants){

        //Ecrit dans le fichier blop.txt tout les noms
        for (Etudiant etudiant : etudiants) {
            etudiant.writeFile();
        }

    }
}
