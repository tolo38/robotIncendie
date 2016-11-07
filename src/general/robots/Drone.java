
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

    public Drone(Case position, int qteDeversee, int tempsRemplissage) {
        super(position, 10000, vitesseDrone, qteDeversee, tempsRemplissage);
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
    
   /* @Override
    public void seDeplacer(Direction direction) throws ForbiddenMoveException {
        if (deplacement interdit) {
            throw new ForbiddenMoveException(this.getPosition(), direction);
        }
        this.setPosition(this.getPosition().getVoisin(direction));
    }*/

    @Override
    public void seDeplacer(Direction direction) throws ForbiddenMoveException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
