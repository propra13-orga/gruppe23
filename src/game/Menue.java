/*
 * Author: Martha Tatusch
 */

package game;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Menue {

	private BufferedImage bg;
	private BufferedImage bt1;
	private BufferedImage bt2;
	private BufferedImage bt3;
	private BufferedImage bt4;

	
	public Menue(){
		
		//Bilder des Menues werden geladen
		
		try {
			bg = ImageIO.read(getClass().getClassLoader().getResourceAsStream("gfx/background.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			bt1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("gfx/Neues_Spiel.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			bt2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("gfx/Spiel_Verlassen.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			bt3 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("gfx/Multiplayer.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			bt4 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("gfx/fortfahren.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//Bilder werden gezeichnet:
	
	public void draw(Graphics g){
		
		g.drawImage(bg, 0, 0, null);
		g.drawImage(bt1, 200, 150, null);
		g.drawImage(bt2, 200, 540, null);
		g.drawImage(bt3, 200, 410, null);
		g.drawImage(bt4, 200, 280, null);
		
	}

}
