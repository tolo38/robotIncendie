/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plusCourtChemin;

import exceptions.NegativeCostException;


/**
 *
 * @author 66
 */

/*
    Class utilisée pour l'algorithme de Dijkstra :
    pour stocker le cout et les suites de sommets des plus courts chemins
*/
public class Sommet {
    private int numCase;
    private double cout;
    private int parent;
    private boolean flag;
    
    public Sommet(int nCase, double cost, int directparent){
        numCase = nCase;
        cout = cost;
        parent = directparent;
        flag = false;
    }
    
    public Sommet(int nCase, double cost, int directparent, boolean drapeau){
        numCase = nCase;
        cout = cost;
        parent = directparent;
        flag = drapeau;
    }
    
    public int getCase(){
        return numCase;
    }
    
    public int getParent(){
        return parent;
    }
    
    public double getCout(){
        return cout;
    }
    public boolean getFlag(){
        return flag;
    }
    
    public void setCout(double cost){// throws NegativeCostException{
        if(cost>=0){
            cout = cost;
        }else{
            System.out.println("Negative Cost !!!!!!!!!!!!!!!!!!!");
            //throw new NegativeCostException(cost);
        }
    }
    public void setParent(int directParent){
        parent = directParent;
    }
    
    public void setFlag(boolean drapeau){
        flag = drapeau;
    }
}
