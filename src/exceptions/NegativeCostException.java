/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptions;

/**
 *
 * @author Matt
 */
public class NegativeCostException extends Exception {
    private double cout;
    public NegativeCostException(double cost){
        cout = cost;
    }
    public double getCout(){
        return cout;
    }
}
