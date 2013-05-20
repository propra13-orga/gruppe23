package main_package;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Menue {

	private BufferedImage bg;
	private BufferedImage bt1;
	private BufferedImage bt2;
	
	public Menue(){
		
		//Bilder des Menüs werden geladen
		
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
	}
	
	public void draw(Graphics g){
		
		g.drawImage(bg, 0, 0, null);
		g.drawImage(bt1, 150, 200, null);
		g.drawImage(bt2, 150, 400, null);
		
	}

}
