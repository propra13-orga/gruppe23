package main_package;

import java.awt.Frame;

import javax.swing.*;






import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class frame extends JFrame implements ActionListener{
	
	public static JButton schliessen;
	public static JButton beenden;
	
	public static int lvl = 0;
	
	public static void main(String[] args) {
		
		frame mframe = new frame ("Menü");
		mframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mframe.setSize(400, 400);	
		mframe.setLayout(null);
		mframe.setVisible(true);
		
	}
	
	public frame(String title){
		
		super(title);
		
		schliessen = new JButton("Spiel starten");
		schliessen.setBounds(100, 100, 200, 70);
		schliessen.addActionListener(this);
		add(schliessen);
		
		beenden = new JButton("Beenden");
		beenden.setBounds(100, 210, 200, 70);
		beenden.addActionListener(this);
		add(beenden);
		
	}


public static void fenster(){
	
	Gameframe gframe = new Gameframe ();
	gframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	gframe.setSize(800, 600);		
	gframe.setLayout(null);		
	gframe.setLocationRelativeTo(null);
	gframe.setUndecorated(true);
	gframe.setVisible(true);
	gframe.makestrat();
	gframe.loop(gframe);
	
}	
		


public void actionPerformed(ActionEvent e){
	
	if(e.getSource()==schliessen){
		lvl = 1;
		fenster();
	}

	if(e.getSource()==beenden){
		System.exit(0);
	
	}
}
}
