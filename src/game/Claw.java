//Author: Martha Tatusch

package game;

public class Claw {
	
	private int line = 0, row;
	private long cTimer = 4000, lastC = System.currentTimeMillis(), lastF = System.currentTimeMillis(), framT = 100;
	private int fram = 0, framCount, framadd = 1; // framadd = abstrakte Animiergeschwindigkeit

	public Claw (){
	}
	
	public void play(){
		
		if(System.currentTimeMillis() - lastF > framT){
			
			if(fram > 3) fram = 0;
			lastF = System.currentTimeMillis();
			
			
			
		   if (framCount <= 3 && framCount >= 0){
			   row = (int) (fram) * (128);
			   line = 0;
			   lastF = System.currentTimeMillis();
		   }
		   else if (framCount <= 7 && framCount >= 4){
			   
			   row = ((int) (fram)) * (128);
			   line = 128;
			   lastF = System.currentTimeMillis();
		   }
		   else if (framCount <= 11 && framCount >= 8){
			   row = ((int) (fram)) * (128);
			   line = (128)*2;
			   lastF = System.currentTimeMillis();
			   
		   }
			else  if (framCount <= 15  && framCount >=12){
			   row = ((int) (fram)) * (128);
			   line = (128)*3;
			   lastF = System.currentTimeMillis();
		   }
		
			else if (framCount == 16){
				fram = 0;
				framCount = 0;
				line = 0;
				row = 0;
				Main.lightningclaw = false;
			}
		   
		   
		    if(framCount == 11) framCount = 17;
		    
			   if(System.currentTimeMillis() - lastC > cTimer ){ //Wird ausgeführt sobald Ende der Wirkungszeit erreicht ist
				   lastC = System.currentTimeMillis();
				   lastF = System.currentTimeMillis();
				   framCount = 12;
				   Main.player.setMana(Main.player.getMana() - 10);
			   }
		  
		   framCount ++;
		   fram += framadd;
	}   
	}

	//Getters und Setters:

	public int getLine() {
		return line;
	}

	public int getRow() {
		return row;
	}



	public double getFram() {
		return fram;
	}

	public void setLine(int line) {
		this.line = line;
	}

	public void setRow(int row) {
		this.row = row;
	}


	public void setFram(int fram) {
		this.fram = fram;
	}

	public void setLastC(long lastC){
		this.lastC = lastC;
	}

	public void setLastF(long lastF){
		this.lastF = lastF;
	}



	}

