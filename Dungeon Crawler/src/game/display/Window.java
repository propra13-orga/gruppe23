package game.display;

import game.constants.TextConstants;

import javax.swing.JFrame;

/**
 * Das Fenster in dem alles dargestellt wird
 */
public class Window extends JFrame{

	private static final long serialVersionUID = -6265787243875762718L;
	
	/**
	 * Erzeugt ein neues Fenster
	 * @param width Die Fensterbreite
	 * @param height Die Fensterhoehe
	 */
	public Window(int width, int height) {
		super(TextConstants.TITLE);
		
		this.setSize(width, height);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		this.setVisible(true);
	}
}
