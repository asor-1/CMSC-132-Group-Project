package game;
import java.awt.Color;
import java.awt.Graphics;

/*
 * CLASS: TrashCan
 * DESCRIPTION: Trash can element for the game which extends Polygon. It is
 * 				the object which symbolizes the trash can.
 */
public class TrashCan extends Polygon 
{
	/**
	 * Constructor for TrashCan
	 * @param pos Position/Point for trash can
	 */
    public TrashCan(Point pos) 
    {
        // shape for the trash bin
        super(new Point[] {
            new Point(0, 0), new Point(60, 0), new Point(50, 80), new Point(10, 80)
        }, pos, 0);
    }
    
    /**
     * draw on canvas
     * @param g
     */
    public void paint(Graphics g) {
        Point[] points = this.getPoints();
        int[] xPoints = new int[points.length];
        int[] yPoints = new int[points.length];
        
        for (int i = 0; i < points.length; i++) {
            xPoints[i] = (int) points[i].x;
            yPoints[i] = (int) points[i].y;
        }
        
        g.setColor(Color.DARK_GRAY);
        g.fillPolygon(xPoints, yPoints, points.length);
    }
}