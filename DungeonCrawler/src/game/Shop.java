package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Shop extends JFrame implements ActionListener
{
	public JButton Buy, Sell, GoodBye, Buy_it;
	public String Label;
	public int buy_price, sell_price;
	
	Object value = new Item();
	Item item = new Item();
	Inventory inv = new Inventory();
	
	JList Blub = new JList(inv.inventory_obj);
	JList Blab = new JList(item.itemlist_obj);
	
	public void init()
	{
		JFrame frame = new JFrame ();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(300,400);
		
		JPanel panel = new JPanel();
		frame.add(panel);
		
		JButton Buy = new JButton("Buy");
		JButton Sell = new JButton("Sell");
		JButton GoodBye = new JButton("Good Bye");
		
		panel.add(Buy);
		panel.add(Sell);
		panel.add(GoodBye);
		
		Buy.addActionListener(this);
		Sell.addActionListener(this);
		GoodBye.addActionListener(this);
	}
	
	
	public void buyItem()
	{
		getContentPane().setLayout (new BorderLayout());
		  
		Box box = Box.createVerticalBox();
		JLabel label2 = new JLabel("Auswahl");
		box.add(label2);
		  
		label2.setAlignmentY(Component.LEFT_ALIGNMENT);
		label2.setAlignmentX(Component.LEFT_ALIGNMENT);
		box.add(Box.createVerticalStrut(5));
		
		
		JScrollPane scrollPane = new JScrollPane(Blab);

		scrollPane.setAlignmentX(LEFT_ALIGNMENT);
		box.add(scrollPane);
		box.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
		getContentPane().add(box, BorderLayout.CENTER);
		  
		box = Box.createHorizontalBox();
		box.add(Box.createHorizontalGlue());
		
		JButton Buy_it = new JButton("Buy");
		getRootPane().setDefaultButton(Buy_it);
		
		box.add(Buy_it);
		
		box.add(Box.createHorizontalStrut(5));
		
		box.add(new JButton("Cancel"));
		box.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
		getContentPane().add(box, BorderLayout.SOUTH);
		
	}
	
	public void sellItem()
	{
		getContentPane().setLayout (new BorderLayout());
		  
		Box box = Box.createVerticalBox();
		JLabel label3 = new JLabel("Auswahl");
		box.add(label3);
		  
		label3.setAlignmentY(Component.LEFT_ALIGNMENT);
		label3.setAlignmentX(Component.LEFT_ALIGNMENT);
		box.add(Box.createVerticalStrut(5));
		
		
		JScrollPane scrollPane = new JScrollPane(Blub);

		scrollPane.setAlignmentX(LEFT_ALIGNMENT);
		box.add(scrollPane);
		box.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
		getContentPane().add(box, BorderLayout.CENTER);
		  
		box = Box.createHorizontalBox();
		box.add(Box.createHorizontalGlue());
		
		JButton Sell_it= new JButton("Sell");
		getRootPane().setDefaultButton(Sell_it);
		
		box.add(Sell_it);
		
		box.add(Box.createHorizontalStrut(5));
		
		box.add(new JButton("Cancel"));
		box.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
		getContentPane().add(box, BorderLayout.SOUTH);
	}
	
	public void actionPerformed(ActionEvent event)
	{
		if(event.getSource() == Buy)
		{
			buyItem();
		}
		
		if(event.getSource() == Sell)
		{
			sellItem();
		}
		
		
		
		if ("Buy_it".equals(event.getActionCommand())) 
   	 	{
            value = Blab.getSelectedValue();
            
            buy_price = item.getPrice(value);
            
            if (inv.Money > buy_price)
            {
            	inv.Money = inv.Money - buy_price;
            	inv.addItem(value);
            }
            
   	 	}
		
		if ("Sell_it".equals(event.getActionCommand())) 
   	 	{
            value = Blab.getSelectedValue();
            item.getPrice(value);
            
            sell_price = item.getPrice(value);
            inv.Money = inv.Money + sell_price;
            
            inv.removeItem(value);
   	 	}
		
		repaint();
	}
}
