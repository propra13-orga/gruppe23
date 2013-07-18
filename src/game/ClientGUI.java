/*
 * author Brigitta Wanner
 * graphische Oberflaeche fuer den Client, nachdem man auf Suchen geht
 */
package game;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JScrollPane;

public class ClientGUI 
{
	public static boolean inClient, clientReady;
	static String gamedifficulty = "easy";
	static int playercount = 1;
	static JFrame client_frame = new JFrame();
	static JTextField client_field = new JTextField();
	static JButton SendButtonC = new JButton();
	static JButton SettingsButtonC = new JButton();
	static JButton ReadyButton = new JButton();
	static JTextArea show_areaC = new JTextArea();
	static JTextArea show_partiesC = new JTextArea();
	static String user_name = "unnamend";
	static String serveruser_name = "unnamend";
	static JPanel settings_panel = new JPanel();
	static JFrame settings_frameC = new JFrame();
	static JLabel changeUsernameC = new JLabel();
	static JButton SaveSettingsC = new JButton();
	static JTextField nickname_fieldC = new JTextField();
	static JScrollPane scrollTextC = new JScrollPane();
	static JFrame port_frame = new JFrame();
	static JLabel port_label = new JLabel();
	static JLabel DotLabelOne = new JLabel();
	static JLabel DotLabelTwo = new JLabel();
	static JLabel DotLabelThree = new JLabel();
	static JButton SetPortButton = new JButton();
	static JTextField PortAdressOne = new JTextField();
	static JTextField PortAdressTwo = new JTextField();
	static JTextField PortAdressThree = new JTextField();
	static JTextField PortAdressFour = new JTextField();
	static JPanel PortPanel = new JPanel();
	
	
	//Methode, die die GUI erstellt
	public static void clientLobby()
	{
		inClient = true;
		clientReady = false;
		
		client_frame = new JFrame("Multiplayer Lobby");
		client_frame.setLayout(new BorderLayout(5,5));
		client_frame.setSize(600,350);
		client_frame.setVisible(true);
		client_frame.setLocation(100,175);
		client_frame.setResizable(false);
		
		// center panel
		client_field = new JTextField();
		client_field.setDocument(new JTextFieldLimit(47));
		client_field.setText("Write a Message");
		SendButtonC = new JButton("Send");
		SettingsButtonC = new JButton("Settings");
		JPanel message_panel = new JPanel();
		message_panel.setLayout(new BorderLayout());
		message_panel.add(SettingsButtonC, BorderLayout.LINE_START);
		message_panel.add(client_field, BorderLayout.CENTER);
		message_panel.add(SendButtonC, BorderLayout.LINE_END);
		 		
		show_areaC = new JTextArea();
		show_areaC.setEditable(false);
		scrollTextC = new JScrollPane(show_areaC);
		
		JPanel center_panel = new JPanel();
		center_panel.setLayout(new BorderLayout());
		center_panel.add(scrollTextC, BorderLayout.CENTER);
		center_panel.add(message_panel, BorderLayout.PAGE_END);
		
		client_frame.add(center_panel, BorderLayout.CENTER);
		
		// right panel
		ReadyButton = new JButton("Ready!");
		show_partiesC = new JTextArea("User in Lobby: " + "\n" + user_name);
		show_partiesC.setEditable(false);
				
		JPanel right_panel = new JPanel();
		right_panel.setLayout(new BorderLayout());
		right_panel.add(show_partiesC, BorderLayout.CENTER);
		right_panel.add(ReadyButton, BorderLayout.PAGE_END);
		
		ReadyButton.addActionListener(new java.awt.event.ActionListener() 
		{
	        public void actionPerformed(java.awt.event.ActionEvent e) 
	        {
	        	Client.sendMessage(new Message(0,"",user_name,1));
	        	clientReady = true;
	        	refreshUserNames();
	        }
		});
		
 		client_frame.add(right_panel, BorderLayout.LINE_END);
 		
 		SendButtonC.addActionListener(new ActionListener() 
		{
            public void actionPerformed(ActionEvent e) 
            {
            	if(!client_field.getText().trim().equals(""))
       	 		{
       	 			if(ClientGUI.inClient == true)
       	 			{
       	 				Client.sendMessage(new Message(0, client_field.getText(), user_name, 0));
       	 			}
       	 			
       	 			show_areaC.append(user_name + ": " + client_field.getText() + "\n");
       	 			show_areaC.selectAll(); // Scrollt automatisch nach unten
       	 			client_field.setText("");
       	 			
       	 		}
            }
		});
 		
 		SettingsButtonC.addActionListener(new ActionListener() 
		{
            public void actionPerformed(ActionEvent e) 
            {
            	setThings();
            }
		});
		
	}
	
	// Refreshing the Username TextArea
	public static void refreshUserNames()
	{
		if(playercount == 2)
		{
			if(clientReady == false)
			{
				show_partiesC.setText("User in Lobby: " + "\n" + user_name + ": " + "\n" + "   UnReady" + "\n" + serveruser_name + "\n" );
			}
			if(clientReady == true)
			{
				show_partiesC.setText("User in Lobby: " + "\n" + user_name + ": " + "\n" + "   Ready" + "\n" + serveruser_name + "\n" );
			}
		}
		else
		{
			show_partiesC.setText("User in Lobby: " + "\n" + user_name + "\n");
		}
		
	}
	
