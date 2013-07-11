package game;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;







public class Save {
	
    public static ArrayList attributes = new ArrayList();

	public Save(){
	
	}
	
	public static void save() throws IOException{
		
//		attributes.clear();
//		attributes.add(Main.player.total_life);
//		attributes.add(Main.player.life);
//		attributes.add(Main.player.mana);
//		attributes.add(Main.room);
//		attributes.add(Main.player.lastCheckpointX);
//		attributes.add(Main.player.lastCheckpointY);
		File savegame = new File("maps/save2.txt");
		FileWriter saveWrite = new FileWriter(savegame, true);
		
		saveWrite.write("Test");
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
	}

}
