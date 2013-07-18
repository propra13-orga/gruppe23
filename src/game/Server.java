/*
 * author Brigitta Wanner
 */

package game;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.*;

public class Server  
{
	
	//Instanzvariablen
	public static boolean connected = false, inServer = true, inGame = false;
	public final static int port = 4321;		// Port, der zum Verbinden geoeffnet wird 	
	private static ObjectOutputStream clientWriter;
	private ObjectInputStream clientReader;
	private Thread thread;
	private ServerSocket server;
	static Socket clientSocket;
	static Message msg;

	
	/*
	 * Konstruktor
	 */
	public Server() 
	{
		inServer = true;
		
		try
        {
            server = new ServerSocket(port);
        }
		
		//Ignorieren der Fehlermeldung
        catch(IOException ioe)
        {
            System.out.println("Quitting.");
        } 

		try
		{
		//	System.out.println("Search");
			clientSocket = server.accept();
			connected = true;
		//	System.out.println("Connected");
				
			clientWriter = new ObjectOutputStream(clientSocket.getOutputStream());
            clientReader  = new ObjectInputStream(clientSocket.getInputStream());
            
            // refresh ServerGUI and send Username
            ServerGUI.playercounts = 2;
            ServerGUI.refreshUserNames();
            sendMessage(new Message(0,"",ServerGUI.user_name, 2));
            
            // Solange wie im Server oder im Multiplayer = true müssen Daten bearbeitet werden
	        while(true)
			{
	        	try
	        	{
	        		msg = (Message) clientReader.readObject();
	        		Server.connected = true;
	        		processMsg(msg);
	        	}
	        	catch(Exception io)
	        	{
	        		
	        	}
			}
		}
		catch(IOException ioe)
		{
			
		}
		finally
		{
            // Clean up
				try
				{
					Server.connected = false;
					clientWriter.flush();
					clientReader.close();
					clientWriter.close();
					clientSocket.close();
					System.out.println("...Stopped");
				}
				catch(IOException ioe)
				{
            		System.out.println(ioe.getMessage());
				}
        }
	}
	
	private void processMsg(Message inputmsg)
	{
		if(Multiplayer.inMulti == true)
		{
			if(inputmsg.getIndicator() == 0) 
			{
				Main.keyCheck(inputmsg.getMessage(), inputmsg.getType()); // Bewegungssteuerung 
			}
		}
		if(ServerGUI.inServer == true)
		{
			if(inputmsg.getIndicator() == 1)
			{
				ServerGUI.cReady = true;
				ServerGUI.StartButton.setBackground(Color.GREEN);
			}
			if(inputmsg.getIndicator() == 2)
			{
				String username = inputmsg.getUsername();
				ServerGUI.clientuser_name = username;
				ServerGUI.refreshUserNames();
			}
			if(inputmsg.getIndicator() == 0)
			{
				ServerGUI.appendInput(inputmsg);
			}
			if(inputmsg.getIndicator() == 3)
			{
				// Client sendet nicht die Map
			}
			if(inputmsg.getIndicator() == 4)
			{
				// Client sendet keinte Startmessage
			}
		}
	}
	

	// Senden von Messages ueber den Socket
		public static void sendMessage(Message chatmessage ) 
		{
			try
			{
				clientWriter.writeObject(chatmessage);
			}
			catch(Exception io)
			{
				
			}
		}
		

		public static void sendCommand(Message command)
		{
			
		}
	
	public void initializeServer(String mapFile) 
	{
		
		while (connected == false) {
			try {
				thread.sleep(1000);
			} catch (InterruptedException iE) {
				System.out.println(iE.getMessage());
			}
		}
		
	}
	
}

