import ReadFile.DataLoader;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import chart.Chart;
import chart.TimeChart;
import org.jfree.ui.RefineryUtilities;

import javax.swing.*;


public class Main {
    public static void main (String[] args){



        File fileTentatives = new File("Data\\exo31Tentatives.txt");
        File fileReussites = new File("Data\\exo31Reussites.txt");
        DataLoader n = new DataLoader(fileTentatives,fileReussites);

        Chart chart = new Chart("nbCar / nbTentatives","nbCar / nbTentatives",n.getEtudiants());
        chart.pack( );
        RefineryUtilities.centerFrameOnScreen( chart );
        chart.setVisible( true );



            TimeChart example = new TimeChart("nbCar / date",n.getEtudiants());
            example.setSize(800, 400);
            example.setLocationRelativeTo(null);
            example.setVisible(true);
            example.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        System.out.println("test");
    }
}