package game;
import java.awt.Color;
import java.awt.Graphics;

public class Pencil extends Projectile {
	//needed to have it initialize here so that can call super in constructor
	private static final Point[] p = { new Point(0, 0), new Point(20, 0), new Point(25, 5), new Point(20, 10), new Point(0, 10)
	};
    public Pencil(Point position) {
        // defining a thing long rectangle
        super(p, position, 0);
    }

    /**
     * paints the pencil onto the canvas
     * @param Grpahics g
     * return void
     */
    public void paint(Graphics g) {
        Point[] points = this.getPoints();
        int[] xPoints = new int[points.length];
        int[] yPoints = new int[points.length];
        
        for (int i = 0; i < points.length; i++) {
            xPoints[i] = (int) points[i].x;
            yPoints[i] = (int) points[i].y;
        }
        //draw the pencil
        g.setColor(Color.YELLOW);
        g.fillPolygon(xPoints, yPoints, points.length);
    }
}