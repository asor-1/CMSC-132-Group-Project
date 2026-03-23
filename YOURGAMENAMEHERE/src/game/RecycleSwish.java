package game;

/*
CLASS: RecycleSwish
DESCRIPTION: Extending Game, RecycleSwish is all in the paint method.
NOTE: This class is the metaphorical "main method" of your program,
      it is your control center.

*/
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

import javax.swing.JOptionPane;

class RecycleSwish extends Game 
{
	//Determined by user
	static int widthSet = 0, heightSet = 0;
	
	//Default values
	static int score = 0;
	static double angle = 45, power = 45; 
	
	//Keyboard input
	boolean upButton = false;
	boolean downButton = false;
	boolean leftButton = false;
	boolean rightButton = false;
	boolean spaceBarButton = false;

	// Objects
	Paperball paperBall;
	TrashCan trashCan;
	Pencil pencil;
	Projectile currObj;
	
	//inner class 
	Scoreboard s;
	AimVisual av;
	
	/**
	 * RecycleSwish constructor for game
	 */
	public RecycleSwish() 
	{
	    super("RecycleSwish!", widthSet, heightSet);
	    this.setFocusable(true);
		this.requestFocus();
		//Creates objects
		paperBall = new Paperball(new Point(150, heightSet - 180));
		trashCan = new TrashCan(new Point(widthSet - 200, heightSet - 220));
		pencil = new Pencil(new Point(150, heightSet - 180));
		s = new Scoreboard();
		av = new AimVisual();
		//Default with paperball
		currObj = paperBall;

		//Anonymous class for keyboard inputs
		this.addKeyListener(new KeyListener() 
		{

			//Check for key pressed
			public void keyPressed(KeyEvent e) 
			{
				int key = e.getKeyCode();

				if (key == KeyEvent.VK_UP) 
				{
					upButton = true;
				}
				if (key == KeyEvent.VK_DOWN) 
				{
					downButton = true;
				}
				if (key == KeyEvent.VK_LEFT) 
				{
					leftButton = true;
				}
				if (key == KeyEvent.VK_RIGHT)
				{
					rightButton = true;
				}
				if (key == KeyEvent.VK_SPACE)
				{
					spaceBarButton = true;
				}
				if (key == KeyEvent.VK_1)
				{
					currObj = paperBall;
				}
				if (key == KeyEvent.VK_2)
				{
					currObj = pencil;
				}
			}

			//Check for key released for power/angle
			public void keyReleased(KeyEvent e) 
			{
				int key = e.getKeyCode();

				if (key == KeyEvent.VK_UP)
				{
					upButton = false;
				}
				if (key == KeyEvent.VK_DOWN)
				{
					downButton = false;
				}
				if (key == KeyEvent.VK_LEFT) 
				{
					leftButton = false;
				}
				if (key == KeyEvent.VK_RIGHT)
				{
					rightButton = false;
				}
			}

			public void keyTyped(KeyEvent e) 
			{
				//Nothing
			}
		});
	}
	
	/**
	 * Method to draw the game
	 * @param brush Graphics of brush
	 */
	public void paint(Graphics brush) 
	{	
		//fixing this exception: Exception in thread "AWT-EventQueue-0" java.lang.NullPointerException: Cannot invoke "game.Projectile.updatePhysics(double)" because "this.currentObject" is null
		if (currObj == null || s == null || av == null || trashCan == null) {
			return; 
		}
		// draw black background
        brush.setColor(Color.black);
    	brush.fillRect(0, 0, widthSet, heightSet);
    	
    	//adjusting the power and the angle using the arrow keys
    	if (upButton && angle < 85)
    	{
    		angle += 1;
    	}
    	if(downButton && angle > 5)
    	{
    		angle -= 1;
    	}
    	if(rightButton && power < 90)
    	{
    		power += 0.2;
    	}
    	if (leftButton && power > 1)
    	{
    		power -= 0.2;
    	}
    	
    	// launch
    	if (spaceBarButton) 
    	{
    		currObj.launchInAir(angle, power);
    		spaceBarButton = false;
    	}

    	currObj.updatePhysics(0.1);

    	// Draw Objects
    	currObj.paint(brush);
    	trashCan.paint(brush);
    	
    	//lambda expression 
    	GameAction sP = () -> {
    		score++;
    		resetObject();
    	};
    	// checking for collision
    	if (currObj.collides(trashCan)){
    		sP.execute();
    	} 
    	else if(currObj.position.y > heightSet - 120 || currObj.position.x > widthSet || currObj.position.x < 0 || currObj.position.y < 0) {
    		resetObject();
    	}

        //draw using innerclass scoreboard
        s.draw(brush);
    	
        if(!currObj.isInAir())
        {
        	av.draw(brush);
        }
	}
  
