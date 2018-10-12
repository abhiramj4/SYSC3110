package entities;

abstract public class Entity {
	private int health;
	private String name;
	
	public Entity(int health, String name) {
		this.health = health;
		this.name = name;
	}
	
	public void setHealth(int health) {
		this.health = health;
	}
	
	public int getHealth() {
		return this.health;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
}
