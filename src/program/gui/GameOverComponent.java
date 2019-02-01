package program.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.JFrame;
import javax.swing.JPanel;
import program.classes.JsonClass;
import program.object.models.PlayerObject;


public class GameOverComponent extends JPanel {

	private static final long serialVersionUID = 1L;
	private Image backgroundImage;
	private JFrame _mainWindow;
	private String totalScore;
	private String title = "GAME OVER";
	private String scoreText = "Your total score is: ";
	private PlayerObject _player;
	private JsonClass jsonClass;
	
	public GameOverComponent(JFrame mainWindow,Image image, PlayerObject player) {
		this.backgroundImage = image;
		this.totalScore = Integer.toString(player.getPlayerScore());
		setLayout(null);
		_mainWindow = mainWindow;
		_player = player;
		jsonClass = new JsonClass(totalScore, player);
		jsonClass.writeInFile();
	}
	public PlayerObject getPlayer() {
		return _player;
	}
	public JFrame getMainWindow() {
		return this._mainWindow;
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g.create();
		g2.drawImage(backgroundImage, 0, 0, this.getWidth(), this.getHeight(), null);
		
		g2.setColor(Color.YELLOW);
		Font font = new Font ("Courier New", 1, 30);
		FontMetrics fm = g2.getFontMetrics();
		int xTitle = (getWidth() - fm.stringWidth(title))/2 - 50;
		int yTitle = 100;
		g2.setFont(font);
		g2.drawString(title, xTitle, yTitle);
		
		g2.setColor(Color.WHITE);
		font = new Font ("Courier New", 1, 20);
		g2.setFont(font);
		int xText = (getWidth() - fm.stringWidth(title))/2 - 100;
		int yText = 200;
		g2.drawString(scoreText+totalScore,xText, yText);
		
		 g2.drawString("Play again", 180, 300);
		 g2.drawString("Exit", 330, 300);
	}
}
