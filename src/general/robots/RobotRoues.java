
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
    
    @Override
    public void remplirReservoir() throws WrongPositionException {
        boolean allowed = UtileRobot.nextToWater(this.getPosition());
        if (allowed) {
            super.remplirReservoir();
        }
        else throw new WrongPositionException(this.getPosition());
    }
    
    public String getType() {
        return "ROUES";
    }

    @Override
    public void deplacerRobot(Direction direction) {
        try {
            Case caseSuivante = UtileRobot.caseSuivante(this.getPosition(), direction);
            if (caseSuivante.getNature() != NatureTerrain.TERRAIN_LIBRE && caseSuivante.getNature() != NatureTerrain.HABITAT)
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
