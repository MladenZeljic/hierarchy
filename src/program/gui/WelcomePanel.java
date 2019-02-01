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
import program.listeners.ButtonListener;

public class WelcomePanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private BufferedImage frameBackground;
	private JFrame window;
	private JButton sound;
	private JButton play;
	private JButton settings;
	private JButton score;
	private JButton exit;
	private WelcomeComponent content;
	
	public WelcomePanel() {
		
		window = new JFrame("Hierarchy - Main menu");
	
		try {
			frameBackground = ImageIO.read(new File("images/shapes.jpg"));
		} catch (IOException e1) {
			JOptionPane.showMessageDialog(null, "Background could not be set! ", "Error! ", JOptionPane.ERROR_MESSAGE);
		}
		
		try {
			window.setIconImage(new ImageIcon("images/hierarchy-title.png").getImage());
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(null, "Icon could not be found! ", "Error! ", JOptionPane.ERROR_MESSAGE);
		}
		
		setBackground( Color.LIGHT_GRAY );
	    setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));
	    
	    content = new WelcomeComponent(frameBackground);
	    content.setLayout(null);
    	
	    // sound icon
	    ImageIcon soundIcon = new ImageIcon("images/VolumeNormal.png");
    	Image soundIconContent = soundIcon.getImage();  
        Image resizedSoundIconContent = soundIconContent.getScaledInstance(40, 40,  java.awt.Image.SCALE_SMOOTH);  
        soundIcon.setImage(resizedSoundIconContent);
        
        sound = new JButton(soundIcon);
        sound.addActionListener(new ButtonListener());
	    
        sound.setContentAreaFilled( false );
        sound.setBorder( null );
        sound.setBounds(30, 80, 40, 40);
        sound.setName("Sound");
        content.add(sound);
	    
        // play icon
	    ImageIcon playIcon = new ImageIcon("images/play.png");
    	Image playIconContent = playIcon.getImage();  
        Image resizedPlayIconContent = playIconContent.getScaledInstance(40, 40,  java.awt.Image.SCALE_SMOOTH);  
        playIcon.setImage(resizedPlayIconContent);
        
        play = new JButton(playIcon);
	    play.addActionListener(new ButtonListener());
	    
	    play.setContentAreaFilled( false );
	    play.setBorder( null );
	    play.setBounds(220, 170, 40, 40);
	    play.setName("Play");
	    
	    // score icon
	    ImageIcon scoreIcon = new ImageIcon("images/score.png");
    	Image scoreIconContent = scoreIcon.getImage();  
        Image resizedScoreIconContent = scoreIconContent.getScaledInstance(40, 40,  java.awt.Image.SCALE_SMOOTH);  
        scoreIcon.setImage(resizedScoreIconContent);
        
        score = new JButton(scoreIcon);
	    score.addActionListener(new ButtonListener());
	    
	    score.setContentAreaFilled( false );
	    score.setBorder( null );
	    score.setBounds(330, 170, 40, 40);
	    score.setName("Scores");
        
	    // settings
	    ImageIcon settingsIcon = new ImageIcon("images/settings.png");
    	Image settingsIconContent = settingsIcon.getImage();  
        Image resizedSettingsIconContent = settingsIconContent.getScaledInstance(40, 40,  java.awt.Image.SCALE_SMOOTH);  
        settingsIcon.setImage(resizedSettingsIconContent);
        
        settings = new JButton(settingsIcon);
	    settings.addActionListener(new ButtonListener());
	    
	    settings.setContentAreaFilled( false );
	    settings.setBorder( null );
	    settings.setBounds(220, 260, 40, 40);
	    settings.setName("Settings");
	    
	    // exit
	    ImageIcon exitIcon = new ImageIcon("images/quit.png");
    	Image exitIconContent = exitIcon.getImage();  
        Image resizedExitIconContent = exitIconContent.getScaledInstance(40, 40,  java.awt.Image.SCALE_SMOOTH);  
        exitIcon.setImage(resizedExitIconContent);
        
	    exit = new JButton(exitIcon);
	    exit.addActionListener(new ButtonListener());
	    exit.setContentAreaFilled( false );
	    exit.setBorder( null );
	    exit.setBounds(330, 260, 40, 40);
	    exit.setName("Exit");
	    
	    window.setSize(600, 500);
	    window.setLocationRelativeTo(null);
	    window.setResizable(false);
	    
	    content.add(play);
	    content.add(score);
	    content.add(settings);
	    content.add(exit);

	    window.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	    window.addWindowListener(new WindowAdapter() {
	        @Override
	        public void windowClosing(WindowEvent event) {
	        	exitProcedure();
	        	
	        }
	    });
	    window.setContentPane(content);
	    window.setVisible(true);
	}
	private void exitProcedure() {
		window.dispose();
		content.getMusicPlayer().close();
		System.exit(0);
	}
	
}
