/*
 * @author Brigitta Wanner
 * 
 * Klasse die das Inventar beschreibt
 * 
 */
package game;

import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;

public class Inventory extends JFrame implements ActionListener
{
     
	private static final long serialVersionUID = 1L;

	
	public String value_str, info;
	public final static int MAX_INVENTORYSIZE = 10;
	public JLabel ItemPrice, ItemDamage, ItemDefense, ItemHeal, ItemMana, Equipped;
	public int item_price, item_damage, item_defense, item_heal, item_mana, value_i;
	public static int player_money = 0;
	public static int player_exp = 0;
	public static int equip = -1;
    public static ArrayList<Item> inventory = new ArrayList<Item>();
    Vector inventory_vec = new Vector();
    DefaultComboBoxModel model = new DefaultComboBoxModel(inventory_vec);
    JComboBox choice = new JComboBox(model);
    JButton SetButton;
    JLabel MoneyLabel;

	
     
     // Das Benutzen eines Items aus dem Inventar
     public void useItem(String inventory_sel_obj, int inventory_sel_index)
     {
    	 for (int i=0; i< Main.itemlist.size(); i++) 
         {
              if (Main.itemlist.get(i).name == inventory_sel_obj)
              {
            	  if(i == 0 || i == 1)
            	  {	  
            		  Weapon Worn = (Weapon) Main.itemlist.get(i);
            		  Worn.isEquipped();   
            		  equip = i;
            		  Equipped.setVisible(true);
            		  Equipped.setText("Weapon "+ Main.itemlist.get(i).name+ " is equipped");
            		  Main.sound.play("sound/sword.wav");
            		 
            	  } 
            	  if(i > 1)
            	  {
            		  Potion Drunk = (Potion) Main.itemlist.get(i);
            		  Drunk.drinkPotion();
            		  inventory.remove(inventory_sel_index);
            		  inventory_vec.remove(inventory_sel_obj);
            		  model.setSelectedItem(null);
            		  
            		  ItemPrice.setVisible(false);
       		 		  ItemDamage.setVisible(false);
       		 		  ItemDefense.setVisible(false);
       		 		  ItemHeal.setVisible(false);
       		 		  ItemMana.setVisible(false);
       		 		  Equipped.setVisible(false);
       		 		  Main.sound.play("sound/bottle.wav");
            	  }
              }
         }
     }
     
     public void itemGetInfo(String value)
 	{
 		
 		for (int m = 0; m < Main.itemlist.size(); m++)
 		{
 			if (value == Main.itemlist.get(m).name)
 			{
 				this.item_price = Main.itemlist.get(m).itemprice;
				this.item_damage = Main.itemlist.get(m).damage;
				this.item_defense = Main.itemlist.get(m).defense;
				this.item_heal = Main.itemlist.get(m).health_plus;
				this.item_mana = Main.itemlist.get(m).mana_plus;
				ItemPrice.setText("Value: " + item_price);
				ItemPrice.setVisible(true);
	 			
			
 				if (m == 0 || m == 1)
 				{
 					ItemDamage.setVisible(true);
 	 				ItemDefense.setVisible(true);
 					ItemDamage.setText("Damage: " + item_damage);
 	 				ItemDefense.setText("Defense: " + item_defense);
 	 				ItemHeal.setVisible(false);
 	 				ItemMana.setVisible(false);
 	 				
 	 				if(equip >= 0)
 	 				{
 	 					Equipped.setText("Weapon "+ Main.itemlist.get(equip).name+ " is equipped");
 	 					Equipped.setVisible(true);
 	 				}
 	 				
 	 				
 				}
 				if (m > 1)
 				{
 					Equipped.setVisible(false);
 					ItemDamage.setVisible(false);
 	 				ItemDefense.setVisible(false);
 	 				ItemHeal.setText("Heal: " + item_heal);
 	 				ItemMana.setText("Mana: " + item_mana);
 	 				ItemHeal.setVisible(true);
 	 				ItemMana.setVisible(true);
 				}
 			}
 		}
 		
 	}
     public void buttonChange(String value)
     {
    	 for (int m = 0; m < Main.itemlist.size(); m++)
  		{
  			if (value == Main.itemlist.get(m).name)
  			{
 	 			
  				if (m == 0 || m == 1)
  				{
  					SetButton.setText("equip weapon");
  				}
  				
  				if (m > 1)
  				{
  					SetButton.setText("drink potion");
  				}
  			}
  		}
     }
     
     // Das Oeffnen des Inventars
     public void showInventory()
     {
    	
    	JFrame inv_frame = new JFrame ();
    	JLabel MoneyLabel = new JLabel("Money: " + player_money + " $");
    	JLabel ExperienceLabel = new JLabel ("Experience:" + player_exp);
    	inv_frame.setLayout(new FlowLayout());
 		inv_frame.setVisible(true);
 		inv_frame.setSize(310,120);
 		inv_frame.setTitle("Inventory");
 		SetButton = new JButton("please select");
 		ItemDamage = new JLabel();
		ItemDefense = new JLabel();
		ItemHeal = new JLabel();
		ItemMana = new JLabel();
		ItemPrice = new JLabel();
		Equipped = new JLabel();

 		inv_frame.add(choice);
 		inv_frame.add(SetButton);
 		inv_frame.add(MoneyLabel);
 		inv_frame.add(ItemPrice);
 		inv_frame.add(ItemDamage);
 		inv_frame.add(ItemDefense);
 		inv_frame.add(ItemHeal);
 		inv_frame.add(ItemMana);
 		inv_frame.add(Equipped);
 		inv_frame.add(ExperienceLabel);
 		inv_frame.setLocation(200, 350);

 		Equipped.setVisible(false);
 		ItemPrice.setVisible(false);
 		ItemDamage.setVisible(false);
 		ItemDefense.setVisible(false);
 		ItemHeal.setVisible(false);
 		ItemMana.setVisible(false);
 		
		if (inventory_vec.size() != inventory.size())
		{
			inventory_vec.removeAllElements();
			
			for(int j = 0; j < inventory.size(); j++)
			{
				inventory_vec.add(inventory.get(j).name);	
			}
		}
 		SetButton.addActionListener(this);

 		choice.addItemListener(new java.awt.event.ItemListener() 
 		{
 			public void itemStateChanged(ItemEvent e) 
 			{
 				if (e.getStateChange() != e.DESELECTED)
 				{
 					info = (String) model.getSelectedItem();
 	 		        itemGetInfo(info);
 	 		        buttonChange(info);
 				}
 				
 			}
 		});
     }
     		
     public void actionPerformed(ActionEvent event)
     {
    	 
    	 
    	 Object src = event.getSource();
    	 if(src == SetButton)
    	 {
    		 value_i = choice.getSelectedIndex();
    		 value_str = (String) model.getSelectedItem();
    				 
    		 useItem(value_str, value_i);
    	 }
     }    
}
