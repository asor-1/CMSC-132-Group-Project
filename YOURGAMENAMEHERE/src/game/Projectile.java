package game;

import java.awt.Graphics;

/*
 * CLASS: Projectile
 * DESCRIPTION: Projectile class which extends Polygona and implements Reset.
 * 				This allows objects to have some kind of physics such as 
 * 				flying in the air.
 */
public abstract class Projectile extends Polygon implements Reset{
    //variables for classes to inherit
    private double vX;
    private double vY;
    private boolean isInAir;

    /**
     * Projectile constructor
     * @param s Array of points for the shape
     * @param p Point p
     * @param r double r for rotation
     */
    public Projectile(Point[] s, Point p, double r) {
        //call super with shape, point, and rotation
        super(s, p, r); 
        //boolean is false as projectile is not in air to start
        this.isInAir = false;
        //velocity should be 0 at start
        this.vY = 0.0;
        this.vX = 0.0;
    }

    /**
     * this method launches the projectile into the air
     * @param angleDegrees
     * @param power
     */
    public void launchInAir(double angleDegrees, double power) {
    	// make boolean true
        this.isInAir = true;
        // calculate the velocity for x and y component
        this.vX = power * Math.cos(Math.toRadians(angleDegrees));
        this.vY = -power * Math.sin(Math.toRadians(angleDegrees));
    }

    /**
     *  This updates the physics of the projectile
     * @param dTIME
     */
    public void updatePhysics(double dTIME) {
    	//check if in air
        if(isInAir) {
            //changing the velocity y component
            this.vY += (9.8 * dTIME); 
            
            // update the position
            this.position.x += vX;
            this.position.y += vY;
            
            // rotate the projectile
            this.rotate(7);
        }
    }
    
    /**
     * Getter method for isInAir
     * @return true or false based on isInAir
     */
    public boolean isInAir()
    {
    	return isInAir;
    }
    
    /**
     * Resets the projectile and based on start point
     * @param start Point of starting position
     */
    public void resetPosition(Point start) 
    {
    	this.position = start;
    	this.vX = 0;
    	this.vY = 0;
    	this.isInAir = false;
    	this.rotation = 0;
    }
    
    //child class must define how it looks
    public abstract void paint(Graphics brush);
}