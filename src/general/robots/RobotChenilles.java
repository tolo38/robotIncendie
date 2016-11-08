
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
        return "CHENILLES";
    }
    
}
