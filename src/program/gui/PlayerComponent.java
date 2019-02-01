package program.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import program.object.models.PlayerObject;

public class PlayerComponent extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private JLabel nameLabel;
	private JTextField nameTextField;
	private JLabel surnameLabel;
	private JTextField surnameTextField;
	private PlayerObject playerObject;
	private JButton play;
	private JFrame frame;
	private JFrame mainWindow;
		
	 public PlayerComponent(JFrame ancestor, JFrame parent) {
		 frame = parent;
		 mainWindow = ancestor;
		 playerObject = new PlayerObject();
		 this.setBounds(0,0, this.getWidth(), this.getHeight());
		 form();
	 }
	 
		
	public void form() {
		//name
		nameLabel = new JLabel("Player Name");
		nameLabel.setBounds(20,  45, 150,50);
		nameLabel.setFont(new Font("Courier New", 1, 15));
		this.add(nameLabel);
			
		nameTextField = new JTextField();
		nameTextField.setBounds(180, 60, 320, 60);
		nameTextField.setSize(350, 30);
		this.add(nameTextField);
			
		//surname
		surnameLabel = new JLabel("Player Surname");
		surnameLabel.setBounds(20, 75, 150,85);
		surnameLabel.setFont(new Font("Courier New", 1, 15));
		this.add(surnameLabel);
			
		surnameTextField = new JTextField();
		surnameTextField.setBounds(180, 100, 320, 110);
		surnameTextField.setSize(350, 30);
		this.add(surnameTextField);
			
		// play button
		play = new JButton("Play");
		play.setBackground(Color.WHITE);
		play.setBounds(450,140,350,160);
		play.setSize(80,30);
	
		play.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String player = "";
				if(nameTextField.getText() != null  && surnameTextField.getText() != null) {
					if(nameTextField.getText().trim().equals("")&& surnameTextField.getText().trim().equals("")) {
						player = "Player";
					}
					else {
						player = nameTextField.getText()+ " " + surnameTextField.getText();
					}
				}
				
				else {
					player = "Player";
				}
				playerObject.setPlayerName(player);
			    HierarchicalModeling game = new HierarchicalModeling(mainWindow);
			    ((WelcomeComponent)mainWindow.getContentPane()).getGameSettings().setPlayerName(player);
			    game.setPlayer(playerObject);
			    frame.dispose();
			    game.showWindow();
			  }
			}
		);
		this.add(play);
	}
}


