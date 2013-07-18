package game;

import java.io.Serializable;

public class Message implements Serializable
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// indicator = 0 Normal Message, = 1 Client pressed Ready, indicator = 2 new username;
	// = 3 Server choose new map, = 4 Server started game
	private int type, indicator; 
	private String message, username;
	
		     
	// Konstruktor
	Message(int type, String message, String username, int indicator) 
	{
		this.type = type;
		this.message = message;
		this.username = username;
		this.indicator = indicator;
	}
		     
	// getter
	int getType() 
	{
		 return type;
	}
		 
	String getMessage() 
	{
		return message;
	}
	String getUsername()
	{
		return username;
	}
	int getIndicator()
	{
		return indicator;
	}

}
