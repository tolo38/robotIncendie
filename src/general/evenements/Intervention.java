
package general.evenements;

import exceptions.TankTooSmallException;
import general.Incendie;
import general.robots.AbstractRobot;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 66
 */
public class Intervention extends Evenement {
    private AbstractRobot robot;
    private Incendie incendie;
    private int qteDeversee;
    
    public Intervention(long date, AbstractRobot robot, Incendie incendie, int qteDeversee) {
        super(date);
        this.robot = robot;
        this.incendie = incendie;
        this.qteDeversee = qteDeversee;
    }

    @Override
    public void execute() {
        int qteEffectivementDeversee = qteDeversee;
        try {
            robot.deverserEau(qteDeversee);
        } catch (TankTooSmallException ex) {
            qteEffectivementDeversee = ex.getCurrentReservoir();
        }
        incendie.intervention(qteEffectivementDeversee);
        int eau = incendie.getEauNecessaire();
        System.out.println("le robot a deversé " + qteDeversee + "L d'eau.");
        System.out.println("il faut encore déverser " + eau + "L d'eau.");
        if (eau == 0) {
            new IncendieEteint();
        }
    }
    
}
