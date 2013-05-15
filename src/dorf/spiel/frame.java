package dorf.spiel;

import java.awt.Frame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class frame extends JFrame implements ActionListener{
	
	private JButton schliessen;
	private JButton einstellungen;
	private JButton info;
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
/*	public void ActionPerformed(ActionEvent e){
		
		if(e.getSource()==schliessen){
			fenster();
		}
		
		if(e.getSource()== info){
			Object[] options = {"OK"};
		
			JOptionPane.showOptionDialog(null, "Programmiert von Martha","Information",JOptionPane.DEFAULT_OPTION,JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
		
	}

	if(e.getSource()==einstellungen){
	//	auswahl();
		
	}
	
	if(e.getSource()==ende){
		System.exit(0);
		
	}
}*/

public static void fenster(){
	JFrame fenster = new JFrame();
	fenster.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	fenster.setSize(650,350);
	fenster.setVisible(true);
	fenster.add(new gui());
		
}

public void actionPerformed(ActionEvent e){
	if(e.getSource()==schliessen){
		fenster();
	}

if(e.getSource()==beenden){
	System.exit(0);
	
}
}
}
