package game;

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

}
