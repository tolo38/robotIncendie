
package exceptions;

import general.Case;

/**
 *
 * @author 66
 */
public class WrongPositionException extends Exception {
    private Case position;
    
    public WrongPositionException(Case Position) {
        this.position = position;
    }
    
}
