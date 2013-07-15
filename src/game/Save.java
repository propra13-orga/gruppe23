//author: Martha Tatusch

package game;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


public class Save {
	
    public static ArrayList<String> attributes = new ArrayList<String>();
    static boolean itemsAdded = false;

	public Save(){
	
	}

	
	public static void save() throws IOException{
		
		attributes.clear();
		attributes.add(String.valueOf(Main.player.total_life));
		attributes.add(String.valueOf(Main.player.life));
		attributes.add(String.valueOf(Main.player.mana));
		attributes.add(String.valueOf(Main.room));
		attributes.add(String.valueOf(Main.player.lastCheckpointX));
		attributes.add(String.valueOf(Main.player.lastCheckpointY));
		attributes.add(String.valueOf(Inventory.player_money));
		attributes.add(String.valueOf(Inventory.player_exp));
		
		for( int j = 0 ; j < Inventory.inventory.size() ; j++){ // Items ab Stelle 8
			Item currentItem = Inventory.inventory.get(j);
			int indexOfCurrentItem = Main.itemlist.indexOf(currentItem);
			attributes.add(String.valueOf(indexOfCurrentItem));
		}
		
		File savegame = new File("maps/save.txt");
		FileWriter saveWrite = new FileWriter(savegame);
		
		for(int i= 0; i < attributes.size() ; i++){
			saveWrite.write(attributes.get(i));
			if(i < attributes.size() - 1) saveWrite.write("\n");		
		}
		saveWrite.flush();
		saveWrite.close();
		
		
		
	}
	
	public static void load() throws IOException{
		
		attributes.clear();
		
		
		BufferedReader reader = new BufferedReader(new FileReader("maps/save.txt"));

		
        while (true) 
        {
            String line = reader.readLine(); //Zeile wird in line als String gespeichert
            
            
            	if (line == null) //Wenn line leer ist
            	{
            		reader.close(); //reader schließen 
            		break; //While schleife beenden
            	}

            	if (!line.startsWith("!")) //Zeilen, die mit ! anfangen werden ignoriert
            	{
            		attributes.add(line); //line wird der Arraylist lines hinzugefügt
            	}
        }
        
        Player.total_life = Integer.parseInt(attributes.get(0));
        Player.life = Integer.parseInt(attributes.get(1));
        Player.mana = Integer.parseInt(attributes.get(2));
        Main.room =Integer.parseInt(attributes.get(3));
        Main.player.setP_X(Integer.parseInt(attributes.get(4)));
        Main.player.setP_Y(Integer.parseInt(attributes.get(5)));
        Inventory.player_money = Integer.parseInt(attributes.get(6));
        Inventory.player_exp = Integer.parseInt(attributes.get(7));
        
        if(!itemsAdded){
        for(int i = 8 ; i < attributes.size(); i++){
        	Item addItem = Main.itemlist.get(Integer.parseInt(attributes.get(i)));
        	Inventory.inventory.add(addItem);
        }
        itemsAdded = true;
        }
	}
	
	public static void reset () throws IOException{
		
		File savegame = new File("maps/save.txt");
		FileWriter saveWrite = new FileWriter(savegame);
		saveWrite.write("3\n100\n100\n1\n84\n84");
		saveWrite.flush();
		
}
}
