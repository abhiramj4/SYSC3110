package entities.plants;

import java.util.Observable;
import java.util.Observer;

import entities.Entity;

public abstract class Plant extends Entity implements Observer {

	private int cost;
	private int coolDown;
	private int damage;
	private String name;
	private int health; // in number of turns

	/**
	 * Constructor for class Plant
	 * @param name of the plant
	 * @param damage this plant deals (if it does)
	 * @param coolDown before this plant can be planted again
	 * @param cost of this plant in sun
	 * @param health of this plant
	 */
	public Plant(String name, int damage, int coolDown, int cost, int health) {
		super(health, name);
		this.name = name;
		this.damage = damage;
		this.coolDown = coolDown;
		this.cost = cost;
		this.health = health;
	}

	/**
	 * Get the cost of this plant 
	 * @return the cost of this plant
	 */
	public int getCost() {
		return cost;
	}

	/**
	 * Set the cost of this plant
	 * @param cost of this plant in sun to be set
	 */
	public void setCost(int cost) {
		this.cost = cost;
	}

	/**
	 * Get the cooldown of this plant
	 * @return the cooldown
	 */
	public int getCoolDown() {
		return coolDown;
	}

	/**
	 * Set the cooldown of this plant
	 * @param coolDown to be set
	 */
	public void setCoolDown(int coolDown) {
		this.coolDown = coolDown;
	}

	/**
	 * Get the damage this plant deals (if it does)
	 * @return the damage
	 */
	public int getDamage() {
		return damage;
	}

	/**
	 * Set the damage this plant deals 
	 * @param damage this plant deals
	 */
	public void setDamage(int damage) {
		this.damage = damage;
	}

	/**
	 * Get the name of this plant
	 * @return the name of this plant
	 */
	public String getName() {
		return name;
	}

	/**
	 * Set the name of this plant
	 * @param name of this plant
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Get the health of this plant
	 * @return the health of this plant
	 */
	public int getHealth() {
		return health;
	}

	/**
	 * Set the health of this plant
	 * @param health of this plant
	 */
	public void setHealth(int health) {
		this.health = health;
	}
}
