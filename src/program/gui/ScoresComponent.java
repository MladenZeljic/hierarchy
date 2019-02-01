package program.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ScoresComponent extends JPanel {
			
			private static final long serialVersionUID = 1L;
			private JFrame mainWindow;
			private Image image;
			
			public ScoresComponent(JFrame main, Image image) {		        
		    	this.image = image;
		    	this.mainWindow = main;
		    }
			
		    @Override
		    protected void paintComponent(Graphics g) {
		        super.paintComponent(g);
		        String gameName = "High scores";
			    String text = "Here are the top five highscores!";
		        Graphics2D g2 = (Graphics2D) g.create();		        

		        Font font = new Font ("Courier New", 1, 25);
		        g2.setFont (font);
		        FontMetrics fm = g2.getFontMetrics();
		        int x = (getWidth() - fm.stringWidth(gameName)) / 2;
		        int y = ((getHeight() + fm.getHeight()) / 2) + fm.getAscent();
		        
		        if (image != null) {
		        	g2.drawImage(image, 30, 10, 200, 150, null);
		        	g2.drawImage(image, this.getWidth()/2 + 50, 10, 200, 150, null);
		        }
		        g2.setColor(Color.WHITE);
		        g2.drawString (gameName, x, y-200); 
		        
		        Font f = new Font ("Courier New", 1, 15);
		        g2.setFont (f);
		        g2.drawString(text,x-70, y-90);
            }
		    
			
		    public JFrame getMainWindow() {
				return this.mainWindow;
			}
}
