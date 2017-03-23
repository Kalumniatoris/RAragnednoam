package ka2.tesa.elements;

public abstract class Player {
private int posX;
private int posY;
private int power;
private int capacity;
private int id;
private String name; 

	public Player(int id, String name) {
		
		this.setPower(0);
		this.setId(id);
		this.setName(name);
		this.setCapacity(1000); //CAPACITY
		// TODO Auto-generated constructor stub
	}

	
	public abstract String run(int id, int power, int x, int y, int[][] look );

	

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


	public int getPower() {
		return power;
	}


	public void setPower(int power) {
		this.power = power;
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


	public String getName() {
		// TODO Auto-generated method stub
		return this.name;
	}


	public void setName(String name) {
		this.name = name;
	}

}
