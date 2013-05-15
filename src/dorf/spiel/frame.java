package dorf.spiel;

import java.awt.Frame;

import javax.swing.*;

import main_package.Gameframe;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class frame extends JFrame implements ActionListener{
	
	private JButton schliessen;
	private JButton beenden;
	
	public static void main(String[] args) {
		
		frame frame = new frame ("Menü");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400, 400);
		
		frame.setLayout(null);
		frame.setVisible(true);
		
	}
	
	public frame(String title){
		super(title);
		
		schliessen = new JButton("Spiel starten");
		schliessen.setBounds(120,100,160,40);
		schliessen.addActionListener(this);
		add(schliessen);
		
		beenden = new JButton("Ende");
		beenden.setBounds(120,200,160,40);
		beenden.addActionListener(this);
		add(beenden);
		
		
	}


public static void fenster(){
	JFrame fenster = new JFrame();
	fenster.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	fenster.setSize(650,350);
	fenster.setVisible(true);
	fenster.add(new gui());
	
		
}

public void actionPerformed(ActionEvent e){
	if(e.getSource()==schliessen){
		Gameframe gframe = new Gameframe();
	}

if(e.getSource()==beenden){
	System.exit(0);
	
}
}
}
