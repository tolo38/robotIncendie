
package exceptions;

public class TankTooSmallException extends Exception {
    int currentReservoir;
    int vol;

    public TankTooSmallException(int currentReservoir, int vol) {
        this.currentReservoir = currentReservoir;
        this.vol = vol;
    }
}
