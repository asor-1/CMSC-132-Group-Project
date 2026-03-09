package game;

/*
CLASS: YourGameNameoids
DESCRIPTION: Extending Game, YourGameName is all in the paint method.
NOTE: This class is the metaphorical "main method" of your program,
      it is your control center.

*/
import java.awt.*;
import java.awt.event.*;

import javax.swing.JOptionPane;

class RecycleSwish extends Game 
{
	static int counter = 0, widthSet = 0, heightSet = 0;

	public RecycleSwish() 
	{
	    super("RecycleSwish!", widthSet, heightSet);
	    this.setFocusable(true);
		this.requestFocus();
	}
	  
	public void paint(Graphics brush) 
	{
    	brush.setColor(Color.black);
    	brush.fillRect(0, 0, widthSet, heightSet);
    	
    	brush.setColor(Color.white);
    	Font font = new Font("Arial", Font.BOLD, 30);
    	brush.setFont(font);
    	
    	FontMetrics fm = brush.getFontMetrics(font);
    	String title = "Recycle Swish";
    	brush.drawString(title, (widthSet - fm.stringWidth(title)) / 2, 100);
	}
  
	public static void main (String[] args) 
	{
		widthSet = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter width for resolution (Recommended 1600):", 1600));
		heightSet  = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter height for resolution (Recommended 900):", 900));
		RecycleSwish a = new RecycleSwish();
		a.repaint();
	}
}