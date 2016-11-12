package test;

import general.Case;
import general.DonneesSimulation;
import general.NatureTerrain;
import static general.NatureTerrain.EAU;
import general.robots.Drone;
import general.robots.RobotChenilles;
import general.robots.RobotPattes;
import general.robots.RobotRoues;
import plusCourtChemin.PlusCourtChemin;
import gui.GUISimulator;
import java.awt.Color;
import java.util.LinkedList;



/**
 *
 * @author 66
 */
public class TestPlusCourtChemin {
    public static void main(String[] args) {
        GUISimulator gui = new GUISimulator(800, 600, Color.BLACK);
        DonneesSimulation dataGame = new DonneesSimulation(gui);
        
        
        PlusCourtChemin pluscourtchemin = new PlusCourtChemin(dataGame.getCarte(), new Drone(new Case(0, 0, NatureTerrain.EAU), 100, 100, 100));
        LinkedList<Integer> listCases = new LinkedList<Integer>();
        listCases = pluscourtchemin.getParcours(0, 43);
        pluscourtchemin.afficheParcours(listCases);
        
        PlusCourtChemin pluscourtchemin2 = new PlusCourtChemin(dataGame.getCarte(), new RobotChenilles(new Case(0, 0, NatureTerrain.EAU), 100));
        LinkedList<Integer> listCases2 = new LinkedList<Integer>();
        listCases2 = pluscourtchemin2.getParcours(0, 43);
        pluscourtchemin2.afficheParcours(listCases2);
        
        PlusCourtChemin pluscourtchemin3 = new PlusCourtChemin(dataGame.getCarte(), new RobotPattes(new Case(0, 0, NatureTerrain.EAU)));
        LinkedList<Integer> listCases3 = new LinkedList<Integer>();
        listCases3 = pluscourtchemin3.getParcours(0, 43);
        pluscourtchemin3.afficheParcours(listCases3);
        
        PlusCourtChemin pluscourtchemin4 = new PlusCourtChemin(dataGame.getCarte(), new RobotRoues(new Case(0, 0, NatureTerrain.EAU), 100));
        LinkedList<Integer> listCases4 = new LinkedList<Integer>();
        listCases4 = pluscourtchemin4.getParcours(0, 43);
        pluscourtchemin4.afficheParcours(listCases4);
        
        
        
        
    }
    
}
