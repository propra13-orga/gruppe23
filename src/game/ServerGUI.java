/*
 * author Brigitta Wanner
 * graphische Oberflaeche fuer den Host, nachdem man auf eigenes spiel starten geht
 */
package game;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

//Methode, die die GUI erstellt
public class ServerGUI implements ActionListener
{
	public static boolean inServer;
	static JFrame server_frame = new JFrame();
	static JTextField server_field = new JTextField();
	static JButton SendButtonS = new JButton();
	static JButton SettingsButtonS = new JButton();
	static JButton StartButton = new JButton();
	static JTextArea show_areaS = new JTextArea();
	static JTextArea show_partiesS = new JTextArea();
	static String user_name = "unnamend";
	static JFrame settings_frameS = new JFrame();
	static JLabel changeUsernameS = new JLabel();
	static JButton SaveSettingsS = new JButton();
	static JTextField nickname_fieldS = new JTextField();
	
	//Methode erstellt die Lobby
	public static void serverLobby()
	{
		inServer = true;
		server_frame = new JFrame("Multiplayer Lobby");
		server_frame.setLayout(new BorderLayout(5,5));
		server_frame.setSize(600,350);
		server_frame.setVisible(true);
		server_frame.setLocation(100,175);
		
		// center panel
		server_field = new JTextField("Write a Message");
		SendButtonS = new JButton("Send");
		SettingsButtonS = new JButton("Settings");
		StartButton = new JButton("Start Game");
		JPanel message_panelS = new JPanel();
		message_panelS.setLayout(new BorderLayout());
		message_panelS.add(SettingsButtonS, BorderLayout.LINE_START);
		message_panelS.add(server_field, BorderLayout.CENTER);
		message_panelS.add(SendButtonS, BorderLayout.LINE_END);
		 		
		show_areaS = new JTextArea();
		show_areaS.setEditable(false);
		JPanel center_panel = new JPanel();
		center_panel.setLayout(new BorderLayout());
		center_panel.add(show_areaS, BorderLayout.CENTER);
		center_panel.add(message_panelS, BorderLayout.PAGE_END);
		
		// right panel
		show_partiesS = new JTextArea("User in Lobby: ");
		show_partiesS.setEditable(false);
				
		JPanel right_panel = new JPanel();
		right_panel.setLayout(new BorderLayout());
		right_panel.add(show_partiesS, BorderLayout.CENTER);
		right_panel.add(StartButton, BorderLayout.PAGE_END);
		
		StartButton.addActionListener(new java.awt.event.ActionListener() 
		{
            public void actionPerformed(java.awt.event.ActionEvent e) 
            {
    	 		inServer = false;
    	 		Multiplayer.inMulti = true;
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
       	 				System.out.println(server_field.getText());
       	 				ServiceThread.sendString(server_field.getText(), user_name);
       	 			}
       	 			
       	 			show_areaS.append(user_name + ": " + server_field.getText() + "\n");
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
	
	// Settings
	public static void setThings()
	{
		settings_frameS.setLayout(new FlowLayout());
		settings_frameS.setSize(300,120);
		settings_frameS.setVisible(true);
		settings_frameS.setLocation(200,350);
			
		changeUsernameS = new JLabel("Change your Nickname:");
		SaveSettingsS = new JButton("Save Settings");
		nickname_fieldS = new JTextField(user_name);
		nickname_fieldS.setSize(50, 20);
			
		settings_frameS.add(changeUsernameS);
		settings_frameS.add(nickname_fieldS);
		settings_frameS.add(SaveSettingsS);
			
		SaveSettingsS.addActionListener(new ActionListener() 
		{
	        public void actionPerformed(ActionEvent e) 
	        {
	        	user_name = nickname_fieldS.getText();

	    	 	settings_frameS.dispose();
	    	 	settings_frameS = new JFrame();
	        }
		});
	}
		
	public static void appendInput(String input)
	{
		System.out.println("input");
		show_areaS.append(input + "\n");
	}

	@Override
	public void actionPerformed(ActionEvent evt) 
	{
		// TODO Auto-generated method stub
		
	}

}
