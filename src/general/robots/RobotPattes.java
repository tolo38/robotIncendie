
package general.robots;

import exceptions.WrongPositionException;
import general.Case;
import general.NatureTerrain;

/**
 *
 * @author 66
 */
public class RobotPattes extends AbstractRobot {
    
    private static double vitesseRobotPattes = 30;

    /**
     * tailleReservoir : infinie
     * qteDeversee : 10L en 1 seconde
     * tempsRemplissage : 0
     * @param position
     */
    public RobotPattes(Case position) {
        super(position, Integer.MAX_VALUE, vitesseRobotPattes, 10, 0);
    }
    
    @Override
    public double getVitesse(NatureTerrain nature) {
        if (nature == NatureTerrain.ROCHE)
            return this.getVitesse() - 10;
        return this.getVitesse();
    }
    
    /**
     * Un robot à pattes ne se remplit jamais
     * @throws exceptions.WrongPositionException
     */
    @Override
    public void remplirReservoir() throws WrongPositionException {
        throw new WrongPositionException(this.getPosition());
    }
    
    public String getType() {
        return "PATTES";
    }
    
}
