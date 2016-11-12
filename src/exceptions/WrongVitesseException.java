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
public class WrongVitesseException extends Exception {
    private double vitesse;
    public WrongVitesseException(double speed){
        vitesse = speed;
    }
    public double getVitesse(){
        return vitesse;
    }
}
