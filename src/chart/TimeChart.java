package chart;

import Etudiant.Etudiant;
import calculDistance.Distance;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.time.*;
import org.jfree.data.xy.XYDataset;

import javax.swing.*;
import java.util.ArrayList;

public class TimeChart extends JFrame {

    public TimeChart(String title, ArrayList<Etudiant> etudiants) {
        super(title);
        // Create dataset
        XYDataset dataset = createDataset(etudiants);
        // Create chart
        JFreeChart chart = ChartFactory.createTimeSeriesChart(
                "nbCar / date", // Chart
                "Date", // X-Axis Label
                "nb caractères", // Y-Axis Label
                dataset);

        ChartPanel panel = new ChartPanel(chart);
        setContentPane(panel);
    }

    private XYDataset createDataset(ArrayList <Etudiant> etudiants) {
        TimePeriodValues s1;
        TimePeriodValuesCollection dataset = new TimePeriodValuesCollection();

        for(int i = 0;i < etudiants.size();i++) {
            s1 = new TimePeriodValues(etudiants.get(i).getNomPrenom());
            for (int j = 0; j < etudiants.get(i).getTentatives().size(); j++) {

                Minute m = new Minute(etudiants.get(i).getTentativeIndex(j).getDateHeure().getMinute(),
                        etudiants.get(i).getTentativeIndex(j).getDateHeure().getHour(),
                        etudiants.get(i).getTentativeIndex(j).getDateHeure().getDayOfMonth(),
                        etudiants.get(i).getTentativeIndex(j).getDateHeure().getMonthValue(),
                        etudiants.get(i).getTentativeIndex(j).getDateHeure().getYear());

                s1.add(new SimpleTimePeriod(m.getStart(), m.getStart()),Distance.calculDistanceNbCar(etudiants.get(i).getTentativeIndex(j),96));
            }
            dataset.addSeries(s1);
        }

        return dataset;
    }

}