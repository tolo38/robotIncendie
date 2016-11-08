
package general.robots;

import general.DonneesSimulation;

/**
 *
 * @author 66
 */
public class UtileRobot {
    private static DonneesSimulation dataGame;
    
    private UtileRobot() {
        
    }

    public static void setDataGame(DonneesSimulation dataGame) {
        UtileRobot.dataGame = dataGame;
    }
    
    /**
     *
     * @return
     */
    public static String tooString() {
        return dataGame.getCarte().getCase(0, 0).getNature().toString() + "euigvfrlhbfvrekjfizerbfieyrfguy";
    }
    
    
    
}
