package game;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class Inventory extends JFrame implements ActionListener
{
     /**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	Object value = new Item();
     
     
	 public int Money = 10;
     public static Object[] inventory_obj = new Item[10];
     
     
     Item item = new Item();
     JList New = new JList(inventory_obj);
     
     // Anlegen der Buttons fuer das Inventar
     JButton Use = new JButton("Use");
	 JButton Cancel = new JButton("Cancel");

     // Anfangsgegenstaende im Inventar einrichten
     public void startupInventory(int start_itemone, int start_itemtwo)
     {
    	 
    	 
    	 inventory_obj[0] = item.itemlist_obj[start_itemone];
    	 inventory_obj[1] = item.itemlist_obj[start_itemtwo];
    	 updateJList();
     }

     // Wenn der Held ein neues Item ins Inventar aufnimmt
     public void addItem (Object e) 
     {
           int slot = findFreeSlot();
           if (slot >= 0) 
           {
               inventory_obj[slot] = e;
           }
     }
     
// Wenn der Held ein Item verbraucht, muss es entfernt werden 
     public Object removeItem(Object e) 
     {
           for (int i=0; i<inventory_obj.length; i++) 
           {
                if (inventory_obj[i].equals(e)) 
                {
                      Object temp = inventory_obj[i];
                      inventory_obj[i] = null;
                      updateJList();
                      return temp;
                      
                }
            }
            updateJList();
            return null;
            
     }
     
     public void updateJList()
     {
    	 JList New = new JList(inventory_obj);
     }

     

// Suche nach einem leeren Slot im Inventar (wichtig fuer removeItem)
     private int findFreeSlot() 
     {
          for (int i=0; i<inventory_obj.length; i++) 
          {
               if (inventory_obj[i] == null) 
               {
                    return i;
               }
          }

          return -1;
     }
     
     // Das Benutzen eines Items aus dem Inventar
     public void useItem(Object value)
     {
    	 for (int i=0; i< item.itemlist_obj.length; i++) 
         {
              if (item.itemlist_obj[i] == value)
              {
            	  if(i == 0 || i < 2 )
            	  {	  
            		  Weapon Worn = (Weapon) item.itemlist_obj[i];
            		  Worn.isEquipped(); 
            	  } 
            	  
            	  if(i > 2)
            	  {
            		  Potion Drunk = (Potion) item.itemlist_obj[i];
            		  Drunk.drinkPotion();
            		  removeItem(value);
            	  }
            	  
            	  
              }
         }
    	 updateJList();
     }
     
     // Das Oeffnen des Inventars
     public void showInventory()
     {
    	JFrame inv_frame = new JFrame ();
 		inv_frame.setVisible(true);
 		inv_frame.setSize(300,400);
 		
 		JPanel panel = new JPanel();
 		inv_frame.add(panel);
 		
 		
 		
 		getContentPane().setLayout (new BorderLayout());
		  
		Box box = Box.createVerticalBox();
		JLabel label2 = new JLabel("Auswahl");
		box.add(label2);
		  
		label2.setAlignmentY(Component.LEFT_ALIGNMENT);
		label2.setAlignmentX(Component.LEFT_ALIGNMENT);
		box.add(Box.createVerticalStrut(5));
		
	
		JScrollPane scrollPane = new JScrollPane(new JList(inventory_obj));

		scrollPane.setAlignmentX(LEFT_ALIGNMENT);
		box.add(scrollPane);
		box.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
		getContentPane().add(box, BorderLayout.CENTER);
		  
		box = Box.createHorizontalBox();
		box.add(Box.createHorizontalGlue());
		
		JButton Use = new JButton("Use");
		getRootPane().setDefaultButton(Use);
		
		box.add(Use);
		
		box.add(Box.createHorizontalStrut(5));
		
		box.add(new JButton("Cancel"));
		box.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
		getContentPane().add(box, BorderLayout.SOUTH);
		
		Use.addActionListener(this);
 		Cancel.addActionListener(this);
     }
     
     
     
     public void actionPerformed(ActionEvent event)
     {
    	 if ("Use".equals(event.getActionCommand())) 
    	 {
             value = (New.getSelectedValue());
             useItem(value);
    	 }
    	 
     }	 
}
