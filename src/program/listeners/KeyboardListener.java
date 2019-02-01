package program.listeners;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import program.classes.MusicPlayer;
import program.gui.HierarchicalModeling;
import program.gui.WelcomeComponent;

public class KeyboardListener implements KeyListener {
		
	private HierarchicalModeling _panel;

	public KeyboardListener(HierarchicalModeling panel) {
		this._panel = panel;
	}
		
	@Override
	public void keyPressed(KeyEvent e) {
			
		double xWagonCoordinate = _panel.getPlayer().getWagon().getX();
		double yWagonCoordinate = _panel.getPlayer().getWagon().getY();
			
		double xMaxCoordinate = _panel.getHierarchicalModel().getxMaxCoordinate();
		double xMinCoordinate = _panel.getHierarchicalModel().getxMinCoordinate();
		MusicPlayer wagonSwitching = _panel.getPlayer().getWagonSwitchingSound();
		MusicPlayer wagonMoving = _panel.getPlayer().getWagonMovingSound();
		double soundLevel = ((WelcomeComponent)_panel.getMyParent().getContentPane()).getGameSettings().getSoundLevel();
		
		wagonSwitching.setClipVolume(soundLevel);
		wagonMoving.setClipVolume(soundLevel);
		
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			wagonMoving.replay(false);
			if(xWagonCoordinate <= xMaxCoordinate - 0.8) {
				xWagonCoordinate += 0.1;
			}
			
			_panel.getPlayer().getWagon().setX(xWagonCoordinate);
			_panel.repaint();
		}
		   
		if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			wagonMoving.replay(false);
			if(xWagonCoordinate >= xMinCoordinate + 0.7) {
				xWagonCoordinate -= 0.1;
			}
			
			_panel.getPlayer().getWagon().setX(xWagonCoordinate);
			_panel.repaint();
		}
		if(e.getKeyCode() == KeyEvent.VK_UP) {
			if(!_panel.getPlayer().getWagon().getOtherTrack()) {
				wagonSwitching.replay(false);
				yWagonCoordinate += 0.3;
				_panel.getPlayer().getWagon().setOtherTrack(true);
			}
			_panel.getPlayer().getWagon().setY(yWagonCoordinate);
			_panel.repaint();
		}
		if(e.getKeyCode() == KeyEvent.VK_DOWN) {
		    if(_panel.getPlayer().getWagon().getOtherTrack()) {
		    	wagonSwitching.replay(false);
		    	yWagonCoordinate = 0;
				_panel.getPlayer().getWagon().setOtherTrack(false);
		    }
		    _panel.getPlayer().getWagon().setY(yWagonCoordinate);
		    _panel.repaint();
		}
	}


	@Override
	public void keyReleased(KeyEvent e) {
		e.consume();
	}
	@Override
	public void keyTyped(KeyEvent e) {
		e.consume();	
	}
		 
}
