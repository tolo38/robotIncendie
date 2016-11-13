package general;

import general.evenements.Evenement;
import general.evenements.OrdreChefPompier;
import general.robots.AbstractRobot;
import general.robots.UtileRobot;
import io.CopieurDonnees;
import java.io.FileNotFoundException;
import java.util.zip.DataFormatException;

import java.awt.Color;
import java.util.ArrayList;

import gui.GUISimulator;
import gui.ImageElement;
import gui.Oval;
import gui.Rectangle;
import gui.Simulable;
import java.awt.Container;
import java.util.LinkedList;
import javax.swing.JPanel;

public class DonneesSimulation implements Simulable {
    private ArrayList<AbstractRobot> robots;
    private ArrayList<Incendie> incendies;
    private Carte carte;
    private GUISimulator gui;
    private Simulateur simulateur;
    private static final String DATA = "cartes/";
    private static final String RES = "res/";
    
    public DonneesSimulation(GUISimulator gui) {
        this.gui = gui;
        gui.setSimulable(this);
        robots = new ArrayList<AbstractRobot>();
        incendies = new ArrayList<Incendie>();
        carte = null;
        this.simulateur = new Simulateur();
        this.getData(DATA+"carteSujet.map");
        
        // pour la genericite
        UtileRobot.setDataGame(this);
        
        this.draw();
    }

    public DonneesSimulation(GUISimulator gui, String file) {
        this.gui = gui;
        gui.setSimulable(this);
        robots = new ArrayList<AbstractRobot>();
        incendies = new ArrayList<Incendie>();
        carte = null;
        this.simulateur = new Simulateur();
        this.getData(DATA+file);
        
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
        ArrayList<Evenement> listEvenement = simulateur.getListEvenements().get(simulateur.getDateSimulation());
        if(listEvenement != null) {
            for (Evenement e : listEvenement) {
                e.execute();
            }
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
        this.simulateur.setDateSImulation(0);
        this.simulateur.getListEvenements().clear();
        OrdreChefPompier.getChefPompier().debutMission();
                
        this.draw();
    }

    private void draw() {
        gui.reset();	// clear the window
        //int tailleCases = this.carte.getTailleCases();
        // affichage de la carte case par case
        int nbCol = this.carte.getNbColonnes();
        int nbLig = this.carte.getNbLignes();
        //JPanel[][] pan = new JPanel[nbLig][nbCol];
        Container pan = gui.getContentPane();
        for (int i = 0 ; i < nbCol ; i++) {
            for (int j = 0 ; j < nbLig ; j++) {
                //tabdraw[i][j] = new JPanel();
                gui.addGraphicalElement(new ImageElement(j * 50 , i * 50 , RES+"sol.jpg", 50, 50, pan));
                switch (this.carte.getCase(i, j).getNature()) {
                    case EAU :
                        //gui.addGraphicalElement(new Rectangle(j * 50 + 25, i * 50 + 25, Color.CYAN, Color.CYAN, 50));
                        gui.addGraphicalElement(new ImageElement(j * 50 , i * 50 ,RES+"eau.jpg" , 50, 50,pan));
                        break;
                    case FORET :
                        //gui.addGraphicalElement(new Rectangle(j * 50 + 25, i * 50 + 25, Color.GREEN, Color.GREEN, 50));
                        gui.addGraphicalElement(new ImageElement(j * 50 , i * 50 , RES+"foret.png", 50, 50, pan));
                        break;
                    case ROCHE :
                        //gui.addGraphicalElement(new Rectangle(j * 50 + 25, i * 50 + 25, Color.GRAY, Color.GRAY, 50));
                        gui.addGraphicalElement(new ImageElement(j * 50 , i * 50 , RES+"rocher.png", 50, 50, pan));
                        break;
                    case TERRAIN_LIBRE :
                        //gui.addGraphicalElement(new Rectangle(j * 50 + 25, i * 50 + 25, Color.WHITE, Color.WHITE, 50));
                        gui.addGraphicalElement(new ImageElement(j * 50 , i * 50 , RES+"sol.jpg", 50, 50, pan));
                        break;
                    case HABITAT :
                        //gui.addGraphicalElement(new Rectangle(j * 50 + 25, i * 50 + 25, Color.RED, Color.RED, 50));
                        gui.addGraphicalElement(new ImageElement(j * 50 , i * 50 , RES+"maisons.png", 50, 50, pan));
                        break;
                    case UNSET :
                        gui.addGraphicalElement(new Rectangle(j * 50 + 25, i * 50 + 25, Color.RED, Color.RED, 50));
                        break;
                }
            }
        }
        
        // affichage des incendies
        for (Incendie incendie : this.incendies) {
            // System.out.println(robot.getTailleReservoir());
            int i = incendie.getLigne();
            int j = incendie.getColonne();
            gui.addGraphicalElement(new ImageElement(j * 50 , i * 50 , RES+"0feu.png", 50, 50, pan));
            if(incendie.eteint()) {
                gui.addGraphicalElement(new ImageElement(j * 50 , i * 50 , RES+"0feu.png", 50, 50, pan));
            } else if(incendie.getEauNecessaire() <= 10000) {
                //gui.addGraphicalElement(new Oval(incendie.getColonne() * 50 + 15, incendie.getLigne() * 50 + 25, Color.orange, Color.orange, 20));
                gui.addGraphicalElement(new ImageElement(j * 50 , i * 50 , RES+"1feu.png", 50, 50, pan));
            } else if(incendie.getEauNecessaire() <= 40000) {
                gui.addGraphicalElement(new ImageElement(j * 50 , i * 50 , RES+"2feu.png", 50, 50, pan));
            } else {
                gui.addGraphicalElement(new ImageElement(j * 50 , i * 50 , RES+"3feu.png", 50, 50, pan));
            }
        }
        
        // affichage des robots
        for (AbstractRobot robot : this.robots) {
            int i = robot.getPosition().getLigne();
            int j = robot.getPosition().getColonne();
            switch (robot.getType()) {
                case "DRONE" :
                    //gui.addGraphicalElement(new Oval(robot.getPosition().getColonne() * 50 + 35, robot.getPosition().getLigne() * 50 + 25, Color.BLUE, Color.MAGENTA, 20));
                    gui.addGraphicalElement(new ImageElement(j * 50 +5 , i * 50 +5 , RES+"drone.png", 25, 25, pan));
                    break;
                case "ROUES" :
                    //gui.addGraphicalElement(new Oval(robot.getPosition().getColonne() * 50 + 35, robot.getPosition().getLigne() * 50 + 25, Color.WHITE, Color.MAGENTA, 20));
                    gui.addGraphicalElement(new ImageElement(j * 50 +20, i * 50 +5 , RES+"roue.png", 25, 25, pan));
                    break;
                case "PATTES" :
                    //gui.addGraphicalElement(new Oval(robot.getPosition().getColonne() * 50 + 35, robot.getPosition().getLigne() * 50 + 25, Color.BLACK, Color.MAGENTA, 20));
                    gui.addGraphicalElement(new ImageElement(j * 50 +5, i * 50 +20 , RES+"robotpatte.png", 25, 25, pan));
                    break;
                case "CHENILLES" :
                    //gui.addGraphicalElement(new Oval(robot.getPosition().getColonne() * 50 + 35, robot.getPosition().getLigne() * 50 + 25, Color.GREEN, Color.MAGENTA, 20));
                    gui.addGraphicalElement(new ImageElement(j * 50 +20, i * 50 +20, RES+"chenille.png", 25, 25, pan));
                    break;
            }
        }        
    }
    
}
