
package exceptions;

import general.Case;
import general.Direction;
import general.robots.AbstractRobot;

/**
 *
 * @author 66
 */
public class ForbiddenMoveException extends Exception {
    private Case position;
    private Direction direction;
    //private AbstractRobot robot;
    
    /**
     *
     * @param position
     * @param direction
     */
    public ForbiddenMoveException(Case position, Direction direction) {
        this.position = position;
        this.direction = direction;
        //this.robot = robot;
    }
    
}
