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

class YourGameName extends Game 
{
	static int counter = 0, widthSet = 0, heightSet = 0;
	

  public YourGameName() 
  {
    super("YourGameName!", widthSet, heightSet);
    this.setFocusable(true);
	this.requestFocus();
  }
  
	public void paint(Graphics brush) {
    	brush.setColor(Color.black);
    	brush.fillRect(0,0,width,height);
    	
    	// sample code for printing message for debugging
    	// counter is incremented and this message printed
    	// each time the canvas is repainted
    	counter++;
    	brush.setColor(Color.white);
    	brush.drawString("Counter is " + counter,10,10);
  }
  
	public static void main (String[] args) 
	{
		widthSet = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter width for resolution (Recommended 1920):", 1920));
		heightSet  = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter height for resolution (Recommended 1080):", 1080));
		YourGameName a = new YourGameName();
		a.repaint();
	}
}