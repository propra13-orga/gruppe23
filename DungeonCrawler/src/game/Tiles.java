package game;

import java.awt.Image;
import java.awt.Rectangle;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Tiles {		//public string floor für NCP und Checkpoint; public static rectangle n, c
	
    private int tileX, tileY, speedX, speedY;
    private String type;
    public Image tileImage;
    public String floor = " ", wall = "#" , empty = "+", trap = "F", trap2 = "T", exit = "A", entry = "E", wall2 = "<", floor3 = ".", wall3 = "-", e_floor = "*", ncp = "N", checkpoint = "C", shop = "S", muenzen = "M", all_m = "P";
    public static Rectangle r, t, e, n, c, s, m, p;
    public static int[][] e_walk = new int[19][19];
    private int e_walk_x = 0, e_walk_y = 0;
    public static boolean finished = false;
    public static int checkX, checkY, lastRoomCheck, entryX, entryY;  
    public static int a;
    public static int b;
    public static int q,w;
    Shop shop1 = new Shop();
    
    ArrayList<Rectangle> walls = new ArrayList<Rectangle>();
    
    public Tiles (int x, int y, String typeInt) {
        tileX = x * 40;
        tileY = y * 40;
        r = new Rectangle();
        t = new Rectangle();
        e = new Rectangle();
        n = new Rectangle ();
        c = new Rectangle ();
        s = new Rectangle ();
        m = new Rectangle();
        p = new Rectangle();
        	//Neue Rectangles für n und c
        type = typeInt;
        


        if (type.equals(floor)) {
            tileImage = Main.tilefloor;
        } else if (type.equals(wall)) {
            tileImage = Main.tilewall;
        }
        else if (type.equals(trap)) {
            tileImage = Main.tiletrap;
            t.setBounds(tileX+10, tileY+10, 20, 20);
        }
        
        else if (type.equals(trap2)) {
            tileImage = Main.tiletrap2;
            t.setBounds(tileX+10, tileY+10, 20, 20);
        }
        
        else if (type.equals(exit)) {
            tileImage = Main.tileexit;
        }
        
        else if(type.equals(e_floor)){
        	if(Main.room >=1 && Main.room <= 7){
        		tileImage = Main.tilefloor;	
        	}
        	else if (Main.room == 8){
        		tileImage = Main.tilefloor3;
        	}
        	e_walk_x = x;
        	e_walk_y = y;
        	e_walk[x][y] = 1;
        	
        	
        }
        else if (type.equals(empty)) {
            tileImage = null;
        }
        else if (type.equals(entry)) {
        	//entryX = tileX;
        	//entryY = tileY;
        	Main.player.setP_X(x*40+4);
        	Main.player.setP_Y(y*40+4);
        	q = Main.player.getP_X();
        	w = Main.player.getP_Y();
        	Main.enemy.setX(40+(int)(Math.random()*729)); //zufällige Platzierung des Gegners bei Wechsel des Raumes
        	Main.enemy.setY(40+(int)(Math.random()*601));
            tileImage = Main.tileentry;
        }
        
        else if (type.equals(wall2)) {
        	tileImage = Main.tilewall2;
        }
        
        else if (type.equals(floor3)) {
        	tileImage = Main.tilefloor3;
        }
        else if (type.equals(wall3)) {
        	tileImage = Main.tilewall3;
        }
        
        else if (type.equals(muenzen)){
        	tileImage = Main.geld;
        }
        
        else if (type.equals(all_m)){
        	tileImage = Main.all_middle;
        }
        
        else if (type.equals(ncp)) {  // Bild für Ncp eingefügt
        	tileImage = Main.ncp1;
        	
        }
        else if (type.equals(shop)){ //Bild für Shop eingefuegt
        	tileImage = Main.shop;
        }
        else if (type.equals(checkpoint)){  //Bild für Checkpoint eingefügt
        	tileImage = Main.checkpoint;
        }
      
      if(x == 19 && y == 19){    	  
  	  for (int i = 0; i <= 18; i++){
		  for (int j = 0; j <= 18; j++){
			  if (Tiles.e_walk[i][j] == 1){
		  System.out.println("x-Koordinate: " + i + " y_Koordinate: " + j + " GegnerPfad: " + Tiles.e_walk[i][j]);
			  }
		  }
	  }
	
      
      
      
      int walk_count = 0;
		
  	  for (int i = 0; i <= 18; i++){
		  for (int j = 0; j <= 18; j++){
			  if (Tiles.e_walk[i][j] == 1){
				  walk_count += 1;	    		  
			  }
		  }
	  }
  	  
  	  System.out.println(walk_count);
  	  
  	  Boss.x_follow = new int[walk_count];
  	  Boss.y_follow = new int[walk_count];
  	  
  	     int k = 0;	      	    	 
      	  for (int i = 0; i <= 18; i++){
    		  for (int j = 0; j <= 18; j++){
    			  if (Tiles.e_walk[i][j] == 1){
    				  Boss.x_follow[k] = i * 40;
    				  Boss.y_follow[k] = j * 40;
    				  k += 1;
    			  
    		  }
    	  }
    	}
  	     
  	   for(int i = 0; i < walk_count; i++){
  		   System.out.println(Boss.x_follow[i] + " " + Boss.y_follow[i]);
  	   }
  	   
  	   finished = true;
  	   
      }

    }
    

    
    public void checkCollision(Rectangle up , Rectangle right, Rectangle down, Rectangle left){
        if (up.intersects(r)){
        	Main.player.setP_Y(Main.player.getP_Y() + 1);
        }
        
        if (right.intersects(r)){
        	Main.player.setP_X(Main.player.getP_X() - 1);
        }
        
        if (down.intersects(r)){
        	Main.player.setP_Y(Main.player.getP_Y() - 1);
        }
        
        if (left.intersects(r)){
        	Main.player.setP_X(Main.player.getP_X() + 1);
        }
        
        

        }
    
    
  //Versuch MEthode Checkpoint auszufuhren
    public void checkpoint (Rectangle rect){
   	 		a = getTileX();
   	 		b = getTileY();
   	 if (rect.intersects(c)  ) {
   	
   		 	Main.checkpoint_reached = true;
   		 	System.out.println("true");}}
    
    public void shop (Rectangle rect){
    	a = getTileX();
    	b = getTileY();
    if (rect.intersects(s)) {
    	Main.shop_reached = true;
    	System.out.println("true");
    }
    }
   
    
    public void checkTrap(Rectangle rect){ // Wenn in eine Falle gelaufen : total_life-1
        if(rect.intersects(t)){
        	Main.player.total_life = Main.player.total_life-1;
        	trapped (rect);
        }
    }
    public void trapped (Rectangle rect){		//Aufruf von Death()
    	if (rect.intersects(t) ) {
    		Main.player.Death();}
    			
    		}
    
    public void checkExit(Rectangle rect){
        if(rect.intersects(e)){
        	Main.checkpoint_reached = false;
        	switch(Main.room){
        	case 1:       		
        		try {
        			Main.map.loadMap("maps/map2.txt");
        		} catch (IOException e) {
        			// TODO Auto-generated catch block
        			e.printStackTrace();
        		}
        		Main.room = 2;
        		break;
        		
        	case 2:
        		try {
        			Main.map.loadMap("maps/map3.txt");
        		} catch (IOException e) {
        			// TODO Auto-generated catch block
        			e.printStackTrace();
        		}
        		Main.boss.setX(Boss.x_follow[0]);
        		Main.boss.setY(Boss.y_follow[0]);
        		Main.room = 3;
        		break;
        	        		
        	case 4:        		
        		try {
        			Main.map.loadMap("maps/map5.txt");
        		} catch (IOException e) {
        			// TODO Auto-generated catch block
        			e.printStackTrace();
        		}
        		Main.room = 5;
        		Main.boss.setLife(100);
        		break;
        		
        	case 5:        		
        		try {
        			Main.map.loadMap("maps/map6.txt");
        		} catch (IOException e) {
        			// TODO Auto-generated catch block
        			e.printStackTrace();
        		}
        		Main.boss.setX(Boss.x_follow[0]);
        		Main.boss.setY(Boss.y_follow[0]);
        		Main.room = 6;
        		break;
        		       	
        		
        	case 7:        		
        		try {
        			Main.map.loadMap("maps/map8.txt");
        		} catch (IOException e) {
        			// TODO Auto-generated catch block
        			e.printStackTrace();
        		}
        		Main.room = 8;
        		Main.boss.setLife(100);
        		break;
        		
        	case 8:        		
        		try {
        			Main.map.loadMap("maps/map9.txt");
        		} catch (IOException e) {
        			// TODO Auto-generated catch block
        			e.printStackTrace();
        		}
        		Main.boss.setX(Boss.x_follow[0]);
        		Main.boss.setY(Boss.y_follow[0]);
        		Main.room = 9;
          		break;
        		
        				}
        	
        	Main.fenster.setTitle("RotkÃ¤ppchen 2.0 - Level " + String.valueOf(Main.level));
        	      	
        	
        }
    }
    
    //CheckNcp hinzugefuegt
    
    private void checkNcp(Rectangle rect) {
		if (Main.ccheckpoint == true && rect.intersects(c)) {
			story();
			}
		}
	public static void story () {
		Main.ccheckpoint = false;
		if (Main.level == 1){
		
		JOptionPane.showMessageDialog(null,
				"Dies ist die erste Hürde, die unser Rotkäppchen meistern muss. Finde den Ausweg, aber Vorsicht, es Spuckt! Nutze Waffen,Zauber und Schutzschilde um dich zu verteidigen.", "Story",
				JOptionPane.OK_CANCEL_OPTION);}
		
		else if (Main.level == 2){
			JOptionPane.showMessageDialog(null,
					"Weiter gehts.", "Story",
					JOptionPane.OK_CANCEL_OPTION);}
		
		else {
			JOptionPane.showMessageDialog(null,
				"Das Ende naht!Bereite dich auf den Kampf vor!", "Story",
				JOptionPane.OK_CANCEL_OPTION); }
}
	
    private void checkShop(Rectangle rect) {
    	if (Main.checkShop == true && rect.intersects(s)) {
    		shop1.init();
    		
    		
    	}
		
	}
    
    private void checkmuenzen(Rectangle rect) {
		if (rect.intersects(m)){
			Inventory.player_money ++;
			
		}
		
	}
    
	private void checkAll_m(Rectangle rect) {
		if (rect.intersects(p)){
			Player.life =+10;
			Player.mana =+10;
		}
		
	}

	
    
    
    public void update(){
  	  
        t.setBounds(tileX+19, tileY+19, 2, 2);
        e.setBounds(tileX+19, tileY+19, 2, 2);
        n.setBounds(tileX+19, tileY+19, 2, 2);
        c.setBounds(tileX+19, tileY+19, 2, 2);	//Update für NCP und Checkpoint hinzugefügt
        s.setBounds(tileX+19, tileY+19, 2, 2);
        m.setBounds(tileX+19, tileY+19, 2, 2);
        p.setBounds(tileX+19, tileY+19, 2, 2);
        
        
        if (type.equals(wall)){
        	r.setBounds(tileX, tileY, 40, 40);
            checkCollision(Player.up, Player.right , Player.down , Player.left);         
        }
        
        if (type.equals(wall2)){
        	r.setBounds(tileX, tileY, 40, 40);
            checkCollision(Player.up, Player.right , Player.down , Player.left);
        }
        
        if (type.equals(wall3)){
        	r.setBounds(tileX, tileY, 40, 40);
            checkCollision(Player.up, Player.right , Player.down , Player.left);
            

        }
        
        if (type.equals(trap)){
            checkTrap(Player.r);	
        }
        
        if (type.equals(trap2)){
        	checkTrap(Player.r);
        }
        
        if (type.equals(exit)){
            checkExit(Player.r);	
        }
        if (type.equals(shop)){ //Update fuer shop
        	checkShop (Player.r);
        }
        if (type.equals(ncp)){  //Update für NCP
        	checkNcp(Player.r);
        }
    	if (type.equals(checkpoint)){ //Update für Checkpoint
    		checkpoint (Player.r);
    	}
    	
    	if (type.equals(muenzen)){
    		checkmuenzen(Player.r);
    	}
    	
    	if (type.equals(all_m)){
    		checkAll_m(Player.r);
    	}
    	
    	
    }




	public int getTileX() {
		return tileX;
	}

	public int getTileY() {
		return tileY;
	}

	public int getSpeedX() {
		return speedX;
	}

	public int getSpeedY() {
		return speedY;
	}

	public String getType() {
		return type;
	}

	public Image getTileImage() {
		return tileImage;
	}

	public void setTileX(int tileX) {
		this.tileX = tileX;
	}

	public void setTileY(int tileY) {
		this.tileY = tileY;
	}

	public void setSpeedX(int speedX) {
		this.speedX = speedX;
	}

	public void setSpeedY(int speedY) {
		this.speedY = speedY;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setTileImage(Image tileImage) {
		this.tileImage = tileImage;
	}
    
    

}
