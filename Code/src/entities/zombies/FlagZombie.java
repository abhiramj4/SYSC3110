package entities.zombies;

import board.Coordinate;
import controller.Game;

/**
 * 
 * @author Everett
 *
 */

public class FlagZombie extends Zombie {

	private final static int HEALTH = 4;
	private final static String NAME = "FLZB";
	private final static int DAMAGE = 1;
	private final static int MOVEMENT = 2;
	private final static String IMAGEPATH = "resources/images/zombies/zombie_tutorial_flag.png";

	/**
	 * Flag Zombie Constructor
	 */
	public FlagZombie() {
		super(HEALTH, NAME, DAMAGE, IMAGEPATH);
	}

	/**
	 * String representation of the Flag Zombie
	 */
	@Override
	public String toString() {
		return NAME;
	}

	/**
	 * Update Method for the Flag Zombie
	 * 
	 * Every tick the zombie moves forward if there is room to move, or attacks a
	 * plant.
	 */
	@Override
	public void update(Game g, String type) {
		if (type == "TICK") {
			Coordinate curr = this.getPosition();
			Coordinate toCheck;
			if (curr.getX() == 0) {
				toCheck = curr;
			} else {
				toCheck = new Coordinate(curr.getX() - 1, curr.getY());
			}

			if (g.getGameboard().getSquare(toCheck).isEmpty() && (curr.getX() != 0)) {
				g.getGameboard().move(curr, new Coordinate(curr.getX() - 1, curr.getY()));
			}

			if (g.getGameboard().getSquare(toCheck).getEntity().getClass().getSuperclass().getName().toLowerCase()
					.contains("plant")) {
				System.out.println("attack");
				int orighealth = g.getGameboard().getSquare(toCheck).getEntity().getHealth();
				System.out.println(orighealth);
				if ((orighealth - getDamage()) < 0) {
					g.getGameboard().removeEntity(g, toCheck);
				} else {
					g.getGameboard().getSquare(toCheck).getEntity().setHealth(orighealth - getDamage());
				}
			}
		}
	}
}