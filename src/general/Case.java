
package general;

import exceptions.ForbiddenMoveException;

/**
 *
 * @author 66
 */
public class Case {
    private int ligne;
    private int colonne;
    private NatureTerrain nature;

    public Case(int ligne, int colonne, NatureTerrain nature) {
        this.ligne = ligne;
        this.colonne = colonne;
        this.nature = nature;
    }

    public int getLigne() {
        return ligne;
    }

    public int getColonne() {
        return colonne;
    }

    public NatureTerrain getNature() {
        return nature;
    }
    
    public void setNatureTerrain(NatureTerrain natureTerrain){
        this.nature = natureTerrain;
    }
    
    
    @Override
    public String toString() {
        return "(" + this.ligne + "," + this.colonne +")";
    }
    
    /**
     *
     * @param direction
     * @throws exceptions.ForbiddenMoveException
     */
//    public void getVoisin(Direction direction) throws ForbiddenMoveException {
//        if (direction == Direction.NORD) {
//            if (this.ligne == 0) throw new ForbiddenMoveException(this, direction);
//            this.ligne -= 1;
//        } else if (direction == Direction.SUD) {
//            if (this.ligne == max) throw new ForbiddenMoveException(this, direction);
//            this.ligne -= 1;
//            
//        } else if (direction == Direction.EST) {
//            if (this.ligne == 0) 
//        } else {
//            if (this.ligne == 0) throw new ForbiddenMoveException(this, direction);
//            this.ligne -= 1;
//            
//        }
//    }
}
