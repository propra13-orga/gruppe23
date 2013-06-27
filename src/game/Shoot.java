/*
 * Author: Martha Tatusch
 */

package game;

import java.awt.Rectangle;

public class Shoot {
	
	private int x, y, speedX, speedY;
	public static Rectangle r;
	private boolean visible = false;

	public Main main;
	
	public Shoot(int x, int y, int speedX, int speedY){
		
		this.x = x;
		this.y = y;
		this.speedX = speedX;
		this.speedY = speedY;
		r = new Rectangle(0,0,0,0);
		visible = true;

		
		
	}
	
	public void setT(Tiles t){
		Tiles x = t;
		if (r.intersects(x.r)){
			visible = false;
		}
	}
	
	public void update(){
		
		r.setBounds(x, y, 5, 5);
		x += speedX;
		y += speedY;
		
		if(r.intersects(Player.r)){
			visible = false;
			if(!Main.spell_iceshield)main.player.setLife(Main.player.getLife() - 5);
		}
		
		if(x > 760 || x < 40) visible = false;
		if(y > 640 || y < 40) visible = false;
		
	}
	
	
	public void shoot(){
		
	}
	
	//Getters und Setters:

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getSpeedX() {
		return speedX;
	}

	public int getSpeedY() {
		return speedY;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setSpeedX(int speedX) {
		this.speedX = speedX;
	}

	public void setSpeedY(int speedY) {
		this.speedY = speedY;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	
	
	
	

}