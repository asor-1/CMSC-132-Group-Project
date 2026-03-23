package game;

/**
 * INTERFACE: Reset
 * DESCRIPTION: Interface to reset an Object at a certain point
 */
public interface Reset
{
    /**
     * Resets the object to the given starting point
     * @param starting point of where to reset
     */
    void resetPosition(Point s);
}