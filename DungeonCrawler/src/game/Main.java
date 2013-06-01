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

public class Main extends Applet implements Runnable, KeyListener{
	
	public static Player player;
	private Image image, character, health_empty, health_full, mana_full, mana_empty;
	public static Image tilefloor, tilewall, tiletrap, tileexit, tileentry;
	private ArrayList<Tiles> tilearray = new ArrayList<Tiles>();
	private URL base;
	private Graphics second;
	public static double frame = 0, frameAdd = .3;
	public static int direction;
	public static boolean animating = false;

   @Override
   public void init() {
	   
	   
	   

      setSize(800, 800);
      setBackground(Color.BLACK);
      setFocusable(true);
      addKeyListener(this);
      Frame frame = (Frame) this.getParent().getParent();
      frame.setTitle("Rotkäppchen 2.0");
      
  	try 
  	{
  		base = getDocumentBase();
  	} 
  	catch (Exception e) 
  	{

  	}


  		// Image Setups
  		character = getImage(base, "gfx/MainChar.png");
  		tilefloor = getImage(base, "gfx/Tile_Floor.png");
  		tilewall = getImage(base, "gfx/Tile_Wall.png");
  		health_empty = getImage(base,"gfx/LifeOrbEmpty.png");
  		health_full = getImage(base,"gfx/LifeOrbFull.png");
  		mana_empty = getImage(base,"gfx/ManaOrbEmpty.png");
  		mana_full = getImage(base,"gfx/ManaOrbFull.png");
  		tiletrap = getImage(base, "gfx/Trap.png");
  		tileexit = getImage(base, "gfx/Exit.png");
  		tileentry = getImage(base, "gfx/Exit.png");
  		
   }

   @Override
   public void start() 
   {
	   frame = 0 ;
	   player = new Player(400,400);
	   Thread thread = new Thread(this);
	   thread.start();
	   
       try 
       {
           level1("maps/map1.txt");
       } 
       catch (IOException e) 
       {
           e.printStackTrace();
       }
       
	   
   }
   
	private void level1(String filepath) throws IOException
	{
        ArrayList lines = new ArrayList();
        int width = 0;
        int height = 0;
        BufferedReader reader = new BufferedReader(new FileReader(filepath));
        
        while (true) 
        {
            String line = reader.readLine();
            
            	if (line == null) 
            	{
            		reader.close();
            		break;
            	}

            	if (!line.startsWith("!")) 
            	{
            		lines.add(line);
            		width = Math.max(width, line.length());

            	}
        }
        height = lines.size();

        	for (int y = 0; y < height; y++) 
        	{
        		String line = (String) lines.get(y);
        			for (int x = 0; x < width; x++) 
        			{
        					if (x < line.length()) 
        					{
        						char ch = line.charAt(x);
        						Tiles t = new Tiles(x, y, Character.toString(ch));
        						tilearray.add(t);
        					}

        			}
        	}
	
	}

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
    	  
    	  if(animating == true){
    		   if (frame < 0.5) frameAdd =  .1;
    		   if (frame > 2.5) frameAdd = -.1;
    		   frame += frameAdd;  
    	  }
    	  player.update();
    	  
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
	   g.drawImage(character, player.getP_X(), player.getP_Y(), player.getP_X()+32, player.getP_Y()+32, 32*(int)frame, 0+direction, 32*(int)frame + 32, 32+direction, this);
	   g.drawRect((int)player.r.getX(), (int)player.r.getY(), (int)player.r.getWidth(), (int)player.r.getHeight());
	   g.setColor(Color.white);
	   g.drawRect((int)Tiles.r.getX(), (int)Tiles.r.getY(), (int)Tiles.r.getWidth(), (int)Tiles.r.getHeight());
	   g.drawImage(health_empty, 100, 700, this);
	   g.drawImage(health_full, 100, 700 + (67 -  67*player.getLife()/100), 172, 767, 0, 67 -  67*player.getLife()/100, 72, 67, this);
	   g.drawImage(mana_empty, 628, 700, this);
	   g.drawImage(mana_full, 628, 700 + (67 -  67*player.getMana()/100), 700, 767, 0, 67 -  67*player.getMana()/100, 72, 67, this);
   }
   
   public void anim()
   {
	   if (frame < 0.5) frameAdd =  .3;
	   if (frame > 2.5) frameAdd = -.3;
	   frame += frameAdd;   
   }
   
   private void updateTiles()
   {
	   for (int i = 0; i < tilearray.size(); i++) 
	   {
		   Tiles t = (Tiles) tilearray.get(i);
		   t.update();
	   }
   }
	
	
	private void paintTiles(Graphics g) 
	{
		for (int i = 0; i < tilearray.size(); i++) 
		{
			Tiles t = (Tiles) tilearray.get(i);
			g.drawImage(t.getTileImage(), t.getTileX(), t.getTileY(), this);
		}
	}

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
    	player.setLife(player.getLife() - 13);
    	player.setMana(player.getMana() - 7);
        break;

    }
	
}

@Override
public void keyTyped(KeyEvent e) {
	// TODO Auto-generated method stub
	
}
}
