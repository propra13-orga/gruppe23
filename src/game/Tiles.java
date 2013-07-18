package game;

import java.awt.Image;
import java.awt.Rectangle;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;
/*
 * @author Martha Tatusch and Maike Fox
 */
public class Tiles {		
    private int tileX, tileY, speedX, speedY;
    private String type;
    public Image tileImage;
    public String floor = " ", wall = "#" , empty = "+", trap = "F", trap2 = "T", exit = "A", entry = "E", wall2 = "<", floor3 = ".", wall3 = "-", e_floor = "*", ncp = "N", checkpoint = "C", shop = "S", quest = "Q" , quest_wall = "W", quest_exit = "X", quest_wall2 = "Z" , quest_wall3 = "D", entry2 = "P";
    public static Rectangle r, t, e, n, c, s,q , W , Z, D;
    public static int[][] e_walk = new int[19][19];
    private int e_walk_x = 0, e_walk_y = 0;
    public static boolean finished = false;
    public static int checkX, checkY, lastRoomCheck, entryX, entryY;  
    public static int a;
    public static int b;
    public static int v,w, i, j;
    public static int counterShop = 1000;
	private boolean shop_reached = false;
    
    ArrayList<Rectangle> walls = new ArrayList<Rectangle>();
    
    /*
     * @author Martha Tatusch, Maike Fox
     */
    public Tiles (int x, int y, String typeInt) {
        tileX = x * 40;
        tileY = y * 40;
        r = new Rectangle();
        t = new Rectangle();
        e = new Rectangle();
        n = new Rectangle ();
        c = new Rectangle ();
        s = new Rectangle ();
        q = new Rectangle ();
        W = new Rectangle ();
        Z = new Rectangle();
        D = new Rectangle();
        	
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
        	v = Main.player.getP_X();
        	w = Main.player.getP_Y();
        	Main.enemy.setX(40+(int)(Math.random()*729)); //zufällige Platzierung des Gegners bei Wechsel des Raumes
        	Main.enemy.setY(40+(int)(Math.random()*601));
            tileImage = Main.tileentry;
        }
        
