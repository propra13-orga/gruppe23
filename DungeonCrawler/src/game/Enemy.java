package game;

import java.awt.Rectangle;

public class Enemy {
	
	private int x, y, speedX, speedY, damage, life = 100, direction ;
	private boolean animating, yAligned , xAligned;
    private boolean movingLeft = false;
    private boolean movingRight = false;
    private boolean movingUp = false;
    private boolean movingDown = false;
	public static Rectangle r;
	private double frame = 0, frameAdd;
	
	public Enemy (int x, int y, int damage, int life){
		
		this.x = x;
		this.y = y;
		this.damage = damage;
		this.life = life;
		r = new Rectangle();
	}
	
	public void update(){
		
		   if (frame < 0.5) frameAdd =  .1;
		   if (frame > 2.5) frameAdd = -.1;
		   frame += frameAdd;
		

			speedX =(int) (Math.random()*2);


			speedY =(int) (Math.random()*2);

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
	   
		
//		x += speedX;
//		y += speedY;
		
		r.setBounds(x, y, 32, 32);
		
		checkCollision(Main.player.r);
		
		
	}
	
	public void checkCollision(Rectangle r){
		if(r.intersects(this.r)){
			Main.player.setLife(Main.player.getLife()-damage);
			x = (40+(int)(Math.random()*729));
			y = (40+(int)(Math.random()*601));
		}
	}
	
	public void moveRight() {
		animating = true;
	direction = 64;
	speedX = 1;
//	speedY =  0;
	}

	public void moveLeft() {
		animating = true;
	direction = 32;
	speedX = -1;
//	speedY =  0;
	}
	
	public void moveUp() {
		animating = true;
    direction = 96;
	speedY = -1;
//	speedX = 0;
	}

	public void moveDown() {
		animating = true;
	direction = 0;
	speedY =  1;
//	speedX = 0;
	}

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

	public boolean isAnimating() {
		return animating;
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

	public void setAnimating(boolean animating) {
		this.animating = animating;
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
