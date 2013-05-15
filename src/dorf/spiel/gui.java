package dorf.spiel;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class gui extends JPanel{

	Image img;
	
	public gui(){
		setFocusable(true);
		ImageIcon u = new ImageIcon("");
		img = u.getImage();
	}
	
	public void paint (Graphics g){
		
		super.paint(g);
		Graphics2D f2 = (Graphics2D)g;
		
		f2.drawImage(img, 0, 0, null);
	}
}
