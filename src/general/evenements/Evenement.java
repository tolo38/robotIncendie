
package general.evenements;

import general.DonneesSimulation;

/**
 *
 * @author 66
 */
public abstract class Evenement {
    private long date;
    private DonneesSimulation dataGame;
    
    public Evenement(long date, DonneesSimulation dataGame) {
        this.date = date;
        this.dataGame = dataGame;
    }

    public long getDate() {
        return date;
    }

    public DonneesSimulation getDataGame() {
        return dataGame;
    }
    
    public abstract void execute();
}
