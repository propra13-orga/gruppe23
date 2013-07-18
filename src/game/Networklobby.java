/*
* author Brigitta Wanner
*/

package game;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Networklobby extends JFrame implements ActionListener
{
	//Instanzvariablen
	Client client;
	Server server;
	JButton SearchButton = new JButton();
	JButton StartLobby = new JButton();
	

	
	static String input;
	private static String user_name = "unnamend";
	static JFrame settings_frame = new JFrame();
	JFrame start_frame = new JFrame();
	static JFrame lobby_frame = new JFrame();
	
	
	//Abfrage ob Mitspieler gesucht werden sollen oder ob selbst ein Spiel gestartet werden soll
	public void startLobby()
	{
		start_frame.setSize(300,150);
		start_frame.setVisible(true);
		start_frame.setLayout(new FlowLayout());
		start_frame.setLocation(200,350);

		SearchButton.setText("Search for gamelobbys");
		StartLobby.setText("Start your own game");
		
		start_frame.add(SearchButton);
		start_frame.add(StartLobby);
		
		SearchButton.addActionListener(this);
		StartLobby.addActionListener(this);
	}
	
	//Methode, die die GUI der Lobby erstellt
	
	@Override
	public void actionPerformed(ActionEvent evt) 
	{
		Object src = evt.getSource();
   	 
	 	if(src == StartLobby)
	 	{
	 		ServerGUI.serverLobby();
	 		
	 		start_frame.dispose();
	 		this.start_frame = new JFrame();
 
	 		 Thread LobbyThreadS = new Thread()
		 		{
		 			@Override 
		 		    public void run () 
		 		    {
		 				ServerGUI.show_areaS.append("Spiel" + ": " + "Warte auf Spieler" + "\n");
		 				
		 		    	while(Server.connected == false)
		 		    	{
		 		    		try 
		 		    		{
		 						Thread.sleep(600);
		 					} catch (InterruptedException iE) 
		 					{
		 						System.out.println(iE.getMessage());
		 					}
		 		    	}
		 		    	if(Server.connected == true)
		 		    	{
		 		    		ServerGUI.show_areaS.append("Spiel" + ": " + "Verbindung ist hergestellt" + "\n");
		 		    	}
		 		    }
		 		};LobbyThreadS.start();
		 		
		 		new Thread() 
		 		{
		 		    @Override 
		 		    public void run () 
		 		    {
		 		    	new Server();
		 		    }
		 		}.start();
	 	}
 	
	 	if(src == SearchButton)
	 	{
	 		ClientGUI.setPort();
	 		start_frame.dispose();
	 		this.start_frame = new JFrame();
	 	} 
	}
}
