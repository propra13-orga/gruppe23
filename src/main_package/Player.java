package main_package;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Player {

	public static float xpos;
	public static float ypos;
	
	private BufferedImage look;
	
	public Player(int xpos, int ypos){
		Player.xpos = xpos;
		Player.ypos = ypos;
		
		try {
			look = ImageIO.read(getClass().getClassLoader().getResourceAsStream("gfx/player.PNG"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void draw(Graphics g){
		
		g.drawImage(look, (int)xpos, (int)ypos, null);
		
	}
	
	//Spielerposition wird abgefragt
	
	public void update(){
		if (frame.lvl == 1) if((xpos == 350-25) && (ypos == 450-25)){
			xpos = 350 - 25;
			ypos = 550 - 25;
			frame.lvl = 2;
		}
		
		if (frame.lvl == 1) if((xpos == 550-25) && (ypos == 650-25)){
			frame.lvl = 4;
		}
			
		if (frame.lvl == 2) if((xpos == 650-25) && (ypos == 150-25)){
			frame.lvl = 3;
			xpos =  150 -25;
			ypos = 150 -25 ;
		}		
		
		if (frame.lvl == 2) if((xpos == 550-25) && (ypos == 250-25)){
			frame.lvl = 4;
		}
		
		if (frame.lvl == 3) if((xpos == 250-25) && (ypos == 650-25)){
			frame.lvl = 5;
		}
		
		if (frame.lvl == 3) if((xpos == 350-25) && (ypos == 550-25)){
			frame.lvl = 4;
		}
		
		if(xpos < 100) xpos = 150-25;
		if(xpos > 700) xpos = 650-25;
		if(ypos < 100) ypos = 150-25;
		if(ypos > 700) ypos = 650-25;
	}

	
}

