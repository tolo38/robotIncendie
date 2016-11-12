package plusCourtChemin;

/**
 *
 * @author 66
 */

/*
    Class utilisée pour construire le graphe : graphe = tableau de linkedlist de chemin
    Vitesse en km/h
    LongueurCase en m
    Cout en s

*/


public class Chemin {
    private int numCase;
    private double vitesse;
    private double cout;

    public Chemin(int nbcase, double speed, int longueurCase){
        numCase = nbcase;
        vitesse = speed;
        cout = calcCout(vitesse, longueurCase);
    }
    public int getNumCase(){
        return numCase;
    }
    public double getVitesse(){
        return vitesse;
    }
    private double calcCout(double vitesse, int longueurCase){
        return ((double)longueurCase*3.6)/vitesse;
    }
    public double getCout(){
        return cout;
    }
}
