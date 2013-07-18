/*
 * author Brigitta Wanner
 */

package game;

//Imports die benoetigt werden
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.*;


public class Client  
{
	//Instanzvariablen
	private final int port = 4321;		// Port, der zum Verbinden geoeffnet wird
	static String host = "";
	public static boolean connected;	// boolean, der fragt, ob der client verbunden wurde
	public static boolean inClient = true, inGame = false;
	private Socket client = new Socket();
	public static ObjectOutputStream socketWriter;
	public  ObjectInputStream socketReader;
	static Message msg;
	
	/*
	 * Konstruktor
	 * 
	 */
	public Client() 
	{
		inClient = true;
		
		try
        {
			// eroeffnung einer neuen Verbindung 
            client = new Socket(host, this.port);
            socketWriter = new ObjectOutputStream(client.getOutputStream());
            socketReader  = new ObjectInputStream(client.getInputStream());
			
			
            
            ClientGUI.playercount = 2;
            sendMessage(new Message(0,"",ClientGUI.user_name, 2));
            ClientGUI.refreshUserNames();
            
			//Solange wie im Client oder im Multiplayer = true müssen Daten bearbeitet werden
			while(true)
			{
				
				connected = true;
				try
	        	{
	        		msg = (Message) socketReader.readObject();
	        		
	        		processMsg(msg);
	        	}
	        	catch(Exception io)
	        	{
	        		
	        	}
			}

        }
		
		//Ignorieren der Fehlermeldungen
        catch(IOException ioe)
        {
        	System.out.println(ioe.getMessage());        
        } 
		
		// Schließen des Sockets
		finally
        {
            // Clean up
            try
            {	
            	//socketWriter.flush();
            	//socketReader.close();
            	//socketWriter.close();
        		client.close();	
                System.out.println("...Stopped");
            }
            catch(IOException ioe)
            {
            	System.out.println(ioe.getMessage());
            }
        }
	}


	// Verarbeitung der Servermessages (Protokoll)
	private void processMsg( Message inputmsg ) 
	{	// Check ob man sich bereits im Spiel befindet oder ob man sich noch im Client ist
		if(Multiplayer.inMulti == true)
		{
			if(inputmsg.getIndicator() == 0) 
			{
				Main.keyCheck(inputmsg.getMessage(), inputmsg.getType());
			}
		}
		if(ClientGUI.inClient == true)
		{
			if(inputmsg.getIndicator() == 1) 
			{
				// Client ready - wird vom Server nicht verschickt
			}
			if(inputmsg.getIndicator() == 2) // Server hat den Usernamen gewechselt
			{
				String username = inputmsg.getUsername();
				ClientGUI.serveruser_name = username;
				ClientGUI.refreshUserNames();
			}
			if(inputmsg.getIndicator() == 0) // Server schickt eine normale Message
			{
				ClientGUI.appendInput(inputmsg);
			}
			if(inputmsg.getIndicator() == 3) // Server hat neuen Schwierigkeitsgrad ausgewählt
			{
				ClientGUI.gamedifficulty = inputmsg.getMessage();
			}
			if(inputmsg.getIndicator() == 4) // Server startet das Spiel
			{
				ClientGUI.client_frame.setVisible(false);
				ClientGUI.inClient = false;
				inGame = true;
				Multiplayer.inMulti = true;
				try 
				{
				if(ClientGUI.gamedifficulty.equals("easy"))
				  {
					  Main.map.loadMap("maps/multiplayermap.txt"); //Karte laden 
				  }
				  if(ClientGUI.gamedifficulty.equals("medium"))
				  {
					  Main.map.loadMap("maps/multiplayer2.txt"); //Karte laden 
				  }
				  if(ClientGUI.gamedifficulty.equals("hard"))
				  {
					  Main.map.loadMap("maps/multiplayer3.txt"); //Karte laden 
				  }
				}
				catch(Exception io)
				{
					
				}
				Main.fenster.setTitle("Multiplayer - Run to the Exit!");
				Main.inMenue = false; // Multiplayer startet
			}
		}
	}
	


	// Sendet einen String 
	public static void sendMessage(Message chatmessage ) 
	{
		try
		{
			socketWriter.writeObject(chatmessage);
		}
		catch(Exception io)
		{
			
		}
	}
	

	public static void sendCommand(Message command)
	{
		
	}
	
	
	
}