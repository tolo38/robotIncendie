/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package general.evenements;

import exceptions.ForbiddenMoveException;
import general.Direction;
import general.DonneesSimulation;
import general.robots.AbstractRobot;

/**
 *
 * @author 66
 */
public class DeplacerRobot extends Evenement {
    private AbstractRobot robot;
    private Direction direction;
    
    public DeplacerRobot(long date, DonneesSimulation dataGame, AbstractRobot robot, Direction direction) {
        super(date, dataGame);
        this.robot = robot;
        this.direction = direction;
    }
    
    public void execute() {
        // TODO suivant le type du robot et le terrain /!\
        try {
            this.robot.setPosition(this.getDataGame().getCarte().getVoisin(this.robot.getPosition(), direction));
        } catch(Exception e) {
            System.out.println("Forbidden move : " + this.robot + "\n Location: " + this.robot.getPosition() + "can't move" + this.direction);
        }
    }
}
