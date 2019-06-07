package chart;

import Etudiant.Etudiant;
import calculDistance.Distance;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;

import java.util.ArrayList;


public class Chart extends ApplicationFrame {


    public Chart(String applicationTitle , String chartTitle , ArrayList <Etudiant> etudiants) {
        super(applicationTitle);
        JFreeChart lineChart = ChartFactory.createLineChart(chartTitle,
                "nb tentatives","nb caractères",
                createDatasetNbTentatives(etudiants),
                PlotOrientation.VERTICAL,
                true,true,false);

        ChartPanel chartPanel = new ChartPanel( lineChart );
        chartPanel.setPreferredSize( new java.awt.Dimension( 560 , 367 ) );
        setContentPane( chartPanel );



    }



    private DefaultCategoryDataset createDatasetNbTentatives(ArrayList <Etudiant> etudiants) {

        DefaultCategoryDataset dataset = new DefaultCategoryDataset( );


        for(int i = 0;i < etudiants.size();i++) {
            for (int j = 0; j < etudiants.get(i).getTentatives().size(); j++) {
                dataset.addValue(Distance.calculDistanceNbCar(etudiants.get(i).getTentativeIndex(j),96), etudiants.get(i).getNomPrenom(), "" + j);
            }
        }

        return dataset;
    }

}
