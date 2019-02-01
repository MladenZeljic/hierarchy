package program.object.models;

public class EnergyObject extends GameObject {

	private int _energyHeal;

	public EnergyObject() {
		super();
		super.setType("Energy");
		_energyHeal = 1;
	}
	public EnergyObject(int energyHeal) {
		super();
		super.setType("Energy");
		this._energyHeal = energyHeal;
	}
	public int getEnergyHeal() {
		return this._energyHeal;
	}
	public void setEnergyHeal(int energyHeal) {
		this._energyHeal = energyHeal;
	}
}
