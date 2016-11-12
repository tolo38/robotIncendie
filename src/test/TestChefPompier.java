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
import general.evenements.Intervention;
import general.evenements.RemplirReservoir;
import general.robots.AbstractRobot;
import gui.GUISimulator;
import java.awt.Color;

/**
 *
 * @author 66
 */

public class TestChefPompier {

    public static void main(String[] args) {
        // crée la fenêtre graphique dans laquelle dessiner
        GUISimulator gui = new GUISimulator(1000, 1000, Color.BLACK);
        DonneesSimulation donneesSimulation = new DonneesSimulation(gui);
        new ChefPompier(donneesSimulation);
    }
}