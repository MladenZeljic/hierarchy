package program.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import program.classes.JsonClass;
import program.listeners.ButtonListener;

public class ScoresPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private JFrame parentWindow;
	private BufferedImage frameBackground;
	private JFrame window;
	private ScoresComponent content;
	private JsonClass jsonClass;
	private JSONArray highScores;
	private JScrollPane scrollPane;
	private JPanel panel;
	private JButton backBtn;
	private JButton clearScores;
	private JTable table;
	
	public ScoresPanel(JFrame parent)
	{
		this.parentWindow = parent;
    	parentWindow.setVisible(false);
    	jsonClass = new JsonClass();
    	highScores = jsonClass.readFromFile();
    	
    	window = new JFrame("Hierarchy - High scores");
		try {
			frameBackground = ImageIO.read(new File("images/scores.jpg"));
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
	    
	    content = new ScoresComponent(parentWindow, frameBackground);
        Color backgroundColor = Color.decode("#02A14D");
        content.setBackground(backgroundColor);
        this.back(content);
        this.clear(content);
    	this.addScoreTable();	    	
	    
	    window.addWindowListener(new WindowAdapter() {
	        @Override
	        public void windowClosing(WindowEvent event) {
	            exitProcedure();
	        }
	    });
	    
	    window.setContentPane(content);
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
	
    public void addScoreTable() 
    {
    	DefaultTableModel model = new DefaultTableModel();
    	DefaultTableCellRenderer dtcr= new DefaultTableCellRenderer();
    	
    	table = new JTable(model);
    	model.addColumn("Rank");
    	model.addColumn("Name & Surname");
    	model.addColumn("Score");
    	
    	addTableData(model);    	
    	
    	// centering columns text and set row count 
    	dtcr.setHorizontalAlignment(SwingConstants.CENTER);  
    	table.getColumnModel().getColumn(0).setCellRenderer(dtcr);
    	table.getColumnModel().getColumn(1).setCellRenderer(dtcr);
    	table.getColumnModel().getColumn(2).setCellRenderer(dtcr);
    	model.setRowCount(5);
    	table.setRowHeight(50);
    
    	content.setLayout(null);
    	panel = new JPanel();
    	Color backgroundColor = Color.decode("#02A14D");
    	panel.setBackground(backgroundColor);
    	panel.setBounds(0,200,595,350);
    	
    	scrollPane = new JScrollPane(table);
    	scrollPane.setBounds(0,0,595,350);
    	scrollPane.setPreferredSize(new Dimension(595,300));
    	
    	panel.add(scrollPane);
    	content.add(panel);
    }
    
    public void addTableData(DefaultTableModel model)
    {
    	for(int i=0; i < this.highScores.size(); i++) {
    		JSONObject item =  (JSONObject) this.highScores.get(i);
    		String rank = i+1+".";
    		String playerName = (String) item.get("player");
    		String score = (String) item.get("score");
    		model.addRow(new Object[] {rank, playerName, score});
    	}
    }
    
    public void clear(ScoresComponent content) 
	{
	    ImageIcon backIcon = new ImageIcon("images/clear.png");
		Image backIconContent = backIcon.getImage();  
	    Image resizedBackIconContent = backIconContent.getScaledInstance(20, 20,  java.awt.Image.SCALE_SMOOTH);  
	    backIcon.setImage(resizedBackIconContent);
	    
	    clearScores = new JButton(backIcon);
	    clearScores.setBounds(560, 8, 20, 20);
	    clearScores.setContentAreaFilled(false);
	    clearScores.setBorder(null);
	    ScoresPanel panel = this;
		
	    clearScores.addActionListener(new ActionListener() {
	    	@Override
			public void actionPerformed(ActionEvent e) {
				new JsonClass().clearFile();
		    	highScores = jsonClass.readFromFile();
		    	DefaultTableModel model = (DefaultTableModel) table.getModel();
		    	model.setRowCount(0);
		    	model.setRowCount(5);
				panel.repaint();
			}
	    	
	    });
	    clearScores.setFocusPainted(false);
	    content.add(clearScores);
    }
    
    public void back(ScoresComponent content) 
	{
	    ImageIcon backIcon = new ImageIcon("images/back.png");
		Image backIconContent = backIcon.getImage();  
	    Image resizedBackIconContent = backIconContent.getScaledInstance(20, 20,  java.awt.Image.SCALE_SMOOTH);  
	    backIcon.setImage(resizedBackIconContent);
	    
	    backBtn = new JButton(backIcon);
	    backBtn.setBounds(5, 8, 20, 20);
	    backBtn.setContentAreaFilled(false);
	    backBtn.setBorder(null);
	    backBtn.setName("BackScores");
	    backBtn.addActionListener(new ButtonListener());
	    backBtn.setFocusPainted(false);
	    content.add(backBtn);
    }	
}
