
package general.evenements;

import exceptions.ForbiddenMoveException;
import general.Direction;
import general.DonneesSimulation;
import general.NatureTerrain;
import general.robots.AbstractRobot;
import general.robots.Drone;

/**
 *
 * @author 66
 */
public class RemplirReservoir extends Evenement {
    private AbstractRobot robot;

    public RemplirReservoir(long date, DonneesSimulation dataGame, AbstractRobot robot) {
        super(date, dataGame);
        this.robot = robot;
    }

    @Override
    public void execute() {
        boolean waterCond = false;
        switch (this.robot.getType()) {
            case "DRONE" :
                waterCond = (this.robot.getPosition().getNature() == NatureTerrain.EAU);
                if (waterCond) robot.remplirReservoir();
                this.getDataGame().getSimulateur().addEvenement(new FinRemplissage(getDate() + Drone.tempsRemplissage, getDataGame(), robot));
                break;
            case "PATTES" :
                break;
            default :
                try {
                    waterCond = (this.getDataGame().getCarte().getVoisin(this.robot.getPosition(), Direction.NORD).getNature() == NatureTerrain.EAU);
                    waterCond = waterCond || (this.getDataGame().getCarte().getVoisin(this.robot.getPosition(), Direction.EST).getNature() == NatureTerrain.EAU);
                    waterCond = waterCond || (this.getDataGame().getCarte().getVoisin(this.robot.getPosition(), Direction.SUD).getNature() == NatureTerrain.EAU);
                    waterCond = waterCond || (this.getDataGame().getCarte().getVoisin(this.robot.getPosition(), Direction.OUEST).getNature() == NatureTerrain.EAU);
                } catch (ForbiddenMoveException e) {
                    // on est au bord
                }
                if (waterCond) robot.remplirReservoir();
                break;
        }
        
        if (!waterCond) System.out.println("On ne peut pas remplir un " + robot.getType() + " ici");
        
    }
    
}
