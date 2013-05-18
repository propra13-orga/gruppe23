package game.display;

import game.control.entities.Entity;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

public class GameField extends JPanel {
	
	private static final long serialVersionUID = -4251990227984173445L;

	private List<Entity> entities;
	
	public GameField() {
		this.entities = new ArrayList<Entity>();
	}
	
	public void clearEntities() {
		this.entities.clear();
	}
	
	public void addEntity(Entity e) {
		this.entities.add(e);
	}
	
	public void removeEntity(Entity e) {
		this.entities.remove(e);
	}
	
	public void paint(Graphics g) {
		for(Entity e : entities) {
			e.paint(g);
		}
	}
}
