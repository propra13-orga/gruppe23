/*
 * author Brigitta Wanner
 */

package game;

import java.awt.event.WindowEvent;

import javax.swing.*;

public class Worker extends SwingWorker<String, Void>
{
	private String input;
	
	/*
	 * Konstruktor
	 */
	public Worker(String input)
	{
		this.input = input;
	}
	@Override
	protected String doInBackground() throws Exception //abfangen der exception
	{				
		return null;
	}
				
	@Override
	protected void done() 
	{
		try 
		{ 
			if(ServerGUI.inServer == true)
			{
				while(Networklobby.inChat == true)
				{
					ServerGUI.appendInput(input);
				}
			}
			if(ClientGUI.inClient == true)
			{
				while(Networklobby.inChat == true)
				{
					ClientGUI.appendInput(input);
				}
			}
		} 
		catch (Exception ignore) 
		{
			        	   
		}
	} 		
}
