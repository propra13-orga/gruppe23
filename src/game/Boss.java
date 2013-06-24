package game;

import java.awt.Rectangle;
import java.io.IOException;
import java.util.ArrayList;

public class Boss {
	
	private int x, y, speedX, speedY, damage, life, direction, w = 0, frame = 0, frameAdd = 1 ;
	private long lastShoot = System.currentTimeMillis(), shootTimer = 500, lastFrame = System.currentTimeMillis(), frameTimer = 200;
	private boolean yAligned , xAligned;
	public static Rectangle r;
	public static int[] x_follow;
	public static int[] y_follow;
	public Shoot s;
	
	private ArrayList<Shoot> shoot = new ArrayList<Shoot>();
	
	//Eigenschaften des Feindes:
	
	public Boss (int x, int y, int damage, int life){
		
		this.x = x;
		this.y = y;
		this.damage = damage; //Schaden, den der Gegner anrichtet
		this.life = life; //Leben des Gegners
		r = new Rectangle(); //Rechteck am Gegner
		
	      	
	}
	
	
	
	public void update(){
		//frame ist der Faktor, um den die Position des auszuschneidenden Bildes aus den Grafiken der Gegner verschoben wird
		animation();
		   
		   if(System.currentTimeMillis() - lastShoot > shootTimer){ shoot(); lastShoot = System.currentTimeMillis(); }
		

			speedX =(int) (Math.random()*2); //zufällige X-Geschwindigkeit des Gegners


			speedY =(int) (Math.random()*2); //zufällige Y-Geschwindigkeit

		//Auf vorgegebenem Weg bewegen:

				if(Tiles.finished == true){
					
				if(xAligned == false){
					if(x > x_follow[w]){ x -= speedX; direction = 32;}
					else if(x < x_follow[w]) { x += speedX; direction = 64; }
					else if(x == x_follow[w]) xAligned = true;
				}

				else if (xAligned == true){	
				if(y > y_follow[w]){ y -= speedX; direction = 96;}
				else if(y < y_follow[w]){ y += speedX; direction = 0;}
				else if(y == y_follow[w]){ xAligned = false; w += 1 ;}
				}

				if(w == y_follow.length) w = 0;
		
		r.setBounds(x, y, 32, 32); //Quadrat um den Geist herum
		
		checkCollision(Main.player.up, Main.player.right, Main.player.down, Main.player.left); //Abfrage nach Kollision mit dem Spieler
		
		if(this.life<=0) {
			
			shoot.clear();
			
			for (int i = 0; i <= 18; i++){
	    		  for (int j = 0; j <= 18; j++){
	    			  Tiles.e_walk[i][j] = 0;
	    		  }
			}
			
			//Wenn der Gegner besiegt wurde (life <= 0), wechsle zu nächstem Raum und Level:
			
			if(Main.room==3){
				Main.room = 4;
				Main.level = 2;
				Player.lastCheckpointX = 0;
				Player.lastCheckpointY = 0;
				Tiles.lastRoomCheck = 4;
			try {
    			Main.map.loadMap("maps/map4.txt");
    		} catch (IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();

    		}}
			else if(Main.room==6){
				Main.room = 7;
				Main.level = 3;
				Player.lastCheckpointX = 0;
				Player.lastCheckpointY = 0;
				Tiles.lastRoomCheck = 7;
				try {
	    			Main.map.loadMap("maps/map7.txt");
	    		} catch (IOException e) {
	    			// TODO Auto-generated catch block
	    			e.printStackTrace();

	    		}}
			
			else if(Main.room==9){
				Main.room = 1;
				Main.level = 1;
				Player.lastCheckpointX = 0;
				Player.lastCheckpointY = 0;
				Tiles.lastRoomCheck = 1;
				Main.inMenue = true;
				try {
	    			Main.map.loadMap("maps/map1.txt");
	    		} catch (IOException e) {
	    			// TODO Auto-generated catch block
	    			e.printStackTrace();

	    		}}
			//setze Leben des (neuen) Bosses = 100:
			this.life = 100 ;
		
		}
				}
	}
	
	
	//Schuss bewegt sich (je nach Level verschieden) vom Gegner weg, Richtung (Innen-)Spielfeld
	public void shoot(){
		if(Main.room == 3){
			Shoot s = new Shoot(x , y , (int)(Math.random()*4-2), -1);
			shoot.add(s);	
		}
		
		if(Main.room == 6){
			Shoot s = new Shoot(x , y , (int)(Math.random()*4-2), 1);
			shoot.add(s);	
		}
		
		if(Main.room == 9){
			Shoot s = new Shoot(x , y , -1, (int)(Math.random()*4-2));
			shoot.add(s);	
		}
	}
	
	public void checkCollision(Rectangle ru, Rectangle rr, Rectangle rd, Rectangle rl){ //Abfrage nach Kollision
		if(ru.intersects(this.r)){
			if(Main.spell_iceshield)Main.player.setLife(Main.player.getLife()-(damage-10));
			else Main.player.setLife(Main.player.getLife()-damage);//ziehe Leben des Spielers ab
			Main.player.setP_Y(Main.player.getP_Y() - 20); //setze Spieler etwas weiter von Gegner weg (damit nicht unaufhörlich Leben abgezogen wird)
			this.life-=100; //vorläufig: ziehe gesamtes Leben des Bossgegners ab
			
		}
		else if(rr.intersects(this.r)){
			if(Animation.casted)Main.player.setLife(Main.player.getLife()-(damage-10));
			else Main.player.setLife(Main.player.getLife()-damage); //ziehe Leben des Spielers ab
			Main.player.setP_X(Main.player.getP_X() - 20); //setze Spieler etwas weiter von Gegner weg (damit nicht unaufhörlich Leben abgezogen wird)
			this.life-=100; //vorläufig: ziehe gesamtes Leben des Bossgegners ab
		}
		
		else if(rd.intersects(this.r)){
			if(Animation.casted)Main.player.setLife(Main.player.getLife()-(damage-10));
			else Main.player.setLife(Main.player.getLife()-damage); //ziehe Leben des Spielers ab
			Main.player.setP_Y(Main.player.getP_Y() + 20); //setze Spieler etwas weiter von Gegner weg (damit nicht unaufhörlich Leben abgezogen wird)
			this.life-=100; //vorläufig: ziehe gesamtes Leben des Bossgegners ab
		}
		
		else if(rl.intersects(this.r)){
			if(Animation.casted)Main.player.setLife(Main.player.getLife()-(damage-10));
			else Main.player.setLife(Main.player.getLife()-damage);//ziehe Leben des Spielers ab
			Main.player.setP_X(Main.player.getP_X() + 20); //setze Spieler etwas weiter von Gegner weg (damit nicht unaufhörlich Leben abgezogen wird)
			this.life-=100; //vorläufig: ziehe gesamtes Leben des Bossgegners ab
		}
	}
	
	public void animation(){
		if(System.currentTimeMillis() - lastFrame > frameTimer){
		frame += frameAdd;
		if(frame < 0){ frame = 1; frameAdd =  1;}
		if(frame > 2){ frame = 1; frameAdd = -1;}
		lastFrame = System.currentTimeMillis();	
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

	public void setDirection(int direction) {
		this.direction = direction;
	}

	public static void setR(Rectangle r) {
		Enemy.r = r;
	}

	public double getFrame() {
		return frame;
	}

	public void setFrame(int frame) {
		this.frame = frame;
	}

	public int getLife() {
		return life;
	}

	public void setLife(int life) {
		this.life = life;
	}
	
	public ArrayList getShoot() {
		return shoot;
}
	
	

}

