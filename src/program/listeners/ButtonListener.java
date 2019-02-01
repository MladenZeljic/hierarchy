package program.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import program.classes.MusicPlayer;
import program.gui.GameOverComponent;
import program.gui.HierarchicalModeling;
import program.gui.ScoresPanel;
import program.gui.ScoresComponent;
import program.gui.SettingsComponent;
import program.gui.SettingsPanel;
import program.gui.WelcomeComponent;
import program.object.models.PlayerObject;

public class ButtonListener implements ActionListener {
	private JButton pressedButton;
	
    public void actionPerformed(ActionEvent e) {
    	
    	Object source = e.getSource();
        
    	pressedButton = (JButton) source;
    	JFrame frame = (JFrame) SwingUtilities.getRoot(pressedButton);
    	if(pressedButton.getName().equals("Sound")) {
    		WelcomeComponent c = ((WelcomeComponent)frame.getContentPane());
    		try {
    			String volumeImagePath = "";
    			c.getGameSettings().incrementImageCount();
    			int imageCount = c.getGameSettings().getImageCount();
    			if(imageCount == 1) {
    				volumeImagePath = "images/VolumeNormal.png";
    				c.getGameSettings().setSoundLevel(0.8);
    				c.getMusicPlayer().setClipVolume(0.8);
    				c.getMusicPlayer()._volume = MusicPlayer.Volume.MEDIUM;
    			}
    			else if(imageCount == 2) {
    				volumeImagePath = "images/VolumeLow.png";
    				c.getGameSettings().setSoundLevel(0.2);
    				c.getMusicPlayer().setClipVolume(0.2);
    				c.getMusicPlayer()._volume = MusicPlayer.Volume.LOW;
    			}
    			else if(imageCount == 3) {
    				volumeImagePath = "images/VolumeMuted.png";
    				c.getGameSettings().setSoundLevel(0);
    				c.getMusicPlayer().mute();
    			}
    			else {
    				c.getMusicPlayer().setClipVolume(1.5);
    				c.getGameSettings().setSoundLevel(1.5);
    				volumeImagePath = "images/VolumeHigh.png";
    				c.getMusicPlayer()._volume = MusicPlayer.Volume.HIGH;
    			}
    			if(c.getMusicPlayer()._playback == MusicPlayer.Playback.PAUSED) {
    				c.getMusicPlayer().play(true);
    			}
   				pressedButton.setIcon(new ImageIcon(ImageIO.read(new File(volumeImagePath))
   							 .getScaledInstance(40, 40,  java.awt.Image.SCALE_SMOOTH)));
			} catch (IOException e1) {
				e1.printStackTrace();
			}
    		
    	}
    	else if(pressedButton.getName().equals("Play")) {
	    	
	        new HierarchicalModeling(frame).showWindow();
	        frame.setVisible(false);
	         
	    }
	    else if(pressedButton.getName().equals("Again")) {
	    	frame.setVisible(false);
	    	HierarchicalModeling m = new HierarchicalModeling(((GameOverComponent)frame.getContentPane())
	    							.getMainWindow());
	    	PlayerObject p = ((GameOverComponent)frame.getContentPane()).getPlayer();
	    	p.setPlayerScore(0);
	    	p.setPlayerBombCount(0);
			p.setPlayerEnergy(0);
			p.getWagon().setX(2);
			p.getWagon().setY(0);
			p.getWagon().setOtherTrack(false);
	    	m.setPlayer(p);
	    	m.showWindow();
	    }
	    else if(pressedButton.getName().equals("Scores")) {
	    	frame.setVisible(false);
	        new ScoresPanel(frame).showWindow();
	    }
	    else if(pressedButton.getName().equals("Settings")) {
	    	frame.setVisible(false);
	        new SettingsPanel(frame).showWindow();
	    }
	         
	    else if (pressedButton.getName().equals("Exit")) {
	    	frame.dispose();
	    	System.exit(0);
	    }
	    else if(pressedButton.getName().equals("BackSettings")) {
	    	frame.dispose();
	    	((SettingsComponent)frame.getContentPane()).getMainWindow().setVisible(true);
	    	
	    }
	    else if(pressedButton.getName().equals("BackScores")) {
	    	frame.dispose();
	    	((ScoresComponent)frame.getContentPane()).getMainWindow().setVisible(true);
	    	
	    }
	    
    }
}
