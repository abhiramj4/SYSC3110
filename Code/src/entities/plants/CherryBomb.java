package entities.plants;

import board.Coordinate;
import controller.Game;
import entities.plants.Plant;

/**
 * @author Everett Soldaat
 *
 */
public class CherryBomb extends Plant {

	private final static String NAME = "CBMB";
	private final static int DAMAGE = 50;
	private final static int COOLDOWN = 3;
	private final static int COST = 150;
	private final static int HEALTH = 1;
	private final static String IMAGEPATH = "resources/images/plants/cherrybomb.png";

	/**
	 * Constructor of cherry bomb
	 */
	public CherryBomb() {
		super(NAME, DAMAGE, COOLDOWN, COST, HEALTH, IMAGEPATH);
		// TODO Auto-generated constructor stub
	}

	/**
	 * String representation of this
	 */
	public String toString() {
		return this.getName();
	}
	
	public void attack(Game g, Coordinate c) {
		if (g.getGameboard().getSquare(c).isEmpty()) {

		} else if (g.getGameboard().getSquare(c).getEntity().getClass().getSuperclass().getName().toLowerCase()
				.contains("zombie")) {
			int orighealth = g.getGameboard().getSquare(c).getEntity().getHealth();
			
			if ((orighealth - getDamage()) < 0) {
				g.getGameboard().removeEntity(g, c);
			} else {
				g.getGameboard().getSquare(c).getEntity().setHealth(orighealth - getDamage());
			}
		}
	}

	/**
	 * Update method for cherry bomb
	 * 
	 * 
	 */
	@Override
	public void update(Game g, String type) {
		
		Coordinate temp = new Coordinate(getPosition().getX() + 1, getPosition().getY());	
		attack(g, temp);	
		temp = new Coordinate(getPosition().getX() - 1, getPosition().getY());
		attack(g, temp);	
		temp = new Coordinate(getPosition().getX(), getPosition().getY() + 1);
		attack(g, temp);
		temp = new Coordinate(getPosition().getX(), getPosition().getY() - 1);
		attack(g, temp);
		temp = new Coordinate(getPosition().getX() + 1, getPosition().getY() + 1);
		attack(g, temp);
		temp = new Coordinate(getPosition().getX() + 1, getPosition().getY() - 1);
		attack(g, temp);
		temp = new Coordinate(getPosition().getX() - 1, getPosition().getY() - 1);
		attack(g, temp);
		temp = new Coordinate(getPosition().getX() - 1, getPosition().getY() - 1);
		attack(g, temp);
		
	}
}