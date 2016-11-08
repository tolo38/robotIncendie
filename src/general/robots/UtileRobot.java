
package general.robots;

import exceptions.ForbiddenMoveException;
import general.Case;
import general.Direction;
import general.DonneesSimulation;
import general.NatureTerrain;

/**
 *
 * @author 66
 */
public class UtileRobot {
    private static DonneesSimulation dataGame;

    static Case caseSuivante(Case position, Direction direction) throws ForbiddenMoveException {
        return dataGame.getCarte().getVoisin(position, direction);
    }
    
    private UtileRobot() {
        
    }

    public static void setDataGame(DonneesSimulation dataGame) {
        UtileRobot.dataGame = dataGame;
    }
    
    public static boolean aboveWater(Case position) {
        if (position.getNature() == NatureTerrain.EAU) return true;
        return false;
    }
    
    public static boolean nextToWater(Case position) {
        boolean waterCond = false;
        try {
            waterCond = (dataGame.getCarte().getVoisin(position, Direction.NORD).getNature() == NatureTerrain.EAU);
            waterCond = waterCond || (dataGame.getCarte().getVoisin(position, Direction.EST).getNature() == NatureTerrain.EAU);
            waterCond = waterCond || (dataGame.getCarte().getVoisin(position, Direction.SUD).getNature() == NatureTerrain.EAU);
            waterCond = waterCond || (dataGame.getCarte().getVoisin(position, Direction.OUEST).getNature() == NatureTerrain.EAU);
        } catch (ForbiddenMoveException e) {
            // on est au bord
        }
        return waterCond;
    }
    
}
