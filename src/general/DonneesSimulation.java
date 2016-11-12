package general;

import general.evenements.Evenement;
import general.robots.AbstractRobot;
import general.robots.UtileRobot;
import io.CopieurDonnees;
import java.io.FileNotFoundException;
import java.util.zip.DataFormatException;

import java.awt.Color;
import java.util.ArrayList;

import gui.GUISimulator;
import gui.Oval;
import gui.Rectangle;
import gui.Simulable;

public class DonneesSimulation implements Simulable {
    private ArrayList<AbstractRobot> robots;
    private ArrayList<Incendie> incendies;
    private Carte carte;
    private GUISimulator gui;
    private Simulateur simulateur;
    
    public DonneesSimulation(GUISimulator gui) {
        this.gui = gui;
        gui.setSimulable(this);
        robots = new ArrayList<AbstractRobot>();
        incendies = new ArrayList<Incendie>();
        carte = null;
        this.simulateur = new Simulateur();
        this.getData("cartes/carteSujet.map");
        
        // pour la genericite
        UtileRobot.setDataGame(this);
        
        this.draw();
    }

    public void addEvenement(Evenement e) {
        this.simulateur.addEvenement(e);
    }
    
    public ArrayList<AbstractRobot> getRobots() {
        return robots;
    }

    public ArrayList<Incendie> getIncendies() {
        return incendies;
    }

    public Carte getCarte() {
        return carte;
    }

    public Simulateur getSimulateur() {
        return simulateur;
    }
    
    public void createCarte(int nbLignes, int nbColonnes, int tailleC){
        carte = new Carte(nbLignes, nbColonnes, tailleC);
    }
    
    public void setNatureTerrain(int ligne, int colonne, NatureTerrain terrain) {
        carte.getCase(ligne, colonne).setNatureTerrain(terrain);
    }
    
    public void getData(String fileName){
        if (fileName.isEmpty()) {
            System.out.println("Syntaxe: java TestLecteurDonnees <nomDeFichier>");
            System.exit(1);
        }
        try {
            CopieurDonnees.copier(fileName, this);
        } catch (FileNotFoundException e) {
            System.out.println("fichier " + fileName + " inconnu ou illisible");
        } catch (DataFormatException e) {
            System.out.println("\n\t**format du fichier " + fileName + " invalide: " + e.getMessage());
        }
    }
    
    

    @Override
    public void next() {
        // si la simulation est terminée on ne fait rien
        if (simulateur.simulationTerminee()) return;
        
        this.simulateur.incrementeDate();
        for (Evenement e : simulateur.getListEvenements().get(simulateur.getDateSimulation())) {
            e.execute();
        }
        draw();
    }

    @Override
    public void restart() {
        this.simulateur.setDateSImulation(0);
        for (AbstractRobot robot : robots) {
            robot.reset();
        }
        for (Incendie incendie : incendies) {
            incendie.reset();
        }
      
        
        this.draw();
    }

    private void draw() {
        gui.reset();	// clear the window
        //int tailleCases = this.carte.getTailleCases();
        // affichage de la carte case par case
        for (int i = 0 ; i < this.carte.getNbColonnes() ; i++) {
            for (int j = 0 ; j < this.carte.getNbLignes() ; j++) {
                switch (this.carte.getCase(i, j).getNature()) {
                    case EAU :
                        gui.addGraphicalElement(new Rectangle(j * 50 + 25, i * 50 + 25, Color.CYAN, Color.CYAN, 50));
                        break;
                    case FORET :
                        gui.addGraphicalElement(new Rectangle(j * 50 + 25, i * 50 + 25, Color.GREEN, Color.GREEN, 50));
                        break;
                    case ROCHE :
                        gui.addGraphicalElement(new Rectangle(j * 50 + 25, i * 50 + 25, Color.GRAY, Color.GRAY, 50));
                        break;
                    case TERRAIN_LIBRE :
                        gui.addGraphicalElement(new Rectangle(j * 50 + 25, i * 50 + 25, Color.WHITE, Color.WHITE, 50));
                        break;
                    case HABITAT :
                        gui.addGraphicalElement(new Rectangle(j * 50 + 25, i * 50 + 25, Color.RED, Color.RED, 50));
                        break;
                    case UNSET :
                        gui.addGraphicalElement(new Rectangle(j * 50 + 25, i * 50 + 25, Color.BLACK, Color.BLACK, 50));
                        break;
                }
            }
        }
        
        // affichage des incendies
        for (Incendie incendie : this.incendies) {
            // System.out.println(robot.getTailleReservoir());
            gui.addGraphicalElement(new Oval(incendie.getColonne() * 50
                    + 15, incendie.getLigne() * 50 + 25, Color.orange, Color.orange, 20));
        }
        
        // affichage des robots
        for (AbstractRobot robot : this.robots) {
            switch (robot.getType()) {
                case "DRONE" :
                    gui.addGraphicalElement(new Oval(robot.getPosition().getColonne() * 50
                    + 35, robot.getPosition().getLigne() * 50 + 25, Color.BLUE, Color.MAGENTA, 20));
                    break;
                case "ROUES" :
                    gui.addGraphicalElement(new Oval(robot.getPosition().getColonne() * 50
                    + 35, robot.getPosition().getLigne() * 50 + 25, Color.WHITE, Color.MAGENTA, 20));
                    break;
                case "PATTES" :
                    gui.addGraphicalElement(new Oval(robot.getPosition().getColonne() * 50
                    + 35, robot.getPosition().getLigne() * 50 + 25, Color.BLACK, Color.MAGENTA, 20));
                    break;
                case "CHENILLES" :
                    gui.addGraphicalElement(new Oval(robot.getPosition().getColonne() * 50
                    + 35, robot.getPosition().getLigne() * 50 + 25, Color.GREEN, Color.MAGENTA, 20));
                    break;
            }
        }        
    }
    
}
