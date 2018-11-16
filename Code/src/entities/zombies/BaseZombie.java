package entities.zombies;

import board.Coordinate;
import controller.Game;

/**
 * @author Liam Murphy
 *
 */
public class BaseZombie extends Zombie {
	private final static int HEALTH = 3;
	private final static String NAME = "BZMB";
	private final static int DAMAGE = 1;
	private final static int MOVEMENT = 1;
	private final static String IMAGEPATH = "resources/images/zombies/zombie_tutorial.png";

	/**
	 * Base zombie constructor
	 */
	public BaseZombie() {
		super(HEALTH, NAME, DAMAGE, IMAGEPATH);
	}

	/**
	 * String representation of this
	 */
	@Override
	public String toString() {
		return NAME;
	}

	/**
	 * Update method for the base zombie
	 * 
	 * On every tick the zombie either moves forward (if there is room to move) or attacks (if a plant is in front)
	 */
	@Override
	public void update(Game g, String type) {
		if (type == "TICK") {
			Coordinate curr = this.getPosition();
			Coordinate toCheck = new Coordinate(curr.getX() - 1, curr.getY());

			if (g.getGameboard().getSquare(toCheck).isEmpty() && !(getPosition().getX() == 0)) {
				System.out.println("move");
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
