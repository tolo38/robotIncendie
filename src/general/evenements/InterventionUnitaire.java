
package general.evenements;

import exceptions.TankTooSmallException;
import general.Incendie;
import general.robots.AbstractRobot;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * Permet de derverser une unite d'eau sur l'incendit en fonction du robot
 */
public class InterventionUnitaire extends Evenement {
    private AbstractRobot robot;
    private Incendie incendie;
    
    public InterventionUnitaire(long date, AbstractRobot robot, Incendie incendie) {
        super(date);
        this.robot = robot;
        this.incendie = incendie;
    }

    @Override
    public void execute() {
        int qteEffectivementDeversee;
        try {
            qteEffectivementDeversee = robot.deverserEau();
            incendie.intervention(qteEffectivementDeversee);
        } catch (TankTooSmallException ex) {
            qteEffectivementDeversee = ex.getCurrentReservoir();
        }
        //DEBUG
        int eau = incendie.getEauNecessaire();
        System.out.println("il faut encore déverser " + eau + "L d'eau.");
    }
    
}
