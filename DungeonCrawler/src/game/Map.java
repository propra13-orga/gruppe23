package game;

import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Map {
	
	private ArrayList<Tiles> tilearray = new ArrayList<Tiles>();
	
	public Map(){
		
	}
	
	public void loadMap(String filepath) throws IOException
	{
		tilearray.clear();
        ArrayList lines = new ArrayList();
        int width = 0;
        int height = 0;
        BufferedReader reader = new BufferedReader(new FileReader(filepath));
        
        while (true) 
        {
            String line = reader.readLine();//Zeile wird in line als String gespeichert
            
            	if (line == null) //Wenn line leer ist
            	{
            		reader.close(); //reader schlie�en 
            		break; //While schleife beenden
            	}

            	if (!line.startsWith("!")) //Zeilen, die mit ! anfangen werden ignoriert
            	{
            		lines.add(line); //line wird der Arraylist lines hinzugef�gt
            		width = Math.max(width, line.length()); //width entspricht der Zeilenl�nge

            	}
        }
        height = lines.size();//H�he entspricht der Arraygr��e

        	for (int y = 0; y < height; y++) //Solange y < Arraygr��e , y erh�hen
        	{
        		String line = (String) lines.get(y); //line wird der Wert des Objekts an y. Stelle des Arrays zugewiesen
        			for (int x = 0; x < width; x++) //Solange x < Zeilenl�nge , x erh�hen
        			{
        					if (x < line.length()) // wenn x < Zeilenl�nge
        					{
        						char ch = line.charAt(x); // Buchstabe an Position x wird ch zugewiesen
        						Tiles t = new Tiles(x, y, Character.toString(ch)); // Neues Tiles Objekt wird erstellt mit aktualisierten Parametern
        						tilearray.add(t); // Neu erstelltes Tiles Objekt wird der Arrayliste tilearray hinzugef�gt
        					}

        			}
        	}
	
	}

	public ArrayList<Tiles> getTilearray() {
		return tilearray;
	}
	

	

	public void setTilearray(ArrayList<Tiles> tilearray) {
		this.tilearray = tilearray;
	}

}
