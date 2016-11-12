
package general.robots;

import exceptions.ForbiddenMoveException;
import general.Case;
import general.Direction;
import general.DonneesSimulation;
import general.NatureTerrain;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;

/**
 *
 * @author 66
 */
public class UtileRobot {
    private static DonneesSimulation dataGame;

    private UtileRobot() {
        
    }
    
    public static double getTempsTraverser(AbstractRobot robot) {
        return dataGame.getCarte().getTailleCases()/robot.getVitesse(robot.getPosition());
    }
    
    static Case caseSuivante(Case position, Direction direction) throws ForbiddenMoveException {
        return dataGame.getCarte().getVoisin(position, direction);
    }

    public static LinkedList<Direction> numCaseToDirection(LinkedList<Double> doubleList) {
        LinkedList<Direction> DirList = new LinkedList<Direction>();
        ListIterator<Double> it = doubleList.listIterator();
        while(it.hasNext()) {
            double numCase = it.next();
            if(it.hasNext()) {
                double numCaseNext = it.next();
                it.previous();
                switch ((int)(numCaseNext - numCase)) {
                    case 1 :
                        //EST
                        DirList.addLast(Direction.EST);
                        break;
                    case -1 :
                        //OUEST
                        DirList.addLast(Direction.OUEST);
                        break;
                    default :
                        if(numCaseNext<numCase){
                            //Nord
                            DirList.addLast(Direction.NORD);
                        }
                        else{
                            //SUD
                            DirList.addLast(Direction.SUD);
                        }
                }
            }
        }
        return DirList;
    }
    

    public static void setDataGame(DonneesSimulation dataGame) {
        UtileRobot.dataGame = dataGame;
    }

    public static DonneesSimulation getDataGame() {
        return dataGame;
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
