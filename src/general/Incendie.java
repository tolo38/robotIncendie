package general;

import general.robots.AbstractRobot;
import general.robots.UtileRobot;
import general.Case;

/*
        Défini par :
            - la case où il se situe
            - eau necessaire pour eteindre
    
        Arrosage
    */
public class Incendie {
    private int eauNecessaire;
    private int initEauNecessaire;
    private Case iCase; //case est un mot cle java
    
    public Incendie(Case iCase, int eau) {
        this.iCase = iCase;
        eauNecessaire = eau;
        initEauNecessaire = eau;
    }

    public int getLigne() {
        return iCase.getLigne();
    }

    public int getColonne() {
        return iCase.getColonne();
    }

    public int getEauNecessaire() {
        return eauNecessaire;
    }
    
    public boolean eteint() {
        return (eauNecessaire <= 0);
    }
    
    public void intervention(int qteDeversee) {
        eauNecessaire -= qteDeversee;
    }
    
    public void reset() {
        eauNecessaire = initEauNecessaire;
    }

    public Case getPosition() {
        return iCase;
    }
}
