package plusCourtChemin;

import exceptions.NegativeCostException;
import general.Carte;
import general.Case;
import general.NatureTerrain;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ListIterator;
import general.robots.AbstractRobot;
import general.robots.Drone;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
/**
 *
 * @author 66
 */

/*
    Création d'une structure de données en arbre avec chemin avec couts
    Utilisation de l'algo de Bellman optimisé
    

    A ne pas oublier :
        - Couts de déplacements différents en fct du robot et terrain
        - Optimisation des différents robots pour les différents incendies
*/
public class PlusCourtChemin {
    private Carte carte;
    HashMap<Integer, LinkedList<Chemin>> graphe;
    HashMap<Integer, Sommet> final_couts_sommets;
    
    public PlusCourtChemin(Carte map, AbstractRobot robot) {
        carte = map;
        this.initGraphe(robot, carte);
    }
    
    public void initGraphe(AbstractRobot robot, Carte carte){
        graphe = robot.initGrapheRobot(carte);

        /*switch(robot.getType()){
            case "Drones":
                System.out.println("Drones");
                this.initGrapheDrones();
                break;
            case "Robot à roues":
                System.out.println("Robot à roues");
                break;
            default:
                System.out.println("Type non reconnu");
        }*/
    } 
    
    
    
    public void afficheGraphe(ArrayList<LinkedList<Chemin>> graphe){
        if(graphe.size()==0){
            System.out.println("Graphe vide");
        }
        for(int i = 0; i < graphe.size(); i++) {
            System.out.println("Case "+i+":");
            for(Chemin chemin : graphe.get(i)){
                System.out.println("         "+chemin.getNumCase()+"  "+chemin.getVitesse()+"  "+chemin.getCout());
            }
                
        }
    }
    
    public double Dijkstra(HashMap<Integer, LinkedList<Chemin>> graphe, int depart, int arrivee) {
        final_couts_sommets = new HashMap<Integer, Sommet>();
        final_couts_sommets.put(depart, new Sommet(depart, 0, -1, true));
        
        
        HashMap<Integer, Sommet> tmp_couts_sommets = new HashMap<Integer, Sommet>();
        
        for (Iterator<Map.Entry<Integer, LinkedList<Chemin>>> it = graphe.entrySet().iterator(); it.hasNext();) {
            Map.Entry<Integer, LinkedList<Chemin>> e = it.next();
            tmp_couts_sommets.put(e.getKey(), new Sommet(e.getKey(), 1000000, -1));
        }
        
        // Initialisation depart : cout = 0 , flag = 1; 
        tmp_couts_sommets.get(depart).setCout(0);
        tmp_couts_sommets.get(depart).setFlag(true);
        /*try{
        }catch(NegativeCostException e){
            System.out.println("ERREUR : Il est impossible d'utiliser dans Dijkstra le cout négatif suivant : "+e.getCout());
        }*/
        
        int current_sommet = depart;

        // Boucle pour parcourir tous les sommets
        for (int i = 0; i < graphe.size(); i++) {
            if(current_sommet!=-1 && graphe.get(current_sommet)!=null){
                //System.out.println(current_sommet);
                for (Iterator<Chemin> it = graphe.get(current_sommet).iterator(); it.hasNext();) {
                    Chemin chemin = it.next();
                    double cout_current_sommet = tmp_couts_sommets.get(current_sommet).getCout();
                    double cout_chemin_since_current = chemin.getCout();
                    double cout_new_sommet = tmp_couts_sommets.get(chemin.getNumCase()).getCout();
                    if(cout_current_sommet + cout_chemin_since_current < cout_new_sommet){
                        tmp_couts_sommets.get(chemin.getNumCase()).setCout(cout_current_sommet + cout_chemin_since_current);
                        tmp_couts_sommets.get(chemin.getNumCase()).setParent(current_sommet);
                    }
                }
            }
            current_sommet = addFinalCouts(tmp_couts_sommets);
        }
        //DEBUG
        //afficheCoutsSommets(final_couts_sommets);
        if(final_couts_sommets.get(arrivee) == null){
            System.out.println("");
        }
        return final_couts_sommets.get(arrivee).getCout();
    }
    
    private int addFinalCouts(HashMap<Integer, Sommet> tmp_couts_sommets){
        Sommet sommetMin = null;
        for (Iterator<Map.Entry<Integer, Sommet>> it = tmp_couts_sommets.entrySet().iterator(); it.hasNext();) {
            Map.Entry<Integer, Sommet> e = it.next();
            
            if(e.getValue().getFlag()==false){
                if(sommetMin!=null){
                    if(e.getValue().getCout()<sommetMin.getCout()){
                        sommetMin = e.getValue();
                    }
                }else{
                    sommetMin = e.getValue();
                }
            }
        }
        if(sommetMin!=null){
            tmp_couts_sommets.get(sommetMin.getCase()).setFlag(true);
            final_couts_sommets.put(sommetMin.getCase(), sommetMin);
            return sommetMin.getCase();
        }
        return -1;
    }
    
    
    public LinkedList <Double> getParcours(int depart, int arrive){
        LinkedList <Double> listCases = new LinkedList <Double>();
        double cout = this.Dijkstra(graphe, depart, arrive);
        Sommet sommet = final_couts_sommets.get(arrive);
        while(sommet.getParent()!=-1){
            listCases.addFirst((double)sommet.getCase());
            sommet = final_couts_sommets.get(sommet.getParent());
        }
        listCases.addFirst(cout);
        return listCases;
    }
    
    public void afficheCoutsSommets(HashMap<Integer, Sommet> coutsSommets){
        for(Map.Entry<Integer,Sommet> sommet : coutsSommets.entrySet()){
            System.out.println("Sommet : "+sommet.getValue().getCase()+" ; cout : "+sommet.getValue().getCout()+" ; Parent : "+sommet.getValue().getParent()+" ; Flag :"+sommet.getValue().getFlag());
        }
    }
    
    public void afficheParcours(LinkedList<Double> listCases){
        for(Double numCase : listCases){
            System.out.println("Case : "+numCase);
        }
    }
    
}
