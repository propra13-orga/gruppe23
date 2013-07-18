/*
 * author Brigitta Wanner
 */
package game;

import javax.swing.*;
import java.awt.*;




public class Multiplayer 
{
	public static boolean inMulti = false, loadMultiMap = false;
	

	/*
	 * Konstruktor
	 */
	public Multiplayer()
	{
		
		
	}
		
	public static void playerWon(String WhoWon) 
	{
		Main.inMenue = true;
		inMulti = false;
		
		if(WhoWon.equals("Server"))
		{
			ServerGUI.appendInput(new Message(0,ServerGUI.user_name + " hat gewonnen!","Spiel",0));
			Server.sendMessage(new Message(0,ServerGUI.user_name + " hat gewonnen!","Spiel",0));
		}
		if(WhoWon.equals("Client"))
		{
			ClientGUI.appendInput(new Message(0,ClientGUI.user_name + " hat gewonnen!","Spiel",0));
			Client.sendMessage(new Message(0,ClientGUI.user_name + " hat gewonnen!","Spiel",0));
		}
	}	
}	