
package test;

import general.Direction;
import gui.GUISimulator;
import gui.Simulable;
import java.awt.Color;
import general.DonneesSimulation;
import general.evenements.DeplacerRobot;
import general.evenements.RemplirReservoir;


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
        donneesSimulation.addEvenement(new DeplacerRobot(1, donneesSimulation.getRobots().get(0), Direction.NORD));
        donneesSimulation.addEvenement(new DeplacerRobot(2, donneesSimulation.getRobots().get(0), Direction.NORD));
        donneesSimulation.addEvenement(new DeplacerRobot(3, donneesSimulation.getRobots().get(0), Direction.NORD));
        donneesSimulation.addEvenement(new DeplacerRobot(4, donneesSimulation.getRobots().get(0), Direction.NORD));
    }
}
