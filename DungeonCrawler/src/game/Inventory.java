package game;

<<<<<<< HEAD
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
=======
public class Inventory {
	
	private int maxItems = 10;  // 10 Plätze im Inventar
	private boolean invFull = false; // Inventar voll bzw. nicht voll
	int[] inventory; // Integer-Array zum speichern der Itemnummern
	
	public Inventory(){
		
		inventory = new int[maxItems]; // Neues Inventar mit festgelegter Anzahl an Plätzen wird erstellt
	    for(int i = 0; i < maxItems; i++){ // Alle Array-Werte werden auf 0 gesetzt
	        inventory[i] = 0;
	        
	        
	        
	   }
		
		
	}
	
	public void addItem(int itemNo){
		
		boolean exit = false; // Variablen werden zurückgesetzt
	    int posInArray = 0; 
	    
	    for(int i = 0; i < maxItems; i++){ // Prüfung , ob ein Item einen Platz belegt
	        if(inventory[i] == 0){ // Wenn der Platz leer ist...
	             posInArray = i;
	             invFull = false; // Inventar ist nicht voll
	             exit = true;
	             break;
	        }
	        else invFull = true; // Wenn alle Plätze ungleich 0 sind, ist das Inventar voll
	   }
	    
	    if(exit = true){ // Wenn exit true ist...
	        inventory[posInArray] = itemNo; // Itemnummer wird am festgelegten Array-Platz eingetragen
	   }
		
	}
	
	public void printInv(){ // Ausgabe des Inventars über die Konsole
		
		
		if(invFull == false){ // Wenn das Inventar nicht voll ist...	
		for(int i = 0; i < maxItems; i++){ // Items werden mit zugehörigen Plätzen ausgegeben
            System.out.println("Inventar Platz: " + i + " beinhaltet: Item Nummmer " + inventory[i]); 
        }
		}
		else System.out.println("Inventar ist voll."); // Wenn das Inventar voll ist...
		
	}

>>>>>>> 1d3d1960b19f05fe7836b215d3c8b69e25de2d1d
}
