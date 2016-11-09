/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package general.evenements;

import general.Direction;
import general.robots.AbstractRobot;

/**
 *
 * @author 66
 */
public class DeplacerRobot extends Evenement {
    private AbstractRobot robot;
    private Direction direction;
    
    public DeplacerRobot(long date, AbstractRobot robot, Direction direction) {
        super(date);
        this.robot = robot;
        this.direction = direction;
    }
    
    public void execute() {
        // TODO suivant le type du robot et le terrain /!\
        robot.deplacerRobot(direction);
        /*try {
            this.robot.setPosition(this.getDataGame().getCarte().getVoisin(this.robot.getPosition(), direction));
        } catch(ForbiddenMoveException e) {
            System.out.println("Forbidden move : " + this.robot + "\nLocation: " + this.robot.getPosition() + "can't move " + this.direction);
        }*/
    }

    @Override
    public void reset() {
        robot.reset();
    }
}