        else if(type.equals(entry2))
        {
        	if(Multiplayer.inMulti == true)
        	{
        		Main.player2.setP_Y(y*40+4);
        		Main.player2.setP_X(x*40+4);
        		i = Main.player2.getP_X();
        		j = Main.player2.getP_Y();
        		tileImage = Main.tileentry;
        	}
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
        else if (type.equals(ncp)) {  // Bild für Ncp eingefügt
        	tileImage = Main.ncp1;
        	
        }
        else if (type.equals(shop)){ //Bild für Shop eingefuegt
        	tileImage = Main.shop;
        }
        else if (type.equals(checkpoint)){  //Bild für Checkpoint eingefügt
        	tileImage = Main.checkpoint;
        }
        else if (type.equals(quest)){
        	tileImage = Main.quest;
        }
        else if (type.equals(quest_exit)){
        	tileImage = Main.Ausgang;
        }
        else if (type.equals(quest_wall)){
        	tileImage = Main.Wand;
        }
        else if (type.equals(quest_wall2)){
        	tileImage = Main.Wand2;
        }
        else if (type.equals(quest_wall3)){
        	tileImage = Main.Wand3;
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
    
/*
 * Author: Martha Tatusch
 */
    
    public void checkCollision(Rectangle up , Rectangle right, Rectangle down, Rectangle left){
        if (up.intersects(r)){
        	if(Server.inGame == true)
        	{
        		Main.player.setP_Y(Main.player.getP_Y() + 1);
        	}
        	if(Client.inGame == true)
        	{
        		Main.player2.setP_Y(Main.player2.getP_Y() + 1);
        	}
        	if(Main.inSingle == true)
        	{
        		Main.player.setP_Y(Main.player.getP_Y() + 1);
        	}
        }
        
        if (right.intersects(r)){
        	if(Server.inGame== true)
        	{
        		Main.player.setP_X(Main.player.getP_X() - 1);
        	}
        	if(Client.inGame == true)
        	{
        		Main.player2.setP_X(Main.player2.getP_X() - 1);
        	}
        	if(Main.inSingle == true)
        	{
        		Main.player.setP_X(Main.player.getP_X() - 1);
        	}
        }
        
        if (down.intersects(r)){
        	if(Server.inGame == true)
        	{
        		Main.player.setP_Y(Main.player.getP_Y() - 1);
        	}
        	if(Client.inGame == true)
        	{
        		Main.player2.setP_Y(Main.player2.getP_Y() - 1);
        	}
        	if(Main.inSingle == true)
        	{
        		Main.player.setP_Y(Main.player.getP_Y() - 1);
        	}
        }
        
        if (left.intersects(r)){
        	if(Server.inGame == true)
        	{
        		Main.player.setP_X(Main.player.getP_X() + 1);
        	}
        	if(Client.inGame == true)
        	{
        		Main.player2.setP_X(Main.player2.getP_X() + 1);
        	}
        	if(Main.inSingle == true)
        	{
        		Main.player.setP_X(Main.player.getP_X() + 1);
        	}
        }
    }
    
    
    /*
     * @author Maike Fox
     */
    public void checkpoint (Rectangle rect) throws IOException{
   	 		a = getTileX();
   	 		b = getTileY();
   	 if (rect.intersects(c)  ) {
   		 	Player.lastCheckpointX = a;
   		 	Player.lastCheckpointY = b;
   		 	Main.checkpoint_reached = true;
   		 	System.out.println("true");
   		 	Save.save();}}
    
    /*
     * @author Maike Fox
     */
    public void checkquest (Rectangle rect) {
    	if (rect.intersects(q)) {
    		setType(floor3);
    		tileImage = Main.tilefloor;
    	
    		Inventory.player_exp = Inventory.player_exp + 10;
    		Inventory.player_money = Inventory.player_money +5;
    		}
 
    }
    /*
     * @author Maike Fox
     */
   public void checkwall (Rectangle rect) {
	   if (rect.intersects(W)) {
		   setType(floor3);
		   tileImage = Main.tilefloor;
		   
	   }
   }
   /*
    * @author Maike Fox
    */
   public void checkwall2 (Rectangle rect) {
	   if (rect.intersects(Z)) {
		   setType(floor3);
		   tileImage = Main.tilefloor;
		   
	   }
   }
   /*
    * @author Maike Fox
    */
   public void checkwall3 (Rectangle rect) {
	   if (rect.intersects(D)) {
		   setType(floor3);
		   tileImage = Main.tilefloor;
		   
	   }
   }
    	
   /*
    * @author Brigitta Wanner
    */
    public void checkShop(Rectangle rect) {
    	if (rect.intersects(s)) 
    	{
    		this.counterShop = this.counterShop + 1;
    		shop(rect);
    		
    	}
	}
    /*
     * @author Brigitta Wanner
     */
    public void shop (Rectangle rect){
    if (rect.intersects(s) && counterShop == 1000) {
    	Shop shop1 = new Shop();
    	shop1.init();	
    }
    
    }
   
    /*
     * @author Martha Tatusch und Maike Fox
     */
    public void checkTrap(Rectangle rect) throws IOException{ // Wenn in eine Falle gelaufen : total_life-1
        if(rect.intersects(t)){
        	Main.player.total_life = Main.player.total_life-1;
        	Main.sound.play("sound/trap.wav");
        	trapped (rect);
        }
    }
    
    /*
     * @author Maike Fox
     */
    public void trapped (Rectangle rect) throws IOException{		//Aufruf von Death()
    	if (rect.intersects(t) ) {
    		Main.player.Death();}
    			
    		}
    
    /*
     * Author: Martha Tatusch
     */
    
    public void checkExit(Rectangle rect){
        if(rect.intersects(e)){
        	if(Main.inSingle == true){
            	Main.checkpoint_reached = false;
            	Main.sound.play("sound/newroom.wav");
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
            	if(Multiplayer.inMulti == true)
            	{
            		if(Server.inGame == true)
            		{
            			Server.inGame = false;
            			ServerGUI.server_frame.setVisible(true);
            			ServerGUI.inServer = true;
            			Multiplayer.playerWon("Server");
            		}
            		if(Client.inGame == true)
            		{
            			Client.inGame = false;
            			ClientGUI.client_frame.setVisible(true);
            			ClientGUI.inClient = true;
            			Multiplayer.playerWon("Client");
            		}
            		
            	}
            	
            }
    }
    
    /*
     * @author Maike Fox
     */
    
    private void checkNcp(Rectangle rect) {
		if (Main.ccheckpoint == true && rect.intersects(c)) {
			story();
			}
		}
    /*
     * @author Maike Fox
     */
	public static void story () {
		Main.ccheckpoint = false;
		Main.sound.play("sound/npc.wav");
		if (Main.level == 1){
		
		JOptionPane.showMessageDialog(null,
				"Dies ist die erste Hürde, die unser Rotkäppchen meistern muss. \n Außerdem Spuckt es! Finde alle 3 Beeren pro Raum, um Erfahrungspunkte in Geld zu wandeln und somit immer bessere Waffen zu bekommen.\n Das Inventory kannst du mit der I-Taste aufrufen, den Shop mit der E-Taste.", "Story",
				JOptionPane.OK_CANCEL_OPTION);}
		
		else if (Main.level == 2){
			JOptionPane.showMessageDialog(null,
					"Auch hier geht die Suche nach den Beeren für Großmutters Wein weiter.\n Finde den Ausweg, aber Vorsicht: nicht alle Wänden sind das, was sie scheinen zu sein!", "Story",
					JOptionPane.OK_CANCEL_OPTION);}
		
		else {
			JOptionPane.showMessageDialog(null,
				"Das Ende naht!Bereite dich auf den Kampf vor!", "Story",
				JOptionPane.OK_CANCEL_OPTION); }
}
	
	 /*
     * @author Martha Tatusch, Maike Fox
     */
    
    public void update(){
  	  
        t.setBounds(tileX+19, tileY+19, 2, 2);
        e.setBounds(tileX+19, tileY+19, 2, 2);
        n.setBounds(tileX+19, tileY+19, 2, 2);
        c.setBounds(tileX+19, tileY+19, 2, 2);	//Update für NCP und Checkpoint hinzugefügt
        s.setBounds(tileX+19, tileY+19, 2, 2);
        q.setBounds(tileX+19, tileY+19, 2, 2);
        W.setBounds(tileX+19, tileY+19, 2, 2);
        Z.setBounds(tileX+19, tileY+19, 2, 2);
        D.setBounds(tileX+19, tileY+19, 2, 2);
        
        
        if (type.equals(wall)){
        	if(Main.inSingle == true)
        	{
        		r.setBounds(tileX, tileY, 40, 40);
        		checkCollision(Player.up, Player.right , Player.down , Player.left);
        	}
        	if(Server.inGame == true)
        	{
        		r.setBounds(tileX, tileY, 40, 40);
        		checkCollision(Player.up, Player.right , Player.down , Player.left);
        	}
        	if(Client.inGame == true)
        	{
        		r.setBounds(tileX, tileY, 40, 40);
        		checkCollision(SecondPlayer.up, SecondPlayer.right , SecondPlayer.down , SecondPlayer.left);
        	}
        }
        
        if (type.equals(wall2)){
        	if(Main.inSingle == true)
        	{
        		r.setBounds(tileX, tileY, 40, 40);
        		checkCollision(Player.up, Player.right , Player.down , Player.left);
        	}
        	if(Server.inGame == true)
        	{
        		r.setBounds(tileX, tileY, 40, 40);
        		checkCollision(Player.up, Player.right , Player.down , Player.left);
        	}
        	if(Client.inGame == true)
        	{
        		r.setBounds(tileX, tileY, 40, 40);
        		checkCollision(SecondPlayer.up, SecondPlayer.right , SecondPlayer.down , SecondPlayer.left);
        	}
        }
        
        if (type.equals(wall3)){
        	if(Main.inSingle == true)
        	{
        		r.setBounds(tileX, tileY, 40, 40);
        		checkCollision(Player.up, Player.right , Player.down , Player.left);
        	}
        	if(Server.inGame == true)
        	{
        		r.setBounds(tileX, tileY, 40, 40);
        		checkCollision(Player.up, Player.right , Player.down , Player.left);
        	}
        	if(Client.inGame == true)
        	{
        		r.setBounds(tileX, tileY, 40, 40);
        		checkCollision(SecondPlayer.up, SecondPlayer.right , SecondPlayer.down , SecondPlayer.left);
        	}
        }
        
        if (type.equals(trap)){
            try {
				checkTrap(Player.r);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}	
        }
        
        if (type.equals(trap2)){
        	try {
				checkTrap(Player.r);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        }
        
        if (type.equals(exit))
        {
        	if(Main.inSingle == true)
        	{
        		checkExit(Player.r);
        	}
        	if(Server.inGame == true)
        	{
        		checkExit(Player.r);
        	}
        	if(Client.inGame == true)
        	{
        		checkExit(SecondPlayer.r);
        	}
        }
        if (type.equals(shop)){ //Update fuer shop
        	checkShop(Player.r);
        }
        
        
        if (type.equals(ncp)){  //Update für NCP
        	checkNcp(Player.r);
        }
        if (type.equals(quest)){
        	checkquest(Player.r);
        }	
        if (type.equals(quest_wall)){
        	checkwall(Player.r);
        }
        if (type.equals(quest_wall2)){
        	checkwall(Player.r);
        }
        if (type.equals(quest_wall3)){
        	checkwall(Player.r);
        }
    	if (type.equals(checkpoint)){ //Update für Checkpoint
    		try {
				checkpoint (Player.r);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
    	}
  
    	
    	
    }

// Getters und Setters:


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