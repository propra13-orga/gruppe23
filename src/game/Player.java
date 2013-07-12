package game;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.io.IOException;

public class Player {

	public int p_X, p_Y, p_SpeedX, p_SpeedY;
	public static int total_life;
	public static int life;
	public static int mana;
	public int damage = 0;
	public int frame = 0;
	public int frameAdd = 1;
	public int direction;
	public static int lastCheckpointX = 84, lastCheckpointY = 84;
	private long lastFrame = System.currentTimeMillis() , frameTimer = 200;
	protected final int PLAYER_SPEED = 1;
    private boolean movingLeft = false;
    private boolean movingRight = false;
    private boolean movingUp = false;
    private boolean movingDown = false;
    private boolean animating = false;
    public static Rectangle up = new Rectangle (0,0,0,0);
    public static Rectangle right = new Rectangle (0,0,0,0);
    public static Rectangle down = new Rectangle (0,0,0,0);
    public static Rectangle left = new Rectangle (0,0,0,0);
    public static Rectangle r = new Rectangle (0,0,0,0);
	public static int attack;
	public static int defense;
	
	//Eigenschaften des Players:
	/*
	   * @author Martha Tatusch and Maike Fox
	   */
    public Player (int x, int y){
		
		p_X = x;
		p_Y = y;
		p_SpeedX = 0; //Geschwindigkeit in X-Richtung
		p_SpeedY = 0; //Geschwindigkeit in Y-Richtung
		life = 100; //setze Leben auf 100
		mana = 100; //setze Mana auf 100
		total_life = 3; // 3 Leben eingerichtet
	}
    
    /*
     * @author Maike Fox
     */
    public void Death(){ 
		// Wenn 3 Mal Gestorben zurück ins Menü
    	if (total_life > 0) {
    		Main.inMenue = false;
    		if (Main.checkpoint_reached == false) {
    			Main.player.setP_X(Tiles.q);
    			Main.player.setP_Y(Tiles.w);
    			Main.player.setLife(100);}
    		else {
    			Main.player.setP_X(Tiles.a);
    			Main.player.setP_Y(Tiles.b);
    			Main.player.setLife(100);

}
}

    	else {
    		p_X = 84;
    		p_Y = 84;
    		Main.level = 1;
    		Main.room = 1;
			try {
			Main.map.loadMap("maps/map1.txt"); //lade Karte 1
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    		Main.inMenue = true;

}

}
    /*
     * Author: Martha Tatusch
     */

public void update() {
		
		animation();
		
		if(life <= 0){
			Main.boss.getShoot().clear(); //lösche Schüsse aus Arraylist
			Main.boss.setLife(100); //setze Leben des Bossgegners = 100
			Death();
			Main.player.total_life -= 1;
			Main.room = Tiles.lastRoomCheck;
			
//			if(Main.room == 1){	
//			try {
//				Main.map.loadMap("maps/map1.txt"); //lade Karte 1
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			}
//			
//			if(Main.room == 2){	
//			try {
//				Main.map.loadMap("maps/map2.txt"); //lade Karte 2
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			}
//			
//			if(Main.room == 3){	
//			try {
//				Main.map.loadMap("maps/map3.txt"); //lade Karte 3
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			}
//			
//			if(Main.room == 4){	
//			try {
//				Main.map.loadMap("maps/map4.txt"); //lade Karte 4
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			}
//			
//			if(Main.room == 5){	
//			try {
//				Main.map.loadMap("maps/map5.txt"); //lade Karte 5
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			}
//			
//			if(Main.room == 6){	
//			try {
//				Main.map.loadMap("maps/map6.txt"); //lade Karte 6
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			}
//			
//			
//			if(Main.room == 7){	
//			try {
//				Main.map.loadMap("maps/map7.txt"); //lade Karte 7
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			}
//			
//			if(Main.room == 8){	
//			try {
//				Main.map.loadMap("maps/map8.txt"); //lade Karte 8
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			}
//			
//			if(Main.room == 9){	
//			try {
//				Main.map.loadMap("maps/map9.txt"); //lade Karte 9
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			}
			Main.inMenue = false; //zeichne Menü
		}
		
		up.setBounds(p_X+1, p_Y, 30, 1); //oberer Punkt am Spieler (zur Prüfung nach Kollisionen)
		right.setBounds(p_X + 32, p_Y+1, 1, 30); //rechter Punkt etc.
		down.setBounds(p_X +1, p_Y + 32, 30, 1);
		left.setBounds(p_X, p_Y + 1, 1, 30);
		r.setBounds(p_X, p_Y, 32, 32); //Quadrat um den Spieler herum
		p_X += p_SpeedX; //Verschiebung des Spielers (Bewegung in X-Richtung)
		p_Y += p_SpeedY; //Verschiebung des Spielers (Bewegung in Y-Richtung)
		

	}
	
/*
 * Author: Martha Tatusch
 */
	public void draw(Graphics g){
		
		   g.drawImage(Main.character, p_X, p_Y, p_X+32, p_Y+32, 32*frame, direction, 32*frame + 32, 32+direction, null); //zeichnet Spieler

	}
	
	/*
	 * Author: Martha Tatusch
	 */
	public void animation(){
	if(System.currentTimeMillis() - lastFrame > frameTimer){	
  	  if(animating == true){
  		  frame += frameAdd;
		   if (frame < 0){frame = 1; frameAdd =  1;}
		   if (frame > 2){frame = 1; frameAdd = -1;}
		   lastFrame = System.currentTimeMillis();
		   
  	  }
	  }
	}
	
	//Methoden zur Steuerung: (Martha Tatusch)

		public void moveRight() {
		animating = true;
		direction = 64;
		p_SpeedX =  1 * PLAYER_SPEED;
		p_SpeedY =  0;
		}

		public void moveLeft() {
		animating = true;
		direction = 32;
		p_SpeedX = -1 * PLAYER_SPEED;
		p_SpeedY =  0;
		}
		
		public void moveUp() {
		animating = true;
	    direction = 96;
		p_SpeedY = -1 * PLAYER_SPEED;
		p_SpeedX = 0;
		}

		public void moveDown() {
		animating = true;
		direction = 0;
		p_SpeedY =  1 * PLAYER_SPEED;
		p_SpeedX = 0;
		}
		
		/*
		 * Author: Martha Tatusch   
		 */
		//stoppen des Spielers; Abfrage, ob nach Loslassen einer Richtungstaste noch eine weitere Richtungstastee gedrückt ist. Wenn ja, laufe in die gegebene Richtung

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
		
		
		//Automatische Methoden:

		public static void attack() {

			

		}
		
		public void castSpell(){
			
		}
		
		public void useItem(){
			
		}
		
		public void die(){
			total_life = 0;
		}
		
		public void takeDamage(){
			damage = 1;
		}
		
		public void stopDamage(){
			damage = 0;
		}

		//Getters und Setters:
		
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
		
		public void settotal_life(int total_life) {
			this.total_life = total_life;
			System.out.println("aufgerufen");
			;
		}
		
		public int gettotal_life() {		//Getter und Setter für Lifund Total_Life hinzugefügt
			return total_life;
		}
		
		public int getMana() {
			return mana;
		}

		public void setMana(int mana) {
			this.mana = mana;
		}
		
		

}
