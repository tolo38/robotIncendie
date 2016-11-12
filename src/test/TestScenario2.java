
package test;

import general.Direction;
import gui.GUISimulator;
import gui.Simulable;
import java.awt.Color;
import general.DonneesSimulation;
import general.evenements.DeplacerRobot;
import general.evenements.InterventionUnitaire;
import general.evenements.RemplirReservoir;
import general.robots.AbstractRobot;


/**
 *
 * @author 66
 */
public class TestScenario2 {
    
    public static void main(String[] args) {
        // crée la fenêtre graphique dans laquelle dessiner
        GUISimulator gui = new GUISimulator(1000, 1000, Color.BLACK);
        DonneesSimulation donneesSimulation = new DonneesSimulation(gui);
        // bourrins pour tester
        AbstractRobot robot = donneesSimulation.getRobots().get(0);
        donneesSimulation.addEvenement(new RemplirReservoir(1, robot));
        donneesSimulation.addEvenement(new DeplacerRobot(2, robot, Direction.NORD));
        donneesSimulation.addEvenement(new DeplacerRobot(3, robot, Direction.EST));
        donneesSimulation.addEvenement(new DeplacerRobot(4, robot, Direction.EST));
        donneesSimulation.addEvenement(new DeplacerRobot(5, robot, Direction.EST));
        donneesSimulation.addEvenement(new DeplacerRobot(6, robot, Direction.EST));
        int i;
        for(i=0;robot.getPosition() == donneesSimulation.getIncendies().get(i).getPosition();i++) ;
        for(int j = 0 ; j<4 ; j++) {
            donneesSimulation.addEvenement(new InterventionUnitaire(7+4*j, robot, donneesSimulation.getIncendies().get(i)));
            donneesSimulation.addEvenement(new DeplacerRobot(8+4*j, robot, Direction.OUEST));
            System.out.println("la nature du terran :" + robot.getPosition().getNature());
            donneesSimulation.addEvenement(new RemplirReservoir(9+4*j, robot));
            donneesSimulation.addEvenement(new DeplacerRobot(10+4*j, robot, Direction.EST));
        }
    }
    
}
