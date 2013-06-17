package game;

import javax.swing.*;
import java.util.*;

import java.awt.*;
import java.awt.event.*;

public class Inventory extends JFrame implements ActionListener
{
     /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	JButton SetButton;
	private int value, set;
	public int counter = 0;
	JComboBox choice = new JComboBox();
	public final static int MAX_INVENTORYSIZE = 10;
	
	public int Money = 10;
    static ArrayList<Item> inventory = new ArrayList<Item>();
     
     // Das Benutzen eines Items aus dem Inventar
     public int useItem(int index_inventory)
     {
    	 int local = 0;
    	 
    	 for (int i=0; i< Main.itemlist.size(); i++) 
         {
              if (Main.itemlist.get(i) == inventory.get(index_inventory))
              {
            	  if(i == 0 || i == 1)
            	  {	  
            		  Weapon Worn = (Weapon) Main.itemlist.get(i);
            		  Worn.isEquipped(); 
            		  System.out.println("Waffe anziehen");
            		  
            	  } 
            	  
            	  if(i > 1)
            	  {
            		  Potion Drunk = (Potion) Main.itemlist.get(i);
            		  Drunk.drinkPotion();
            		  inventory.remove(index_inventory);
            		  System.out.println("Trank trinken");
            		  
            	  }
            	  
            	  local = i;
              }
         }
    	 return local;
     }
     
    
     
     // Das Oeffnen des Inventars
     public void showInventory()
     {
    	JFrame inv_frame = new JFrame ();
    	
    	inv_frame.setLayout(new FlowLayout());
 		inv_frame.setVisible(true);
 		inv_frame.setSize(300,400);
 		inv_frame.setTitle("Inventar");
 		SetButton = new JButton("Use Item");

 		
 		if (inventory.size() != choice.getItemCount())
 		{
 			choice.removeAllItems();
 			
 			for(int j = 0; j < inventory.size(); j++)
 			{
 				choice.addItem(inventory.get(j).name);
 			}
 			
 		}
 		
 		inv_frame.add(choice);
 		inv_frame.add(SetButton);
 		inv_frame.pack();
 		inv_frame.setLocation(200, 350);
 		
 		
 		SetButton.addActionListener(this);
 		choice.addActionListener(this);
 		
     }
     		
     public void actionPerformed(ActionEvent event)
     {
    	 Object src = event.getSource();
    	 if(src == SetButton)
    	 {
    		 
    		 value = choice.getSelectedIndex();
    		 useItem(value);
    		 
    		 
    		 
    	 }
     }    
}
