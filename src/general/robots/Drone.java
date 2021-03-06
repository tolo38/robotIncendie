
package general.robots;

import general.robots.AbstractRobot;
import exceptions.ForbiddenMoveException;
import exceptions.TankTooSmallException;
import exceptions.WrongPositionException;
import exceptions.WrongVitesseException;
import general.Carte;
import general.Case;
import general.Direction;
import general.NatureTerrain;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import plusCourtChemin.Chemin;

/**
 *
 * @author 66
 */
public class Drone extends AbstractRobot {
    
    private static final double DEFAUTVITESSE = 100;
    private static final long TEMPSREMPLISSAGE = 30* 60;
    private static final int TAILLERESERVOIR = 10000;
    private static final int QUNITAIREDEVERSER = 10000;
    private static final int TUNITAIREDEVERSER = 30;

    /**
     * tailleReservoir = 10000L
     * @param position
     * @param qteDeversee
     * @param tempsRemplissage
     */
    public Drone(Case position) {
        super(position, TAILLERESERVOIR, DEFAUTVITESSE, QUNITAIREDEVERSER, TEMPSREMPLISSAGE, TUNITAIREDEVERSER);
    }
    
    
    public Drone(Case position, double vitesse) {
        super(position, TAILLERESERVOIR, vitesse, QUNITAIREDEVERSER, TEMPSREMPLISSAGE, TUNITAIREDEVERSER);
        try{
            vitesseAutorise(vitesse);
        }catch(WrongVitesseException e){
            System.out.println("Error : Vitesse négative "+e.getVitesse());
        }
            
            
        
    }
    
    
    
    public void vitesseAutorise(double vitesse) throws WrongVitesseException{
        if(vitesse<0 && vitesse>151){
            throw new WrongVitesseException(vitesse);
        }
    }
    
    @Override
    public void remplirReservoir() throws WrongPositionException {
        boolean allowed = UtileRobot.aboveWater(this.getPosition());
        System.out.println("allowed : " +this.getPosition().getNature());
        if (allowed) {
            super.remplirReservoir();
        } else throw new WrongPositionException(this.getPosition());
    }
    
    
    public String getType() {
        return "DRONE";
    }

    @Override
    public void deplacerRobot(Direction direction) {
        try {
            Case caseSuivante = UtileRobot.caseSuivante(this.getPosition(), direction);
            this.setPosition(caseSuivante);
        } catch (ForbiddenMoveException ex) {
            System.out.println("Forbidden move : " + this + "\nLocation: " + this.getPosition() + "can't move " + direction);
        }
    }

    @Override
    public boolean isASourceCase(Case sCase) {
        return (sCase.getNature() == NatureTerrain.EAU);
    }
    
    
    /*
        Construction du graphe pour le Drone:
        Vitesse uniforme sur toutes les cases : 100km/h
    
    */
    @Override
    public HashMap<Integer, LinkedList<Chemin>> initGrapheRobot(Carte carte) {
        HashMap<Integer, LinkedList<Chemin>> graphe = new HashMap<Integer, LinkedList<Chemin>>();
        // graphe : tableau contenant, pour chaque case, la liste des cases atteignables
        // graphe : tableau de Nbcolonnes*NbLignes
        for(int i=0; i<carte.getNbLignes(); i++) {
            for(int j=0; j<carte.getNbColonnes(); j++) {
                // chemins : liste des cases atteignables
                LinkedList chemins = new LinkedList<Chemin>();
                
                // Traitement des Nord / Sud
                if(i==0){
                    // Sur la premiere ligne : Nord impossible
                    // On ajoute le deplacement Sud
                    chemins.add(new Chemin((i+1)*carte.getNbLignes()+j, super.getVitesse(), carte.getTailleCases()));
                }else if(i==carte.getNbLignes()-1){
                    // Sur la derniere ligne : Sud impossible
                    // On ajoute le deplacement Nord
                    chemins.add(new Chemin((i-1)*carte.getNbLignes()+j, super.getVitesse(), carte.getTailleCases()));
                }else{
                    // On ajoute les deplacements Nord et Sud
                    chemins.add(new Chemin((i-1)*carte.getNbLignes()+j, super.getVitesse(), carte.getTailleCases()));
                    chemins.add(new Chemin((i+1)*carte.getNbLignes()+j, super.getVitesse(), carte.getTailleCases()));
                }
                // Traitement des Est / Ouest
                if(j==0){
                    // Sur la premiere colonne : Ouest impossible
                    // On ajoute le deplacement Est
                    chemins.add(new Chemin(i*carte.getNbLignes()+j+1, super.getVitesse(), carte.getTailleCases()));
                }else if(j==carte.getNbColonnes()-1){
                    // Sur la derniere colonne : Est impossible
                    // On ajoute le deplacement Ouest
                    chemins.add(new Chemin(i*carte.getNbLignes()+j-1, super.getVitesse(), carte.getTailleCases()));
                }else{
                    // On ajoute les deplacements Est et Ouest
                    chemins.add(new Chemin(i*carte.getNbLignes()+j+1, super.getVitesse(), carte.getTailleCases()));
                    chemins.add(new Chemin(i*carte.getNbLignes()+j-1, super.getVitesse(), carte.getTailleCases()));
                }
                graphe.put(i*carte.getNbLignes()+j, chemins);
            }
        }
        return graphe;
    }

    @Override
    public double getVitesse(Case c) {
        return super.getVitesse();
    }
    
    @Override
    public boolean testCaseValid(Case cases) {
        return true;
    }
    
}
