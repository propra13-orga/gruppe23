package main_package;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

public class Gameframe extends JFrame {
	
	private BufferStrategy strat;
	
	public Gameframe(){
		
		super("Dungeon Crawler");
	}
	
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
		

	}
	
	public void update(){
		
		
	}
	

}
