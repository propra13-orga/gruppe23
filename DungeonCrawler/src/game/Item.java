package game;



public abstract class Item 
{

	protected int itemprice;
	protected String name;
	
		
	

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
	
	public String getName(Object value)
	{
		for (int i=0; i < Main.itemlist.size(); i++) 
        {
             if (Main.itemlist.get(i) == value)
             {
           	  if(i == 0 || i == 1 )
           	  {	  
           		  Weapon Name_Weapon = (Weapon) Main.itemlist.get(i);
           		 this.name = Name_Weapon.name;
           	  } 
           	  if(i > 1)
           	  {
           		  Potion Name_Potion = (Potion) Main.itemlist.get(i);
           		  this.name = Name_Potion.name;
           	  }
             }
        }
		return name;
	}
	
	public int getPrice(Object value)
	{
		
		for (int i=0; i < Main.itemlist.size(); i++) 
        {
             if (Main.itemlist.get(i) == value)
             {
           	  if(i == 0 || i == 1 )
           	  {	  
           		  Weapon Price_Weapon = (Weapon) Main.itemlist.get(i);
           		 this.itemprice = Price_Weapon.itemprice;
           	  } 
           	  if(i > 1)
           	  {
           		  Potion Price_Potion = (Potion) Main.itemlist.get(i);
           		  this.itemprice = Price_Potion.itemprice;
           	  }
           	  
             }
        }
		return itemprice;
	}
}