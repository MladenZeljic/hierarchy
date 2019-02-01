package program.object.models;

import program.classes.MusicPlayer;

public class PlayerObject {

	private String _playerName;
	private int _playerScore;
	private int _playerEnergy;
	private int _playerBombCount;
	private int _playerMaxBombCount;
	private WagonObject _wagon;
	private MusicPlayer _wagonMoving;
	private MusicPlayer _wagonSwitching;
	
	public PlayerObject () {
		_playerName = "";
		_playerScore = 0;
		_playerEnergy = 0;
		_playerBombCount = 0;
		_playerMaxBombCount = 3;
		_wagon = new WagonObject();
		_wagonMoving = new MusicPlayer("wagon.wav", "effect");
		_wagonSwitching = new MusicPlayer("switch.wav", "effect");
	}
	public PlayerObject (String playerName,int playerEnergy) {
		_playerName = playerName;
		_playerScore = 0;
		_playerEnergy = playerEnergy;
		_playerBombCount = 0;
		_playerMaxBombCount = 3;
		_wagon = new WagonObject();
		_wagonMoving = new MusicPlayer("wagon.wav", "effect");
		_wagonSwitching = new MusicPlayer("switch.wav", "effect");
	}
	public String getPlayerName() {
		return this._playerName;
	}
	public void setPlayerName(String playerName) {
		this._playerName = playerName;
	}
	public int getPlayerScore() {
		return this._playerScore;
	}
	public void setPlayerScore(int playerScore) {
		this._playerScore = playerScore;
	}
	public WagonObject getWagon() {
		return this._wagon;
	}
	public void setWagon(WagonObject wagon) {
		this._wagon = wagon;
	}
	public int getPlayerEnergy() {
		return this._playerEnergy;
	}
	public void setPlayerEnergy(int playerEnergy) {
		this._playerEnergy = playerEnergy;
	}
	public int getPlayerMaxBombCount() {
		return this._playerMaxBombCount;
	}
	public void setPlayerMaxBombCount(int playerMaxBombCount) {
		this._playerMaxBombCount = playerMaxBombCount;
	}
	public int getPlayerBombCount() {
		return this._playerBombCount;
	}
	public void setPlayerBombCount(int playerBombCount) {
		this._playerBombCount = playerBombCount;
	}
	public MusicPlayer getWagonMovingSound() {
		return this._wagonMoving;
	}
	public void addEnergy(int energy) {
		this._playerEnergy+= energy;
	}
	public MusicPlayer getWagonSwitchingSound() {
		return this._wagonSwitching;
	}
}
