package game;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class Main extends Applet implements Runnable, KeyListener{

	private URL base;
	private Graphics second;
	public static Map map;
	public static Sound sound, sound2;
	
	//Spielelemente   // public static Imge ncp, ncp1,checkpoint,story hinzugefügt
	public static Image image, character, health_empty, health_full, heart, mana_full, mana_empty,enemy_ghost, enemy_monster, lightning, boss1, boss2, boss3, ncp, lightning_claw, shop;
	public static Image axe, sword, spear, mana_small, mana_big, health_small, health_big, all_small, all_middle;
	public static Image tilefloor, tilewall, tiletrap, tiletrap2, tileexit, tileentry, tilewall2, tilefloor3, tilewall3, ncp1, checkpoint, story, geld,quest, Ausgang, Wand , Wand2 , Wand3;
	public static Player player;
	public static SecondPlayer player2;
	public static Enemy enemy;
	public Animation animation;
	public Claw claw;
	public Inventory invent;
	public static ArrayList<Item> itemlist = new ArrayList<Item>();
	public static Item item;
	public static Shop shop1;
	public Menue menu;
	public static Boss boss;
	public static Frame fenster;
	public static long lastMusic = -31767, soundInterval = 31767;

	//private int damage;
	//public static Item item;
	public static Player mana;

	
	
	//Variablen			// public static boolean ccheckpoint hinzugefügt
	public static double frame = 0, frameAdd = .1;
	public static int room, level;
	public static int direction;
	public static boolean animating = false, spell_iceshield = false, inMenue = true, lightningclaw = false, inSingle = false;
	public static boolean ccheckpoint = false, checkpoint_reached = false;
	public static boolean sword_act = false, axe_act = false;
	public static int i = 1;


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
  		quest = getImage(base, "gfx/Quest_Floor.png"); // Quests hinzugefügt
  		Ausgang = getImage(base, "gfx/Quest_Exit.png");
  		Wand = getImage(base, "gfx/Quest_Wall.png");
  		Wand2 = getImage(base, "gfx/Quest_Wall2.png");
  		Wand3 = getImage(base, "gfx/Quest_Wall3.png");

   }

  /*
   * @author Brigitta Wanner
   */
   public void itemInit()
   {
	   Weapon Axe = new Weapon("Axt",10, 5, 20);
	   Weapon Sword = new Weapon("Sword",5, 3, 10);
	   Potion Mana_Small = new Potion("Small Manapotion",0, 10, 10);
	   Potion Mana_Big = new Potion("Big Manapotion",0, 20, 20);
	   Potion Health_Small = new Potion("Small Healthpotion",10, 0, 10);
	   Potion Health_Big = new Potion("Big Healthpotion",20, 0, 20);
	   
	   itemlist.add(0,Axe);
	   itemlist.add(1,Sword);
	   itemlist.add(2,Mana_Small);
	   itemlist.add(3,Mana_Big);
	   itemlist.add(4,Health_Small);
	   itemlist.add(5,Health_Big);
//	   invent.inventory.add(0, Main.itemlist.get(1));
//	   invent.inventory.add(1, Main.itemlist.get(5));
	   
   }
