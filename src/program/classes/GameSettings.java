package program.classes;

public class GameSettings {
	private String _playerName;
	private int _imageCount;
	private double _soundLevel;
	
	public GameSettings() {
		_playerName = "Player";
		_imageCount = 1;
		_soundLevel = 0.8;
	}
	public void incrementImageCount() {
		if(_imageCount == 4) {
			_imageCount = 1;
		}
		else {
			_imageCount++;
		}
	}
	public int getImageCount() {
		return _imageCount;
	}
	public double getSoundLevel() {
		return this._soundLevel;
	}
	public void setSoundLevel(double soundLevel) {
		this._soundLevel = soundLevel;
	}
	public void setPlayerName(String playerName) {
		_playerName = playerName;
	}
	public String getPlayerName() {
		return this._playerName;
	}

}
