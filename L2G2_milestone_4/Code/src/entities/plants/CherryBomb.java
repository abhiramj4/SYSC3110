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
		
		Coordinate curr = new Coordinate(getPosition().getX(), getPosition().getY());
		int i = curr.getX() - 1;
		if(i < 0) {
			i = 0;
		}
		int j = curr.getY() - 1;
		if(j < 0) {
			j = 0;
		}
		for(; (i <= 4 &&  i <= curr.getX() + 1); i++ ) {
			for(; (j<=4 && j <= curr.getY() + 1);j++) {
				attack(g, new Coordinate(i, j));
			}
		}
		/* Remove the Cherry Bomb after is has exploded */
		g.getGameboard().removeEntity(g, curr);

	}
}