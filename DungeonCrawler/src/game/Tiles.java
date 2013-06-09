package game;

import java.awt.Image;
import java.awt.Rectangle;
import java.io.IOException;

public class Tiles {
	
    private int tileX, tileY, speedX, speedY;
    public String type;
    public Image tileImage;
    public String floor = " ", wall = "#" , empty = "+", trap = "F", exit = "A", entry = "E";
    public static Rectangle r, t, e;
    
    public Tiles (int x, int y, String typeInt) {
        tileX = x * 40;
        tileY = y * 40;
        r = new Rectangle();
        t = new Rectangle();
        e = new Rectangle();

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
        else if (type.equals(exit)) {
            tileImage = Main.tileexit;
        }
        else if (type.equals(empty)) {
            tileImage = null;
        }
        else if (type.equals(entry)) {
        	Main.player.setP_X(x*40+4);
        	Main.player.setP_Y(y*40+4);
            tileImage = Main.tileentry;
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
    
    public void checkTrap(Rectangle rect){
        if(rect.intersects(t)){
        	Main.player.setLife(0);
        }
    }
    
    public void checkExit(Rectangle rect){
        if(rect.intersects(e)){
        	
        	switch(Main.level){
        	case 1:       		
        		try {
        			Main.map.loadMap("maps/map2.txt");
        		} catch (IOException e) {
        			// TODO Auto-generated catch block
        			e.printStackTrace();
        		}
        		Main.level = 2;
        		break;
        		
        	case 2:        		
        		try {
        			Main.map.loadMap("maps/map3.txt");
        		} catch (IOException e) {
        			// TODO Auto-generated catch block
        			e.printStackTrace();
        		}
        		Main.level = 3;
        		break;
        		
        				}
        	
        	
        	System.out.println(Main.level);
        	
        	
        }
    }
    
    public void update(){
    	
        r.setBounds(tileX, tileY, 40, 40);
        t.setBounds(tileX+19, tileY+19, 2, 2);
        e.setBounds(tileX+19, tileY+19, 2, 2);
        
        if (type.equals(wall)){
            checkCollision(Player.up, Player.right , Player.down , Player.left);
        }
        
        if (type.equals(trap)){
            checkTrap(Player.r);	
        }
        
        if (type.equals(exit)){
            checkExit(Player.r);	
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
