package game;

import java.awt.Rectangle;

public class Player {
	
	public int p_X, p_Y, p_SpeedX, p_SpeedY, life = 100, mana = 100, damage = 0;
    private boolean movingLeft = false;
    private boolean movingRight = false;
    private boolean movingUp = false;
    private boolean movingDown = false;
    public static Rectangle up = new Rectangle (0,0,0,0);
    public static Rectangle right = new Rectangle (0,0,0,0);
    public static Rectangle down = new Rectangle (0,0,0,0);
    public static Rectangle left = new Rectangle (0,0,0,0);
    public static Rectangle r = new Rectangle (0,0,0,0);
	
	public Player (int x, int y){
		
		p_X = x;
		p_Y = y;
		p_SpeedX = 0;
		p_SpeedY = 0;
		life = 100;
		
	}
	
	public void update() {
		
		up.setBounds(p_X+16, p_Y, 1, 1);
		right.setBounds(p_X + 31, p_Y + 16, 1, 1);
		down.setBounds(p_X + 16, p_Y + 31, 1, 1);
		left.setBounds(p_X, p_Y + 16, 1, 1);
		r.setBounds(p_X, p_Y, 32, 32);
		p_X += p_SpeedX;
		p_Y += p_SpeedY;

	}

		public void moveRight() {
		Main.animating = true;
		Main.direction = 64;
		p_SpeedX =  1;
		p_SpeedY =  0;
		}

		public void moveLeft() {
			Main.animating = true;
		Main.direction = 32;
		p_SpeedX = -1;
		p_SpeedY =  0;
		}
		
		public void moveUp() {
			Main.animating = true;
	    Main.direction = 96;
		p_SpeedY = -1;
		p_SpeedX = 0;
		}

		public void moveDown() {
			Main.animating = true;
		Main.direction = 0;
		p_SpeedY =  1;
		p_SpeedX = 0;
		}
		
		   

		public void stop() {
	        if (isMovingRight() == false && isMovingLeft() == false) {
	            p_SpeedX = 0;
	        }
	        
	        if (isMovingUp() == false && isMovingDown() == false) {
	            p_SpeedY = 0;
	        }

	        if (isMovingRight() == false && isMovingLeft() == true) {
	            moveLeft();
	        }

	        if (isMovingRight() == true && isMovingLeft() == false) {
	            moveRight();
	        }
	        
	        if (isMovingUp() == false && isMovingDown() == true) {
	            moveDown();
	        }

	        if (isMovingUp() == true && isMovingDown() == false) {
	            moveUp();
	        }

		}
		
		public void stopUp(){
			setMovingUp(false);
			stop();
		}
		
		public void stopDown(){
			setMovingDown(false);
			stop();
		}
		
		public void stopLeft(){
			setMovingLeft(false);
			stop();
		}
		
		public void stopRight(){
			setMovingRight(false);
			stop();
		}

		public void attack() {

		}
		
		public void castSpell(){
			
		}
		
		public void useItem(){
			
		}
		
		public void die(){
			
		}
		
		public void takeDamage(){
			damage = 1;
		}
		
		public void stopDamage(){
			damage = 0;
		}

		public int getP_X() {
			return p_X;
		}

		public int getP_Y() {
			return p_Y;
		}

		public int getP_SpeedX() {
			return p_SpeedX;
		}

		public void setP_X(int p_X) {
			this.p_X = p_X;
		}

		public void setP_Y(int p_Y) {
			this.p_Y = p_Y;
		}

		public void setP_SpeedX(int p_SpeedX) {
			this.p_SpeedX = p_SpeedX;
		}

		public int getP_SpeedY() {
			return p_SpeedY;
		}

		public boolean isMovingLeft() {
			return movingLeft;
		}

		public boolean isMovingRight() {
			return movingRight;
		}

		public boolean isMovingUp() {
			return movingUp;
		}

		public boolean isMovingDown() {
			return movingDown;
		}

		public void setP_SpeedY(int p_SpeedY) {
			this.p_SpeedY = p_SpeedY;
		}

		public void setMovingLeft(boolean movingLeft) {
			this.movingLeft = movingLeft;
		}

		public void setMovingRight(boolean movingRight) {
			this.movingRight = movingRight;
		}

		public void setMovingUp(boolean movingUp) {
			this.movingUp = movingUp;
		}

		public void setMovingDown(boolean movingDown) {
			this.movingDown = movingDown;
		}

		public int getLife() {
			return life;
		}

		public void setLife(int life) {
			this.life = life;
		}

		public int getMana() {
			return mana;
		}

		public void setMana(int mana) {
			this.mana = mana;
		}
		
		

}
