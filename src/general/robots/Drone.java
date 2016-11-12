
package general.robots;

import general.robots.AbstractRobot;
import exceptions.ForbiddenMoveException;
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
    
    private static double vitesseDrone = 100;
    public static final long tempsRemplissage = 30 * 60;

    /**
     * tailleReservoir = 10000L
     * @param position
     * @param qteDeversee
     * @param tempsRemplissage
     */
    public Drone(Case position, double qteDeversee, long tempsRemplissage) {
        super(position, 10000, vitesseDrone, qteDeversee, tempsRemplissage);
    }
    
    
    public Drone(Case position, double qteDeversee, long tempsRemplissage, double vitesse) {
        super(position, 10000, vitesse, qteDeversee, tempsRemplissage);
        try{
            setVitesse(vitesse);
            vitesseDrone = vitesse;
        }catch(WrongVitesseException e){
            System.out.println("Error : Vitesse négative "+e.getVitesse());
        }
            
            
        
    }
    
    public void setVitesse(double vitesse) throws WrongVitesseException{
        if(vitesse<0 & vitesse<151){
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
                    chemins.add(new Chemin((i+1)*carte.getNbLignes()+j, vitesseDrone, carte.getTailleCases()));
                }else if(i==carte.getNbLignes()-1){
                    // Sur la derniere ligne : Sud impossible
                    // On ajoute le deplacement Nord
                    chemins.add(new Chemin((i-1)*carte.getNbLignes()+j, vitesseDrone, carte.getTailleCases()));
                }else{
                    // On ajoute les deplacements Nord et Sud
                    chemins.add(new Chemin((i-1)*carte.getNbLignes()+j, vitesseDrone, carte.getTailleCases()));
                    chemins.add(new Chemin((i+1)*carte.getNbLignes()+j, vitesseDrone, carte.getTailleCases()));
                }
                // Traitement des Est / Ouest
                if(j==0){
                    // Sur la premiere colonne : Ouest impossible
                    // On ajoute le deplacement Est
                    chemins.add(new Chemin(i*carte.getNbLignes()+j+1, vitesseDrone, carte.getTailleCases()));
                }else if(j==carte.getNbColonnes()-1){
                    // Sur la derniere colonne : Est impossible
                    // On ajoute le deplacement Ouest
                    chemins.add(new Chemin(i*carte.getNbLignes()+j-1, vitesseDrone, carte.getTailleCases()));
                }else{
                    // On ajoute les deplacements Est et Ouest
                    chemins.add(new Chemin(i*carte.getNbLignes()+j+1, vitesseDrone, carte.getTailleCases()));
                    chemins.add(new Chemin(i*carte.getNbLignes()+j-1, vitesseDrone, carte.getTailleCases()));
                }
                graphe.put(i*carte.getNbLignes()+j, chemins);
            }
        }
        return graphe;
    }
    
}