	public static void setThings()
	{
		settings_frameC.dispose();
		
		settings_frameC = new JFrame();
		settings_frameC.setLayout(new BorderLayout());
		settings_frameC.setSize(350,80);
		settings_frameC.setVisible(true);
		settings_frameC.setLocation(200,350);
		settings_frameC.setResizable(false);
		
		settings_panel = new JPanel();
		settings_panel.setLayout(new BorderLayout());
		changeUsernameC = new JLabel("Change your Nickname:");
		SaveSettingsC = new JButton("Save Settings");
		nickname_fieldC = new JTextField();
		nickname_fieldC.setDocument(new JTextFieldLimit(12));
		nickname_fieldC.setText(user_name);
		
		settings_panel.add(changeUsernameC, BorderLayout.LINE_START);
		settings_panel.add(nickname_fieldC, BorderLayout.CENTER);
		settings_panel.add(SaveSettingsC, BorderLayout.LINE_END);
		
		settings_frameC.add(settings_panel, BorderLayout.PAGE_START );
		
		
		SaveSettingsC.addActionListener(new ActionListener() 
		{
            public void actionPerformed(ActionEvent e) 
            {
            	user_name = nickname_fieldC.getText();
    	 		settings_frameC.dispose();
    	 		settings_frameC = new JFrame();
    	 		refreshUserNames();
    	 		Client.sendMessage(new Message(0, "", user_name, 2));
            }
		});
	}
	
	public static void setPort()
	{
		port_frame.dispose();
		
		port_frame = new JFrame();
		port_frame.setLayout(new BorderLayout(5,5));
		port_frame.setSize(300,110);
		port_frame.setVisible(true);
		port_frame.setLocation(200,350);
		port_frame.setResizable(false);
		
		port_label = new JLabel("Please enter the networkadress of the gamehost");
		port_frame.add(port_label, BorderLayout.PAGE_START);
		
		// Eingabefeld für die Netzwerkadress des Hosts
		
		PortAdressOne = new JTextField();
		PortAdressOne.setDocument(new JTextFieldLimit(3));
		PortAdressOne.setText("127");
		DotLabelOne = new JLabel(".");
		PortAdressTwo = new JTextField();
		PortAdressTwo.setDocument(new JTextFieldLimit(3));
		PortAdressTwo.setText("0");
		DotLabelTwo = new JLabel(".");
		PortAdressThree = new JTextField();
		PortAdressThree.setDocument(new JTextFieldLimit(3));
		PortAdressThree.setText("0");
		DotLabelThree = new JLabel(".");
		PortAdressFour = new JTextField();
		PortAdressFour.setDocument(new JTextFieldLimit(3));
		PortAdressFour.setText("1");
		
		PortPanel = new JPanel();
		PortPanel.setLayout(new GridLayout(1,7));
		
		PortPanel.add(PortAdressOne );
		PortPanel.add(DotLabelOne );
		PortPanel.add(PortAdressTwo);
		PortPanel.add(DotLabelTwo);
		PortPanel.add(PortAdressThree);
		PortPanel.add(DotLabelThree);
		PortPanel.add(PortAdressFour);
		
		port_frame.add(PortPanel, BorderLayout.CENTER);
		// Button hinzufuegen
		
		SetPortButton = new JButton("Next...");
		
		port_frame.add(SetPortButton, BorderLayout.PAGE_END);
		
		SetPortButton.addActionListener(new ActionListener() 
		{
            public void actionPerformed(ActionEvent e) 
            {
            	port_frame.dispose();
            	Client.host = PortAdressOne.getText() + "." + PortAdressTwo.getText() + "." + PortAdressThree.getText() + "." + PortAdressFour.getText();
            	
            	clientLobby();
            	
            	Thread LobbyThreadC = new Thread()
    	 		{
    	 			@Override 
    	 		    public void run () 
    	 		    {
    	 				ClientGUI.show_areaC.append("Spiel" + ": " + "Suche nach Server" + "\n");
    	 				
    	 		    	while(Client.connected == false)
    	 		    	{
    	 		    		try 
    	 		    		{
    	 						Thread.sleep(600);
    	 					} catch (InterruptedException iE) 
    	 					{
    	 						System.out.println(iE.getMessage());
    	 					}
    	 		    	}
    	 		    	if(Client.connected == true)
    	 		    	{
    	 		    		ClientGUI.show_areaC.append("Spiel" + ": " + "Verbindung ist hergestellt" + "\n");
    	 		    	}
    	 		    }
    	 		}; LobbyThreadC.start();
    	 		
    	 		new Thread() 
    	 		{
    	 		    @Override 
    	 		    public void run () 
    	 		    {
    	 		    	new Client();
    	 		    }
    	 		}.start();
            }
		});
	}
		
		
		
	

	
	public static void appendInput(Message msg)
	{
		String musername = msg.getUsername();
		String chatmessage = msg.getMessage();
		show_areaC.append(musername +": " + chatmessage + "\n");
		show_areaC.selectAll(); // Scrollt automatisch nach unten
	}
}