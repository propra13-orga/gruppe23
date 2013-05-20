package main_package;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//Main Methode
public class frame extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static JButton schliessen;
	public static JButton beenden;
	
	public static int lvl = 0;
	
	//Men�fenster erstellen
	
	public static void main(String[] args) {
		
		Gameframe gframe = new Gameframe();
		gframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gframe.setSize(800, 800);		
		gframe.setLayout(null);		
		gframe.setLocationRelativeTo(null);
		gframe.setUndecorated(true);
		gframe.setVisible(true);
		gframe.makestrat();

		//Hauptschleife
		
		while (true){
			
			if(lvl==0)gframe.menuepaint();
			else
			{
				
			gframe.update();
			gframe.repaint();
			
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			}
		}
				
	}
}

