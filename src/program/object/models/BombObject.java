package program.object.models;

public class BombObject extends GameObject {

	private int _bombDamage;
	
	public BombObject() {
		super();
		super.setType("Bomb");
		this._bombDamage = 2;
	}
	public BombObject(int bombDamage) {
		super();
		super.setType("Bomb");
		this._bombDamage = bombDamage;
	}
	public void setBombDamage(int bombDamage) {
		this._bombDamage = bombDamage;
	}
	public int getBombDamage() {
		return this._bombDamage;
	}
	
}
