package general;

import general.robots.AbstractRobot;
import general.robots.UtileRobot;


/*
        Défini par :
            - la case où il se situe
            - eau necessaire pour eteindre
    
        Arrosage
    */
public class Incendie {
    private int ligne;
    private int colonne;
    private int eauNecessaire;
    private int initEauNecessaire;
    
    public Incendie(int lig, int col, int eau) {
        ligne = lig;
        colonne = col;
        eauNecessaire = eau;
        initEauNecessaire = eau;
    }

    public int getLigne() {
        return ligne;
    }

    public int getColonne() {
        return colonne;
    }

    public int getEauNecessaire() {
        return eauNecessaire;
    }
    
    public void intervention(int qteDeversee) {
        eauNecessaire -= qteDeversee;
        if (qteDeversee <= 0) {
            //UtileRobot.getDataGame().addEvenement(new IncendieEteint(AbstractRobot robot, Incendie incendie));
        }
    }
    
    public void reset() {
        eauNecessaire = initEauNecessaire;
    }
}
