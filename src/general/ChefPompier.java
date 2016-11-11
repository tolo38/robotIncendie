/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package general;

import general.evenements.OrdreChefPompier;
import general.evenements.DeplacerRobot;
import general.evenements.Intervention;
import general.evenements.RemplirReservoir;
import general.robots.AbstractRobot;
import gui.GUISimulator;
import java.awt.Color;

/**
 *
 * @author thomas
 */
public class ChefPompier {
    
    DonneesSimulation donneesSimulation;
        
        public ChefPompier(DonneesSimulation donneesSimulation) {
            this.donneesSimulation = donneesSimulation;
            debutMission();
        }
        
        public void debutMission() {
            OrdreChefPompier.setChefPompier(this);
            for (AbstractRobot robot : donneesSimulation.getRobots()) {
                ordrePourRobot(robot,0);
                
            }
        }

    private void ordrePourRobot(AbstractRobot robot, int date) {
        //calcule de l'ordre
        int tempsOrdreMin = Integer.MAX_VALUE;
        for (Incendie incendie : donneesSimulation.getIncendies()) {
            if (incendie.eteint()) {  //parcoure sur les feu allumer
                for(int i =0; i < donneesSimulation.getCarte().getNbLignes();i++) {
                    for(int j =0 ; j<donneesSimulation.getCarte().getNbColonnes();j++) {
                        Case sourceCase = donneesSimulation.getCarte().getCase(i, j);
                        if(robot.isASourceCase(sourceCase)) {
                           //parcoure sur les case sources
                           //plus court chemin sur robot.getPosition() to sourceCase
                           //plus court chemin sur sourceCase to incendie.getPosition()
                           //somme les temps 
                           int tps = 0;
                           //si temps inferieur au ordre precedent
                           if(tps < tempsOrdreMin) {
                               //alors l'ordre (liste des case de robot a source a incendit ok 
                           }
                        }
                    }
                }  
             }
        }
        int t = date;
        for(Case sCase : cheminSource) {
            donneesSimulation.addEvenement(new DeplacerRobot(t, robot, Direction.NORD));
            t++;
        }
        donneesSimulation.addEvenement(new RemplirReservoir(t, robot));
        t++;
        for(Case iCase : cheminIncendie) {
            donneesSimulation.addEvenement(new DeplacerRobot(t, robot, Direction.NORD));
            t++;
        }
        donneesSimulation.addEvenement(new Intervention(t, robot, incendie,robot.getTailleReservoir()));
        t++;
        
        donneesSimulation.addEvenement(new OrdreChefPompier(robot));
    }
}