/*
 * Author: Martha Tatusch
 */
   
   @Override
   public void start() 
   {
	   

	   room = 1 ;
	   level = 1;
	   player = new Player(400 ,400);
	   player2 = new SecondPlayer(400, 400);
	   map = new Map();
	   boss = new Boss(0,0,5,100);
	   sound = new Sound();
	   sound2 = new Sound();
	   animation = new Animation();
	   claw = new Claw();
	   invent = new Inventory();
	   itemInit();
	   shop1 = new Shop();
	   menu = new Menue();
	   inMenue = true;
	   enemy = new Enemy(0, 0, 10);
	   
	   
	   Thread thread = new Thread(this);
	   thread.start();
       
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
    	  if(inSingle == true)
    	  {
    	  if(System.currentTimeMillis() - lastMusic > soundInterval){
    		  sound.play("sound/bg_music.wav");
    		  lastMusic = System.currentTimeMillis();
    	  }
    	  
    	  updateTiles();
    	  if(lightningclaw == true) claw.play();
       	  if(spell_iceshield == true) animation.play();
    	 
    	  try {
			player.update();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} //Spieler wird aktualisiert
    		if(room != 9 && lightningclaw == false){
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
    	  if(Multiplayer.inMulti == true)
		  {
			  updateTiles();
			  try {
				player.update();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			  player2.update();
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
   }
	
   /*
    * Author: Martha Tatusch
    */
   
   @Override
   public void paint(Graphics g)
   {

	   

	   if(inMenue == true) menu.draw(g); //zeichnet das MenÃ¼, wenn inMenue=true
	   else{   
		   if(inSingle == true)
		   {
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
	   		
	   
			   //zeichnet Blitzmagie:
			   	if(lightningclaw == true){
			   		g.drawImage(lightning_claw, enemy.getX()-42, enemy.getY()-42, enemy.getX()+86,enemy.getY()+86, claw.getRow(), claw.getLine(), claw.getRow()+128, claw.getLine()+128, this); 
			   		}
		   	}
		   if(Multiplayer.inMulti == true)
		   {
			   paintTiles(g); //zeichnet den Raum
			   player.draw(g);
			   player2.draw(g);
			   
			   if(Server.inGame == true)
			   {
				   g.drawImage(health_empty, 100, 700, this);
				   g.drawImage(health_full, 100, 700 + (67 -  67*player.getLife()/100), 172, 767, 0, 67 -  67*player.getLife()/100, 72, 67, this);
				   g.drawImage(mana_empty, 628, 700, this);
				   g.drawImage(mana_full, 628, 700 + (67 -  67*player.getMana()/100), 700, 767, 0, 67 -  67*player.getMana()/100, 72, 67, this);
			   
				   for(int i =1; i <= player.total_life; i++)
				   {
					   g.drawImage(heart, 180 + i*34, 730, this);
				   }
			   }
			   if(Client.inGame == true)
			   {
				   g.drawImage(health_empty, 100, 700, this);
				   g.drawImage(health_full, 100, 700 + (67 -  67*player2.getLife()/100), 172, 767, 0, 67 -  67*player2.getLife()/100, 72, 67, this);
				   g.drawImage(mana_empty, 628, 700, this);
				   g.drawImage(mana_full, 628, 700 + (67 -  67*player2.getMana()/100), 700, 767, 0, 67 -  67*player2.getMana()/100, 72, 67, this);
			   
				   for(int i =1; i <= player2.total_lifeS; i++)
				   {
					   g.drawImage(heart, 180 + i*34, 730, this);
				   }
			   }
		   }
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
	 * @author Martha Tatusch,Maike Fox and Brigitta Wanner
	 */
	
	//Steuerung:		
	

	@Override
	public void keyPressed(KeyEvent e) {
	    switch (e.getKeyCode()) {
	    case KeyEvent.VK_UP:
	    	if(inSingle == true)
	    	{
	    		player.moveUp();
		        player.setMovingUp(true);
	    	}
	        if(Client.inGame == true)
	        {
	        	player2.moveUp();
	        	player2.setMovingUp(true);
	        	Client.sendMessage(new Message(0,"up","", 0));
	        }
	        if(Server.inGame == true)
	        {
	        	player.moveUp();
	        	player.setMovingUp(true);
	        	Server.sendMessage(new Message(0,"up","", 0));
	        }
	        break;
	    	

	    case KeyEvent.VK_DOWN:
	    	if(inSingle == true)
	    	{
	    		player.moveDown();
		        player.setMovingDown(true);
	    	}
	        if(Client.inGame == true)
	        {
	        	player2.moveDown();
	    		player2.setMovingDown(true);
	    		Client.sendMessage(new Message(0,"down","", 0));
	        }
	        if(Server.inGame == true)
	        {
	        	player.moveDown();
	        	player.setMovingDown(true);
	        	Server.sendMessage(new Message(0,"down","", 0));
	        }
	        break;

	    case KeyEvent.VK_LEFT:
	    	if(inSingle == true)
	    	{
	    		player.moveLeft();
		        player.setMovingLeft(true);
	    	}
	        if(Client.inGame == true)
	        {
	        	player2.moveLeft();
		        player2.setMovingLeft(true);
		        Client.sendMessage(new Message(0,"left","", 0));
	        }
	        if(Server.inGame == true)
	        {
	        	player.moveLeft();
	        	player.setMovingLeft(true);
	        	Server.sendMessage(new Message(0,"left","", 0));
	        }
	        break;

	    case KeyEvent.VK_RIGHT:
	    	if(inSingle == true)
	    	{
	    		player.moveRight();
		        player.setMovingRight(true);
	    	}
	        if(Client.inGame == true)
	        {
	        	player2.moveRight();
		        player2.setMovingRight(true);
		        Client.sendMessage(new Message(0,"right","", 0));
	        }
	        if(Server.inGame == true)
	        {
	        	player.moveRight();
		        player.setMovingRight(true);
		        Server.sendMessage(new Message(0,"right","", 0));
	        }
	        break;

	    case KeyEvent.VK_SPACE:
	    	if(inSingle == true)
	    	{
	    	if (player.getMana() >= 15 && !spell_iceshield){
	    		animation.setLastCast(System.currentTimeMillis());
	    		animation.setLastFrame(System.currentTimeMillis());
	    		spell_iceshield = true;
	    		sound.play("sound/iceshield.wav");
	    	}
	    	}
	        break;
	 
	    case KeyEvent.VK_A:
	    	if(inSingle == true)
	    	{
	    	if (player.getMana() >= 15 && !lightningclaw ){
	    		claw.setLastC(System.currentTimeMillis());
	    		claw.setLastF(System.currentTimeMillis());
	    		lightningclaw = true;
	    		sound.play("sound/lightningclaw.wav");
	    	}
	    	}
	    	break;

	    	
	    case KeyEvent.VK_S:
	    	if(inSingle == true)
	    	{
	    	ccheckpoint = true;
	    	}
	    	break; 
	    	
	    case KeyEvent.VK_I:
	    	if(inSingle == true)
	    	{
	    	invent.showInventory();
	    	}
	    	break;
	    	
	    case KeyEvent.VK_1:
	    	if(inSingle == true)
	    	{
	    	axe_act = true;
	    	sound.play("sound/axe.wav");
//	    	while (i <= 10) i ++; //vorläufig
//	    	axe_act = false; //vorläufig
	    	}
	    	break;
	    	
	    case KeyEvent.VK_2:
	    	if(inSingle == true)
	    	{
	    	sword_act = true;
	    	sound.play("sound/sword.wav");
//	    	while (i <= 10) i ++; //vorläufig
//	    	sword_act = false; //vorläufig
	    	}
	    	break;
 	
//    case KeyEvent.VK_3:
//
//    	spear_act = true;
//    	sound.play("sound/spear.wav");
//    	break;
    }
	
}


/*
 * @author Martha Tatusch,Maike Fox and Brigitta Wanner
 */


	/*
	 * @author ...,Maike Fox and Brigitta Wanner
	 */


	@Override
	public void keyReleased(KeyEvent e) {
	    switch (e.getKeyCode()) {
	    case KeyEvent.VK_UP:
	    	if(inSingle == true)
	    	{
	    		player.stopUp();
	    	}
	        if(Client.inGame == true)
	        {
	    		player2.stopUp();
	    		Client.sendMessage(new Message(1,"up","", 0));
	        }
	    	if(Server.inGame == true)
	        {
	    		player.stopUp();
	    		Server.sendMessage(new Message(1,"up","", 0));
	        }
	        break;

	    case KeyEvent.VK_DOWN:
	    	if(inSingle == true)
	    	{
	    		player.stopDown();
	    	}
	    	if(Client.inGame == true)
	        {
	    		player2.stopDown();
	    		Client.sendMessage(new Message(1,"down","", 0));
	        }
	    	if(Server.inGame == true)
	        {
	    		player.stopDown();
	    		Server.sendMessage(new Message(1,"down","", 0));
	        }
	        break;

	    case KeyEvent.VK_LEFT:
	    	if(inSingle == true)
	    	{
	    		player.stopLeft();
	    	}
	    	if(Client.inGame == true)
	        {
	    		player2.stopLeft();
	    		Client.sendMessage(new Message(1,"left","", 0));
	        }
	    	if(Server.inGame == true)
	        {
	    		player.stopLeft();
	    		Server.sendMessage(new Message(1,"left","", 0));
	        }
	        break;

	    case KeyEvent.VK_RIGHT:
	    	if(inSingle == true)
	    	{
	    		player.stopRight();
	    	}
	    	if(Client.inGame == true)
	        {
	    		player2.stopRight();
	    		Client.sendMessage(new Message(1,"right","", 0));
	        }
	    	if(Server.inGame == true)
	        {
	    		player.stopRight();
	    		Server.sendMessage(new Message(1,"right","", 0));
	        }
	        break;

	    case KeyEvent.VK_SPACE:
	        break;
	        

	    case KeyEvent.VK_A:
	    	break;
	    

	    case KeyEvent.VK_S:
	    	if(inSingle == true)
	    	{
	    		ccheckpoint = false;
	    	}
	    	break;
	    	
	    case KeyEvent.VK_I:
	    	break;
	    	
	    case KeyEvent.VK_E:
	    	if(inSingle == true)
	    	{
	    		Tiles.counterShop = 999;
	    	}
	    	break;


	    }
		
	}
	
	// author Brigitta Wanner
	// Check keymessages übers Netzwerk
	public static void keyCheck(String key, int movement) 
	{
			
			if(Client.inGame == true)
	        {
				if(movement == 0)
				{
					if(key.equals("down"))
					{
						player.moveDown();
			        	player.setMovingDown(true);
					
					}
					if(key.equals("up"))
					{
						player.moveUp();
			        	player.setMovingUp(true);
					
					}
					if(key.equals("right"))
					{
						player.moveRight();
			        	player.setMovingRight(true);
					
					}
					if(key.equals("left"))
					{
						player.moveLeft();
			        	player.setMovingLeft(true);
					}
				}
				if(movement == 1)
				{
					if(key.equals("down"))
					{
						player.stopDown();						
					}
					if(key.equals("up"))
					{
						player.stopUp();
					}
					if(key.equals("right"))
					{
						player.stopRight();
					}
					if(key.equals("left"))
					{
						player.stopLeft();
					}
				}	
	        }
			if(Server.inGame == true)
			{
				if(movement == 0)
				{
					if(key.equals("down"))
					{
						player2.moveDown();
			        	player2.setMovingDown(true);
						
					}
					if(key.equals("up"))
					{
						player2.moveUp();
			        	player2.setMovingUp(true);
						
					}
					if(key.equals("right"))
					{
						player2.moveRight();
			        	player2.setMovingRight(true);
						
					}
					if(key.equals("left"))
					{
						player2.moveLeft();
			        	player2.setMovingLeft(true);
						
					}
				}
				if(movement == 1)
				{
					if(key.equals("down"))
					{
						player2.stopDown();
					}
					if(key.equals("up"))
					{
						player2.stopUp();
					}
					if(key.equals("right"))
					{
						player2.stopRight();
					}
					if(key.equals("left"))
					{
						player2.stopLeft();
					}
				}
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