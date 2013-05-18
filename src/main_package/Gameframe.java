package main_package;

import java.awt.Color;

import main_package.Spielfeld;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.util.prefs.BackingStoreException;

import javax.swing.JButton;
import javax.swing.JFrame;


public class Gameframe extends JFrame {
	
	private BufferStrategy strat;
	/*Spielfeld feld;
    int level_Zahl = 1;
    int [][]Level;*/
	
	public Gameframe(){
		
		super("Dungeon Crawler");
		//this.feld = feld;
	
	}
	
	/*int xpos;
	int ypos;
	int xpos2;
	int ypos2;*/
	
	//Buffer zum Laden der Zeichnungen im Voraus
	
	public void makestrat(){
		
		createBufferStrategy(2);
		strat = getBufferStrategy();
	
	}
	
	public void repaint(){
		
		Graphics g = strat.getDrawGraphics();
		g.dispose();
		strat.show();
	}
	
	
	/*public void update(){
		
		xpos += 5;
		ypos2 += 5;

	}
	
	public void update2(){

		ypos += 5;
		xpos2 +=5;
	
	}
	
	public void update3(){
	
		ypos -= 5;
		xpos2 -=5;
		
		if(ypos==30){
			xpos -= 5;
			ypos2 -= 5;
		}
		
	}*/
	
	//Hauptschleife
	
	public void loop(Gameframe gframe){
	
		while(frame.lvl == 1){
		
			/*gframe.update();
			gframe.repaint();
		
				if(xpos == 540) frame.lvl += 1;*/
					try {
						Thread.sleep(10);
					}
					catch (InterruptedException e) {
						e.printStackTrace();
					}
			}
	
		while(frame.lvl == 2){
			
			/*gframe.update2();
			gframe.repaint();
			
			if(ypos == 540) frame.lvl += 1;*/
				try {
					Thread.sleep(10);
				}
				catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		
		while(frame.lvl == 3){
			
			/*gframe.update3();
			gframe.repaint();
			
			if(ypos == 30){ frame.lvl = 0;
			gframe.setVisible(false);
			}*/
			try {
				Thread.sleep(10);
			}
			catch (InterruptedException e) {
				e.printStackTrace();
			}	
		}
		
		System.exit(0);
	}
}


