package main_package;

import javax.swing.JFrame;
import java.awt.Graphics;

public class Main {
	


	public static void main(String[] args) {
	
	
		
		Gameframe gframe = new Gameframe ();
		gframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gframe.setSize(800, 800);	
		gframe.setVisible(true);
		gframe.makestrat();
		
		Spielfeld Neu = new Spielfeld(8, 8, 7, 7, 2, 2, 3, 3);
		gframe.add(Neu);
		
		
		while(true){
			
			gframe.update();
			
			gframe.repaint(2);
			
			
			
			
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
		
		
		
		
	}
}

