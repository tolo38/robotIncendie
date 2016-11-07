
package test;

import gui.GUISimulator;
import gui.Simulable;
import java.awt.Color;
import general.DonneesSimulation;


/**
 *
 * @author 66
 */
public class TestRobots {
    
    public static void main(String[] args) {
        // crée la fenêtre graphique dans laquelle dessiner
        GUISimulator gui = new GUISimulator(1000, 1000, Color.BLACK);
        DonneesSimulation donneesSimulation = new DonneesSimulation(gui);
    }
}
