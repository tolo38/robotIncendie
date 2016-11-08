
package general.robots;

import exceptions.WrongPositionException;
import general.Case;
import general.NatureTerrain;

/**
 *
 * @author 66
 */
public class RobotRoues extends AbstractRobot {
    
    private static double vitesseRobotRoues = 80;

    /**
     * tailleReservoir : 5000L
     * qteDeversee : 100L en 5 secondes
     * tempsRemplissage : 10 minutes
     * @param position
     */
    public RobotRoues(Case position) {
        super(position, 5000, vitesseRobotRoues, 20, 10 * 60);
    }
    
    public RobotRoues(Case position, double vitesse) {
        super(position, 5000, vitesse, 20, 10 * 60);
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
        return "ROUES";
    }
    
}
