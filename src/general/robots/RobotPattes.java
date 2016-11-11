
package general.robots;

import exceptions.ForbiddenMoveException;
import exceptions.WrongPositionException;
import general.Case;
import general.Direction;
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
     */
    @Override
    public void remplirReservoir() {
    }
    
    public String getType() {
        return "PATTES";
    }

    @Override
    public void deplacerRobot(Direction direction) {
        try {
            Case caseSuivante = UtileRobot.caseSuivante(this.getPosition(), direction);
            if (caseSuivante.getNature() == NatureTerrain.EAU)
                throw new ForbiddenMoveException(getPosition(), direction);
            this.setPosition(caseSuivante);
        } catch (ForbiddenMoveException ex) {
            System.out.println("Forbidden move : " + this + "\nLocation: " + this.getPosition() + " can't move " + direction);
        }
    }

    @Override
    public boolean isASourceCase(Case sCase) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
