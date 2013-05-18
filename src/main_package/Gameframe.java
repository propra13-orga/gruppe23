package main_package;

import java.awt.Color;
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
	
	public Gameframe(){
		
		super("Dungeon Crawler");
	
	}
	
	int xpos;
	int ypos;
	int xpos2;
	int ypos2;
	
	public void makestrat(){
		
		createBufferStrategy(2);
		strat = getBufferStrategy();
	
	}
	
	public void repaint(){
		
		Graphics g = strat.getDrawGraphics();
		draw(g);
		g.dispose();
		strat.show();
	}
	
	public void draw(Graphics g){
		
		g.setColor(Color.gray);
		g.fillRect(0, 0, 800, 600);
		g.setColor(Color.red);
		g.fillRect(xpos, ypos, 50, 50);
		g.setColor(Color.blue);
		g.fillRect(xpos2, ypos2, 50, 50);
		
	}
	
	public void update(){
		
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
		
	}
	
	public void loop(Gameframe gframe){
	
		while(frame.lvl == 1){
		
			gframe.update();
			gframe.repaint();
		
				if(xpos == 540) frame.lvl += 1;
					try {
						Thread.sleep(10);
					}
					catch (InterruptedException e) {
						e.printStackTrace();
					}
			}
	
		while(frame.lvl == 2){
			
			gframe.update2();
			gframe.repaint();
			
			if(ypos == 540) frame.lvl += 1;
				try {
					Thread.sleep(10);
				}
				catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		
		while(frame.lvl == 3){
			
			gframe.update3();
			gframe.repaint();
			
			if(ypos == 30){ frame.lvl = 0;
			gframe.setVisible(false);
			}
			try {
				Thread.sleep(10);
			}
			catch (InterruptedException e) {
				e.printStackTrace();
			}	
		}
		
		//System.exit(0);
	}
}


