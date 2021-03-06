
package test;

import general.Direction;
import gui.GUISimulator;
import gui.Simulable;
import java.awt.Color;
import general.DonneesSimulation;
import general.evenements.DeplacerRobot;
import general.evenements.RemplirReservoir;
import general.robots.AbstractRobot;


/**
 *
 * @author 66
 */
public class TestScenario0 {
    
    public static void main(String[] args) {
        // crée la fenêtre graphique dans laquelle dessiner
        GUISimulator gui = new GUISimulator(1000, 1000, Color.BLACK);
        DonneesSimulation donneesSimulation = new DonneesSimulation(gui);
        // bourrins pour tester
        AbstractRobot robot = donneesSimulation.getRobots().get(0);
        donneesSimulation.addEvenement(new DeplacerRobot(1, robot, Direction.NORD));
        donneesSimulation.addEvenement(new DeplacerRobot(2, robot, Direction.NORD));
        donneesSimulation.addEvenement(new DeplacerRobot(3, robot, Direction.NORD));
        donneesSimulation.addEvenement(new DeplacerRobot(4, robot, Direction.NORD));
    }
}
