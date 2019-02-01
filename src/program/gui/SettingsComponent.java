package program.gui;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class SettingsComponent extends JPanel {
			
			private static final long serialVersionUID = 1L;
			private Image image;
			private String settings = "Game Infos & Players";
			private JFrame mainWindow;
			
			public SettingsComponent(JFrame main,Image image)
			{
		    	this.image = image;
		    	mainWindow = main;
		    }
			
		    @Override
		    protected void paintComponent(Graphics g)
		    {
		        super.paintComponent(g);
		        
		        Graphics2D g2 = (Graphics2D) g.create();
		        Font font = new Font ("Courier New", Font.BOLD, 15);
		        g2.setFont (font);
		        FontMetrics fm = g2.getFontMetrics();
		        int x = (getWidth() - fm.stringWidth(settings)) / 2;
		        int y = ((getHeight() + fm.getHeight()) / 2) + fm.getAscent();
		        
		        
		        if (image != null) {
		         	g2.drawImage(image, 50,20, this.getWidth()/6, this.getHeight()/6, null);
		         	g2.drawImage(image,  this.getWidth()/2 + fm.stringWidth(settings) ,20, this.getWidth()/6, this.getHeight()/6, null);
		        }
		        
		        g2.drawString (settings, x, y-200);
	      }
		    
		public JFrame getMainWindow() {
			return this.mainWindow;
		}		
}
