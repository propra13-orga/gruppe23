package main_package;

import javax.swing.JFrame;

public class Main {
	


	public static void main(String[] args) {
	
	//	int lvl = 0;
		
		
		Gameframe gframe = new Gameframe ();
		gframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gframe.setSize(800, 600);		
		gframe.setLayout(null);
		gframe.setVisible(true);
		gframe.makestrat();
		
		while(true){
			
			gframe.update();
			
			gframe.repaint();
			
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
}
