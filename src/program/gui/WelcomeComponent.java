package program.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.JPanel;
import program.classes.GameSettings;
import program.classes.MusicPlayer;



public class WelcomeComponent extends JPanel{
	
	private static final long serialVersionUID = 1L;
	private Image image;
	private MusicPlayer backgroundMusic;
	private GameSettings gameSettings;

	public WelcomeComponent(Image image) {
    	this.image = image;
    	backgroundMusic = new MusicPlayer("background.wav","background");
		backgroundMusic.play(true);
		backgroundMusic.setClipVolume(0.8);
		gameSettings = new GameSettings();
    }
	
	public MusicPlayer getMusicPlayer() {
		return this.backgroundMusic;
	}
	
	public GameSettings getGameSettings() {
		return this.gameSettings;
	}
	
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        String gameName = "Hierarchy";
        Graphics2D g2 = (Graphics2D) g.create();
        Font font = new Font ("Courier New", 1, 25);
        g2.setFont (font);
        FontMetrics fm = g2.getFontMetrics();
        int x = (getWidth() - fm.stringWidth(gameName)) / 2;
        int y = ((getHeight() + fm.getHeight()) / 2) + fm.getAscent();
        if (image != null) {
        	g2.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), null);
        }
        g2.setColor(Color.WHITE);
        g2.drawString (gameName, x, y-150);

        font = new Font ("Courier New", 1, 17);
        g2.setFont (font);
        g2.drawString("Play", 220, 240);
        g2.drawString("Highscores", 298, 240);
        g2.drawString("Game Infos", 190, 330);
        g2.drawString("& Players", 194, 350);
        g2.drawString("Exit", 330, 330);
    }

	
}