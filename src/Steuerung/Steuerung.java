package Steuerung;




import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Steuerung implements KeyListener {
	public static final int X_KOORD = 0;
	public static final int Y_KOORD = 1;
	String key;
	private int[] position = new int[2];

	public void keyPressed1(KeyEvent event){


			
			if (event.getKeyCode() == KeyEvent.VK_LEFT) {
				String key = "left";
				
			}

			else if (event.getKeyCode() == KeyEvent.VK_RIGHT) {
				String key = "right";
				
			}

			else if (event.getKeyCode() == KeyEvent.VK_UP) {
				String key = "up";
				
			}

			else if (event.getKeyCode() == KeyEvent.VK_DOWN) {
				String key = "down";
			
			}

			
		}
	public void moveLeft() {
		// Calculating new position
		int[] newPosition = new int[2];
		newPosition[X_KOORD] = this.position[X_KOORD] - 1; // nach links
		newPosition[Y_KOORD] = this.position[Y_KOORD];

		
	}
	public void moveRight() {
		// Calculating new position
		int[] newPosition = new int[2];
		newPosition[X_KOORD] = this.position[X_KOORD] + 1; // nach rechts
		newPosition[Y_KOORD] = this.position[Y_KOORD];

		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void KeyCheck (String Key) {
		if (key.equals("left"))
			Get_Left();
		else if (key.equals("right"))
			Get_Right();
		else if (key.equals("up"))
			Get_Up();
		else if (key.equals("down"))
			Get_Down();
	}
	public int[] coordinates() {
		return this.position;
	}
//todo: player mit koordinaten erstellen: filledCircle(1 , 1, 2) aus Std.Draw 
	
public static void filledCircle(double x, double y, double r) {
		
}
	
	public void Get_Right() {
		int[] newPosition = new int[2];
		newPosition[X_KOORD] = this.position[X_KOORD] + 1; // nach rechts
		newPosition[Y_KOORD] = this.position[Y_KOORD];
	
		// TODO Auto-generated method stub
		
	}
	
	public void Get_Left() {
		int[] newPosition = new int[2];
		newPosition[X_KOORD] = this.position[X_KOORD] - 1; 
		newPosition[Y_KOORD] = this.position[Y_KOORD];
		// TODO Auto-generated method stub
		
	}
	public void Get_Up() {
		int[] newPosition = new int[2];
		newPosition[X_KOORD] = this.position[X_KOORD] ; 
		newPosition[Y_KOORD] = this.position[Y_KOORD] +1;
		// TODO Auto-generated method stub
		
	}
	public void Get_Down() {
		int[] newPosition = new int[2];
		newPosition[X_KOORD] = this.position[X_KOORD] ; 
		newPosition[Y_KOORD] = this.position[Y_KOORD] -1;
		// TODO Auto-generated method stub
		
	}


	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public int[] registerPlayer(int pos) {
		
		int x, y;
		int[] returnPosition = new int[2];

		
		x = returnPosition[X_KOORD];
		y = returnPosition[Y_KOORD];
		
	

		return returnPosition;
	}
	}



