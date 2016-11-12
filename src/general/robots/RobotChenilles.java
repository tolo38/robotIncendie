
package general.robots;

import exceptions.ForbiddenMoveException;
import exceptions.WrongPositionException;
import exceptions.WrongVitesseException;
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
public class RobotChenilles extends AbstractRobot {
    
    private static final double DEFAUTVITESSE = 60;
    private static final long TEMPSREMPLISSAGE = 5 * 60;
    private static final int TAILLERESERVOIR = 2000;
    private static final int QUNITAIREDEVERSER = 100;
    private static final int TUNITAIREDEVERSER = 8;
    
    /**
     * tailleReservoir : 2000L
     * qteDeversee : 100L en 8 secondes
     * tempsRemplissage : 5 minutes
     * @param position
     */
    public RobotChenilles(Case position) {
        super(position, TAILLERESERVOIR, DEFAUTVITESSE, QUNITAIREDEVERSER, TUNITAIREDEVERSER, TUNITAIREDEVERSER);
    }
    
     
    public RobotChenilles(Case position, double vitesse) {
        super(position, TAILLERESERVOIR, vitesse, QUNITAIREDEVERSER, TUNITAIREDEVERSER, TUNITAIREDEVERSER);
        try{
            vitesseAutorise(vitesse);
        }catch(WrongVitesseException e){
            System.out.println("Error : Vitesse négative "+e.getVitesse());
        }
    }
    
    
    public void vitesseAutorise(double vitesse) throws WrongVitesseException{
        if(vitesse<0 & vitesse<81){
            throw new WrongVitesseException(vitesse);
        }
    }
    
    @Override
    public double getVitesse(NatureTerrain nature) {
        if (nature == NatureTerrain.FORET)
            return this.getVitesse() / 2;
        return this.getVitesse();
    }
    
    @Override
    public void remplirReservoir() throws WrongPositionException {
        boolean allowed = UtileRobot.nextToWater(this.getPosition());
        if (allowed) {
            super.remplirReservoir();
        }
        else throw new WrongPositionException(this.getPosition());
    }
    
    
    public String getType() {
        return "CHENILLES";
    }

    @Override
    public void deplacerRobot(Direction direction) {
        try {
            Case caseSuivante = UtileRobot.caseSuivante(this.getPosition(), direction);
            if (caseSuivante.getNature() == NatureTerrain.EAU || caseSuivante.getNature() == NatureTerrain.ROCHE)
                throw new ForbiddenMoveException(getPosition(), direction);
            this.setPosition(caseSuivante);
        } catch (ForbiddenMoveException ex) {
            System.out.println("Forbidden move : " + this + "\nLocation: " + this.getPosition() + " can't move " + direction);
        }
    }
    
    @Override
    public boolean isASourceCase(Case sCase) {
        return UtileRobot.nextToWater(sCase);
    }
    
    /*
        Construction du graphe pour le Robot a chenilles:
        Vitesse TERRAIN_LIBRE, HABITAT, UNSET  : 60 km/h
        Vitesse FORET : 60*50% = 30 km/h
        Vitesse EAU, ROCHE : 0km/h

        
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
        if(carte.getCase(i, j).getNature()== NatureTerrain.EAU ||  carte.getCase(i, j).getNature()== NatureTerrain.ROCHE){
            return false;
        }
        return true;
    }
    
    @Override
    public boolean testCaseValid(Case cases) {
        if(cases.getNature()==NatureTerrain.EAU ||  cases.getNature()==NatureTerrain.ROCHE) {
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
                return -1;
            case FORET:
                return super.getVitesse()*0.5;
            default:
                return super.getVitesse();
        }
    }
    
 
}
