/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import general.ChefPompier;
import general.Direction;
import general.DonneesSimulation;
import general.evenements.DeplacerRobot;
import general.evenements.InterventionUnitaire;
import general.evenements.RemplirReservoir;
import general.robots.AbstractRobot;
import gui.GUISimulator;
import io.LecteurDonnees;
import java.awt.Color;
import java.io.FileNotFoundException;
import java.util.zip.DataFormatException;

/**
 *
 * @author 66
 */

public class TestChefPompier {

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Syntaxe: java TestLecteurDonnees <nomDeFichier>");
            System.exit(1);
        }
        // crée la fenêtre graphique dans laquelle dessiner
        GUISimulator gui = new GUISimulator(1000, 1000, Color.GRAY);
        //DonneesSimulation donneesSimulation = new DonneesSimulation(gui,"carteSujet.map");
        //DonneesSimulation donneesSimulation = new DonneesSimulation(gui,"desertOfDeath-20x20.map");
        //DonneesSimulation donneesSimulation = new DonneesSimulation(gui,"mushroomOfHell-20x20.map");
        //DonneesSimulation donneesSimulation = new DonneesSimulation(gui,"spiralOfMadness-50x50.map");
        DonneesSimulation donneesSimulation = new DonneesSimulation(gui,args[0]);
        new ChefPompier(donneesSimulation);
    }
}