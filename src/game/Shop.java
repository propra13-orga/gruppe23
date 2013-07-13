/*
 * @author Brigitta Wanner
 */

package game;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Shop extends JFrame implements ActionListener
{
	public JButton Going_In;
	
	public String Label;
	public int buy_price, sell_price, item_price, item_heal, item_mana, item_damage, item_defense;
	public JLabel WelcomeShop;
	public String valueBuy, value_sell_str, infoBuy, infoSell;
	public int valueSell;
	public boolean indikator = false;
	boolean player_money_bool = false;
	
	ItemListener BuyListener = new ItemListener() 
		{
			public void itemStateChanged(ItemEvent e) 
			{
				if (e.getStateChange() != e.DESELECTED)
				{
					infoBuy = (String) modelBuy.getSelectedItem();
	 		        itemGetInfoBuy(infoBuy);
				}
				
			}
		};
		
		ItemListener SellListener = new ItemListener() 
 		{
 			public void itemStateChanged(ItemEvent e) 
 			{
 				if (e.getStateChange() != e.DESELECTED)
 				{
 					infoSell = (String) modelSell.getSelectedItem();
 	 		        itemGetInfoSell(infoSell);
 				}
 				
 				
 			}
 		};
		
	public int player_money = Inventory.player_money;
	
	Vector buy_vec = new Vector();
	Vector sell_vec = new Vector();
	
	JButton BuyMenu = new JButton("Buy Stuff");
	JButton SellMenu = new JButton("Sell Stuff");
	JButton Buy_it = new JButton("Buy Item");
	JButton Sell_it = new JButton("Sell Item");
	JLabel PlayerMoney = new JLabel("Money: " + player_money + " $");
	
	JLabel NotEnoughMoney = new JLabel("You need more Money to buy this.");
	JLabel InventoryFull = new JLabel("You can't buy this. Your inventory is full.");
	JLabel InventoryEmpty = new JLabel("Your inventory is empty.");
	JLabel ItemDamageSell = new JLabel();
	JLabel ItemDefenseSell = new JLabel();
	JLabel ItemHealSell = new JLabel();
	JLabel ItemManaSell = new JLabel();
	JLabel ItemPriceSell = new JLabel();
	JLabel ItemDamageBuy = new JLabel();
	JLabel ItemDefenseBuy = new JLabel();
	JLabel ItemHealBuy = new JLabel();
	JLabel ItemManaBuy = new JLabel();
	JLabel ItemPriceBuy = new JLabel();
	
	
	JFrame init_frame = new JFrame ();
	JFrame buy_frame = new JFrame ();
	JFrame sell_frame = new JFrame();
	JFrame welcome_frame = new JFrame ();
	DefaultComboBoxModel modelBuy = new DefaultComboBoxModel(buy_vec);
	DefaultComboBoxModel modelSell = new DefaultComboBoxModel(sell_vec);
	JComboBox choiceBuy = new JComboBox(modelBuy);
	JComboBox choiceSell = new JComboBox(modelSell);
	
	
	
	public void init()
	{
		
    	
    	init_frame.setLayout(new FlowLayout());
 		init_frame.setVisible(true);
 		init_frame.setSize(350,100);
 		init_frame.setTitle("Welcome!");
 		Going_In = new JButton("Go in the Shop");
 		WelcomeShop = new JLabel("Welcome to the Thrift Shop. Do you want to come in?");
 		
 		init_frame.add(WelcomeShop);
 		init_frame.add(Going_In);
 		init_frame.setLocation(200, 350);
 		
 		Going_In.addActionListener(this);
 		
	}
	public void welcomeShop()
	{
    	welcome_frame.setLayout(new FlowLayout());
 		welcome_frame.setVisible(true);
 		welcome_frame.setSize(290,200);
 		welcome_frame.setTitle("Shop");
 		

 		welcome_frame.add(BuyMenu);
 		welcome_frame.add(SellMenu);
 		welcome_frame.setLocation(200, 350);
 		
 		BuyMenu.addActionListener(this);
 		SellMenu.addActionListener(this);
	}
	
	public void itemGetInfoSell(String value)
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
				ItemPriceSell.setText("Value: " + item_price);
				ItemPriceSell.setVisible(true);
				
 				if (m == 0 || m == 1)
 				{
 					ItemDamageSell.setVisible(true);
 	 				ItemDefenseSell.setVisible(true);
 					ItemDamageSell.setText("Damage: " + item_damage);
 	 				ItemDefenseSell.setText("Defense: " + item_defense);
 	 				ItemHealSell.setVisible(false);
 	 				ItemManaSell.setVisible(false);
 				}
 				if (m > 1)
 				{
 					ItemDamageSell.setVisible(false);
 	 				ItemDefenseSell.setVisible(false);
 	 				ItemHealSell.setText("Heal: " + item_heal);
 	 				ItemManaSell.setText("Mana: " + item_mana);
 	 				ItemHealSell.setVisible(true);
 	 				ItemManaSell.setVisible(true);
 				}
 			}
 		}
 		
 	}
	
	public void itemGetInfoBuy(String value)
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
				ItemPriceBuy.setText("Value: " + item_price);
				ItemPriceBuy.setVisible(true);
 				if (m == 0 || m == 1)
 				{
 					ItemDamageBuy.setVisible(true);
 	 				ItemDefenseBuy.setVisible(true);
 					ItemDamageBuy.setText("Damage: " + item_damage);
 	 				ItemDefenseBuy.setText("Defense: " + item_defense);
 	 				ItemHealBuy.setVisible(false);
 	 				ItemManaBuy.setVisible(false);		
 				}
 				if (m > 1)
 				{
 					ItemDamageBuy.setVisible(false);
 	 				ItemDefenseBuy.setVisible(false);
 	 				ItemHealBuy.setText("Heal: " + item_heal);
 	 				ItemManaBuy.setText("Mana: " + item_mana);
 	 				ItemHealBuy.setVisible(true);
 	 				ItemManaBuy.setVisible(true);
 				}
 			}
 		}
 		
 	}
	private boolean inventoryFull()
	{
		
		boolean inventory_space = true;
		boolean inventory_space_full = true;
		
		if (Inventory.inventory.size() >= Inventory.MAX_INVENTORYSIZE)
		{
			inventory_space_full = false;
			inventory_space = false;
		}
		
		if (inventory_space_full == false)
		{
			InventoryFull.setVisible(true);
		}
	
		return inventory_space;
	}
	
	private boolean inventoryEmpty()
	{
		
		boolean inventory_space = true;
		boolean inventory_space_empty = true;
		
		if (Inventory.inventory.size() == 0)
		{
			inventory_space_empty = false;
			inventory_space = false;
		}
		
		if (inventory_space_empty == false)
		{
			InventoryEmpty.setVisible(true);
		}
		return inventory_space;
	}
	
	public boolean playerChangeMoney(String value)
	{
		this.player_money_bool = false;
		NotEnoughMoney.setVisible(false);
		
		for (int m= 0; m < Main.itemlist.size(); m++)
		{
			if (value == Main.itemlist.get(m).name)
			{
				if (indikator == true)
				{	
					if (Main.itemlist.get(m).itemprice <= this.player_money)
					{
						this.player_money = this.player_money - Main.itemlist.get(m).itemprice;
						this.player_money_bool = true;
						Inventory.player_money = player_money;
						PlayerMoney.setText("Money: " + player_money + " $");
					}
				}
				if (indikator == false)
				{
					this.player_money = this.player_money + Main.itemlist.get(m).itemprice;
					this.player_money_bool = true;
					Inventory.player_money = player_money;
					PlayerMoney.setText("Money: " + player_money + " $");
					
					
				}
			}
		}
		if (player_money_bool == false)
		{
			NotEnoughMoney.setVisible(true);
		}
		return player_money_bool;
	}
	
	public void addItem(String value_local)
	{
		for (int i=  0; i < Main.itemlist.size(); i++) 
        {
			if (Main.itemlist.get(i).name == value_local)
			{
              	 if(i == 0 || i == 1)
              	 {	  
              	 	Weapon AddWeapon = (Weapon) Main.itemlist.get(i);
              	 	Inventory.inventory.add(AddWeapon); 
              	 } 
              	  
              	 if(i > 1)
              	 {
              		  Potion AddPotion = (Potion) Main.itemlist.get(i);
              		  Inventory.inventory.add(AddPotion);
              	 }  	  
			 }
        }	
	}
	
	public void removeItem(String value_sell_str, int value_sell_index)
	{
		for (int m = 0; m < Main.itemlist.size(); m++)
  		{
  			if (value_sell_str == Main.itemlist.get(m).name)
  			{
  				Inventory.inventory.remove(value_sell_index);
        		sell_vec.remove(value_sell_str);
  			}
  		}
		
	}
	
	public void buyItem()
	{
		choiceSell.removeItemListener(SellListener);
		Sell_it.removeActionListener(this);
		welcome_frame.remove(choiceSell);
 		welcome_frame.remove(Sell_it);
 		welcome_frame.remove(PlayerMoney);
 		welcome_frame.remove(ItemPriceSell);
 		welcome_frame.remove(ItemDamageSell);
 		welcome_frame.remove(ItemDefenseSell);
 		welcome_frame.remove(ItemHealSell);
 		welcome_frame.remove(ItemManaSell);
 		welcome_frame.remove(InventoryEmpty);
 		welcome_frame.validate();
 		welcome_frame.repaint();
 		
		
 		
		if (buy_vec.size() != Main.itemlist.size())
		{
			buy_vec.removeAllElements();
			
			for(int j = 0; j < Main.itemlist.size(); j++)
			{
				buy_vec.add(Main.itemlist.get(j).name);
			}
		}
 			
 		welcome_frame.add(choiceBuy);
 		welcome_frame.add(Buy_it);
 		welcome_frame.add(PlayerMoney);
 		welcome_frame.add(ItemPriceBuy);
 		welcome_frame.add(ItemDamageBuy);
 		welcome_frame.add(ItemDefenseBuy);
 		welcome_frame.add(ItemHealBuy);
 		welcome_frame.add(ItemManaBuy);
 		welcome_frame.add(NotEnoughMoney);
 		welcome_frame.add(InventoryFull);
 		welcome_frame.setLocation(200, 350);
 		
 		InventoryFull.setVisible(false);
		NotEnoughMoney.setVisible(false);
 		ItemPriceBuy.setVisible(false);
 		ItemDamageBuy.setVisible(false);
 		ItemDefenseBuy.setVisible(false);
 		ItemHealBuy.setVisible(false);
 		ItemManaBuy.setVisible(false);
 		    	
 		choiceBuy.addItemListener(BuyListener);
 		
 		Buy_it.addActionListener(this);
	}
	
	public void sellItem()
	{
		choiceBuy.removeItemListener(BuyListener);
		Buy_it.removeActionListener(this);
		welcome_frame.remove(choiceBuy);
 		welcome_frame.remove(Buy_it);
 		welcome_frame.remove(PlayerMoney);
 		welcome_frame.remove(ItemPriceBuy);
 		welcome_frame.remove(ItemDamageBuy);
 		welcome_frame.remove(ItemDefenseBuy);
 		welcome_frame.remove(ItemHealBuy);
 		welcome_frame.remove(ItemManaBuy);
 		welcome_frame.remove(NotEnoughMoney);
 		welcome_frame.remove(InventoryFull);
 		welcome_frame.validate();
 		welcome_frame.repaint();
 		
		
 		welcome_frame.add(choiceSell);
 		welcome_frame.add(Sell_it);
 		welcome_frame.add(PlayerMoney);
 		welcome_frame.add(ItemPriceSell);
 		welcome_frame.add(ItemDamageSell);
 		welcome_frame.add(ItemDefenseSell);
 		welcome_frame.add(ItemHealSell);
 		welcome_frame.add(ItemManaSell);
 		welcome_frame.add(InventoryEmpty);
 		welcome_frame.setLocation(200, 350);
 		
 		InventoryEmpty.setVisible(false);
 		ItemPriceSell.setVisible(false);
 		ItemDamageSell.setVisible(false);
 		ItemDefenseSell.setVisible(false);
 		ItemHealSell.setVisible(false);
 		ItemManaSell.setVisible(false);
 		
 		if (Inventory.inventory.size() != sell_vec.size())
 		{
 			sell_vec.removeAllElements();
 			
 			for(int j = 0; j < Inventory.inventory.size(); j++)
 			{
 				sell_vec.add(Inventory.inventory.get(j).name);
 			}
 		}
 		
 		Sell_it.addActionListener(this);
 		
 		choiceSell.addItemListener(SellListener);
	}
	
	public void actionPerformed(ActionEvent event)
	{
		
		
		if(event.getSource() == Going_In)
		{
			welcomeShop();
			init_frame.dispose();
			this.init_frame = new JFrame();
		}
		
		if(event.getSource() == BuyMenu)
		{
			buyItem();
		}
		
		if(event.getSource() == SellMenu)
		{
			sellItem();
		}
		
		
		if(event.getSource() == Buy_it)
		{
			this.indikator = true;
			valueBuy = (String) choiceBuy.getSelectedItem();
			if (inventoryFull() == true)
			{
				if (playerChangeMoney(valueBuy) == true)
				{
			        addItem(valueBuy);  
			        
			        modelBuy.setSelectedItem(null);
			        
			        ItemPriceBuy.setVisible(false);
			 		ItemDamageBuy.setVisible(false);
			 		ItemDefenseBuy.setVisible(false);
			 		ItemHealBuy.setVisible(false);
			 		ItemManaBuy.setVisible(false);
			 		Main.sound.play("sound/buy.wav");
				}
			}
		}
		
		if(event.getSource() == Sell_it)
		{
			if (inventoryEmpty() == true)
			{
				this.indikator = false;
				value_sell_str = (String) choiceSell.getSelectedItem();
				valueSell = choiceSell.getSelectedIndex();
				
				 removeItem(value_sell_str, valueSell);
				 playerChangeMoney(value_sell_str);
				 
				 modelSell.setSelectedItem(null);
				 
				 ItemPriceSell.setVisible(false);
			 	 ItemDamageSell.setVisible(false);
			 	 ItemDefenseSell.setVisible(false);
			 	 ItemHealSell.setVisible(false);
			 	 ItemManaSell.setVisible(false);	
			 	 Main.sound.play("sound/pickUpGold.wav");
			}
		}
	}
	
}
