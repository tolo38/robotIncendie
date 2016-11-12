/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package general.evenements;

import general.ChefPompier;
import general.evenements.Evenement;
import general.robots.AbstractRobot;

/**
 *
 * @author thomas
 */
public class OrdreChefPompier extends Evenement {
    private static ChefPompier chefP;
    private AbstractRobot robot;
       
    
    public OrdreChefPompier(long date, AbstractRobot robot) {
        super(date);
        this.robot = robot;
    }

    public static void setChefPompier(ChefPompier chefPompier) {
        chefP = chefPompier;
    }
    
    @Override
    public void execute() {
        chefP.ordrePourRobot(super.getDate()+1,robot);
    }
    
}
