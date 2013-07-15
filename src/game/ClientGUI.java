/*
 * author Brigitta Wanner
 * graphische Oberflaeche fuer den Client, nachdem man auf Suchen geht
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

public class ClientGUI 
{
	public static boolean inClient;
	static JFrame client_frame = new JFrame();
	static JTextField client_field = new JTextField();
	static JButton SendButtonC = new JButton();
	static JButton SettingsButtonC = new JButton();
	static JButton ReadyButton = new JButton();
	static JTextArea show_areaC = new JTextArea();
	static JTextArea show_partiesC = new JTextArea();
	static String user_name = "unnamend";
	static JFrame settings_frameC = new JFrame();
	static JLabel changeUsernameC = new JLabel();
	static JButton SaveSettingsC = new JButton();
	static JTextField nickname_fieldC = new JTextField();
	
	//Methode, die die GUI erstellt
	public static void clientLobby(boolean lobby_startb)
	{
		inClient = true;
		client_frame = new JFrame("Multiplayer Lobby");
		client_frame.setLayout(new BorderLayout(5,5));
		client_frame.setSize(600,350);
		client_frame.setVisible(true);
		client_frame.setLocation(100,175);
		
		// center panel
		client_field = new JTextField("Write a Message");
		SendButtonC = new JButton("Send");
		SettingsButtonC = new JButton("Settings");
		JPanel message_panel = new JPanel();
		message_panel.setLayout(new BorderLayout());
		message_panel.add(SettingsButtonC, BorderLayout.LINE_START);
		message_panel.add(client_field, BorderLayout.CENTER);
		message_panel.add(SendButtonC, BorderLayout.LINE_END);
		 		
		show_areaC = new JTextArea();
		show_areaC.setEditable(false);
		JPanel center_panel = new JPanel();
		center_panel.setLayout(new BorderLayout());
		center_panel.add(show_areaC, BorderLayout.CENTER);
		center_panel.add(message_panel, BorderLayout.PAGE_END);
		
		client_frame.add(center_panel, BorderLayout.CENTER);
		
		// right panel
		ReadyButton = new JButton("Let's Play");
		show_partiesC = new JTextArea("User in Lobby: ");
		show_partiesC.setEditable(false);
				
		JPanel right_panel = new JPanel();
		right_panel.setLayout(new BorderLayout());
		right_panel.add(show_partiesC, BorderLayout.CENTER);
		right_panel.add(ReadyButton, BorderLayout.PAGE_END);
		
		ReadyButton.addActionListener(new java.awt.event.ActionListener() 
		{
	        public void actionPerformed(java.awt.event.ActionEvent e) 
	        {
	        	inClient = false;
	        	Multiplayer.inMulti = true;
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
       	 				System.out.println(client_field.getText());
       	 				Client.sendString(client_field.getText(), user_name);
       	 			}
       	 			
       	 			show_areaC.append(user_name + ": " + client_field.getText() + "\n");
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
	
	public static void setThings()
	{
		settings_frameC.setLayout(new FlowLayout());
		settings_frameC.setSize(300,120);
		settings_frameC.setVisible(true);
		settings_frameC.setLocation(200,350);
		
		changeUsernameC = new JLabel("Change your Nickname:");
		SaveSettingsC = new JButton("Save Settings");
		nickname_fieldC = new JTextField(user_name);
		nickname_fieldC.setSize(50, 20);
		
		settings_frameC.add(changeUsernameC);
		settings_frameC.add(nickname_fieldC);
		settings_frameC.add(SaveSettingsC);
		
		SaveSettingsC.addActionListener(new ActionListener() 
		{
            public void actionPerformed(ActionEvent e) 
            {
            	user_name = nickname_fieldC.getText();

    	 		settings_frameC.dispose();
    	 		settings_frameC = new JFrame();
            }
		});
	}

	
	public static void appendInput(String input)
	{
		show_areaC.append(input + "\n");
	}
}
