package program.gui;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import program.classes.MusicPlayer;
import program.listeners.ButtonListener;
import program.object.models.PlayerObject;

public class GameOverPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private JFrame frame;
	private MusicPlayer gameOverPlayer;
	private JFrame parentWindow;
	private BufferedImage frameBackground;
	private JButton play;
	private JButton exit;
	private GameOverComponent content;
	
	public GameOverPanel(JFrame parent, PlayerObject player) 
	{
		this.parentWindow = parent;
		double soundLevel = ((WelcomeComponent)((HierarchicalModeling)parent.getContentPane()).getMyParent().getContentPane()).getGameSettings().getSoundLevel();	
	    frame = new JFrame("Hierarchy - Game over");
		
		try {
			frameBackground = ImageIO.read(new File("images/shapes.jpg"));
		} catch (IOException e1) {
			JOptionPane.showMessageDialog(null, "Background could not be set! ", "Error! ", JOptionPane.ERROR_MESSAGE);
		}
		
		try {
			frame.setIconImage(new ImageIcon("images/hierarchy.png").getImage());
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(null, "Icon could not be found! ", "Error! ", JOptionPane.ERROR_MESSAGE);
		}
		
		 setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));
		 frame.setSize(600, 500);
		 frame.setLocationRelativeTo(null);
		 frame.setResizable(false);
		 frame.setVisible(true);
		 
		 content = new GameOverComponent(getMainWindow(),frameBackground, player);
		 frame.setContentPane(content);
		 frame.addWindowListener(new WindowAdapter() {
		        @Override
		        public void windowClosing(WindowEvent event) {
		            exitProcedure();
		        }
		    });
		 
		 gameOverPlayer = new MusicPlayer("gameover.wav", "background");
		 gameOverPlayer.setClipVolume(soundLevel);
		 gameOverPlayer.play(false);
		 
		 addPlayBtn();
		 addExitBtn();
	}
	private void exitProcedure() {
		parentWindow.dispose();
		frame.dispose();
		MusicPlayer mainWindowPlayer = ((WelcomeComponent)getMainWindow().getContentPane()).getMusicPlayer();
		mainWindowPlayer.play(true);
		getMainWindow().setVisible(true);
	    
	}
	public JFrame getMainWindow() {
		return ((HierarchicalModeling)parentWindow.getContentPane()).getMyParent();
	}
	
	public void addPlayBtn() 
	{	
		ImageIcon playIcon = new ImageIcon("images/play.png");
		Image playIconContent = playIcon.getImage();  
	    Image resizedPlayIconContent = playIconContent.getScaledInstance(40, 40,  java.awt.Image.SCALE_SMOOTH);  
	    playIcon.setImage(resizedPlayIconContent);
	    
	    play = new JButton(playIcon);
	    play.addActionListener(new ButtonListener());
	    
	    play.setContentAreaFilled( false );
	    play.setBorder( null );
	    play.setBounds(220, 230, 40, 40);
	    play.setName("Again");
	    content.add(play);
	}
	
	public void addExitBtn()
	{	
		ImageIcon exitIcon = new ImageIcon("images/quit.png");
		Image exitIconContent = exitIcon.getImage();  
	    Image resizedExitIconContent = exitIconContent.getScaledInstance(40, 40,  java.awt.Image.SCALE_SMOOTH);  
	    exitIcon.setImage(resizedExitIconContent);
	    
	    exit = new JButton(exitIcon);
	    exit.addActionListener(new ButtonListener());
	    exit.setContentAreaFilled( false );
	    exit.setBorder( null );
	    exit.setBounds(330, 230, 40, 40);
	    exit.setName("Exit");
	    content.add(exit);
	}
}
