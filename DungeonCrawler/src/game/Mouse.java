package game;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
public class Mouse implements MouseListener {
	
	//Mausposition wird abgefragt

	@Override
	public void mouseClicked(MouseEvent m) {
		if((m.getX() > 150) && (m.getX() < 650) && (m.getY() > 200) && (m.getY() < 350)){
			Main.player.setLife(100);
			Main.player.setP_X(84);
			Main.player.setP_Y(84);
			Main.room = 1;
			Main.level = 1;
			Main.inMenue = false; //wenn "neues Spiel" gedrückt wird, starte Spiel
			Main.fenster.setTitle("Rotkäppchen 2.0 - Level " + String.valueOf(Main.level));
		}
		else if ((m.getX() > 150) && (m.getX() < 650) && (m.getY() > 400) && (m.getY() < 550)) System.exit(0); //wenn "Spiel verlassen" gedrückt wird, schließe Spiel
		
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
