package game;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;


import javax.swing.JPanel;

public class Main extends Applet implements Runnable, KeyListener{

	private URL base;
	private Graphics second;
	public static Map map;
	public static Sound sound;
	
	//Spielelemente   // public static Imge ncp, ncp1,checkpoint,story hinzugefügt
	public static Image image, character, health_empty, health_full, heart, mana_full, mana_empty,enemy_ghost, enemy_monster, lightning, boss1, boss2, boss3, ncp, lightning_claw, shop;
	public static Image axe, sword, spear, mana_small, mana_big, health_small, health_big, all_small, all_middle;
	public static Image tilefloor, tilewall, tiletrap, tiletrap2, tileexit, tileentry, tilewall2, tilefloor3, tilewall3, ncp1, checkpoint, story, geld;
	public static Player player;
	public static Enemy enemy;
	public Animation animation;
	public Inventory invent;
	public static ArrayList<Item> itemlist = new ArrayList<Item>();
	public static Item item;
	public static Shop shop1;
	public Menue menu;
	public static Boss boss;
	public static Frame fenster;
	public static long lastMusic = System.currentTimeMillis(), soundInterval = 31767;

	private int damage;
	//public static Item item;
	public static Player mana;

	
	
	//Variablen			// public static boolean ccheckpoint hinzugefügt
	public static double frame = 0, frameAdd = .1;
	public static int room, level;
	public static int direction;
	public static boolean animating = false, spell_iceshield = false, inMenue = true, lightningclaw = false;
	public static boolean ccheckpoint = false;
	public static boolean checkpoint_reached=false;


	//inMenue=true: zeichne MenÃ¼; inMenue=false: zeichne Spielraum; spell_iceshield=false/true: Schutzschild aus/an;

	public String playerName;
	private Tiles t;
	
	/*
	Author: Martha Tatusch
	 */

   @Override
   public void init() {

	      setSize(800, 800); //Größe des Fensters
	      setBackground(Color.BLACK); //Hintergrundfarbe des Fensters
	      setFocusable(true); //Fokus auf das Fenster setzen
	      addKeyListener(this);
	      addMouseListener(new Mouse());
	      fenster = (Frame) this.getParent().getParent(); 
	      fenster.setTitle("Rotkäppchen 2.0"); //Fenster-Titel
	     
//	      TimerTask action = new TimerTask() { //Timertask, der den Sound abspielt
//	    	  	            public void run() {
//	    
//	    	  	            	sound.play("sound/bg_music.wav");
//	    	  	            	
//	    	             }
//	    	  	            
//	     
//	    	  	 
//	    	  	        };
//	    	  	        
//	    	  	        
//	    	  	 
//	    	  	        Timer caretaker = new Timer(); //neuen Timer erstellen
//	    	  	        caretaker.schedule(action, 0, 31767); //Timertask alle 31,767 Sekunden ausführen
	      
	  	try 
	  	{
	  		base = getDocumentBase(); //Pfad des Spiels
	  	} 
	  	catch (Exception e) 
	  	{

	  	}
  	


  		// Bilder:			//ncp1, Story und Checkpont als Bilder hinzugefuegt
  	
  		character = getImage(base, "gfx/MainChar.png");
  		boss1 = getImage(base, "gfx/Boss1.png");
  		boss2 = getImage(base, "gfx/Boss2.png");
  		boss3 = getImage(base, "gfx/Boss3.png");
  		enemy_ghost = getImage(base,"gfx/Geist.png");
  		enemy_monster = getImage(base, "gfx/monster.png");
  		tilefloor = getImage(base, "gfx/Tile_Floor.png");
  		tilewall = getImage(base, "gfx/Tile_Wall.png");
  		health_empty = getImage(base,"gfx/LifeOrbEmpty.png");
  		health_full = getImage(base,"gfx/LifeOrbFull.png");
  		mana_empty = getImage(base,"gfx/ManaOrbEmpty.png");
  		mana_full = getImage(base,"gfx/ManaOrbFull.png");
  		tiletrap = getImage(base, "gfx/Trap.png");
  		tiletrap2 = getImage(base, "gfx/Trap2.png");
  		tileexit = getImage(base, "gfx/Exit.png");
  		tileentry = getImage(base, "gfx/Exit.png");
  		lightning_claw = getImage(base, "gfx/lightningclaw.png");
  		lightning = getImage(base, "gfx/iceshield.png");
  		tilewall2 = getImage(base, "gfx/Wall2.png");
  		tilefloor3 = getImage(base, "gfx/Floor3.png");
  		tilewall3 = getImage(base, "gfx/Wall3.png");
  		axe = getImage(base, "gfx/Axe.png");
  		sword = getImage(base, "gfx/Sword.png");
  		spear = getImage(base, "gfx/Spear.png");
  		mana_small = getImage(base, "gfx/Mana_Small");
  		mana_big = getImage(base, "gfx/Mana_Big");
  		health_small = getImage(base, "gfx/Health_Small");
  		health_big = getImage(base, "gfx/Health_Big");
  		all_small = getImage(base, "gfx/All_Small");
  		all_middle = getImage(base, "gfx/All_Middle.png");
  		ncp1 = getImage(base, "gfx/ncp.gif");
  		story = getImage(base, "gfx/Story.png");
  		checkpoint = getImage (base, "gfx/Checkpoint.png");
  		shop = getImage(base, "gfx/Tile_Shop.png");
  		geld = getImage(base, "gfx/geld.png");
  		heart = getImage(base, "gfx/Heart.png");

   }

