package game;

import java.util.*;

public class Item
{
	public Object[] itemlist_obj = new Item[10];
	public int itemprice;
	public static Weapon Axe;
	public static Weapon Sword;
	public static Weapon Spear;
	public static Potion Mana_Small;
	public static Potion Mana_Big;
	public static Potion Health_Small;
	public static Potion Health_Big;
	public static Potion All_Small;
	public static Potion All_Medium;
	
	public void newItemlist()
	{
		itemlist_obj[0] = (Axe = new Weapon(5, 2, 10));
		itemlist_obj[1] = (Sword = new Weapon(4, 3, 10));
		itemlist_obj[2] = (Spear = new Weapon(3, 4, 10));
		itemlist_obj[3] = (Mana_Small = new Potion(0, 10, 5));
		itemlist_obj[4] = (Mana_Big = new Potion(0, 30, 10));
		itemlist_obj[5] = (Health_Small = new Potion(10, 0, 5));
		itemlist_obj[6] = (Health_Big = new Potion(30, 0, 10));
		itemlist_obj[7] = (All_Small = new Potion(5, 5, 5));
		itemlist_obj[8] = (All_Medium = new Potion(15, 15, 10));
	}
	
	

	/*
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
			
			//positItems(item_posx, item_posy);
		}
	
	}*/
	/*
	public void positItems(int item_posx, int item_posy)
	{
		int number_item = (int)(Math.random() * itemlist.size() + 1 );
		// Ich weiß nicht genau, wie das mit dem tilearray funktioniert?!?
		Map.tilearray[item_posx][item_posy] = itemlist.get(number_item);
		
		
	}*/
	
	public int getPrice(Object value)
	{
		
		
		for (int i=0; i < itemlist_obj.length; i++) 
        {
             if (itemlist_obj[i] == value)
             {
           	  if(i == 0 || i < 2 )
           	  {	  
           		  Weapon Price_Weapon = (Weapon) itemlist_obj[i];
           		 this.itemprice = Price_Weapon.price;
           	  } 
           	  
           	  if(i > 2)
           	  {
           		  Potion Price_Potion = (Potion) itemlist_obj[i];
           		  this.itemprice = Price_Potion.price;
           	  }
           	  
           	  
             }
        }
		return itemprice;
	}
	
	
	
}