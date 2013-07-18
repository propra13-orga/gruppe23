package game;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
public class Mouse implements MouseListener {
	
	//Mausposition wird abgefragt
	/*
	   * @author Martha Tatusch and Maike Fox and Brigitta Wanner
	   */
	@Override
	public void mouseClicked(MouseEvent m) {
		if((m.getX() > 200) && (m.getX() < 600) && (m.getY() > 150) && (m.getY() < 250) && Main.inMenue){
			Main.player.setP_X(84);
			Main.player.setP_Y(84);
			Main.player.setLife(100);
			Main.player.setMana(100);
			Main.player.settotal_life(3);
			Main.checkpoint_reached=false;
			Main.room = 1;
			Main.level = 1;
			if(System.currentTimeMillis() - Main.lastMusic > 31767) Main.lastMusic = 31767;
			try 
		       {
		           Main.map.loadMap("maps/map"+ Main.room + ".txt"); //Karte laden
		       } 
		       catch (IOException e) 
		       {
		           e.printStackTrace(); 
		       }
			
			Main.inSingle = true;
			Multiplayer.inMulti = false;
			Server.inServer = false;
			Server.inGame = false;
			Client.inClient = false;
			Client.inGame = false;
			Main.inMenue = false; //wenn "neues Spiel" gedrückt wird, starte Spiel
			Main.fenster.setTitle("Rotkäppchen 2.0 - Level " + String.valueOf(Main.level));
		}
		
		else if((m.getX() > 200) && (m.getX() < 600) && (m.getY() > 280) && (m.getY() < 380) && Main.inMenue){
			
			if(System.currentTimeMillis() - Main.lastMusic > 31767) Main.lastMusic = 31767;
			
			try {
				Save.load();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		       try 
		       {
		           Main.map.loadMap("maps/map"+ Main.room + ".txt"); //Karte laden
		       } 
		       catch (IOException e) 
		       {
		           e.printStackTrace(); 
		       }
		       
		       	try {
					Save.load();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		   	Main.inSingle = true;
			Multiplayer.inMulti = false;
			Server.inServer = false;
			Server.inGame = false;
			Client.inClient = false;
			Client.inGame = false;
			Main.inMenue = false; //wenn "neues Spiel" gedrückt wird, starte Spiel
			Main.fenster.setTitle("Rotkäppchen 2.0 - Level " + String.valueOf(Main.level));
		}
		
		/*
		 * author Brigitta Wanner
		 */
		else if((m.getX() > 200) && (m.getX() < 600) && (m.getY() > 410) && (m.getY() < 510)){
			
			Main.inSingle = false;
			Networklobby lobby = new Networklobby();	//Wenn Multiplayer gedrückt wird
			lobby.startLobby();					//oeffnet sich das neue Fenster für das Netzwerk
		}
		
		//Author: Martha Tatusch
		else if ((m.getX() > 200) && (m.getX() < 600) && (m.getY() > 540) && (m.getY() < 640) && Main.inMenue){
			
			System.exit(0); //wenn "Spiel verlassen" gedrückt wird, schließe Spiel
		
		}
		
	}

	@Override
	public void mouseEntered(MouseEvent m) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent m) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent m) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent m) {
		// TODO Auto-generated method stub
		
	}

}