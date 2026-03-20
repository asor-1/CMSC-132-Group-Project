package game;

/*
CLASS: YourGameNameoids
DESCRIPTION: Extending Game, YourGameName is all in the paint method.
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
	Projectile currentObject;
	//inner class 
	Scoreboard s;
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
		//Default with paperball
		currentObject = paperBall;

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
					currentObject = paperBall;
				}
				if (key == KeyEvent.VK_2)
				{
					currentObject = pencil;
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
	  
	public void paint(Graphics brush) 
	{
		// draw black background
        brush.setColor(Color.black);
    	brush.fillRect(0, 0, widthSet, heightSet);
    	
    	//adjusting the power and the angle using the arrow keys
    	if (upButton && angle < 85){
    		angle += 1;
    	}
    	if(downButton && angle > 5) {
    		angle -= 1;
    	}
    	if(rightButton && power < 90){
    		power += 0.2;
    	}
    	if (leftButton && power > 1){
    		power -= 0.2;
    	}
    	
    	// launch
    	if (spaceBarButton) {
    		currentObject.launchInAir(angle, power);
    		spaceBarButton = false;
    	}

    	currentObject.updatePhysics(0.1);

    	// Draw Objects
    	currentObject.paint(brush);
    	trashCan.paint(brush);
    	
    	// checking for collision
    	if (currentObject.collides(trashCan)){
    		score++;
    		resetObject();
    	} 
    	else if(currentObject.position.y > heightSet - 120 || currentObject.position.x > widthSet || currentObject.position.x < 0 || currentObject.position.y < 0) {
    		resetObject();
    	}

        //draw using innerclass scoreboard
        s.draw(brush);
    	
	}
  
	public static void main (String[] args) 
	{
		widthSet = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter width for resolution (Recommended 1600):", 1600));
		heightSet  = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter height for resolution (Recommended 900):", 900));
		RecycleSwish a = new RecycleSwish();
		a.repaint();
	}
	
	private void resetObject()
	{
		int minX = 150;
		int maxX = widthSet / 2;

		Random rand = new Random();
		int randX = rand.nextInt(maxX - minX) + minX;
		Point startPoint = new Point(randX, heightSet - 180);

		if (currentObject == paperBall) 
		{
			paperBall = new Paperball(startPoint);
			currentObject = paperBall;
		}
		else 
		{
			pencil = new Pencil(startPoint);
			currentObject = pencil;
		}
	}
	
	private class Scoreboard{
        public void draw(Graphics brush) {
            brush.setColor(Color.white);
            Font font = new Font("Arial", Font.BOLD, 30);
            brush.setFont(font);
            
            // title of canvas
            FontMetrics fm = brush.getFontMetrics(font);
            String title = "Recycle Swish";
            brush.drawString(title, (widthSet - fm.stringWidth(title)) / 2, 100);
            
            // color
            brush.setColor(Color.BLUE);
            brush.setFont(new Font("Arial", Font.PLAIN, 17));
            
            // score
            brush.drawString("Current score: " + score, 20, 40);
            
            // cast angle from double to int 
            brush.drawString("Angle: " + (int) angle, 20, 70);
            
            //format the power
            String power2 = String.format("%.2f", power);
            brush.drawString("Power: " + power2, 20, 100);
            
            // which object is selected using ternary
            brush.drawString("Selected: " + (currentObject == paperBall ? "Paper Ball" : "Pencil"), 20, 130);

            
            brush.setColor(Color.WHITE);
            
            //this is the rules/instructuions
            brush.drawString("1 = Paperball", 20, heightSet - 135);
            brush.drawString("2 = Pencil", 20, heightSet - 115);
            brush.drawString("UP/DOWN (Arrow) = Angle", 20, heightSet - 95);
            brush.drawString("LEFT/RIGHT (Arrow) = Power", 20, heightSet - 75);
            brush.drawString("SPACE = Launch!", 20, heightSet - 55);
        }
    }
}