
package general.robots;
/**
 *
 * @author 66
 */
import exceptions.*;
import general.Carte;
import general.Case;
import general.Direction;
import general.NatureTerrain;
import general.robots.UtileRobot;
import java.util.HashMap;
import java.util.LinkedList;
import plusCourtChemin.Chemin;

public abstract class AbstractRobot {
    private Case position;
    private double vitesse;     // en km/h
    private int tailleReservoir;
    private int currentReservoir;
    private double qteDeversee;    // en 1 seconde
                                // à mettre à jour pr les drones
    private long tempsRemplissage;
    
    private Case initPosition;

    /**
     *
     * @param position
     * @param tailleReservoir
     * @param vitesse
     * @param qteDeversee
     * @param tempsRemplissage
     */
    public AbstractRobot(Case position, int tailleReservoir, double vitesse, double
            qteDeversee, long tempsRemplissage) {
        this.position = position;
        this.initPosition = position;
        this.tailleReservoir = tailleReservoir;
        this.vitesse = vitesse;
        this.qteDeversee = qteDeversee;
        this.tempsRemplissage = tempsRemplissage;
        this.currentReservoir = this.tailleReservoir;
    }
    
    public Case getPosition() {
        return this.position;
    }
    
    public double getVitesse() {
        return this.vitesse;
    }
    
    public void setPosition(Case position) {
        this.position = position;
    }

    public int getTailleReservoir() {
        return this.tailleReservoir;
    }

    public long getTempsRemplissage() {
        return tempsRemplissage;
    }

    public void setCurrentReservoir(int currentReservoir) {
        this.currentReservoir = currentReservoir;
    }
    
    public double getVitesse(NatureTerrain nature) {
        return this.vitesse;
    }
    
    public void deverserEau(int vol) throws TankTooSmallException {
        if (vol > currentReservoir) {
            throw new TankTooSmallException(currentReservoir, vol);
        }
        currentReservoir -= vol;
    }
    
    abstract public boolean isASourceCase(Case sCase);
    
    public void remplirReservoir() throws WrongPositionException {
        this.setCurrentReservoir(this.getTailleReservoir());
    }
    
    abstract public String getType();
    
    @Override
    public String toString() {
        return "Robot " + this.getType() + " positionné en " + this.getPosition().toString();
    }

    abstract public void deplacerRobot(Direction direction);

    public void reset() {
        position = initPosition;
        currentReservoir = tailleReservoir;
    }
    
    abstract public HashMap<Integer, LinkedList<Chemin>> initGrapheRobot(Carte carte);
    
}
