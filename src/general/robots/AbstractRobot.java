
package general.robots;
/**
 *
 * @author 66
 */
import exceptions.*;
import general.Case;
import general.Direction;
import general.NatureTerrain;

public abstract class AbstractRobot {
    private Case position;
    private double vitesse;     // en km/h
    private int tailleReservoir;
    private int currentReservoir;
    private int qteDeversee;    // en 1 seconde
                                // à mettre à jour pr les drones
    private double tempsRemplissage;

    public AbstractRobot(Case position, int tailleReservoir, double vitesse, int
            qteDeversee, int tempsRemplissage) {
        this.position = position;
        this.tailleReservoir = tailleReservoir;
        this.vitesse = vitesse;
        this.qteDeversee = qteDeversee;
        this.tempsRemplissage = tempsRemplissage;
    }
    
    public Case getPosition() {
        return this.position;
    }
    
    public void setPosition(Case position) {
        this.position = position;
    }

    public int getTailleReservoir() {
        return this.tailleReservoir;
    }

    public void setCurrentReservoir(int currentReservoir) {
        this.currentReservoir = currentReservoir;
    }
    
    
    
    // à override ds les sous classes
    public double getVitesse(NatureTerrain nature) {
        return this.vitesse;
    }
    
    public void deverserEau(int vol) throws TankTooSmallException {
        if (vol > currentReservoir) {
            throw new TankTooSmallException(currentReservoir, vol);
        }
        currentReservoir -= vol;
    }
    
    abstract public void remplirReservoir() throws WrongPositionException;
    
    abstract public void seDeplacer(Direction direction) throws ForbiddenMoveException;
}
