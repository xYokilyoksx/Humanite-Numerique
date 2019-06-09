package Etudiant;


import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class Tentative implements Comparable<Tentative> {

    private String tentative;
    private LocalDateTime dateheure;
    private boolean reussite;
    private String date;
    private String heure;

    public Tentative(){}


    public Tentative(String tentative,String date,String heure,boolean reussite){
        this.tentative = tentative;
        this.reussite = reussite;
        this.dateheure = LocalDateTime.parse(date+"T"+heure);
        this.date = date;
        this.heure = heure;
    }

    public String getHeure() {
        return heure;
    }

    public String getDateString() {
        return date;
    }

    public String getTentative() {
        return tentative;
    }


    public boolean isReussite() {
        return reussite;
    }

    public LocalDateTime getDateHeure() {
        return dateheure;
    }

    public Date getDate(){
        return Date.from( dateheure.atZone( ZoneId.systemDefault()).toInstant());
    }

    @Override
    public String toString() {
        return "Tentative{" +
                "tentative='" + tentative + '\'' +
                ", dateheure=" + this.getDate() +
                ", reussite=" + reussite +
                '}';
    }

    @Override
    public int compareTo(Tentative o) {
        return getDateHeure().compareTo(o.getDateHeure());
    }

}
