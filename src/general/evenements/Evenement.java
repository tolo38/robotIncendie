
package general.evenements;

import general.DonneesSimulation;

/**
 *
 * @author 66
 */
public abstract class Evenement {
    private long date;
    
    public Evenement(long date) {
        this.date = date;
    }

    public long getDate() {
        return date;
    }
    
    public abstract void execute();
    
    abstract public void reset();
}
