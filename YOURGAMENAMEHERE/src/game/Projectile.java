package game;

import java.awt.Graphics;


public abstract class Projectile extends Polygon {
    //variables for classes to inherit
    private double vX;
    private double vY;
    private boolean isInAir;

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
        this.vY = power * Math.cos(Math.toRadians(angleDegrees));
        this.vX = power * Math.sin(Math.toRadians(angleDegrees));
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
    
    //child class must define how it looks
    public abstract void paint(Graphics brush);
}