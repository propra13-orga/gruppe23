package game;

import java.util.*;

public class Item
{
	public ArrayList<Object> itemlist = new ArrayList<Object>();

	public Item()
	{
		Weapon Axe = new Weapon(5, 2);
		Weapon Sword = new Weapon(4, 3);
		Weapon Spear = new Weapon(3, 4);
		
		Potion Mana_Small = new Potion(0, 10);
		Potion Mana_Big = new Potion(0, 30);
		
		Potion Health_Small = new Potion(10, 0);
		Potion Health_Big = new Potion(30, 0);
		
		Potion All_Small = new Potion(5, 5);
		Potion All_Middle = new Potion(15, 15);
		
		itemlist.add(0, Axe);
		itemlist.add(1, Sword);
		itemlist.add(2, Spear);
		itemlist.add(3, Mana_Small);
		itemlist.add(4, Mana_Big);
		itemlist.add(5, Health_Small);
		itemlist.add(6, Health_Big);
		itemlist.add(7, All_Small);
		itemlist.add(8, All_Middle);
		
	
	}

	
	// erzeugt eine zufaellige Anzahl an Items und setzt diese an zufaellige Positionen
	public void startupItems(int x_min, int y_min, int x_max, int y_max)
	{
		x_max++;
		y_max++;
		// zufaellige Anzahl Items. Max 2
		int num_items = (int)(Math.random() * 3);
		
		for(int i = 0; i <= num_items; i++)
		{
			// erzeugt an einer zufaelligen Position ein zufaelliges Item
			int item_posx = (int)(Math.random() * (x_max - x_min) + x_min);
			int item_posy = (int)(Math.random() * (y_max - y_min) + y_min);
			
			positItems(item_posx, item_posy);
		}
	
	}
	
	public void positItems(int item_posx, int item_posy)
	{
		int number_item = (int)(Math.random() * itemlist.size() + 1 );
		// Ich weiß nicht genau, wie das mit dem tilearray funktioniert?!?
		Map.tilearray[item_posx][item_posy] = itemlist.get(number_item);
		
		
	}
	
	
}