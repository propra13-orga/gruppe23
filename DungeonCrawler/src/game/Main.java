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
	
	public static Player player;
	public static Enemy enemy;
	private Image image, character, health_empty, health_full, mana_full, mana_empty,enemy_ghost, lightning;
	public static Image tilefloor, tilewall, tiletrap, tileexit, tileentry;
	private ArrayList<Tiles> tilearray = new ArrayList<Tiles>();
	private URL base;
	private Graphics second;
	public static double frame = 0, frameAdd = .1;
	public static int level;
	public static int direction;
	public static boolean animating = false, spell_iceshield = false;
	public static Map map;
	public static Sound sound;
	public Animation animation;
	public Inventory invent;

   @Override
   public void init() {

      setSize(800, 800); //Größe des Fensters
      setBackground(Color.BLACK); //Hintergrundfarbe des Fensters
      setFocusable(true); //Fokus auf das Fenster setzen
      addKeyListener(this); 
      Frame frame = (Frame) this.getParent().getParent(); 
      frame.setTitle("Rotkäppchen 2.0"); //Fenster-Titel
      
      TimerTask action = new TimerTask() { //Timertask, der den Sound abspielt
    	  	            public void run() {
    	  	            	sound.play("sound/bg_music.wav");
    	             }
    	  	 
    	  	        };
    	  	 
    	  	        Timer caretaker = new Timer(); //neuen Timer erstellen
    	  	        caretaker.schedule(action, 0, 31767); //Timertask alle 31,767 Sekunden ausführen
      
  	try 
  	{
  		base = getDocumentBase(); //Pfad des Spiels
  	} 
  	catch (Exception e) 
  	{

  	}


  		// Bilder:
  	
  		character = getImage(base, "gfx/MainChar.png");
  		enemy_ghost = getImage(base,"gfx/Geist.png");
  		tilefloor = getImage(base, "gfx/Tile_Floor.png");
  		tilewall = getImage(base, "gfx/Tile_Wall.png");
  		health_empty = getImage(base,"gfx/LifeOrbEmpty.png");
  		health_full = getImage(base,"gfx/LifeOrbFull.png");
  		mana_empty = getImage(base,"gfx/ManaOrbEmpty.png");
  		mana_full = getImage(base,"gfx/ManaOrbFull.png");
  		tiletrap = getImage(base, "gfx/Trap.png");
  		tileexit = getImage(base, "gfx/Exit.png");
  		tileentry = getImage(base, "gfx/Exit.png");
  		lightning = getImage(base, "gfx/iceshield.png");
  		
   }

   @Override
   public void start() 
   {
	   frame = 0 ;
	   level = 1 ;
	   enemy = new Enemy((40+(int)(Math.random()*729)), (40+(int)(Math.random()*601)),25,100);
	   player = new Player(400,400);
	   map = new Map();
	   sound = new Sound();
	   animation = new Animation(4,4,512,512,200);
	   invent = new Inventory();
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

   @Override
   public void run() 
   {
      while (true) 
      {   	  
    	  updateTiles();
    	  if(spell_iceshield == true) animation.play();
    	  // frame schwankt zwischen 0 und 2
    	  if(animating == true){
    		   if (frame < 0.5) frameAdd =  .1;
    		   if (frame > 2.5) frameAdd = -.1;
    		   frame += frameAdd;  
    	  }
    	  player.update(); //spieler wird aktualisiert
    	  enemy.update();  //gegner wird aktualisiert
    	  
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
   
   @Override
   public void paint(Graphics g) 
   {
	   paintTiles(g);
	   g.drawImage(character, player.getP_X(), player.getP_Y(), player.getP_X()+32, player.getP_Y()+32, 32*(int)frame, direction, 32*(int)frame + 32, 32+direction, this);
	   g.drawImage(enemy_ghost, enemy.getX(), enemy.getY(), enemy.getX()+32, enemy.getY()+32, 32*(int)enemy.getFrame(), enemy.getDirection(), 32*(int)enemy.getFrame() + 32, 32+enemy.getDirection(), this);
	   g.drawImage(health_empty, 100, 700, this);
	   g.drawImage(health_full, 100, 700 + (67 -  67*player.getLife()/100), 172, 767, 0, 67 -  67*player.getLife()/100, 72, 67, this);
	   g.drawImage(mana_empty, 628, 700, this);
	   g.drawImage(mana_full, 628, 700 + (67 -  67*player.getMana()/100), 700, 767, 0, 67 -  67*player.getMana()/100, 72, 67, this);
	   if(spell_iceshield == true) g.drawImage(lightning, player.getP_X()-42, player.getP_Y()-42, player.getP_X() + 86,player.getP_Y()+ 86, animation.getRow(), animation.getLine(), animation.getRow()+128, animation.getLine()+128, this);
   }
   
   
   private void updateTiles()
   {
	   for (int i = 0; i < map.getTilearray().size(); i++) 
	   {
		   Tiles t = (Tiles) map.getTilearray().get(i);
		   t.update();
	   }
   }
	
	
	private void paintTiles(Graphics g) 
	{
		for (int i = 0; i < map.getTilearray().size(); i++) 
		{
			Tiles t = (Tiles) map.getTilearray().get(i);
			g.drawImage(t.getTileImage(), t.getTileX(), t.getTileY(), this);
		}
	}
	
	//Steuerung:

@Override
public void keyPressed(KeyEvent e) {
    switch (e.getKeyCode()) {
    case KeyEvent.VK_UP:
    	
    	animating = true;
        player.moveUp();
        player.setMovingUp(true);
        break;
    	

    case KeyEvent.VK_DOWN:
    	animating = true;
        player.moveDown();
        player.setMovingDown(true);	
        break;

    case KeyEvent.VK_LEFT:
    	animating = true;
        player.moveLeft();
        player.setMovingLeft(true);
        break;

    case KeyEvent.VK_RIGHT:
    	animating = true;
        player.moveRight();
        player.setMovingRight(true);
        break;

    case KeyEvent.VK_SPACE:
    	spell_iceshield = true;
        break;
        
    case KeyEvent.VK_1:
    	invent.addItem(1);
    	invent.printInv();
    	break;
    	
    case KeyEvent.VK_2:
    	invent.addItem(2);
    	invent.printInv();
    	break;
    	
    case KeyEvent.VK_3:
    	invent.addItem(3);
    	invent.printInv();
    	break;
    	
    case KeyEvent.VK_4:
    	invent.addItem(4);
    	invent.printInv();
    	break;
    	
    case KeyEvent.VK_5:
    	invent.addItem(5);
    	invent.printInv();
    	break;
    }
	
}

@Override
public void keyReleased(KeyEvent e) {
    switch (e.getKeyCode()) {
    case KeyEvent.VK_UP:
    	animating = false;
        player.stopUp();
        break;

    case KeyEvent.VK_DOWN:
    	animating = false;
    	player.stopDown();
        break;

    case KeyEvent.VK_LEFT:
    	animating = false;
    	player.stopLeft();
        break;

    case KeyEvent.VK_RIGHT:
    	animating = false;
    	player.stopRight();
        break;

    case KeyEvent.VK_SPACE:
        break;

    }
	
}

@Override
public void keyTyped(KeyEvent e) {
	// TODO Auto-generated method stub
	
}
}
