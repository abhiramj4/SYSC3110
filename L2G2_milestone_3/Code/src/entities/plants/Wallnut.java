package entities.plants;

import board.Coordinate;
import controller.Game;
import entities.plants.Plant;

/**
 * @author Everett Soldaat
 *
 */
public class Wallnut extends Plant {

	private final static String NAME = "WALL";
	private final static int DAMAGE = 0;
	private final static int COOLDOWN = 1;
	private final static int COST = 50;
	private final static int HEALTH = 15;
	private final static String IMAGEPATH = "resources/images/plants/wallnut.png";

	/**
	 * Constructor of wallnut
	 */
	public Wallnut() {
		super(NAME, DAMAGE, COOLDOWN, COST, HEALTH, IMAGEPATH);
		// TODO Auto-generated constructor stub
	}

	/**
	 * String representation of this
	 */
	public String toString() {
		return this.getName();
	}

	/**
	 * Update method for wallnut
	 * 
	 * 
	 */
	@Override
	public void update(Game g, String type) {
		
	}
}

