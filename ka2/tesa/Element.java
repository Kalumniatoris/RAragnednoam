package ka2.tesa;

public class Element {
private int posX;
private int posY;
private int type;
private int value;
private int capacity;
private int id;
public Element(int type, int x, int y, int value, int id){
	this.setPosX(x);
	this.setPosY(y);
	this.setType(type);
	this.setPower(value);
	this.setId(id);
	
}
public void update(int type,int value, int id){
	this.setType(type);
	this.setPower(value);
	this.setId(id);
}

public int getPosX() {
	return posX;
}

public void setPosX(int posX) {
	this.posX = posX;
}

public int getPosY() {
	return posY;
}

public void setPosY(int posY) {
	this.posY = posY;
}

public int getType() {
	return type;
}

public void setType(int type) {
	this.type = type;
}

public int getPower() {
	return value;
}

public void setPower(int value) {
	this.value = value;
}

public int getCapacity() {
	return capacity;
}

public void setCapacity(int capacity) {
	this.capacity = capacity;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
}
