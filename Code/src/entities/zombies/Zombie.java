package entities.zombies;

import board.Coordinate;


/**
 * @author Liam Murphy
 *
 */
abstract public class Zombie {
	private int damage;
	private int health;
	private String name;
	private Coordinate coordinate;
	
	public Zombie(int health, String name, int damage, Coordinate coordinate) {
		this.health = health;
		this.name = name;
		this.damage = damage;
		this.coordinate = coordinate;
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
	/**
	 * @return
	 */
	public Coordinate getCoordinate() {
		return coordinate;
	}

	/**
	 * @param coordinate
	 */
	public void setCoordinate(Coordinate coordinate) {
		this.coordinate = coordinate;
	}
	
	public abstract void move();
	
}
