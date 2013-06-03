package game;

import java.awt.Rectangle;

public class Enemy {
	
	private int x, y, speedX, speedY, damage, life = 100, direction ;
	private boolean animating;
    private boolean movingLeft = false;
    private boolean movingRight = false;
    private boolean movingUp = false;
    private boolean movingDown = false;
	public static Rectangle r;
	
	public Enemy (int x, int y, int damage, int life){
		
		this.x = x;
		this.y = y;
		this.damage = damage;
		this.life = life;
		r = new Rectangle();
	}
	
	public void update(){
		
//		if (speedX == 0){
//			speedY =(int) (Math.random()*3)-1;	
//		}
//		if(speedY == 0){
//			speedX =(int) (Math.random()*3)-1;
//		}
	    System.out.println((int)(Math.random()*11)-10);
	    
	    if(x == Main.player.getP_X()){
	    	if (y > Main.player.getP_Y()) moveUp();
		    else moveDown();
	    }
	    
	    if (y == Main.player.getP_Y()){
	    	if(x > Main.player.getP_X()) moveLeft();
	    	else moveRight();
	    }

	   
		
		x += speedX;
		y += speedY;
		
		r.setBounds(x, y, 32, 32);
		
		checkCollision(Main.player.r);
		
		
	}
	
	public void checkCollision(Rectangle r){
		if(r.intersects(this.r)){
			Main.player.setLife(Main.player.getLife()-damage);
			x = 0;
			y = 0;
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
	
	

}
