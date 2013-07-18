package game;

import java.awt.Graphics;
import java.awt.Rectangle;

public class SecondPlayer 
{
	public int ps_X, ps_Y, ps_SpeedX, ps_SpeedY;
	public static int total_lifeS;
	public static int lifeS;
	public static int manaS;
	public static Rectangle up = new Rectangle (0,0,0,0);
    public static Rectangle right = new Rectangle (0,0,0,0);
    public static Rectangle down = new Rectangle (0,0,0,0);
    public static Rectangle left = new Rectangle (0,0,0,0);
    public static Rectangle r = new Rectangle (0,0,0,0);
    private long lastFrame = System.currentTimeMillis() , frameTimer = 200;
	protected final int PLAYER_SPEED = 1;
    private boolean movingLeft = false;
    private boolean movingRight = false;
    private boolean movingUp = false;
    private boolean movingDown = false;
    private boolean animating = false;
    public int frame = 0;
	public int frameAdd = 1;
	public int directionS;
	
	public SecondPlayer(int x, int y)
	{
		ps_X = x;
		ps_Y = y;
		ps_SpeedX = 0; //Geschwindigkeit in X-Richtung
		ps_SpeedY = 0; //Geschwindigkeit in Y-Richtung
		lifeS = 100; //setze Leben auf 100
		manaS = 100; //setze Mana auf 100
		total_lifeS = 3; // 3 Leben eingerichtet
	}
	
	public void update() {
		
		animation();
		
		up.setBounds(ps_X+1, ps_Y, 30, 1); //oberer Punkt am Spieler (zur Prüfung nach Kollisionen)
		right.setBounds(ps_X + 32, ps_Y+1, 1, 30); //rechter Punkt etc.
		down.setBounds(ps_X +1, ps_Y + 32, 30, 1);
		left.setBounds(ps_X, ps_Y + 1, 1, 30);
		r.setBounds(ps_X, ps_Y, 32, 32); //Quadrat um den Spieler herum
		ps_X += ps_SpeedX; //Verschiebung des Spielers (Bewegung in X-Richtung)
		ps_Y += ps_SpeedY; //Verschiebung des Spielers (Bewegung in Y-Richtung)
	}

	public void animation()
	{
	if(System.currentTimeMillis() - lastFrame > frameTimer){	
  	  if(animating == true)
  	  {
  		  frame += frameAdd;
		   if (frame < 0){frame = 1; frameAdd =  1;}
		   if (frame > 2){frame = 1; frameAdd = -1;}
		   lastFrame = System.currentTimeMillis();
		   
  	  	}
	  }
	}
	public void draw(Graphics g)
	{
		
		   g.drawImage(Main.boss3, ps_X, ps_Y, ps_X+32, ps_Y+32, 32*frame, directionS, 32*frame + 32, 32+directionS, null); //zeichnet Spieler

	}

	//Methoden zur Steuerung: (Martha Tatusch)

		public void moveRight() {
		animating = true;
		directionS = 64;
		ps_SpeedX =  1 * PLAYER_SPEED;
		ps_SpeedY =  0;
		}

		public void moveLeft() {
		animating = true;
		directionS = 32;
		ps_SpeedX = -1 * PLAYER_SPEED;
		ps_SpeedY =  0;
		}
		
		public void moveUp() {
		animating = true;
	    directionS = 96;
		ps_SpeedY = -1 * PLAYER_SPEED;
		ps_SpeedX = 0;
		}

		public void moveDown() {
		animating = true;
		directionS = 0;
		ps_SpeedY =  1 * PLAYER_SPEED;
		ps_SpeedX = 0;
		}
		
		/*
		 * Author: Martha Tatusch   
		 */
		//stoppen des Spielers; Abfrage, ob nach Loslassen einer Richtungstaste noch eine weitere Richtungstastee gedrückt ist. Wenn ja, laufe in die gegebene Richtung

		public void stop() {
	        if (isMovingRight() == false && isMovingLeft() == false) {
	            ps_SpeedX = 0;
	        }
	        
	        if (isMovingUp() == false && isMovingDown() == false) {
	            ps_SpeedY = 0;
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
		
		/*
		 * Author: Martha Tatusch
		 */
		
		//Methoden zum Anhalten des Spielers; werden ausgeführt bei Loslassen der Richtungstasten
		
		public void stopUp(){
			animating = false;
			setMovingUp(false);
			stop();
		}
		
		public void stopDown(){
			animating = false;
			setMovingDown(false);
			stop();
		}
		
		public void stopLeft(){
			animating = false;
			setMovingLeft(false);
			stop();
		}
		
		public void stopRight(){
			animating = false;
			setMovingRight(false);
			stop();
		}
		
		//Getters und Setters:
		
				public int getP_X() {
					return ps_X;
				}

				public int getP_Y() {
					return ps_Y;
				}

				public int getP_SpeedX() {
					return ps_SpeedX;
				}

				public void setP_X(int p_X) {
					this.ps_X = p_X;
				}

				public void setP_Y(int p_Y) {
					this.ps_Y = p_Y;
				}

				public void setP_SpeedX(int p_SpeedX) {
					this.ps_SpeedX = p_SpeedX;
				}

				public int getP_SpeedY() {
					return ps_SpeedY;
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
					this.ps_SpeedY = p_SpeedY;
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
					return lifeS;
				}
				
				public int getMana() {
					return manaS;
				}
}