
package general.robots;

import general.robots.AbstractRobot;
import exceptions.ForbiddenMoveException;
import exceptions.WrongPositionException;
import general.Case;
import general.Direction;
import general.NatureTerrain;

/**
 *
 * @author 66
 */
public class Drone extends AbstractRobot {
    
    private static double vitesseDrone = 100;

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
    
    /**
     *
     * @throws exceptions.WrongPositionException
     */
    @Override
    public void remplirReservoir() throws WrongPositionException {
        if (this.getPosition().getNature() != NatureTerrain.EAU) {
            throw new WrongPositionException(this.getPosition());
        }
        this.setCurrentReservoir(this.getTailleReservoir());
    }
    
    public String getType() {
        return "DRONE";
    }
    
}
