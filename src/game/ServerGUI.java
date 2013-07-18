/*
 * author Brigitta Wanner
 * graphische Oberflaeche fuer den Host, nachdem man auf eigenes spiel starten geht
 */
package game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

//Methode, die die GUI erstellt
public class ServerGUI implements ActionListener
{
	Server server;
	public static String easy = "easy", medium = "medium", hard = "hard", selectedDifficulty = "easy";
	public static boolean inServer, cReady = false, selectedDifficultyb = false;
	static int playercounts = 1;
	static JFrame server_frame = new JFrame();
	static JTextField server_field = new JTextField();
	static JButton SendButtonS = new JButton();
	static JButton SettingsButtonS = new JButton();
	static JButton StartButton = new JButton();
	static JTextArea show_areaS = new JTextArea();
	static JTextArea show_partiesS = new JTextArea();
	static String user_name = "unnamend";
	static String clientuser_name = "unnamend";
	static JPanel nickname_panelS = new JPanel();
	static JPanel difficulty_panelS = new JPanel();
	static JFrame settings_frameS = new JFrame();
	static JLabel changeUsernameS = new JLabel();
	static JLabel changeDifficulty = new JLabel();
	static JButton SaveSettingsS = new JButton();
	static JTextField nickname_fieldS = new JTextField();
	static JScrollPane scrollTextS = new JScrollPane();
	static Vector difficulty_vec = new Vector();
	static DefaultComboBoxModel difficultymodel = new DefaultComboBoxModel(difficulty_vec);
    static JComboBox difficultychoice = new JComboBox(difficultymodel);
	
