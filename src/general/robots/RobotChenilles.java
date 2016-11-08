
package general.robots;

import exceptions.WrongPositionException;
import general.Case;
import general.NatureTerrain;

/**
 *
 * @author 66
 */
public class RobotChenilles extends AbstractRobot {
    
    private static double vitesseRobotChenilles = 60;

    /**
     * tailleReservoir : 2000L
     * qteDeversee : 100L en 8 secondes
     * tempsRemplissage : 5 minutes
     * @param position
     */
    public RobotChenilles(Case position) {
        super(position, 2000, vitesseRobotChenilles, 100 / 8, 5 * 60);
    }
    
    public RobotChenilles(Case position, double vitesse) {
        super(position, 2000, vitesse, 100 / 8, 5 * 60);
    }
    
    @Override
    public double getVitesse(NatureTerrain nature) {
        if (nature == NatureTerrain.FORET)
            return this.getVitesse() / 2;
        return this.getVitesse();
    }
    
    @Override
    public void remplirReservoir() throws WrongPositionException {
        boolean allowed = UtileRobot.nextToWater(this.getPosition());
        if (allowed) {
            super.remplirReservoir();
        }
        else throw new WrongPositionException(this.getPosition());
    }
    
    
    public String getType() {
        return "CHENILLES";
    }
    
}
