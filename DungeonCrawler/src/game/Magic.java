package game;

import java.awt.Color;
import java.awt.Graphics;

public class Magic {
	
	//Variablen
	private int x_pos;
	private int y_pos;
	
	//Schussradius
	private final int radius = 3;
	
	//Konstruktor
	public Magic(int x, int y){
		x_pos = x;
		y_pos = y;
	}
	
	//liefert die y-position zurueck
	public int getYPos(){
		return y_pos;
	}
	
	//bewegt den Schuss in y-richtung
	public void moveShot(int speed){
		y_pos += speed;
	}
	
	//zeichnet den Schuss
	public void drawMagic(Graphics g){
		g.setColor(Color.yellow);
		g.fillOval(x_pos,  y_pos, radius, radius);
	}

}
