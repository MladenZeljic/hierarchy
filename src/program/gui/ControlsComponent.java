package program.gui;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class ControlsComponent extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private Image controlsImage;
	
	 public ControlsComponent() {
		 this.setBounds(0,0, this.getWidth(), this.getHeight());
		 try {
	    		controlsImage = ImageIO.read(new File("images/controls-image.png"));
			} catch (IOException e1) {
				JOptionPane.showMessageDialog(null, "Background could not be set! ", "Error! ", JOptionPane.ERROR_MESSAGE);
			}
	 }
   
	 public void paintComponent(Graphics g) {
		 super.paintComponent(g);
		 Graphics2D g2 = (Graphics2D) g.create();
	     drawControls(g2);
	     controlOptions(g2);
	     howToPlay(g2);
		 
	 }
	 
		public void drawControls(Graphics2D g2)
		{
			Font font = new Font("Arial", Font.BOLD, 15);
			g2.setFont(font);
		    g2.drawString("1",120,50);
		    g2.drawImage(controlsImage, 50, 60,150,100,null);
		    g2.drawString("2",70,180);
		    g2.drawString("3",120,180);
		    g2.drawString("4",170,180);
	   }
		
		public void controlOptions(Graphics2D g2) {
			 Font font = new Font ("Courier New", 1, 17);
		     g2.setFont (font);
			 g2.drawString("Controls:",this.getWidth()/2 + 50,70);
			 g2.drawString("1. Move up",this.getWidth()/2 + 50,90);
			 g2.drawString("2. Move left",this.getWidth()/2 + 50,110);
			 g2.drawString("3. Move down",this.getWidth()/2 + 50,130);
			 g2.drawString("4. Move right",this.getWidth()/2 + 50,150);
		}
		
		void drawMultilineString(Graphics2D g2, String text, int x, int y) {
		    for (String line : text.split("\n"))
		        g2.drawString(line, x, y += g2.getFontMetrics().getHeight());
		}
		
		public void howToPlay(Graphics2D g2) {
			Font font = new Font ("Courier New", 1, 15);
		    g2.setFont (font);
		    String howToPlay = "For this game it is very  important  to collect energy tokens "
		    				+ "\nand avoid bombs. If you  hit the bomb  then you will  lose 30 "
		    				+ "\npoints and 2  energy tokens.  If your energy level  reaches 0 "
		    				+ "\nwhen you hit the bomb you will lose the game, also if you hit "
		    				+ "\n3 bombs,  regardless of  your energy  level you will lose the "
		    				+ "\ngame. Each collected energy token raises your energy level by "
		    				+ "\n1 and your score by 5 points. Each time you collect 10 energy "
		    				+ "\ntokens or your score reaches 100 you will be  allowed  to hit "
		    				+ "\none extra bomb (if you already  did hit at least  one), while "
		    				+ "\nin the first case, your score will be increased  additionally "
		    				+ "\nby  10  points.  If  your  current  score is higher  than the "
		    				+ "\nhighest  recorded  score,  than  your  current  score will be "
		    				+ "\ndecorated with two  stars. If your score suddenly lowers, you "
		    				+ "\nwill lose these stars until your score increases again.";
			drawMultilineString(g2,howToPlay, 10, 225); 
			
			g2.drawString("We hope you'll enjoy this game! :-)", 10, 530);
			font = new Font("Arial",Font.ITALIC, 11);
			g2.setFont(font);
			String footer = "This game  has been made  as a  practical part of 'Hierarchical Modeling' final project from 'Advanced Computer "
					    + "\nGraphics'  course,  studied  as  a part  of  computer  science  master  studies  at  University of Tuzla  (Faculty of "
					    + "\nElectrical Engineering) and attended in 2017/18 year.";
			drawMultilineString(g2,footer,10,560);
			
			
			g2.drawString("Developed by: Azra Suljkanoviæ and Mladen Zeljiæ", 10, 620);
		}
}
