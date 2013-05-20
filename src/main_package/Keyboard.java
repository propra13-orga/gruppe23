package main_package;


import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener {

	@Override
	public void keyPressed(KeyEvent e) {

		if(e.getKeyCode() == KeyEvent.VK_UP) Player.ypos -= 100;
		if(e.getKeyCode() == KeyEvent.VK_LEFT) Player.xpos -= 100;
		if(e.getKeyCode() == KeyEvent.VK_DOWN) Player.ypos += 100;
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) Player.xpos += 100;
		
	}

	@Override
	public void keyReleased(KeyEvent e) {


	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}



}
