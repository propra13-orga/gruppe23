/*
 * author Brigitta Wanner
 */

package game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;

public class Server  
{
	
	//Instanzvariablen
	public static boolean connected = false, inServer = true;
	public final static int port = 4321;		// Port, der zum Verbinden geoeffnet wird 	
	private PrintWriter clientwriter;
	private BufferedReader clientreader;
	private Thread thread;
	private ServerSocket server;
	Socket Csocket;

	
	/*
	 * Konstruktor
	 */
	public Server() 
	{
		this.inServer = true;
		
		try
        {
            server = new ServerSocket(port);
        }
		
		//Ignorieren der Fehlermeldung
        catch(IOException ioe)
        {
            System.out.println("Quitting.");
        } 
		
		while(inServer)
		{
			try
			{
				System.out.println("Search");
				
				Socket clientSocket = server.accept();
				connected = true;
				System.out.println("Connected");
				ServiceThread clientThread = new ServiceThread(clientSocket);
		        
				
				clientThread.start();
			}
			catch(IOException ioe)
			{
			
			}
			
		}
		
		
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


