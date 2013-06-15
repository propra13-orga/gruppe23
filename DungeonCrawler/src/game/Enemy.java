package game;

import java.awt.Rectangle;

public class Enemy {
	
	private int x, y, speedX, speedY, damage ;
	private int direction;
	private boolean yAligned , xAligned;
	public static Rectangle r;
	private double frame = 0, frameAdd;
	int life = 100;
	private boolean visible;
	
	//Eigenschaften des Feindes:
	
	public Enemy (int x, int y, int damage, int life){
		
		this.x = x;
		this.y = y;
		this.damage = damage; //Schaden, den der Gegner anrichtet
		this.life = life; //Leben des Gegners
		r = new Rectangle(); //Rechteck am Gegner
		
		
	}
	
	public void dies(){
		
		
	}
	
	public void update(){
		//frame ist der Faktor, um den die Position des auszuschneidenden Bildes aus den Grafiken der Gegner verschoben wird
		   if (frame < 0.5) frameAdd =  .1; //frameadd entspricht der Animier-Geschwindigkeit
		   if (frame > 2.5) frameAdd = -.1;
		   frame += frameAdd;
		

			speedX =(int) (Math.random()*2); //zufällige X-Geschwindigkeit des Gegners


			speedY =(int) (Math.random()*2); //zufällige Y-Geschwindigkeit

		//Spieler verfolgen:
			
if (xAligned == false){	
if(x > Main.player.getP_X()){ x -= speedX; direction = 32;}
else if(x < Main.player.getP_X()) { x += speedX; direction = 64; }
else if(x == Main.player.getP_X()) xAligned = true;
}

if (xAligned == true){	
if(y > Main.player.getP_Y()){ y -= speedX; direction = 96;}
else if(y < Main.player.getP_Y()){ y += speedX; direction = 0;}
else if(y == Main.player.getP_Y()) xAligned = false;
}

		
		r.setBounds(x, y, 32, 32); //Quadrat um den Geiste herum
		
		checkCollision(Main.player.r); //Abfrage nach Kollision mit dem Spieler
		
		
	}
	
	public void checkCollision(Rectangle r){ //Abfrage nach Kollision
		if(r.intersects(this.r)){
			Main.player.setLife(Main.player.getLife()-damage); //ziehe Leben des Spielers ab
			x = (40+(int)(Math.random()*729)); //setzte Gegner auf zufällige X-Koordinate des Spielfelds
			y = (40+(int)(Math.random()*601)); //setzte Gegner auf zufällige Y-Koordinate des Spielfelds
		}
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

	public int getDamage() {
		return damage;
	}

	public int getLife() {
		return life;
	}

	public int getDirection() {
		return direction;
	}

	public static Rectangle getR() {
		return r;
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

	public void setDamage(int damage) {
		this.damage = damage;
	}

	public void setLife(int life) {
		this.life = life;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}

	public static void setR(Rectangle r) {
		Enemy.r = r;
	}

	public double getFrame() {
		return frame;
	}

	public void setFrame(double frame) {
		this.frame = frame;
	}
	
	

}
