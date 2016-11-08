
package general.robots;

import general.robots.AbstractRobot;
import exceptions.ForbiddenMoveException;
import exceptions.WrongPositionException;
import general.Case;
import general.Direction;
import general.NatureTerrain;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 66
 */
public class Drone extends AbstractRobot {
    
    private static double vitesseDrone = 100;
    public static final long tempsRemplissage = 30 * 60;

    /**
     * tailleReservoir = 10000L
     * @param position
     * @param qteDeversee
     * @param tempsRemplissage
     */
    public Drone(Case position, double qteDeversee, long tempsRemplissage) {
        super(position, 10000, vitesseDrone, qteDeversee, tempsRemplissage);
    }
    
    public Drone(Case position, double qteDeversee, long tempsRemplissage, double vitesse) {
        super(position, 10000, vitesse, qteDeversee, tempsRemplissage);
    }
    
    @Override
    public void remplirReservoir() throws WrongPositionException {
        boolean allowed = UtileRobot.aboveWater(this.getPosition());
        if (allowed) {
            super.remplirReservoir();
        } else throw new WrongPositionException(this.getPosition());
    }
    
    public String getType() {
        return "DRONE";
    }

    @Override
    public void deplacerRobot(Direction direction) {
        try {
            Case caseSuivante = UtileRobot.caseSuivante(this.getPosition(), direction);
            this.setPosition(caseSuivante);
        } catch (ForbiddenMoveException ex) {
            System.out.println("Forbidden move : " + this + "\nLocation: " + this.getPosition() + "can't move " + direction);
        }
    }
    
}
