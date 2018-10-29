package entities.zombies;

import java.util.Observer;

import board.Coordinate;
import entities.Entity;

/**
 * @author Liam Murphy
 *
 */
public abstract class Zombie extends Entity implements Observer {
	private int damage;
	private Coordinate position;
	private int health;
	private String name;

	/**
	 * Constructor of class Zombie
	 * @param health of the zombie
	 * @param name of the zombie
	 * @param damage this zombie deals
	 */
	public Zombie(int health, String name, int damage) {
		super(health, name);
		this.health = health;
		this.name = name;
		this.damage = damage;
	}

	/**
	 * get the position of this zombie
	 * @return the position of this zombie
	 */
	public Coordinate getPosition() {
		return position;
	}

	/**
	 * Set the position of this zombie
	 * @param position of this zombie
	 */
	public void setPosition(Coordinate position) {
		this.position = position;
	}

	/**
	 * get the damage this zombie deals
	 * @return the damage this zombie deals
	 */
	public int getDamage() {
		return damage;
	}

	/**
	 * Set the damage this zombie does
	 * @param damage this zombie does
	 */
	public void setDamage(int damage) {
		this.damage = damage;
	}

	/**
	 * Return the health of this zombie
	 * @return 
	 */
	public int getHealth() {
		return health;
	}

	/**
	 * set the health of this zombie
	 * @param set the health of the zombie
	 */
	public void setHealth(int health) {
		this.health = health;
	}

	/**
	 * get the name of the zombie
	 * @return the name of this zombie
	 */
	public String getName() {
		return name;
	}

	/**
	 * Set the name of this zombie
	 * @param name of this zombie to be set
	 */
	public void setName(String name) {
		this.name = name;
	}
}
