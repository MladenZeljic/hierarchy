package program.gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import program.classes.HierarchyClass;
import program.classes.JsonClass;
import program.classes.MusicPlayer;
import program.listeners.KeyboardListener;
import program.object.models.BombObject;
import program.object.models.EnergyObject;
import program.object.models.GameObject;
import program.object.models.PlayerObject;
import java.awt.geom.*;
import java.util.Collection;
import java.util.Random;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class HierarchicalModeling extends JPanel 
{
	private static final long serialVersionUID = 1L;
	private HierarchyClass hierarchy;
	private JFrame window;
	private JFrame parentWindow;
	private PlayerObject player;
	private BombObject bomb;
	private EnergyObject energy;
	private static int delaySeconds = 2;
	private MusicPlayer bombSound;
	private MusicPlayer energySound;
	private MusicPlayer bombDecrementSound;
	private MusicPlayer backgroundPlayer;
	private MusicPlayer scoreDropPlayer;
	private MusicPlayer scoreStarsPlayer;
	private MusicPlayer scoreStarsDropPlayer;
	private boolean scoreDropPlayed;
	private double soundLevel;
	private boolean gameOverPlayed;
	private boolean scoreStarsPlayed;
	private boolean scoreStarsDropPlayed;
	private boolean starsAwarded;
	private boolean scoreOk;
	private MusicPlayer mainWindowPlayer;
	private static ConcurrentLinkedQueue <GameObject>objects;
	
	public HierarchicalModeling(JFrame parent) {
		parentWindow = parent;
		mainWindowPlayer = ((WelcomeComponent)parent.getContentPane()).getMusicPlayer();
		bombDecrementSound = new MusicPlayer("point.wav", "effect");
		scoreStarsPlayer = new MusicPlayer("star.wav","effect");
		scoreStarsDropPlayer = new MusicPlayer("starDrop.wav","effect");
		scoreOk = false;
		starsAwarded = false;
		
		String playerName = ((WelcomeComponent)parent.getContentPane()).getGameSettings().getPlayerName();
		parentWindow.setVisible(false);
		window = new JFrame("Hierarchy - The Game");
		hierarchy = new HierarchyClass();
			
		player = new PlayerObject();
		player.setPlayerName(playerName);
		gameOverPlayed = false;
		scoreStarsPlayed = false;
		scoreStarsDropPlayed = false;
		objects =  new ConcurrentLinkedQueue<>();
		try {
			window.setIconImage(new ImageIcon("images/hierarchy.png").getImage());
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(null, "Icon could not be found! ", "Error! ", JOptionPane.ERROR_MESSAGE);
		}
		
		setBackground( Color.LIGHT_GRAY );
	    setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));
	    
	    Path2D ground = hierarchy.getGround();
	    ground.moveTo(0,-1);
	    ground.lineTo(0,1);
	    ground.lineTo(7,1);
	    ground.lineTo(7,-1);
	    ground.closePath();
	    hierarchy.setGround(ground);
	    
	    Collection<Path2D>hills = hierarchy.getHills();
	    Path2D hill = new Path2D.Float();
	    hill.moveTo(0,1);
	    hill.lineTo(1,2.1);
	    hill.lineTo(2,1);
	    hill.closePath();
	    
	    hills.add(hill);
	    
	    hill = new Path2D.Float();
	    hill.moveTo(1.5,1);
	    hill.lineTo(2.5,1.8);
	    hill.lineTo(3.5,1);
	    hill.closePath();
	    
	    hills.add(hill);
	    
	    hill = new Path2D.Float();
	    hill.moveTo(3,1);
	    hill.lineTo(4,2.1);
	    hill.lineTo(5,1);
	    hill.closePath();
	    
	    hills.add(hill);
	    
	    hill = new Path2D.Float();
	    hill.moveTo(5,1);
	    hill.lineTo(5.5,1.5);
	    hill.lineTo(6.0,1);
	    hill.closePath();
	    
	    hills.add(hill);
	    hierarchy.setHills(hills);
	    final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
	    
	    executorService.scheduleAtFixedRate(new Runnable() {

			@Override
			public void run() {
				int randomInt = getRandomInteger(0,2);
				if(randomInt != 0) {
					buildBombs();
				}
				else {
					buildEnergy();
				}
			}
	    	
	    }, 0, delaySeconds, TimeUnit.SECONDS);

	    
	    new Timer(50,new ActionListener() {
	    	public void actionPerformed(ActionEvent event) {
	    		hierarchy.incrementFrame();
	    		repaint();
	         }
	      }).start();
	    soundLevel = ((WelcomeComponent)parent.getContentPane()).getGameSettings().getSoundLevel();

	    scoreDropPlayer = new MusicPlayer("scoreDrop.wav", "effect");
		scoreDropPlayed = false;
	    backgroundPlayer = new MusicPlayer("peace.wav", "background");
		
		backgroundPlayer.setClipVolume(soundLevel);
		mainWindowPlayer.setClipVolume(soundLevel);
		scoreDropPlayer.setClipVolume(soundLevel);
		
		backgroundPlayer.play(true);
		mainWindowPlayer.pause();
	
	    window.addKeyListener(new KeyboardListener(this));
	    window.setSize(600, 500);
	    window.setLocationRelativeTo(null);
	    window.setResizable(false);
	    window.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	    window.addWindowListener(new WindowAdapter() {
	        @Override
	        public void windowClosing(WindowEvent event) {
	        	exitProcedure();
	        	
	        }
	    });
	    window.setContentPane(this);
	    	
	}
	
	public HierarchyClass getHierarchicalModel() {
		return this.hierarchy;
	}
	public JFrame getMyParent() {
		return this.parentWindow;
	}
	public PlayerObject getPlayer() {
		return this.player;
	}
	public void setPlayer(PlayerObject p) {
		this.player = p;
	}
	public void hideWindow() {
		this.window.setVisible(false);
	}
	public void showWindow() {
		this.window.setVisible(true);
	    
	}
	public int getTotalScore() {
		return player.getPlayerScore();
	}
	
	private void buildBombs()
	{
		bomb = new BombObject();
		bomb.setX(hierarchy.getxMaxCoordinate());
		
		int randomInt = getRandomInteger(0,2);
		if(randomInt != 0) {
    		double objectSpeed = getRandomInteger(20,30);
    		bomb.setSpeed(objectSpeed);
    		int random = getRandomInteger(0,2);
    		
    		if(random == 1) {
    			bomb.setOtherTrack(true);
    			bomb.setY(0);
    		}
    		else {
    			bomb.setOtherTrack(false);	
    			bomb.setY(-0.4);
    		}
    		
    		
		}
		objects.add(bomb);
	}
	private void buildEnergy() 
	{
		energy = new EnergyObject();
		energy.setX(hierarchy.getxMaxCoordinate());
		
		int randomInt = getRandomInteger(0,2);
		if(randomInt != 0) {
    		double objectSpeed = getRandomInteger(20,50);
    		energy.setSpeed(objectSpeed);
    		int random = getRandomInteger(0,2);
    		
    		if(random == 1) {
    			energy.setOtherTrack(true);
    			energy.setY(0);
    		}
    		else {
    			energy.setOtherTrack(false);	
    			energy.setY(-0.4);
    		}
    		
    		
		}
		objects.add(energy);
	}
	
	private void exitProcedure() {
		objects.clear();
		backgroundPlayer.stop();
		mainWindowPlayer.play(true);
		scoreDropPlayed = false;
		window.setVisible(false);
		parentWindow.setVisible(true);
	}
	

	protected void updateObjects(Graphics2D g2) {	
		double xWagonCoordinate = player.getWagon().getX();
		int playerEnergy = player.getPlayerEnergy();
  		int playerBomb = player.getPlayerBombCount();
	  	int totalScore = player.getPlayerScore();
	  	int scoreIncrease = 0;
		AffineTransform saveTr = g2.getTransform();
	      
	    for(GameObject game : objects) {
	    	
	    	game.setX(game.getX() - 0.5*(game.getSpeed() % 300) / 300.0);
	  	  	
	    	if(game.getOtherTrack() == player.getWagon().getOtherTrack() && Math.abs(game.getX()) <= xWagonCoordinate + 0.8 && Math.abs(game.getX()) >= xWagonCoordinate-1.1){
			  	
			  	int playerMaxBomb = player.getPlayerMaxBombCount();
		  		
			  	if(game.getType().equals("Bomb") ) {
		  			BombObject b = (BombObject)game;
		  			playerEnergy -= b.getBombDamage();
		  			scoreIncrease = -1*30;
		  			totalScore -= 30;
		  			if(playerEnergy < 0) {
		  				playerEnergy = 0;
		  			}
		  			
		  			bombSound = new MusicPlayer("bomb.wav", "effect");
		  			scoreOk = true;
			  		
		  			bombSound.setClipVolume(soundLevel);
		  			
		  			bombSound.replay(false);
		  			playerBomb++;
		  			if(playerBomb == playerMaxBomb || playerEnergy == 0) {
		  				scoreOk = false;
				  		
		  				if(!gameOverPlayed) {
		  					window.setVisible(false);
		  					backgroundPlayer.stop();
		  					player.setPlayerScore(totalScore);
		  					new GameOverPanel(window, player).setVisible(true);
		  					gameOverPlayed = true;
		  				}
		  			}
		  			else {
			  			if(!scoreDropPlayed) {
			  				if(totalScore < 0) {
				  				scoreDropPlayer.replay(false);
				  				scoreDropPlayed = true;
			  				}
			  				
			  			}
		  			}
		  		}
		  		else if(game.getType().equals("Energy")) {
		  			EnergyObject e = (EnergyObject)game;
		  			playerEnergy += e.getEnergyHeal();
		  			scoreOk = true;
			  		
		  			if(playerEnergy % 10 == 0) {
		  				bombDecrementSound.setClipVolume(soundLevel);
			  			
		  				bombDecrementSound.replay(false);
		  				playerBomb --;
			  			
		  				totalScore += 5;
		  				scoreIncrease = 5;
			  			if(playerEnergy % 10 == 0) {
			  				totalScore += 10;
			  				scoreIncrease += 10;
			  			}
		  				if(playerBomb < 0) {
		  					playerBomb = 0;
		  				}
		  			}
		  			else {
		  				energySound = new MusicPlayer("energy.wav", "effect");
		  				energySound.setClipVolume(soundLevel);
			  			
		  				energySound.replay(false);
		  				totalScore += 5;
		  				scoreIncrease = 5;
		  			}
		  		}
			  	if(totalScore >= 0) {
		  			scoreDropPlayed = false;
		  			
		  		}
			  	if(((totalScore > 0 && totalScore % 100 == 0) || ((totalScore - scoreIncrease) > 0 && totalScore - scoreIncrease % 100 == 0)) && totalScore > player.getPlayerScore()) {
			  		bombDecrementSound.setClipVolume(soundLevel);
	  				bombDecrementSound.replay(false);
	  				playerBomb --;
	  				if(playerBomb < 0) {
	  					playerBomb = 0;
	  				}
			  	}
		  		player.setPlayerEnergy(playerEnergy);
		  		
		  		player.setPlayerScore(totalScore);
		  		player.setPlayerBombCount(playerBomb);
		  		game.setCollision(true);
		  		objects.remove(game);
		  	}
		  	else {
		  		if(!game.getCollision()) {
		  			if(game.getX() < -0.4) {
		  				objects.remove(game);
		  			}
		  			else {
		  				g2.translate(game.getX(), game.getY());
		  				if(game.getType().equals("Bomb") ) {
		  					drawBomb(g2);      
		  				}
		  				if(game.getType().equals("Energy") ) {
		  					drawEnergy(g2);      
		  				}
		  				
		  				g2.setTransform(saveTr);
		  			}
		  		}
		  				
		  	}
	    }
	}
	
	private Path2D drawStar(Graphics2D g2, AffineTransform original) {

		Path2D star = new Path2D.Double();
		g2.setTransform(original);
		
        star.moveTo(100,10);
		star.lineTo(125,75);
	    star.lineTo(200,85);
	    star.lineTo(150,125);
	    star.lineTo(160,190);
	    star.lineTo(100,150);
	    star.lineTo(40,190);
	    star.lineTo(50, 125);
	    star.lineTo(0, 85);
	    star.lineTo(75, 70);
	    star.closePath();

	    return star;
	}
	
	protected void drawStars(Graphics2D g2,AffineTransform originalTr,int gameScore) {
		int maxScore = new JsonClass().getMaxScore();
		if(scoreOk) {
			if(gameScore >= maxScore) {
				if(!scoreStarsPlayed) {
					scoreStarsPlayer.setClipVolume(soundLevel);
					scoreStarsPlayer.replay(false);
					scoreStarsPlayed = true;
					scoreStarsDropPlayed = false;
				}
				starsAwarded = true;
				Path2D star = drawStar(g2,originalTr);
				
			    AffineTransform txl = new AffineTransform();
			    txl.translate(195, 86);
			    txl.scale(0.1, 0.1);
			    Shape leftStar = txl.createTransformedShape(star);
			    g2.setColor(Color.ORANGE);
				g2.fill(leftStar);
				

			    AffineTransform txr = new AffineTransform();
			    txr.translate(260, 86);
			    txr.scale(0.1, 0.1);
			    Shape rightStar = txr.createTransformedShape(star);
			    g2.fill(rightStar);
				
			}
			else {
				if(!scoreStarsDropPlayed && starsAwarded) {
					scoreStarsDropPlayer.setClipVolume(soundLevel);
					scoreStarsDropPlayer.replay(false);
					scoreStarsDropPlayed = true;
					scoreStarsPlayed = false;
   				}
			}
		}
	}
	
   protected void paintComponent(Graphics g) { 
      super.paintComponent(g);
      Graphics2D g2 = (Graphics2D)g.create(); 
      g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
      
      AffineTransform originalTr = g2.getTransform();
      applyLimits(g2, hierarchy.getxMinCoordinate(), hierarchy.getyMinCoordinate(), hierarchy.getxMaxCoordinate(), hierarchy.getyMaxCoordinate(), false);
      
      g2.setColor( new Color(135, 206, 235) );
      g2.fillRect(0,0,7,4);
      g2.setColor( new Color(0,250,3) );
      g2.fill(hierarchy.getGround());
      g2.setColor(new Color(181,137,99));
      Collection<Path2D> hills = hierarchy.getHills();
      for (Path2D hill : hills) {
    	  g2.fill(hill);
      }
      g2.setColor(new Color(100,100,150));
      g2.fill(new Rectangle2D.Double(0,-0.4,7,0.8));
      g2.setStroke( new BasicStroke(5*hierarchy.getPenSize()) );
      g2.setColor(Color.WHITE);
      g2.drawLine(0,0,7,0);
      g2.setStroke( new BasicStroke(hierarchy.getPenSize()) );
      
      AffineTransform saveTr = g2.getTransform();  
      g2.translate(5.5,3.3);
      drawSun(g2);
      g2.setTransform(saveTr);      
      
      g2.translate(-1, 0.5);
      drawFlowers(g2);
      g2.setTransform(saveTr);
      
      g2.translate(-1, -0.8);
      drawFlowers(g2);
      g2.setTransform(saveTr);
                 
      updateObjects(g2);
      scoreHeader(g2,originalTr);
      
      g2.translate(player.getWagon().getX(), player.getWagon().getY());
      g2.scale(0.3,0.3);
      drawWagon(g2);
      g2.setTransform(saveTr);  
   }
   
   private int getRandomInteger(int min, int max) {
	   Random r = new Random();
	   return r.nextInt(max-min) + min;
   }
   
   private void applyLimits(Graphics2D g2, double xMin, double yMin, double xMax, double yMax, boolean preserveAspect) {
      int width = getWidth();
      int height = getHeight();
      if (preserveAspect) {
    	 double displayAspect = Math.abs((double)height / width);
         double requestedAspect = Math.abs(( yMin-yMax ) / ( xMax-xMin ));
         if (displayAspect > requestedAspect) {
            double excess = (yMin-yMax) * (displayAspect/requestedAspect - 1);
            yMin += excess/2;
            yMax -= excess/2;
         }
         else if (displayAspect < requestedAspect) {
            double excess = (xMax-xMin) * (requestedAspect/displayAspect - 1);
            xMax += excess/2;
            xMin -= excess/2;
         }
      }
      double pixelWidth = Math.abs(( xMax - xMin ) / width);
      double pixelHeight = Math.abs(( yMin - yMax ) / height);
      hierarchy.setPenSize((float)Math.min(pixelWidth,pixelHeight));
      g2.scale( width / (xMax-xMin), height / (yMin-yMax) );
      g2.translate( -xMin, -yMax );
   }

   private void drawSun(Graphics2D g2) {
      g2.setColor(Color.YELLOW);
      g2.rotate(hierarchy.getFrameNumber()/30.0);
      for (int i = 0; i < 13; i++) {
         g2.rotate( 2*Math.PI / 13 );
         g2.draw( new Line2D.Double(0,0,0.75,0) );
      }
      g2.fill( new Ellipse2D.Double(-0.5,-0.5,1,1) );
      g2.setColor( new Color(180,0,0) );
      g2.draw( new Ellipse2D.Double(-0.5,-0.5,1,1) );
   }
   
   public void drawFlowers(Graphics2D g2) {
	   g2.setColor(Color.DARK_GRAY);
	   
	   for(int i = 0; i< 5; i++) {
		   g2.translate(1.5, 0);
		   g2.setColor(Color.DARK_GRAY);
		   
		   g2.draw(new Line2D.Double(0,0,0,0.2));		   
		   g2.draw(new Line2D.Double(0,0,-0.2,0.2));
		   g2.draw(new Line2D.Double(0,0,0.2,0.2));		 

		   g2.setColor(Color.YELLOW);
		   g2.fill(new Ellipse2D.Double(-0.05,0.3,0.1,0.1));		   

		   g2.setColor(new Color(180,0,0));
		   g2.fill(new Ellipse2D.Double(-0.15,0.3,0.1,0.1));		   
		   g2.fill(new Ellipse2D.Double(0.05,0.3,0.1,0.1));
		   
		   g2.fill(new Ellipse2D.Double(-0.05,0.2,0.1,0.1));
		   g2.fill(new Ellipse2D.Double(-0.05,0.4,0.1,0.1));  
	   }	  
   }
   
   private void drawWagon(Graphics2D g2) 
   {
      AffineTransform tr = g2.getTransform();
      g2.translate(-1.5,-0.1);
      g2.scale(0.8,0.8);
      drawWheel(g2);
      g2.setTransform(tr); 
      g2.translate(1.5,-0.1);
      g2.scale(0.8,0.8);
      drawWheel(g2);
      g2.setTransform(tr);
      g2.setColor(Color.BLUE);
      g2.fill(new Rectangle2D.Double(-2.5,0,5,2) );
      
      g2.setColor(Color.WHITE);
      g2.fill(new Rectangle2D.Double(-2.0, 1, 1, 0.7));
      g2.fill(new Rectangle2D.Double(1, 1, 1, 0.7));
   }
   
   public void drawBomb(Graphics2D g2) {
	   g2.setColor(Color.BLACK);
	   g2.fill(new Ellipse2D.Double(0,0.1,0.2,0.2));	   
	   
	   AffineTransform tr = g2.getTransform();
	   g2.translate(0.15, -0.01);
	   g2.rotate(Math.PI/6);
	   g2.fill(new Rectangle2D.Double(0.1,0.15,0.12,0.12));
	   g2.setTransform(tr);
	   
	   g2.setColor(Color.ORANGE);
	   g2.draw(new Line2D.Double(0.3,0.3,0.4,0.25));
	   g2.draw(new Line2D.Double(0.3,0.3,0.4,0.31));
	   g2.draw(new Line2D.Double(0.3,0.3,0.4,0.35));
   }
   
   public void drawEnergy(Graphics2D g2) {
	   g2.setColor(Color.ORANGE);
	   double dy = 0.4;
	   g2.fill(new Ellipse2D.Double(0,-0.3+dy,0.22,0.22));
	   
	   g2.setColor(Color.WHITE);
	   g2.setStroke(new BasicStroke(3*hierarchy.getPenSize()) );
	   g2.draw(new Line2D.Double(0.05,-0.18+dy,0.15,-0.13+dy));
	   g2.draw(new Line2D.Double(0.05,-0.18+dy,0.15,-0.21+dy));
	   g2.draw(new Line2D.Double(0.15,-0.21+dy,0.06 ,-0.25+dy));
   }
   
   private void drawWheel(Graphics2D g2) {
      g2.setColor(Color.BLACK);
      g2.fill( new Ellipse2D.Double(-1,-1,2,2) );
      g2.setColor(Color.LIGHT_GRAY);
      g2.fill( new Ellipse2D.Double(-0.8,-0.8,1.6,1.6) );
      g2.setColor(Color.BLACK);
      g2.fill( new Ellipse2D.Double(-0.2,-0.2,0.4,0.4) );
      
   }
   
   private void drawScore(AffineTransform originalTr, AffineTransform transform, Graphics2D g2) 
   {
	   		// score - Ellipse
			double xScore = hierarchy.getxMinCoordinate() + 2.3;
			double yScore = hierarchy.getyMaxCoordinate() - 0.9;
			g2.setColor(Color.ORANGE);
			g2.translate(xScore,yScore);
			g2.fill(new Ellipse2D.Double(0,0.05,1,0.27));		
			g2.setTransform(originalTr);
			
			// total score
			int gameScore = player.getPlayerScore();
			String score = Integer.toString(gameScore);
			g2.setColor(Color.WHITE);
			
			if(gameScore >= 0 && gameScore <= 9)
			   g2.drawString(score, 230, 73);
			else if((gameScore >= 10 && gameScore <= 99) || (gameScore >= -9 && gameScore <= -1))
				 g2.drawString(score, 224, 73);
			else 
				g2.drawString(score, 215, 73);
			g2.setTransform(transform);
			
			// lines
			g2.setColor(Color.WHITE);
			double xLine1 = hierarchy.getxMinCoordinate() + 2.6;
			double y1Line = hierarchy.getyMaxCoordinate() - 0.85;
			double y2Line = hierarchy.getyMaxCoordinate() - 0.9;
			double xLine2 = hierarchy.getxMinCoordinate() + 3;
			g2.draw(new Line2D.Double(xLine1, y1Line,xLine1 , y2Line));
			g2.draw(new Line2D.Double(xLine2, y1Line,xLine2 , y2Line));
			
			// rectangle
			double x = hierarchy.getxMinCoordinate() + 2.3;
			double y = hierarchy.getyMaxCoordinate() - 1.13;
			g2.fill(new Rectangle2D.Double(x,y, 1, 0.2));
			g2.setTransform(originalTr);
			
			// stars
			drawStars(g2,originalTr,gameScore);
			
			// score string
			g2.setColor(Color.BLACK);
			g2.setFont(new Font("Courier New", Font.BOLD, 15));
			g2.drawString("Score", 215, 100);
			g2.setTransform(transform);
   }
 
   
   public void scoreHeader(Graphics2D g2, AffineTransform originalTr) 
   {
	    
	    AffineTransform transform = g2.getTransform();
	    g2.setTransform(originalTr);
	    Font font = new Font("Courier New", Font.BOLD, 25);
		g2.setFont(font);
		g2.setColor(Color.WHITE);
		
		// player name
	    g2.drawString(player.getPlayerName(), 25, 35);
	    g2.setTransform(transform);
	    
	    // energy icon
	    double xEnergyIcon = hierarchy.getxMinCoordinate() + 0.3;
		double yEnergyIcon = hierarchy.getyMaxCoordinate() - 0.9;
		g2.translate(xEnergyIcon, yEnergyIcon);
		drawEnergy(g2);
		g2.setTransform(originalTr);
	
		// energy count
		String energyCount = Integer.toString(this.player.getPlayerEnergy());
		g2.drawString(energyCount, 55, 72);
		g2.setTransform(transform);
		
		// bomb icon
		double xBombIcon = hierarchy.getxMinCoordinate() + 1.3;
		double yBombIcon = hierarchy.getyMaxCoordinate() - 0.9;
		g2.translate(xBombIcon, yBombIcon);
		drawBomb(g2);
		g2.setTransform(originalTr);
	
		// number of bombs
		String bombCount = Integer.toString(this.player.getPlayerBombCount());
		g2.setColor(Color.WHITE);
		g2.drawString(bombCount, 155, 72);
		g2.setTransform(transform);
		
		// score
		drawScore(originalTr, transform, g2);	
   }
}