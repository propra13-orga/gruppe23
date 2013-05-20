package main_package;


import java.awt.Color;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import javax.swing.JFrame;


public class Gameframe extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BufferStrategy strat;
	public Spielfeld feld;
	public Spielfeld feld2;
	public Spielfeld feld3;
	public Player player;
	public Menue menue;
	public int over;
	public int fin;
	
	public Gameframe(){
		
		super("Dungeon Crawler");
		feld = new Spielfeld(8,8,3,4,2,1,5,6);
		feld2 = new Spielfeld(8,8,6,1,3,5,5,2);
		feld3 = new Spielfeld(8,8,2,6,1,1,3,5);
		addKeyListener(new Keyboard());
		addMouseListener(new Mouse());
		player = new Player(250-25,150-25);
		menue = new Menue();
		over = 0;
		fin = 0;
	}
	
	//Buffer zum Laden der Zeichnungen im Voraus
	
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
		if(frame.lvl == 1)
		{
		g.setColor(Color.gray);
		g.fillRect(0, 0, 800, 800);
		feld.paint(g);
		player.draw(g);	
		}
		
		else if (frame.lvl == 2){
		g.setColor(Color.gray);
		g.fillRect(0, 0, 800, 800);
		feld2.paint(g);
		player.draw(g);	
		}
		
		else if (frame.lvl == 3){
		g.setColor(Color.gray);
		g.fillRect(0, 0, 800, 800);
		feld3.paint(g);
		player.draw(g);	
		}
		
		else if (frame.lvl == 4){
		g.setColor(Color.black);
		g.fillRect(0, 0, 800, 800);
		g.setColor(Color.white);
		Font schrift = new Font ("Arial", Font.BOLD, 40);
		g.setFont(schrift);
		g.drawString("GAME OVER!", 300, 350);
		over +=1;		
		if (over == 100) frame.lvl = 0;
		}
		
		else if (frame.lvl == 5){
			g.setColor(Color.black);
			g.fillRect(0,0,800,800);
			feld.paintFinish(g);
			fin += 1;
			if (fin== 100) frame.lvl = 0;
		}
	}
	
	public void update(){
		
		player.update();
	}
	
	//Menü erstellen
	
	public void menuepaint(){
		Graphics g = strat.getDrawGraphics();
		menue(g);
		g.dispose();
		strat.show();
	}
	
	public void menue(Graphics g){
		
		menue.draw(g);
		
	}
	
}