	//Methode erstellt die Lobby
	public static void serverLobby()
	{
		inServer = true;
		
		
		server_frame = new JFrame("Multiplayer Lobby");
		server_frame.setLayout(new BorderLayout(5,5));
		server_frame.setSize(600,350);
		server_frame.setVisible(true);
		server_frame.setLocation(100,175);
		server_frame.setResizable(false);
		
		// center panel
		server_field = new JTextField();
		server_field.setDocument(new JTextFieldLimit(47));
		server_field.setText("Write a Message");
		SendButtonS = new JButton("Send");
		SettingsButtonS = new JButton("Settings");
		StartButton = new JButton("Start Game");
		StartButton.setBackground(Color.RED);
		JPanel message_panelS = new JPanel();
		message_panelS.setLayout(new BorderLayout());
		message_panelS.add(SettingsButtonS, BorderLayout.LINE_START);
		message_panelS.add(server_field, BorderLayout.CENTER);
		message_panelS.add(SendButtonS, BorderLayout.LINE_END);
		 		
		show_areaS = new JTextArea();
		show_areaS.setEditable(false);
		scrollTextS = new JScrollPane(show_areaS);
		JPanel center_panel = new JPanel();
		center_panel.setLayout(new BorderLayout());
		center_panel.add(scrollTextS, BorderLayout.CENTER);
		center_panel.add(message_panelS, BorderLayout.PAGE_END);
		
		// right panel
		show_partiesS = new JTextArea("User in Lobby: " + "\n" + user_name);
		show_partiesS.setEditable(false);
				
		JPanel right_panel = new JPanel();
		right_panel.setLayout(new BorderLayout());
		right_panel.add(show_partiesS, BorderLayout.CENTER);
		right_panel.add(StartButton, BorderLayout.PAGE_END);
		
		StartButton.addActionListener(new java.awt.event.ActionListener() 
		{
            public void actionPerformed(java.awt.event.ActionEvent e) 
            {
            	if(ServerGUI.cReady == false) // Wenn der Client nicht ready ist, information ausgeben
            	{
            		show_areaS.append("Spiel: Der Spielleiter möchte das Spiel starten" + "\n");
            		Server.sendMessage(new Message(0, "Der Spielleiter möchte das Spiel starten", "Spiel", 0));
            	}
            	if(ServerGUI.cReady == true) // Wenn Client ready ist, Spiel starten
            	{
            		Server.sendMessage(new Message(0,"","",4));
            		
            		inServer = false;
            		Server.inGame = true;
            		Multiplayer.inMulti = true;
            		server_frame.setVisible(false);
            		// MUltiplayer startet jetzt.
            		try
            		{
            			if(ServerGUI.selectedDifficulty.equals("easy"))
            			{
						  Main.map.loadMap("maps/multiplayermap.txt"); //Karte laden 
            			}
            			if(ServerGUI.selectedDifficulty.equals("medium"))
						 {
						  Main.map.loadMap("maps/multiplayer2.txt"); //Karte laden 
						 }
            			if(ServerGUI.selectedDifficulty.equals("hard"))
            			{
						  Main.map.loadMap("maps/multiplayer3.txt"); //Karte laden 
            			}
            		}
            		catch(Exception io)
            		{
            			
            		}
            		Main.fenster.setTitle("Multiplayer - Run to the Exit!");
            		Main.inMenue = false;
            		
            		
            	}
            }
		});
		
		server_frame.add(center_panel, BorderLayout.CENTER);
 		server_frame.add(right_panel, BorderLayout.LINE_END);
 		
 		SendButtonS.addActionListener(new ActionListener() 
		{
            public void actionPerformed(ActionEvent e) 
            {
            	if(!server_field.getText().trim().equals(""))
       	 		{
       	 			if(Server.connected == true)
       	 			{
       	 				Server.sendMessage(new Message(0, server_field.getText(), user_name, 0));
       	 			}
       	 			
       	 			show_areaS.append(user_name + ": " + server_field.getText() + "\n");
       	 			show_areaS.selectAll();
       	 			server_field.setText("");
       	 			
       	 		}
            }
		});
 		
 		SettingsButtonS.addActionListener(new ActionListener() 
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
		if(playercounts == 2)
		{
			if(cReady == false)
			{
				show_partiesS.setText("User in Lobby: " + "\n" + user_name + "\n" + clientuser_name + ": " + "\n" + "   UnReady" + "\n");
			}
			if(cReady == true)
			{
				show_partiesS.setText("User in Lobby: " + "\n" + user_name + "\n" + clientuser_name + ": " + "\n" + "   Ready" + "\n");
			}
		}
		else
		{
			show_partiesS .setText("User in Lobby: " + "\n" + user_name);
		}
		
	}
	
	// Settings
	public static void setThings()
	{
		settings_frameS.dispose();
		
		settings_frameS = new JFrame();
		settings_frameS.setLayout(new BorderLayout());
		settings_frameS.setSize(250,110);
		settings_frameS.setVisible(true);
		settings_frameS.setLocation(200,350);
		settings_frameS.setResizable(false);
		
		// nickname panel
		nickname_panelS = new JPanel();
		nickname_panelS.setLayout(new BorderLayout());
		changeUsernameS = new JLabel("Change your Nickname: ");
		nickname_fieldS.setDocument(new JTextFieldLimit(12));
		nickname_fieldS.setText(user_name);
		
		nickname_panelS.add(changeUsernameS, BorderLayout.LINE_START);
		nickname_panelS.add(nickname_fieldS, BorderLayout.CENTER);
		
		// Difficulty panel
		difficulty_panelS = new JPanel();
		difficulty_panelS.setLayout(new BorderLayout());
		changeDifficulty = new JLabel("Set the Difficulty:              ");
		
		difficulty_panelS.add(changeDifficulty, BorderLayout.LINE_START);
		difficulty_panelS.add(difficultychoice, BorderLayout.CENTER);
		
		difficulty_vec.add(0, easy);
		difficulty_vec.add(1, medium);
		difficulty_vec.add(2, hard);
		
		// Center Panel
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(2,1));
		panel.add(nickname_panelS);
		panel.add(difficulty_panelS);

		settings_frameS.add(panel, BorderLayout.CENTER);
		
		SaveSettingsS = new JButton("Save Settings");
		settings_frameS.add(SaveSettingsS, BorderLayout.PAGE_END);
		
		
		difficultychoice.addItemListener(new java.awt.event.ItemListener() 
 		{
 			public void itemStateChanged(ItemEvent e) 
 			{
 				if (e.getStateChange() != e.DESELECTED)
 				{
 					selectedDifficulty = (String) difficultymodel.getSelectedItem();
 					selectedDifficultyb = true;
 				}
 				
 			}
 		});
		
		SaveSettingsS.addActionListener(new ActionListener() 
		{
	        public void actionPerformed(ActionEvent e) 
	        {
	        	user_name = nickname_fieldS.getText();
	    	 	settings_frameS.dispose();
	    	 	settings_frameS = new JFrame();
	    	 	refreshUserNames();
	    	 	Server.sendMessage(new Message(0, "", user_name, 2));
	    	 	
	    	 	// Wenn der Schwierigkeitsgrad geaendert wird, wird dies zugeschickt
	    	 	if(selectedDifficultyb == true)
	    	 	{
	    	 		Server.sendMessage(new Message(0, selectedDifficulty ,user_name, 3));
	    	 	}
	    	 	selectedDifficultyb = false;
	        }
		});
	}
		
	public static void appendInput(Message msg)
	{
		String musername = msg.getUsername();
		String chatmessage = msg.getMessage();
		show_areaS.append(musername +": " + chatmessage + "\n");
		show_areaS.selectAll();
	}

	@Override
	public void actionPerformed(ActionEvent evt) 
	{
		// TODO Auto-generated method stub
		
	}

}