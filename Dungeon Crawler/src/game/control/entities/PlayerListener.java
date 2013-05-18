package game.control.entities;

import java.awt.Window;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class PlayerListener implements KeyListener {

	private Player player;
	private Window window;
	
	public PlayerListener(Player player, Window window) {
		this.player = player;
		this.window = window;
	}
	
	@Override
	public void keyPressed(KeyEvent ev) {		
		switch(ev.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			player.move(2);
			break;
		
		case KeyEvent.VK_RIGHT:
			player.move(0);
			break;
			
		case KeyEvent.VK_UP:
			player.move(3);
			break;
			
		case KeyEvent.VK_DOWN:
			player.move(1);
			break;
		}
		
		window.repaint();
	}

	@Override
	public void keyReleased(KeyEvent ev) {
		
	}

	@Override
	public void keyTyped(KeyEvent ev) {
		
	}

}
