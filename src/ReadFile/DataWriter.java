package ReadFile;

import Etudiant.Etudiant;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class DataWriter {

    public DataWriter(ArrayList<Etudiant> etudiants){

        File file = new File("Data\\exo31modifie.txt");
        if (file.exists())
            file.delete();

        //Ecrit dans le fichier blop.txt tout les noms
        for (Etudiant etudiant : etudiants) {
            etudiant.writeFile();
        }

    }
}
