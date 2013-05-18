package game.control.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public abstract class Entity {
	protected int x;
	protected int y;
	
	protected BufferedImage image;
	
	public abstract void paint(Graphics g);
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
}
