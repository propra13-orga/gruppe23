package game;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;







public class Save {
	
    public static ArrayList<String> attributes = new ArrayList<String>();

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
        Player.lastCheckpointX = Integer.parseInt(attributes.get(4));
        Player.lastCheckpointY = Integer.parseInt(attributes.get(5));
	}

}
