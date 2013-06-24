package game;

import java.awt.Image;
import java.util.ArrayList;

public class Animation {
	
	private int line = 0, row;
	private long castTimer = 4000, lastCast = System.currentTimeMillis(), lastFrame = System.currentTimeMillis(), frameTimer = 100;
	private int frame = 0, frameCount, frameadd = 1; // frameadd = abstrakte Animiergeschwindigkeit
	public static boolean casted = false;

	
public Animation (){
}

public void play(){
	
	if(System.currentTimeMillis() - lastFrame > frameTimer){
		
		if(frame > 3) frame = 0;
		lastFrame = System.currentTimeMillis();
		
		
		
	   if (frameCount <= 3 && frameCount >= 0){
		   row = (int) (frame) * (128);
		   line = 0;
		   lastFrame = System.currentTimeMillis();
	   }
	   else if (frameCount <= 7 && frameCount >= 4){
		   
		   row = ((int) (frame)) * (128);
		   line = 128;
		   lastFrame = System.currentTimeMillis();
	   }
	   else if (frameCount <= 11 && frameCount >= 8){
		   row = ((int) (frame)) * (128);
		   line = (128)*2;
		   lastFrame = System.currentTimeMillis();
		   
	   }
		else  if (frameCount <= 15  && frameCount >=12){
		   row = ((int) (frame)) * (128);
		   line = (128)*3;
		   lastFrame = System.currentTimeMillis();
	   }
	
		else if (frameCount == 16){
			frame = 0;
			frameCount = 0;
			line = 0;
			row = 0;
			Main.spell_iceshield = false;
		}
	   
	   
	    if(frameCount == 11) frameCount = 17;
	    
		   if(System.currentTimeMillis() - lastCast > castTimer ){ //Wird ausgeführt sobald Ende der Wirkungszeit erreicht ist
			   lastCast = System.currentTimeMillis();
			   lastFrame = System.currentTimeMillis();
			   frameCount = 12;
			   Main.player.setMana(Main.player.getMana() - 15);
		   }
	  
	   frameCount ++;
	   frame += frameadd;
}   
}

//Getters und Setters:

public int getLine() {
	return line;
}

public int getRow() {
	return row;
}



public double getFrame() {
	return frame;
}

public void setLine(int line) {
	this.line = line;
}

public void setRow(int row) {
	this.row = row;
}


public void setFrame(int frame) {
	this.frame = frame;
}

public void setLastCast(long lastCast){
	this.lastCast = lastCast;
}

public void setLastFrame(long lastFrame){
	this.lastFrame = lastFrame;
}



}