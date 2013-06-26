/*
 * @author Brigitta Wanner
 * 
 * Klasse fuer die Items
 * 
 */
package game;



public abstract class Item 
{

	protected int itemprice, damage, defense, health_plus, mana_plus;
	protected String name;

	
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
	
	public int getPrice(String value)
	{
		
		for (int i=0; i < Main.itemlist.size(); i++) 
        {
             if (Main.itemlist.get(i).name == value)
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