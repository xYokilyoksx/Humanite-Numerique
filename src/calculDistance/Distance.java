package calculDistance;

import Etudiant.Tentative;

public class Distance {

    public static double calculDistanceNbCar(Tentative tentative,int nbCarRef){
        double distance;
        if(tentative.getTentative().length() == nbCarRef){
            return 1;
        }else if(tentative.getTentative().length() == 0) {
            return 0;
        }else{
            distance = Math.abs(nbCarRef - tentative.getTentative().length());
            distance = Math.sqrt(distance);
            //distance = Math.log(distance);
            System.out.println(distance);

            return 1/distance;
        }

    }
}
