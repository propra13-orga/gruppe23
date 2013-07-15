/*
 * author Brigitta Wanner
 */

package game;

//Imports die benoetigt werden
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;


public class Client  
{
	//Instanzvariablen
	private final int port = 4321;		// Port, der zum Verbinden geoeffnet wird
	private final String host = "localhost"; 
	public static boolean connected;	// boolean, der fragt, ob der client verbunden wurde
	public static boolean inClient = true;
	private Socket client = new Socket();
	private static PrintWriter socketWriter;
	private static BufferedReader socketReader;
	
	/*
	 * Konstruktor
	 * 
	 */
	public Client() 
	{
		this.inClient = true;
		
		try
        {
			// eroeffnung einer neuen Verbindung 
            client = new Socket(this.host, this.port);
            this.socketReader = new BufferedReader(new InputStreamReader(this.client.getInputStream()));		// neuer Inputreader
			this.socketWriter = new PrintWriter(this.client.getOutputStream());		// neuer Outputwriter
			
			
			String inputLine;
			//Solange der Klient Daten empfaengt bleibt die Verbindung offen
			while(true)
			{
				this.connected = true;
				System.out.println(connected);
				
				while((inputLine = this.socketReader.readLine()) != null)
				{
					System.out.println("Process msg" + inputLine);
					processMsg(inputLine = this.socketReader.readLine());
				}
			}

        }
		
		//Ignorieren der Fehlermeldungen
        catch(IOException ioe)
        {
        	System.out.println(ioe.getMessage());        
        } 
		
		// wenn der Klient keine Daten mehr empaengt, schliesst er den Socket
		finally
        {
            // Clean up
            try
            {
        		client.close();	
                System.out.println("...Stopped");
            }
            catch(IOException ioe)
            {
            	System.out.println(ioe.getMessage());
            }
        }
	}


	// Verarbeitung der Servermessages
	private void processMsg( final String input )
	{
		if(ClientGUI.inClient == true)
		{
			System.out.println("input message: " +input);
			Worker ClientWorker = new Worker(input);
			ClientWorker.execute();
		}
		if(Multiplayer.inMulti == true)
		{
			String[] msg = input.split("\\s+");
			
			Multiplayer.keyCheck(msg[0], msg[1]);
		}
	}
	


	// Sendet einen String 
	public static void sendString( String message, String playername )
	{
		socketWriter.println(playername + ": " + message);
		System.out.println("Client output" + message);
	}
	

	public static void sendMessage( String message, int playerId)
	{
		socketWriter.println(message + " " + playerId);
	}
	
	
	
}