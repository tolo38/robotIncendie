
package general.evenements;

import general.DonneesSimulation;
import general.robots.AbstractRobot;

/**
 *
 * @author 66
 */
public class FinRemplissage extends Evenement {
    private AbstractRobot robot;

    public FinRemplissage(long date, AbstractRobot robot) {
        super(date);
        this.robot = robot;
    }

    @Override
    public void execute() {
        /*switch (robot.getType()) {
            case "DRONE" :
                break;
            case "ROUES" :
                break;
            case "PATTES" :
                break;
            case "CHENILLES" :
                break;
        }*/
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
