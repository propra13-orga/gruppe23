package game;

import java.awt.Image;
import java.util.ArrayList;

public class Animation {
	
	private int line, row, height, width, count = 0, lines, rows, duration;
	private double frame = .5, frameadd = .2 , manaminus = 0; // frameadd = abstrakte Animiergeschwindigkeit
	private boolean casted = false;

	
public Animation (int lines, int rows, int height, int width, int duration){
	this.line = width/rows; //Breite eines Bildes in Pixel
	this.row = height/lines; // Höhe eines Bildes in Pixel
	this.height = height; // Höhe des gesamten Bildes in Pixel
	this.width = width; // Breite des gesamten Bildes in Pixel
	this.lines = lines; // Anzahl der Zeilen
	this.rows = rows; // Anzahl der Reihen
	this.duration = duration; // Zeit die der Zauber wirkt
}

public void play(){
	
	   if (frame <= 4 && frame >= 0){
		   frame +=  frameadd;
		   row = (int) (frame) * (width/rows);
		   line = 0;
	   }
	   else if (frame <= 7 && frame > 4){
		   frame +=  frameadd;
		   row = ((int) (frame) - 3) * (width/rows);
		   line = height/lines;
	   }
	   else if (frame <= 10 && frame > 7){
		   
		   if (casted == false){		// Wird nicht ausgeführt wenn der Zauber wirkt	   
		   frame +=  frameadd;
		   row = ((int) (frame) - 6) * (width/rows);
		   line = (height/lines)*2;
		   }
		   
		   
	   }
	   else if (frame <= 13 && frame > 10){
		   frame +=  frameadd;
		   row = ((int) (frame) - 9) * (width/rows);
		   line = (height/lines)*3;
	   }
	   else if (frame <= 16 && frame > 13){
		   line = 0;
		   frame = 0;
		   count = 0;
		   Main.spell_iceshield = false;
	   }
	   
	   if (row == 384 && line == 256){  //Wird ausgeführt wenn der Zauber wirkt
		   casted = true;
		   count += 1;
		   manaminus += .1 ;
		   if(manaminus > 1.1){
			   manaminus = 0.5;
		   }
		   Main.player.setMana(Main.player.getMana() - (int)(manaminus));
		   if(count == duration){ //Wird ausgeführt sobald Ende der Wirkungszeit erreicht ist
			   casted = false;
			   count -= 1;
			   manaminus = 0;
		   }
	   }
}

public int getLine() {
	return line;
}

public int getRow() {
	return row;
}

public int getHeight() {
	return height;
}

public int getWidth() {
	return width;
}

public int getCount() {
	return count;
}

public int getLines() {
	return lines;
}

public int getRows() {
	return rows;
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

public void setHeight(int height) {
	this.height = height;
}

public void setWidth(int width) {
	this.width = width;
}

public void setCount(int count) {
	this.count = count;
}

public void setLines(int lines) {
	this.lines = lines;
}

public void setRows(int rows) {
	this.rows = rows;
}

public void setFrame(double frame) {
	this.frame = frame;
}


}

