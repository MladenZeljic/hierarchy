package program.gui;

import java.awt.Color;
import java.awt.Dimension;
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
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import program.listeners.ButtonListener;

public class SettingsPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private JFrame parentWindow;
	private BufferedImage frameBackground;
	private JFrame window;
	private JTabbedPane tabs;
	private JButton backBtn;
	
	public SettingsPanel(JFrame parent){
		this.parentWindow = parent;
    	parentWindow.setVisible(false);
		
    	window = new JFrame("Hierarchy - Settings");
		try {
			frameBackground = ImageIO.read(new File("images/settings-icon.png"));
		} catch (IOException e1) {
			JOptionPane.showMessageDialog(null, "Background could not be set! ", "Error! ", JOptionPane.ERROR_MESSAGE);
		}
		
		try {
			window.setIconImage(new ImageIcon("images/hierarchy.png").getImage());
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(null, "Icon could not be found! ", "Error! ", JOptionPane.ERROR_MESSAGE);
		}
		
		setBackground( Color.LIGHT_GRAY );
	    setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));
	   
		window.setSize(600, 500);
	    window.setLocationRelativeTo(null);
	    window.setResizable(false);
	    
	    SettingsComponent content = new SettingsComponent(parentWindow,frameBackground);
	    Color backgroundColor = Color.decode("#f1b15a");
	    content.setBackground(backgroundColor);
	    
	    back(content);
	    addTabs(content);
	    
	    window.addWindowListener(new WindowAdapter() {
	        @Override
	        public void windowClosing(WindowEvent event) {
	            exitProcedure();
	        }
	    });

		window.setContentPane(content);
	    
		
	}
	public JFrame getMyParent() {
		return this.parentWindow;
	}
	public void addTabs(SettingsComponent content) 
	{
		content.setLayout(null);
		tabs = new JTabbedPane();
		tabs.setBounds(0, 20, this.getWidth(), this.getHeight());

		ControlsComponent controlPanel = new ControlsComponent();
		controlPanel.setBackground(Color.WHITE);

		PlayerComponent playerPanel = new PlayerComponent(parentWindow,window);
		playerPanel.setBackground(Color.WHITE);
		playerPanel.setLayout(null);
		JScrollPane scroll = new JScrollPane();
		controlPanel.setPreferredSize(new Dimension(595,630));
		scroll.setViewportView(controlPanel);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scroll.setPreferredSize(new Dimension (200,100));
		tabs.add("Game Infos", scroll);
		tabs.add("Players", playerPanel);
		tabs.setBounds(0, 120, 595, 350);
		
		content.add(tabs);
		
	}
	
	public void back(SettingsComponent content) 
	{
	    ImageIcon backIcon = new ImageIcon("images/back.png");
		Image backIconContent = backIcon.getImage();  
	    Image resizedBackIconContent = backIconContent.getScaledInstance(20, 20,  java.awt.Image.SCALE_SMOOTH);  
	    backIcon.setImage(resizedBackIconContent);
	    
	    backBtn = new JButton(backIcon);
	    backBtn.setBounds(5, 8, 20, 20);
	    backBtn.setContentAreaFilled(false);
	    backBtn.setBorder(null);
	    backBtn.setName("BackSettings");
	    backBtn.addActionListener(new ButtonListener());
	    backBtn.setFocusPainted(false);
	    content.add(backBtn);
    }
	
	public void exitProcedure() {
		window.dispose();
	    parentWindow.setVisible(true);
	}
	public void showWindow() {
		window.setVisible(true);
		
	}
	public void hideWindow() {
		window.setVisible(false);
		
	}
	
}
