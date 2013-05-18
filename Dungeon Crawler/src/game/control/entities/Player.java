package game.control.entities;

import java.awt.Graphics;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * Der Spieler
 */
public class Player extends Entity {
	private final int SPEED = 32;
	
	public Player(String imagePath) {
		try {
			image = ImageIO.read(new File(imagePath));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Bewegt den Spieler in die uebergebene Richtung
	 * @param direction 0 nach rechts; 
	 * 					1 nach unten;
	 * 					2 nach links;
	 * 					3 nach oben;
	 */
	public void move(int direction) {
		switch(direction) {
		case 0:
			x += SPEED;
			break;
			
		case 1:
			y += SPEED;
			break;
			
		case 2:
			x -= SPEED;
			break;
			
		case 3:
			y -= SPEED;
			break;
		}
	}
	
	
	
	@Override
	public void paint(Graphics g) {
		if (image != null) {
			g.drawImage(image, x, y, null);
		}
	}

}
