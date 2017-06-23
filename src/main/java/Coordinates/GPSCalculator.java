package Coordinates;

public class GPSCalculator {

    private static final int MilliMinToGradFactor = 60000;




    public static double EvaluateBreite(Geokoordinate geokoordinate)
    {
        double breite = (double) (geokoordinate.Breitengrad / MilliMinToGradFactor)/100;


        return breite;
    }

    public static double EvaluateLaenge(Geokoordinate geokoordinate)
    {
        double laenge = (double) geokoordinate.Laengengrad / MilliMinToGradFactor;
        double intPart = (int) (laenge / 360);
        laenge = laenge - (intPart * 360);

        if (laenge < -180)
            laenge = 360 + laenge;

        if (laenge > 180)
            laenge = laenge - 360;


        return laenge;
    }

    private String GradToDms(double inGrad)
    {
        int grade = (int) inGrad;
        double restMinuten = (inGrad - grade);
        int minute = (int)(restMinuten * 60);
        double restSekunden = (double) minute / 60;
        double sekunde = (restMinuten - restSekunden) * 3600;
        return grade + "°" + minute + "" + sekunde;
    }


}
