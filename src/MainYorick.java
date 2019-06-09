import ReadFile.DataLoader;
import ReadFile.DataWriter;

import java.io.File;

public class MainYorick {

    public static void main(String[] args) {
        File fileTentatives = new File("Data\\exo31Tentatives.txt");
        File fileReussites = new File("Data\\exo31Reussites.txt");
        DataLoader n = new DataLoader(fileTentatives,fileReussites);
        DataWriter dataWriter = new DataWriter(n.getEtudiants());

    }
}
