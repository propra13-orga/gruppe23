package game;

import java.util.ArrayList;

public class Inventory 
{
     

     public ArrayList<Object> inventory = new ArrayList<Object>();
     

     // Wenn der Held ein neues Item ins Inventar aufnimmt
     public void addItem (Object e) 
     {
           int slot = findFreeSlot();
           if (slot >= 0) 
           {
               inventory.add(slot, e);
           }
     }
     
// Wenn der Held ein Item verbraucht, muss es entfernt werden und das Inventar "rueckt" auf
     public Object removeItem(Object e) 
     {
           for (int i=0; i<inventory.size(); i++) 
           {
                if (inventory.get(i).equals(e)) 
                {
                      Object temp = inventory.get(i);
                      inventory.remove(i);
                      return temp;
                }
            }

            return null;
     }

     

// Suche nach einem leeren Slot im Inventar (wichtig fuer removeItem)
     private int findFreeSlot() 
     {
          for (int i=0; i<inventory.size(); i++) 
          {
               if (inventory.get(i) == null) 
               {
                    return i;
               }
          }

          return -1;
     }
}
