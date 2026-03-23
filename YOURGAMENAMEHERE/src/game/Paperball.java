package game;
import java.awt.Color;
import java.awt.Graphics;

/**
 * CLASS: PaperBall
 * DESCRIPTION: Paper ball element for the game. It symbolizes the paper ball
 * 				object which extends Projectile.
 */
public class Paperball extends Projectile {
	//needed to have it initialize here so that can call super in constructor
	private static final Point[] p = {
	        new Point(5, 0), 
	        new Point(10, 5), 
	        new Point(10, 10), 
	        new Point(5, 15),
	        new Point(0, 15), 
	        new Point(-5, 10), 
	        new Point(-5, 5), 
	        new Point(0, 0)
	    };
	//The constructor is intended to make an octagon looking like a paperball
    public Paperball(Point position) {
    	super(p, position, 0.0);
    }

    
    /**
     * paints the paperball onto canvas
     * @param g Graphic g for graphics
     * return void
     */
    public void paint(Graphics g) {
        // applies rotation 
        Point[] currPoints = this.getPoints(); 
        
        // get x and y coordinates into array
        int[] xCoords = new int[currPoints.length];
        int[] yCoords = new int[currPoints.length];
        for (int i = 0; i < currPoints.length; i++) {
            // cast double to integers
            xCoords[i] = (int) currPoints[i].x; 
            yCoords[i] = (int) currPoints[i].y;
        }
        
        // Draw the paper ball
        g.setColor(Color.LIGHT_GRAY);
        g.fillPolygon(xCoords, yCoords, currPoints.length);
    }
}