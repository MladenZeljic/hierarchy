package program.object.models;

public class GameObject {
	double _x;
	double _y;
	boolean _inOtherTrack;
	boolean _collided;
	String _type;
	double _speed;
	
	public GameObject() {
		_x = 0;
		_y = 0;
		_inOtherTrack = false;
		_collided = false;
		_type = "";
		_speed = 0;
	}
	public GameObject(double x, double y, String type, double speed) {
		_x = x;
		_y = y;
		_type = type;
		_collided = false;
		_speed = speed;
	}
	public void setType(String type) {
		_type = type;
	}
	public String getType() {
		return _type;
	}
	public void setSpeed(double speed) {
		_speed = speed;
	}
	public double getSpeed() {
		return _speed;
	}
	public void setOtherTrack(boolean track) {
		_inOtherTrack = track;
	}
	public boolean getOtherTrack() {
		return _inOtherTrack;
	}
	public void setCollision(boolean collision) {
		_collided = collision;
	}
	public boolean getCollision() {
		return _collided;
	}
	public void setX(double x) {
		_x = x;
	}
	public void setY(double y) {
		_y = y;
	}
	public double getX() {
		return _x;
	} 
	public double getY() {
		return _y;
	}
}
