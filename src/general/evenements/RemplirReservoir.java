
package general.evenements;

import exceptions.ForbiddenMoveException;
import exceptions.WrongPositionException;
import general.Direction;
import general.DonneesSimulation;
import general.NatureTerrain;
import general.robots.AbstractRobot;
import general.robots.Drone;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 66
 */
public class RemplirReservoir extends Evenement {
    private AbstractRobot robot;

    public RemplirReservoir(long date, DonneesSimulation dataGame, AbstractRobot robot) {
        super(date, dataGame);
        this.robot = robot;
    }

    @Override
    public void execute() {
        try {
            robot.remplirReservoir();
            getDataGame().addEvenement(new FinRemplissage(getDate() + robot.getTempsRemplissage(), getDataGame(), robot));
        } catch (WrongPositionException ex) {
            System.out.println("on ne peut pas remplir un " + robot.getType() + " ici" );
        }
    }
    
}
