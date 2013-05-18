package game;

import game.constants.GameConstants;
import game.control.entities.Player;
import game.control.entities.PlayerListener;
import game.display.GameField;
import game.display.Window;

/**
 * Verwaltet das Spiel
 */
public class Game {
	private Window window;
	private GameField field;
	private Player player;
	
	/**
	 * Erzeugt ein neues Spiel
	 */
	public Game() {
		window = new Window(GameConstants.FRAME_WIDTH, 
							GameConstants.FRAME_HEIGHT);
	}
	
	/**
	 * Startet das Spiel
	 */
	public void start() {
		field = new GameField();
		player = new Player("img/player.png");
		
		field.addEntity(player);
		
		window.add(field);
		window.addKeyListener(new PlayerListener(player, window));
	}
	
	public static void main(String[] args) {
		Game g = new Game();
		g.start();
	}
}
