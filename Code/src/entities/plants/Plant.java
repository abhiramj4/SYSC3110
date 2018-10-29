package entities.plants;

import board.Coordinate;
import entities.Entity;

public abstract class Plant extends Entity {

	private int cost;
	private int coolDown;
	private int damage;
	private String name;
	private int health; // in number of turns
	private Coordinate position;
	
	public Coordinate getPosition() {
		return position;
	}

	public void setPosition(Coordinate position) {
		this.position = position;
	}

	public Plant(String name, int damage, int coolDown, int cost, int health) {
		super(health, name, EntityType.PLANT);
		this.name = name;
		this.damage = damage;
		this.coolDown = coolDown;
		this.cost = cost;
		this.health = health;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public int getCoolDown() {
		return coolDown;
	}

	public void setCoolDown(int coolDown) {
		this.coolDown = coolDown;
	}

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}
}
