package game;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Networklobby extends JFrame implements ActionListener
{
	JButton SendButton, StartButton, SettingsButton, SaveSettings;
	JTextField edit_field = new JTextField();
	JTextField nickname_field = new JTextField();
	JTextArea show_area = new JTextArea();
	JTextArea show_parties = new JTextArea();
	public String user_name = "unnamend";
	JFrame settings_frame = new JFrame();

	
	// Methode erstellt Lobby
	public void initLobby()
	{
		JFrame lobby_frame = new JFrame("Multiplayer Lobby");
		lobby_frame.setLayout(new BorderLayout(5,5));
		lobby_frame.setSize(600,350);
		lobby_frame.setVisible(true);
 		
 		// center panel
 		edit_field = new JTextField("Write a Message");
 		SendButton = new JButton("Send");
 		SettingsButton = new JButton("Settings");
 		JPanel message_panel = new JPanel();
 		message_panel.setLayout(new BorderLayout());
 		message_panel.add(SettingsButton, BorderLayout.LINE_START);
 		message_panel.add(edit_field, BorderLayout.CENTER);
 		message_panel.add(SendButton, BorderLayout.LINE_END);
 		
 		show_area.setEditable(false);
 		JPanel center_panel = new JPanel();
 		center_panel.setLayout(new BorderLayout());
 		center_panel.add(show_area, BorderLayout.CENTER);
 		center_panel.add(message_panel, BorderLayout.PAGE_END);
 		
 		// right panel
 		show_parties = new JTextArea("User in Lobby: ");
 		show_parties.setEditable(false);
		StartButton = new JButton("Start Game");
		JPanel right_panel = new JPanel();
		right_panel.setLayout(new BorderLayout());
		right_panel.add(show_parties, BorderLayout.CENTER);
		right_panel.add(StartButton, BorderLayout.PAGE_END);
		
		
 		lobby_frame.add(center_panel, BorderLayout.CENTER);
 		lobby_frame.add(right_panel, BorderLayout.LINE_END);
 		
 		SendButton.addActionListener(this);
 		StartButton.addActionListener(this);
 		SettingsButton.addActionListener(this);
		
	}
	// Settings
	public void setThings()
	{
		settings_frame.setLayout(new FlowLayout());
		settings_frame.setSize(300,120);
		settings_frame.setVisible(true);
		
		JLabel changeUsername = new JLabel("Change your Nickname:");
		SaveSettings = new JButton("Save Settings");
		nickname_field.setSize(50, 20);
		nickname_field = new JTextField(user_name);
		
		settings_frame.add(changeUsername);
		settings_frame.add(nickname_field);
		settings_frame.add(SaveSettings);
		
		SaveSettings.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent evt) 
	{
		Object src = evt.getSource();
   	 	if(src == SendButton)
   	 	{
   	 		if(!edit_field.getText().trim().equals(""))
   	 		{
   	 			String edit_text = edit_field.getText();
   	 			show_area.append(user_name + ": " + edit_text + "\n");
   	 			edit_field.setText("");
   	 		}
   	 	}
   	 
	 	if(src == StartButton)
	 	{
	 		// However Multiplayer starts
	 		Main MultiMain = new Main();
	 		MultiMain.start();

	 	}
	 	
	 	if(src == SettingsButton)
	 	{
	 		setThings();
	 	}
	 	
	 	if(src == SaveSettings)
	 	{
	 		user_name = nickname_field.getText();
	 		settings_frame.setVisible(false);
	 		
	 	}
	}
}
