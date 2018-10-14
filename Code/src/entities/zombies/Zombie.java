package entities.zombies;

import board.Coordinate;
import entities.Entity;

/**
 * @author Liam Murphy
 *
 */
public abstract class Zombie extends Entity {
	private int damage;
	private int health;
	private String name;

	public Zombie(int health, String name, int damage) {
		super(health, name);
		this.health = health;
		this.name = name;
		this.damage = damage;
	}

	/**
	 * @return
	 */
	public int getDamage() {
		return damage;
	}

	/**
	 * @param coordinate
	 */
	public void setDamage(int damage) {
		this.damage = damage;
	}

	/**
	 * @return
	 */
	public int getHealth() {
		return health;
	}

	/**
	 * @param coordinate
	 */
	public void setHealth(int health) {
		this.health = health;
	}

	/**
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param coordinate
	 */
	public void setName(String name) {
		this.name = name;
	}
}
