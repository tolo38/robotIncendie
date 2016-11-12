
package general.robots;

import exceptions.ForbiddenMoveException;
import exceptions.WrongPositionException;
import general.Carte;
import general.Case;
import general.Direction;
import general.NatureTerrain;
import java.util.HashMap;
import java.util.LinkedList;
import plusCourtChemin.Chemin;

/**
 *
 * @author 66
 */
public class RobotPattes extends AbstractRobot {
    
    private static final double DEFAUTVITESSE = 30;
    private static final long TEMPSREMPLISSAGE = 0;
    private static final int TAILLERESERVOIR = Integer.MAX_VALUE;
    private static final int QUNITAIREDEVERSER = 10;
    private static final int TUNITAIREDEVERSER = 1;
    
    /**
     * tailleReservoir : infinie
     * qteDeversee : 10L en 1 seconde
     * tempsRemplissage : 0
     * @param position
     */
    public RobotPattes(Case position) {
        super(position, TAILLERESERVOIR, DEFAUTVITESSE, QUNITAIREDEVERSER, TEMPSREMPLISSAGE, TUNITAIREDEVERSER);
    }
    
    @Override
    public double getVitesse(NatureTerrain nature) {
        if (nature == NatureTerrain.ROCHE)
            return this.getVitesse() - 10;
        return this.getVitesse();
    }
    
    /**
     * Un robot à pattes ne se remplit jamais
     */
    @Override
    public void remplirReservoir() {
    }
    
    public String getType() {
        return "PATTES";
    }

    @Override
    public void deplacerRobot(Direction direction) {
        try {
            Case caseSuivante = UtileRobot.caseSuivante(this.getPosition(), direction);
            if (caseSuivante.getNature() == NatureTerrain.EAU)
                throw new ForbiddenMoveException(getPosition(), direction);
            this.setPosition(caseSuivante);
        } catch (ForbiddenMoveException ex) {
            System.out.println("Forbidden move : " + this + "\nLocation: " + this.getPosition() + " can't move " + direction);
        }
    }

    @Override
    public boolean isASourceCase(Case sCase) {
        return false;
    }
    
     /*
        Construction du graphe pour le Robot a chenilles:
        Vitesse  : 60 km/h
        Vitesse ROCHE : 10 km/h
        Vitesse EAU: 0km/h

        
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
                
                
                double vitesseRobot = getVitesse(carte.getCase(i, j));
                
                if(vitesseRobot>0){
                     // Traitement des Nord / Sud
                    if(i==0){
                        // Sur la premiere ligne : Nord impossible
                        // On ajoute le deplacement Sud
                        if(testCaseValid(carte, i+1, j)){
                            chemins.add(new Chemin((i+1)*carte.getNbLignes()+j, vitesseRobot, carte.getTailleCases()));
                        }
                    }else if(i==carte.getNbLignes()-1){
                        // Sur la derniere ligne : Sud impossible
                        // On ajoute le deplacement Nord
                        if(testCaseValid(carte, i-1, j)){
                            chemins.add(new Chemin((i-1)*carte.getNbLignes()+j, vitesseRobot, carte.getTailleCases()));
                        }
                    }else{
                        // On ajoute les deplacements Nord et Sud
                        if(testCaseValid(carte, i-1, j)){
                            chemins.add(new Chemin((i-1)*carte.getNbLignes()+j, vitesseRobot, carte.getTailleCases()));
                        }
                        if(testCaseValid(carte, i+1, j)){
                            chemins.add(new Chemin((i+1)*carte.getNbLignes()+j, vitesseRobot, carte.getTailleCases()));
                        }
                    }
                    // Traitement des Est / Ouest
                    if(j==0){
                        // Sur la premiere colonne : Ouest impossible
                        // On ajoute le deplacement Est
                        if(testCaseValid(carte, i, j+1)){
                            chemins.add(new Chemin(i*carte.getNbLignes()+j+1, vitesseRobot, carte.getTailleCases()));
                        }
                    }else if(j==carte.getNbColonnes()-1){
                        // Sur la derniere colonne : Est impossible
                        // On ajoute le deplacement Ouest
                        if(testCaseValid(carte, i, j-1)){
                            chemins.add(new Chemin(i*carte.getNbLignes()+j-1, vitesseRobot, carte.getTailleCases()));
                        }
                    }else{
                        // On ajoute les deplacements Est et Ouest
                        if(testCaseValid(carte, i, j+1)){
                            chemins.add(new Chemin(i*carte.getNbLignes()+j+1, vitesseRobot, carte.getTailleCases()));
                        }
                        if(testCaseValid(carte, i, j-1)){
                            chemins.add(new Chemin(i*carte.getNbLignes()+j-1, vitesseRobot, carte.getTailleCases()));
                        }
                    }
                    
                    graphe.put(i*carte.getNbLignes()+j, chemins);
                }
            }
        }
        return graphe;
    }
    
    private boolean testCaseValid(Carte carte, int i, int j){
        if(carte.getCase(i, j).getNature()== NatureTerrain.EAU){
            return false;
        }
        return true;
    }
    
    @Override
    public boolean testCaseValid(Case cases) {
        if(cases.getNature()==NatureTerrain.EAU) {
            return false;
        }
        return true;
    }
    
    @Override
    public double getVitesse(Case c) { 
        switch(c.getNature()){
            case EAU:
                return -1;
            case ROCHE:
                return 10;
            default:
                return super.getVitesse();
        }
    }
    
}
