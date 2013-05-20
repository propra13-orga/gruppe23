package main_package;


import java.awt.*;
import javax.swing.*;

//Zeichnen von Inhalten auf das JFrame mit extends JPanel
public class Spielfeld extends JPanel
{
	
	private static final long serialVersionUID = 1L;
/*
 * 	Attribute definieren 
 *  - Levelzahler sowie Matrix, als Spielfeld
 */
    public int level_Anz = 0;
    public int [][]Level;

    /* Konstruktor
     *
     */

    public Spielfeld(int x_KoordMax, int y_KoordMax, int x_Ausgang, int y_Ausgang,int x_Eingang,int y_Eingang, int x_Falle, int y_Falle)
    {
    	// Level Zaehler hochsetzen
    	this.level_Anz = level_Anz++;
    	
        /* Initialisierung Spielfeld
         * Wertzuweisungen in die Matrix Level
         * Werte: 0 = leerer Boden, 1 = Ausgang, 2 = Falle, 3 = Wand, 4 = Eingang
        */

        int [][]Level = new int[x_KoordMax][y_KoordMax];

        /* Fuellen des Raumes mit Boden
         *
         */
        
        for(int k = 0; k < x_KoordMax; k++)
        {
            for(int l = 0; l < y_KoordMax; l++)
            {
                Level[k][l] = 0;
            }
        }
		
        /* Setzen des Ausganges, Einganges und der Falle
         *
         */

        Level[x_Ausgang][y_Ausgang] = 1;
        Level[x_Falle][y_Falle] = 2;
        Level[x_Eingang][y_Eingang] = 4;

        /* Setzen der Waende
         * inkl. Abfangen von Ueberschreibungen
         */

        for(int i = 0; i <= x_KoordMax-1; i++)
        {
            if ((Level[i][0] != 1) && (Level[i][0] != 2) && (Level[i][0] != 4))
            {
                Level[i][0] = 3;
            }

            if ((Level[i][y_KoordMax-1] != 1) && (Level[i][y_KoordMax-1] != 2) && (Level[i][y_KoordMax-1] != 4))
            {
                Level[i][y_KoordMax-1] = 3;
            }
        }

        for(int j = 0; j <= y_KoordMax-1; j++)
        {
            if ((Level[0][j] != 1) && (Level[0][j] != 2) && (Level[0][j] != 4))
            {
                Level[0][j] = 3;
            }

            if ((Level[j][y_KoordMax-1] != 1) && (Level[j][y_KoordMax-1] != 2) && (Level[j][y_KoordMax-1] != 4))
            {
                Level[x_KoordMax-1][j] = 3;
            }
        }
        // Uebergabe der gefuellten Matrix
        this.Level = Level;
    }


    /*
     *  Methoden
     */
   
    // paint bildet die Matrix graphisch ab
    public void paint(Graphics g)
    {

    	// Differenzen fuer spaetere Berechnungen bilden
        final int xlinien_Dif = 800 / (Level.length);
        final int ylinien_Dif = 800 / (Level[0].length);

        /*
         *  Bemalung der Felder nach ihrem Wert (Leer, Ausgang, Falle, Wand, Eingang)
         */

        for(int r = 0 ; r <= Level.length-1; r++)
        {
            for(int e = 0 ; e <= Level[0].length-1; e++)
            {
                if (Level[r][e] == 0)
                {
                    g.setColor(Color.DARK_GRAY);
                    g.fillRect((r*xlinien_Dif),(e*ylinien_Dif),xlinien_Dif,ylinien_Dif);
                }
                if (Level[r][e] == 1)
                {
                    g.setColor(Color.yellow);
                    g.fillRect((r*xlinien_Dif),(e*ylinien_Dif),xlinien_Dif,ylinien_Dif);
                }
                if (Level[r][e] == 2)
                {
                    g.setColor(Color.black);
                    g.fillRect((r*xlinien_Dif),(e*ylinien_Dif),xlinien_Dif,ylinien_Dif);
                }
                if (Level[r][e] == 3)
                {
                    g.setColor(Color.LIGHT_GRAY);
                    g.fillRect((r*xlinien_Dif),(e*ylinien_Dif),xlinien_Dif,ylinien_Dif);
                }
                if (Level[r][e] == 4)
                {
                    g.setColor(Color.ORANGE);
                    g.fillRect((r*xlinien_Dif),(e*ylinien_Dif),xlinien_Dif,ylinien_Dif);
                }
            }
        }
        
        /*
         *  Abtrennung Felder, um ein Gitter als Spielfeld zu erhalten
         */

        g.setColor(Color.black);
        
        for(int t = 1; t <= Level.length; t++)
        {
            g.drawLine((t *xlinien_Dif),0,(t *xlinien_Dif),800);
        }

        for(int u = 1; u <= Level[0].length; u++)
        {
            g.drawLine(0,(u *ylinien_Dif),800,(u *ylinien_Dif));
        }

        /*
         *  Umrandung des Spielfeldes erstellen
         */
        
        g.drawLine(0,0,800,0);
        g.drawLine(0,0,0,800);
        g.drawLine(0,800,800,800);
        g.drawLine(800,0,800,800);
        g.drawLine(1,1,799,1);
        g.drawLine(1,1,1,799);
        g.drawLine(1,799,799,799);
        g.drawLine(799,1,799,799);

    }
/*
    public void changeLevel()
    {
    	if (Level[XKoord][YKoord] == 1)
    	{
    		if(level_Anz == 2)
    		{
    			Spielfeld level_Zwei = new Spielfeld(8,8,2,1,7,7,4,4);
    			gframe.add(level_Zwei);
    		}
    		if(level_Anz == 3)
    		{
    			Spielfeld level_Drei = new Spielfeld(8,8,3,4,2,1,5,6);
    			gframe.add(level_Drei);
    		}
    	}
    	
    }*/
    
    
    

    public void paintFinish(Graphics g)
    {
        Font schrift = new Font ("Arial", Font.BOLD, 40);
        g.setFont(schrift);
        g.setColor(Color.WHITE);

        g.drawString("Du hast gewonnen!",300,350);
    }

}
