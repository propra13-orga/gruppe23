package main_package;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Mouse implements MouseListener {
	
	//Mausposition wird abgefragt

	@Override
	public void mouseClicked(MouseEvent m) {
		if((m.getX() > 150) && (m.getX() < 650) && (m.getY() > 200) && (m.getY() < 350)){
			Player.xpos = 250-25;
			Player.ypos = 150-25;
			frame.lvl = 1;
		}
		else if ((m.getX() > 150) && (m.getX() < 650) && (m.getY() > 400) && (m.getY() < 550)) System.exit(0);
		
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
