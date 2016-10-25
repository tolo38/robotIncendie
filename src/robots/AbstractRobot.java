
package robots;

abstract class AbstractRobot {
    private Case position;
    private double vitesse;
    private int tailleReservoir;
    private int currentReservoir;
    private int qteDeversee;    // en 1 seconde
                                // à mettre à jour pr les drones
    private double tempsRemplissage;

    public AbstractRobot(Case position, int tailleReservoir, double vitesse, int qteDeversee, int tempsRemplissage) {
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
    
    // à override
    public double getVitesse(NatureTerrain nature) {
        return this.vitesse;
    }
    
    /*public void deverserEau(int vol) throws TankTooSmallException {
        if (vol > currentReservoir) {
            throw new EmptyTankException(currentReservoir, vol);
        }
        currentReservoir -= vol;
    }*/
}
