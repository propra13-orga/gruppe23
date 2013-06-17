package game;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class Shop extends JFrame implements ActionListener
{
	public JButton Buy, Sell, Buy_it, Sell_it, Going_In;
	public String Label;
	public int buy_price, sell_price;
	public JLabel WelcomeShop;
	
	public int valueBuy, valueSell;
	
	JFrame init_frame = new JFrame ();
	JComboBox choiceBuy = new JComboBox();
	JComboBox choiceSell = new JComboBox();
	
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
		
        JFrame welcome_frame = new JFrame ();
    	
    	welcome_frame.setLayout(new FlowLayout());
 		welcome_frame.setVisible(true);
 		welcome_frame.setSize(350,100);
 		welcome_frame.setTitle("Shop");
 		Buy = new JButton("Buy Stuff");
 		Sell = new JButton("Sell Stuff");

 		
 		
 		
 		welcome_frame.add(Buy);
 		welcome_frame.add(Sell);
 		welcome_frame.setLocation(200, 350);
 		
 		
 		Buy.addActionListener(this);
 		Sell.addActionListener(this);
	}

	private int findFreeSlot() 
	{
		int local_count = -1;
		if (Inventory.inventory.size() <= Inventory.MAX_INVENTORYSIZE)
	    {
			local_count = Inventory.inventory.size();
	    }
		return local_count++;
	}
	
	public void buyItem()
	{
		JFrame buy_frame = new JFrame ();
    	
    	buy_frame.setLayout(new FlowLayout());
 		buy_frame.setVisible(true);
 		buy_frame.setSize(350,100);
 		buy_frame.setTitle("Buy some stuff");
 		Buy_it = new JButton("Buy Item");

 		
 		if (Main.itemlist.size() != choiceBuy.getItemCount())
 		{
 			choiceBuy.removeAllItems();
 			
 			for(int j = 0; j < Main.itemlist.size(); j++)
 			{
 				choiceBuy.addItem(Main.itemlist.get(j).name);
 			}
 			
 		}
 		
 		buy_frame.add(choiceBuy);
 		buy_frame.add(Buy_it);
 		buy_frame.setLocation(200, 350);
 		
 		
 		choiceBuy.addActionListener(this);
 		Buy_it.addActionListener(this);
		
	}
	
	public void sellItem()
	{
		JFrame sell_frame = new JFrame ();
    	
    	sell_frame.setLayout(new FlowLayout());
 		sell_frame.setVisible(true);
 		sell_frame.setSize(350,100);
 		sell_frame.setTitle("Sell your stuff");
 		Sell_it = new JButton("Sell Item");

 		
 		if (Inventory.inventory.size() != choiceSell.getItemCount())
 		{
 			choiceSell.removeAllItems();
 			
 			for(int j = 0; j < Inventory.inventory.size(); j++)
 			{
 				choiceSell.addItem(Inventory.inventory.get(j).name);
 			}
 			
 		}
 		
 		sell_frame.add(choiceSell);
 		sell_frame.add(Sell_it);
 		sell_frame.setLocation(200, 350);
 		
 		
 		choiceBuy.addActionListener(this);
 		Sell_it.addActionListener(this);
	}
	
    
   
	
	public void actionPerformed(ActionEvent event)
	{
		if(event.getSource() == Going_In)
		{
			welcomeShop();
			init_frame.dispose();
			this.init_frame = new JFrame();
		}
		
		if(event.getSource() == Buy)
		{
			buyItem();
		}
		
		if(event.getSource() == Sell)
		{
			sellItem();
		}
		
		
		if(event.getSource() == Buy_it)
		{
	       valueBuy = choiceBuy.getSelectedIndex();
			
		   
	       
	       	   for (int i=0; i< Main.itemlist.size(); i++) 
	           {
	                if (Main.itemlist.get(i).name == choiceBuy.getSelectedItem())
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
		
		if(event.getSource() == Sell_it)
		{
			valueSell = choiceSell.getSelectedIndex();
			if (Inventory.inventory.size() > 0)
			{
				Inventory.inventory.remove(valueSell);
			}
			if (Inventory.inventory.size() < 0)
			{
				System.out.println("Dein Inventar ist leer, du kannst nichts verkaufen!");
			}
		}
	}
	
}
