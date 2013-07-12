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
		if((m.getX() > 150) && (m.getX() < 650) && (m.getY() > 200) && (m.getY() < 350) && Main.inMenue){
			Main.player.setP_X(84);
			Main.player.setP_Y(84);
			Main.player.setLife(100);
			Main.player.setMana(100);
			Main.player.settotal_life(3);
			Main.checkpoint_reached=false;
			Main.room = 1;
			Main.level = 1;
			Main.lastMusic = 32000;
			Main.inMenue = false; //wenn "neues Spiel" gedrückt wird, starte Spiel
			Main.fenster.setTitle("Rotkäppchen 2.0 - Level " + String.valueOf(Main.level));
		}
		
		else if((m.getX() > 150) && (m.getX() < 650) && (m.getY() > 0) && (m.getY() < 150) && Main.inMenue){
//			Main.player.setP_X(84);
//			Main.player.setP_Y(84);
//			Main.player.setLife(Integer.parseInt((String) Save.attributes.get(1)));
//			Main.player.settotal_life(Integer.parseInt((String) Save.attributes.get(0)));
//			Main.player.setMana(Integer.parseInt((String) Save.attributes.get(2)));
			Main.checkpoint_reached=false;
			Main.lastMusic = 32000;
			
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
			
			Main.inMenue = false; //wenn "neues Spiel" gedrückt wird, starte Spiel
			Main.fenster.setTitle("Rotkäppchen 2.0 - Level " + String.valueOf(Main.level));
		}
		
		else if((m.getX() > 150) && (m.getX() < 650) && (m.getY() > 400) && (m.getY() < 550)){
			
			Networklobby lobby = new Networklobby();
			lobby.initLobby();
			Main.inMenue = false;
		}
		
		else if ((m.getX() > 150) && (m.getX() < 650) && (m.getY() > 400) && (m.getY() < 550) && Main.inMenue){
			
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