  /*
   * @author Brigitta Wanner
   */
   public void itemInit()
   {
	   Weapon Axe = new Weapon("Axt",5, 2, 10);
	   Weapon Sword = new Weapon("Sword",4, 3, 10);
	   Potion Mana_Small = new Potion("Small Manapotion",5, 2, 10);
	   Potion Mana_Big = new Potion("Big Manapotion",5, 2, 10);
	   Potion Health_Small = new Potion("Small Healthpotion",5, 2, 10);
	   Potion Health_Big = new Potion("Big Healthpotion",5, 2, 10);
	   
	   itemlist.add(0,Axe);
	   itemlist.add(1,Sword);
	   itemlist.add(2,Mana_Small);
	   itemlist.add(3,Mana_Big);
	   itemlist.add(4,Health_Small);
	   itemlist.add(5,Health_Big);
	   invent.inventory.add(0, Main.itemlist.get(1));
	   invent.inventory.add(1, Main.itemlist.get(5));
	   
   }
/*
 * Author: Martha Tatusch
 */
   
   @Override
   public void start() 
   {
	   try {
		Save.load();
	} catch (IOException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	   room = 1 ;
	   level = 1;
	   player = new Player(400 ,400);
	   map = new Map();
	   boss = new Boss(0,0,20,100);
	   sound = new Sound();
	   animation = new Animation();
	   invent = new Inventory();
	   itemInit();
	   shop1 = new Shop();
	   menu = new Menue();
	   inMenue = true;
	   enemy = new Enemy(0, 0, 10);
	   
	   try {
		Save.save();
	} catch (IOException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	   
	   
	   Thread thread = new Thread(this);
	   thread.start();
	   
       try 
       {
           map.loadMap("maps/map1.txt"); //Karte laden
       } 
       catch (IOException e) 
       {
           e.printStackTrace(); 
       }
       
   }
	
   /*
    * Author: Martha Tatusch
    */

	//Buffer für Bilder
	@Override
	public void update(Graphics g) 
	{
		if (image == null) 
		{
			image = createImage(this.getWidth(), this.getHeight());
			second = image.getGraphics();
		}
	
	second.setColor(getBackground());
	second.fillRect(0, 0, getWidth(), getHeight());
	second.setColor(getForeground());
	paint(second);
	
	g.drawImage(image, 0, 0, this);
	}

   @Override
   public void stop() {
   // TODO Auto-generated method stub
   }

   @Override
   public void destroy() {
   // TODO Auto-generated method stub
   }
   
   /*
    * Author: Martha Tatusch
    */

   @Override
   public void run() 
   {
	  while(true){
		  
      while (!inMenue) 
      {   
    	  if(System.currentTimeMillis() - lastMusic > soundInterval){
    		  sound.play("sound/bg_music.wav");
    		  lastMusic = System.currentTimeMillis();
    	  }
    	  
    	  updateTiles();
    	  if(lightningclaw == true) animation.play();
    	  if(spell_iceshield == true) animation.play();
    	 
    	  player.update(); //Spieler wird aktualisiert
    		if(room != 9){
  		  enemy.update();  //gegner wird aktualisiert   		  
    		}
    	  
      // frame schwankt zwischen 0 und 2
      
      
      		
    	  
		   ArrayList shoot = boss.getShoot();
		   for (int j = 0; j < shoot.size(); j++) {
			   Shoot s = (Shoot) shoot.get(j);
			   if (s.isVisible() == true) {
				   s.update();
				   s.setT(t);
			   } else {
				   shoot.remove(j);
			   }
		   }
    	  
	
    	  
	    	  if(room == 3 || room == 6 || room == 9)boss.update();
	       	  
	    	  repaint();
	    	  	try 
	    	  	{
	    	  		Thread.sleep(17);
	    	  	} 
	    	  	catch (InterruptedException e) 
	    	  	{
	            e.printStackTrace();
	    	  	}
      }
	  }
   }
	
   /*
    * Author: Martha Tatusch
    */
   
   @Override
   public void paint(Graphics g)
   {

	   

	   if(inMenue == true) menu.draw(g); //zeichnet das MenÃ¼, wenn inMenue=true
	   else{   
	   paintTiles(g); //zeichnet den Raum
	   player.draw(g);
	   
	   //zeichnet Gegner je nach Level:
	   if(level == 1 || (level == 3 && room != 9)){
		   g.drawImage(enemy_ghost, enemy.getX(), enemy.getY(), enemy.getX()+32, enemy.getY()+32, 32*(int)enemy.getFrame(), enemy.getDirection(), 32*(int)enemy.getFrame() + 32, 32+enemy.getDirection(), this);
	   }
	   else if(level == 2){
		   g.drawImage(enemy_monster, enemy.getX(), enemy.getY(), enemy.getX()+32, enemy.getY()+32, 32*(int)enemy.getFrame(), enemy.getDirection(), 32*(int)enemy.getFrame() + 32, 32+enemy.getDirection(), this);
	   }
	   
	   // zeichnet Leben +Mana:
	   g.drawImage(health_empty, 100, 700, this);
	   g.drawImage(health_full, 100, 700 + (67 -  67*player.getLife()/100), 172, 767, 0, 67 -  67*player.getLife()/100, 72, 67, this);
	   g.drawImage(mana_empty, 628, 700, this);
	   g.drawImage(mana_full, 628, 700 + (67 -  67*player.getMana()/100), 700, 767, 0, 67 -  67*player.getMana()/100, 72, 67, this);
	   
	   // zeichnet Bossgegner:
	   if(room == 3){
		   g.drawImage(boss1, boss.getX(), boss.getY(), boss.getX()+32, boss.getY()+32, 32*(int)boss.getFrame(), boss.getDirection(), 32*(int)boss.getFrame() + 32, 32+boss.getDirection(), this);
	   }
	   if(room == 6){
		   g.drawImage(boss2, boss.getX(), boss.getY(), boss.getX()+32, boss.getY()+32, 32*(int)boss.getFrame(), boss.getDirection(), 32*(int)boss.getFrame() + 32, 32+boss.getDirection(), this);
	   }
	   if(room == 9){
		   g.drawImage(boss3, boss.getX(), boss.getY(), boss.getX()+32, boss.getY()+32, 32*(int)boss.getFrame(), boss.getDirection(), 32*(int)boss.getFrame() + 32, 32+boss.getDirection(), this);
	   }
	   
	   for(int i =1; i <= player.total_life; i++){
		   g.drawImage(heart, 180 + i*34, 730, this);
	   }
	   
	   // zeichnet Bossschuesse
	   ArrayList shoot = boss.getShoot();
	   for (int i = 0; i < shoot.size(); i++) {
		   Shoot s = (Shoot) shoot.get(i);
		   g.setColor(Color.YELLOW);
		   g.fillRect(s.getX(), s.getY(), 5, 5);
	   }
	   
	   //zeichnet Schutzschild:
	   if(spell_iceshield == true){
		   g.drawImage(lightning, player.getP_X()-42, player.getP_Y()-42, player.getP_X() + 86,player.getP_Y()+ 86, animation.getRow(), animation.getLine(), animation.getRow()+128, animation.getLine()+128, this);
	   }
	   }
	   
	   //zeichnet Blitzmagie:
	   if(lightningclaw == true){
		   g.drawImage(lightning_claw, enemy.getX()-42, enemy.getY()-42, enemy.getX()+86,enemy.getY()+86, animation.getRow(), animation.getLine(), animation.getRow()+128, animation.getLine()+127, this); 
   }
   
}
   
   /*
    * Author: Martha Tatusch
    */
   
   private void updateTiles()
   {
	   for (int i = 0; i < map.getTilearray().size(); i++) 
	   {
		   t = (Tiles) map.getTilearray().get(i);
		   t.update();
		   


	   }
   }
   
   /*
    * Author: Martha Tatusch
    */
	
	
	private void paintTiles(Graphics g) 
	{
		for (int i = 0; i < map.getTilearray().size(); i++) 
		{
			t = (Tiles) map.getTilearray().get(i);
			g.drawImage(t.getTileImage(), t.getTileX(), t.getTileY(), this);
		}
	}
	
	/*
	 * @author ...,Maike Fox and Brigitta Wanner
	 */
	
	//Steuerung:		
	

@Override
public void keyPressed(KeyEvent e) {
    switch (e.getKeyCode()) {
    case KeyEvent.VK_UP:
    	
    
        player.moveUp();
        player.setMovingUp(true);
        break;
    	

    case KeyEvent.VK_DOWN:
        player.moveDown();
        player.setMovingDown(true);	
        break;

    case KeyEvent.VK_LEFT:
        player.moveLeft();
        player.setMovingLeft(true);
        break;

    case KeyEvent.VK_RIGHT:
        player.moveRight();
        player.setMovingRight(true);
        break;

    case KeyEvent.VK_SPACE:
    	animation.setLastCast(System.currentTimeMillis());
    	animation.setLastFrame(System.currentTimeMillis());
    	if (player.getMana() >= 15) spell_iceshield = true;
        break;
 
    case KeyEvent.VK_A:
    	animation.setLastCast(System.currentTimeMillis());
    	animation.setLastFrame(System.currentTimeMillis());
    	if (player.getMana() >= 15 ) lightningclaw = true;
    	break;

    	
    case KeyEvent.VK_S:
    	ccheckpoint = true;
    	break; 
    	
    case KeyEvent.VK_I:
    	invent.showInventory();
    	break;
    	
    case KeyEvent.VK_1:
    	break;
    }
	
}


/*
 * @author ...,Maike Fox and Brigitta Wanner
 */


@Override
public void keyReleased(KeyEvent e) {
    switch (e.getKeyCode()) {
    case KeyEvent.VK_UP:
        player.stopUp();
        break;

    case KeyEvent.VK_DOWN:
    	player.stopDown();
        break;

    case KeyEvent.VK_LEFT:
    	player.stopLeft();
        break;

    case KeyEvent.VK_RIGHT:
    	player.stopRight();
        break;

    case KeyEvent.VK_SPACE:
        break;
        

    case KeyEvent.VK_A:
    	break;
    

    case KeyEvent.VK_S:
    	ccheckpoint = false;
    	break;
    	
    case KeyEvent.VK_I:
    	break;
    	
    case KeyEvent.VK_E:
    	Tiles.counterShop = 999;
    	break;


    }
	
}

@Override
public void keyTyped(KeyEvent e) {
	// TODO Auto-generated method stub
	
}


public Tiles getT() {
	return t;
}

public void setT(Tiles t) {
	this.t = t;
}

}