	public static void main (String[] args) 
	{
		widthSet = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter width for resolution (Recommended 1600):", 1600));
		heightSet  = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter height for resolution (Recommended 900):", 900));
		RecycleSwish a = new RecycleSwish();
		a.repaint();
	}
	
	/**
	 * Private helper method to help reset the object at a random point on the canvas
	 */
	private void resetObject()
	{
		int minX = 150;
		int maxX = widthSet / 2;

		Random rand = new Random();
		int randX = rand.nextInt(maxX - minX) + minX;
		Point startPoint = new Point(randX, heightSet - 180);

		if (currObj == paperBall) 
		{
			paperBall = new Paperball(startPoint);
			currObj = paperBall;
		}
		else 
		{
			pencil = new Pencil(startPoint);
			currObj = pencil;
		}
	}
	
	/**
	 * Inner class that helps display the scoreboard and statistics
	 */
	private class Scoreboard{
        public void draw(Graphics brush) {
            brush.setColor(Color.white);
            Font font = new Font("Arial", Font.BOLD, 30);
            brush.setFont(font);
            
            // title of canvas
            FontMetrics fmetrics = brush.getFontMetrics(font);
            String title = "Recycle Swish";
            brush.drawString(title, (widthSet - fmetrics.stringWidth(title)) / 2, 100);
            
            // color and the score 
            brush.setColor(Color.BLUE);
            brush.setFont(new Font("Arial", Font.PLAIN, 17));
          
            brush.drawString("Current score: " + score, 20, 40);
            
            // cast angle from double to int 
            brush.drawString("Angle: " + (int) angle, 20, 70);
            
            //format the power
            String power2 = String.format("%.2f", power);
            brush.drawString("Power: " + power2, 20, 100);
            
            // which object is selected using ternary
            brush.drawString("Selected Obj: " + (currObj == paperBall ? "Paper Ball" : "Pencil"), 20, 130);

            
            brush.setColor(Color.WHITE);
            
            //this is the rules/instructuions
            brush.drawString("1 = Paperball", 20, heightSet - 135);
            brush.drawString("2 = Pencil", 20, heightSet - 115);
            brush.drawString("Up/down (Arrow key) = Angle", 20, heightSet - 95);
            brush.drawString("Left/Right (Arrow key) = Power", 20, heightSet - 75);
            brush.drawString("SPACE will launch", 20, heightSet - 55);
        }
    }
	
	/**
	 * Inner class that helps show an Aim Visual to assist the user
	 */
	private class AimVisual
	{
		public void draw(Graphics brush)
		{
			//Changes color based on object
			if(currObj == paperBall)
			{
				brush.setColor(Color.WHITE);
			}
			
			if(currObj == pencil)
			{
				brush.setColor(Color.YELLOW);
			}
			
			//Find object position
			int startX = (int) currObj.position.x;
			int startY = (int) currObj.position.y;
			
			int length = 100;
			
			int endX = (int) (startX + length * Math.cos(Math.toRadians(angle)));
	        int endY = (int) (startY - length * Math.sin(Math.toRadians(angle)));
	        
	        brush.drawLine(startX, startY, endX, endY);
		}
	}
	
	//functional interface
	interface GameAction {
		void execute();
	}
}