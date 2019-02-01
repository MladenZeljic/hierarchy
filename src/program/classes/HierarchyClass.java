package program.classes;

import java.awt.geom.Path2D;
import java.util.ArrayList;
import java.util.Collection;

public class HierarchyClass {
	
	private Path2D _ground; // A path used for drawing the ground, with "hills".
	private Collection<Path2D> _hills;
	
	private int _frameNumber; // For animation, increases by 1 in each frame.
	private float _penSize;  

	private double _xMinCoordinate = 0;    
	private double _yMinCoordinate = -1;	  
	private double _xMaxCoordinate = 7;      
	private double _yMaxCoordinate = 4;
	
	public HierarchyClass() {
		_ground = new Path2D.Double();
		_hills = new ArrayList<>();
		_frameNumber = 0;
		_penSize = 0;
	}
	
	public Path2D getGround() {
		return this._ground;
	}
	public Collection<Path2D> getHills(){
		return this._hills;
	}
	public int getFrameNumber() {
		return this._frameNumber;
	}
	public float getPenSize() {
		return this._penSize;
	}
	public void setGround(Path2D ground) {
		this._ground = ground;
	}
	public void setHills(Collection<Path2D> hills){
		this._hills = hills;
	}
	public void setFrameNumber(int frameNumber) {
		this._frameNumber = frameNumber;
	}
	public void setPenSize(float penSize) {
		this._penSize = penSize;
	}
	
	public void incrementFrame() {
		this._frameNumber ++;
	}
	public double getxMinCoordinate() {
		return this._xMinCoordinate;
	}
	public double getxMaxCoordinate() {
		return this._xMaxCoordinate;
	}
	public double getyMinCoordinate() {
		return this._yMinCoordinate;
	}
	public double getyMaxCoordinate() {
		return this._yMaxCoordinate;
	}
	public void setxMinCoordinate(double xMinCoordinate) {
		this._xMinCoordinate = xMinCoordinate;
	}
	public void setxMaxCoordinate(double xMaxCoordinate) {
		this._xMaxCoordinate = xMaxCoordinate;
	}
	public void setyMinCoordinate(double yMinCoordinate) {
		this._yMinCoordinate = yMinCoordinate;
	}
	public void setyMaxCoordinate(double yMaxCoordinate) {
		this._yMaxCoordinate = yMaxCoordinate;
	}
	
	
}
