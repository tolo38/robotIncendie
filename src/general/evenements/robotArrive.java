
package general.evenements;

import general.DonneesSimulation;
import general.robots.AbstractRobot;

/**
 *
 * @author 66
 */
public class robotArrive extends Evenement {
    private AbstractRobot robot;

    public robotArrive(long date, AbstractRobot robot) {
        super(date);
        this.robot = robot;
    }
    

    @Override
    public void execute() {
        System.out.println("Robot " + robot + "has reached its destination: " + robot.getPosition());
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void reset() {
        robot.reset();
    }
    
}
