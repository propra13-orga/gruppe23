/*
 * author Brigitta Wanner
 */
package game;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.applet.Applet;
import java.awt.event.KeyListener;



public class Multiplayer extends Applet implements Runnable, KeyListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static Boolean inGame = false , inMulti = false;
	private URL base;
	private Graphics second;
	public static Map map;
	public static Sound sound, sound2;
	public static Image image, character, boss3;
	public static Image tilefloor, tilewall, tileexit, tileentry;
	public static Player player, player2;
	public Animation animation;
	public Menue menu;
	public static Boss boss;
	public static Frame fenster;
	public static long lastMusic = -31767, soundInterval = 31767;
	public Client client = null;
	public Server server = null;
	
	
	public static double frame = 0, frameAdd = .1;
	public static int room, level;
	public static int direction;
	public static boolean animating = false, inMenue = true;
	public static int i = 1;
	
	public String playerName;
	private Tiles t;

	/*
	 * Konstruktor
	 */
	public Multiplayer()
	{
		
		
	}

	
	public void init() {

		      setSize(800, 800); //Größe des Fensters
		      setBackground(Color.BLACK); //Hintergrundfarbe des Fensters
		      setFocusable(true); //Fokus auf das Fenster setzen
		      addKeyListener(this);
		      addMouseListener(new Mouse());
		      fenster = (Frame) this.getParent().getParent(); 
		      fenster.setTitle("Multiplayer-Modus"); //Fenster-Titel
		      
		      
		      try 
		      {
		    	  base = getDocumentBase(); //Pfad des Spiels
		      }
		      catch (Exception e)
		      {

			  	}
		      
		      character = getImage(base, "gfx/MainChar.png");
		      boss3 = getImage(base, "gfx/Boss3.png");
		      tilefloor = getImage(base, "gfx/Tile_Floor.png");
		      tilewall = getImage(base, "gfx/Tile_Wall.png");
		      tileexit = getImage(base, "gfx/Exit.png");
		      tileentry = getImage(base, "gfx/Exit.png");

		   }
		

		public void start() 
		{
			player = new Player(400 ,400);
			map = new Map();
			player2 = new Player (400, 400);
			sound = new Sound();
			sound2 = new Sound();
			animation = new Animation();
			menu = new Menue();
			inMenue = true;
			   

			Thread thread = new Thread(this);
			thread.start();

			try
			{
				map.loadMap("multiplayermap.txt"); //Karte laden
				}
			catch (IOException e)
			{
				e.printStackTrace(); 
				}
				}
			

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

		   public void run()
		   {
			   while(true)
			   {  
					   updateTiles();
					   try {
						player.update();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					   boss.update();
			   }
		   }
		   
		   
		   public void paint(Graphics g)
		   {
				   paintTiles(g);
				   player.draw(g);
			   
		   }
		   
		   private void updateTiles()
		   {
			   for (int i = 0; i < map.getTilearray().size(); i++)
			   {
				   t = (Tiles) map.getTilearray().get(i);
				   t.update();
			   }
		   }
		   
		   private void paintTiles(Graphics g)
		   {

			   for (int i = 0; i < map.getTilearray().size(); i++){

				   t = (Tiles) map.getTilearray().get(i);
				   g.drawImage(t.getTileImage(), t.getTileX(), t.getTileY(), this);
					}
			}
			   
			public void keyTyped(KeyEvent e) {
					// TODO Auto-generated method stub
					
			}


			public Tiles getT() {
					return t;
			}

			public void setT(Tiles t) {
					this.t = t;
			}
			
/*
 * 			Steuerung
 */
			
			public static void keyCheck(String key, String movement) 
			{
				
				if(Client.inClient == true)
		        {
					if(movement.equals("0"))
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
					if(movement.equals("1"))
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
				if(Server.inServer == true)
				{
					if(movement.equals("0"))
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
					if(movement.equals("1"))
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
			public void keyPressed(KeyEvent e) {
				    switch (e.getKeyCode()) {
				    case KeyEvent.VK_UP:
				        if(Client.inClient == true)
				        {
				        	player2.moveUp();
				        	player2.setMovingUp(true);
				        	Client.sendMessage("up", 0);
				        }
				        if(Server.inServer == true)
				        {
				        	player.moveUp();
				        	player.setMovingUp(true);
				        	ServiceThread.sendMessage("up", 0);
				        }
				        break;
				    	

				    case KeyEvent.VK_DOWN:	
				        if(Client.inClient == true)
				        {
				        	player2.moveUp();
			        		player2.setMovingUp(true);
				        	Client.sendMessage("down", 0);
				        }
				        if(Server.inServer == true)
				        {
				        	player.moveDown();
				        	player.setMovingDown(true);
				        	ServiceThread.sendMessage("down", 0);
				        }
				        break;

				    case KeyEvent.VK_LEFT:
				        if(Client.inClient == true)
				        {
				        	player2.moveLeft();
					        player2.setMovingLeft(true);
					        Client.sendMessage("left", 0);
				        }
				        if(Server.inServer == true)
				        {
				        	player.moveLeft();
				        	player.setMovingLeft(true);
				        	ServiceThread.sendMessage("left", 0);
				        }
				        break;

				    case KeyEvent.VK_RIGHT:
				        if(Client.inClient == true)
				        {
				        	player2.moveRight();
					        player2.setMovingRight(true);
				        	Client.sendMessage("right", 0);
				        }
				        if(Server.inServer == true)
				        {
				        	player.moveRight();
					        player.setMovingRight(true);
				        	ServiceThread.sendMessage("right", 0);
				        }
				        break;
				        
				    }
					
		}

		@Override
		public void keyReleased(KeyEvent e) 
		{
				    switch (e.getKeyCode()) {
				    case KeyEvent.VK_UP:
				    	if(Client.inClient == true)
				        {
				    		player2.stopUp();
				    		Client.sendMessage("up", 1);
				        }
				    	if(Server.inServer == true)
				        {
				    		player.stopUp();
				    		ServiceThread.sendMessage("up", 1);
				        }
				        break;

				    case KeyEvent.VK_DOWN:
				    	if(Client.inClient == true)
				        {
				    		player2.stopDown();
				    		Client.sendMessage("down", 1);
				        }
				    	if(Server.inServer == true)
				        {
				    		player.stopDown();
				    		ServiceThread.sendMessage("down", 1);
				        }
				        break;

				    case KeyEvent.VK_LEFT:
				    	if(Client.inClient == true)
				        {
				    		player2.stopLeft();
				    		Client.sendMessage("left", 1);
				        }
				    	if(Server.inServer == true)
				        {
				    		player.stopLeft();
				    		ServiceThread.sendMessage("left", 1);
				        }
				        break;

				    case KeyEvent.VK_RIGHT:
				    	if(Client.inClient == true)
				        {
				    		player2.stopRight();
				    		Client.sendMessage("right", 1);
				        }
				    	if(Server.inServer == true)
				        {
				    		player.stopRight();
				    		ServiceThread.sendMessage("right", 1);
				        }
				    	
				        break;
				    }
					
		}
}	