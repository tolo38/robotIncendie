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
import general.robots.UtileRobot;
import gui.GUISimulator;
import java.awt.Color;
import java.util.LinkedList;
import plusCourtChemin.PlusCourtChemin;

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
        Carte carte = donneesSimulation.getCarte();
        PlusCourtChemin pcc = new PlusCourtChemin(carte, robot);
        double tempsOrdreMin = Double.MAX_VALUE;
        LinkedList<Double> versSource;
        LinkedList<Double> versIncendie;
        LinkedList<Double> versSourceMin = null;
        LinkedList<Double> versIncendieMin = null;
        Incendie incendieAEteindre = null;
        for (Incendie incendie : donneesSimulation.getIncendies()) {
            if (!incendie.eteint()) {  //parcoure sur les feu allumer
                for(int i =0; i < donneesSimulation.getCarte().getNbLignes();i++) {
                    for(int j =0 ; j<donneesSimulation.getCarte().getNbColonnes();j++) {
                        Case sourceCase = donneesSimulation.getCarte().getCase(i, j);
                        if(robot.isASourceCase(sourceCase)) {
                           //parcoure sur les case sources
                           //plus court chemin sur robot.getPosition() to sourceCase
                           versSource = pcc.getParcours(carte.getNumCase(robot.getPosition()),carte.getNumCase(sourceCase));
                           //plus court chemin sur sourceCase to incendie.getPosition()
                           versIncendie = pcc.getParcours(carte.getNumCase(sourceCase),carte.getNumCase(incendie.getPosition()));
                           //somme les temps 
                           double tps = versSource.removeFirst() + versIncendie.removeFirst();
                           //si temps inferieur au ordre precedent
                           if(tps < tempsOrdreMin) {
                               //alors l'ordre (liste des case de robot a source a incendit ok 
                               tempsOrdreMin = tps;
                               versSourceMin = (LinkedList<Double>) versSource.clone();
                               versIncendieMin = (LinkedList<Double>) versIncendie.clone();
                               incendieAEteindre = incendie;
                           }
                        }
                    }
                }  
             }
        }
        int t = date;
        LinkedList<Direction> DirSource = UtileRobot.numCaseToDirection(versSourceMin);
        for(Direction direction : DirSource) {
            donneesSimulation.addEvenement(new DeplacerRobot(t, robot, direction));
            t++;
        }
        donneesSimulation.addEvenement(new RemplirReservoir(t, robot));
        t++;
        LinkedList<Direction> DirIncedie = UtileRobot.numCaseToDirection(versIncendieMin);
        for(Direction direction : DirIncedie) {
            donneesSimulation.addEvenement(new DeplacerRobot(t, robot, direction));
            t++;
        }
        donneesSimulation.addEvenement(new Intervention(t, robot, incendieAEteindre,robot.getTailleReservoir()));
        t++;
        
        donneesSimulation.addEvenement(new OrdreChefPompier(robot));
    }
}
