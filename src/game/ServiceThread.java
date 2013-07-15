/*
 * author Brigitta Wanner
 */

package game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.*;

public class ServiceThread extends Thread implements Runnable
{
	Socket ClientSocket;
	static BufferedReader inReader;
    static PrintWriter outWriter;
    
    /*
     * Konstruktor
     */
	public ServiceThread()
	{
		super();
	}
	
	ServiceThread(Socket socket)
	{
		ClientSocket = socket;
	}
	@Override
	public void run() 
	{
        
        try
        {  
        	inReader = new BufferedReader(new InputStreamReader(ClientSocket.getInputStream()));
        	outWriter = new PrintWriter(new OutputStreamWriter(ClientSocket.getOutputStream()));

            // An dieser Stelle koennen wir den Input lesen und mit einem angemessenen Output antworten
            
            // laueft in einer Schleife bis m_bRunThread auf false gesetzt wird
            String inputLine;
            while(true)
			{
            	System.out.println();
            	
				Server.connected = true;
				processMsg(inputLine = this.inReader.readLine());
			}
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
        
        finally
        {
            // Clean up
            try
            {
            	Server.connected = false;
                inReader.close();
                outWriter.close();
                ClientSocket.close();
                System.out.println("...Stopped");
            }
            catch(IOException ioe)
            {
            	System.out.println(ioe.getMessage());
            }
        }
    } 
	
	private void processMsg( String input )
	{
		if(ServerGUI.inServer == true)
		{
			if(!input.trim().equals(""))
			{
				Worker ServerWorker = new Worker(input);
				ServerWorker.execute();
			}
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
		try
		{
			System.out.println("server output" + message);
			outWriter.println(playername + ": " + message);
		}
		catch(Exception io)
		{
			
		}
	}
	
	public static void sendMessage( String message, int playerId )
	{
		outWriter.println(message + " " + playerId);
	}


}