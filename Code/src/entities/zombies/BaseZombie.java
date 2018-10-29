package entities.zombies;

import board.Coordinate;
import controller.Game;

/**
 * @author Liam Murphy
 *
 */
public class BaseZombie extends Zombie {
	private final static int HEALTH = 100;
	private final static String NAME = "BZMB";
	private final static int DAMAGE = 50;
	private final static int MOVEMENT = 1;

	public BaseZombie() {
		super(HEALTH, NAME, DAMAGE);
	}

	@Override
	public String toString() {
		return NAME;
	}

	@Override
	public void update(Game g, String type) {
		if (type == "TICK") {
			Coordinate curr = this.getPosition();
			Coordinate toCheck = new Coordinate(curr.getX() -1, curr.getY());
			
			if (g.getGameboard().getSquare(toCheck).isEmpty()) {
				if (g.getGameboard().getSquare(curr).getCoordinate().getX() == 0) {
					g.GameOver();
				} else {
					g.getGameboard().move(curr, new Coordinate(curr.getX() - 1, curr.getY()));
				}
			} else if (g.getGameboard().getSquare(toCheck).getEntity().getEntityType() == EntityType.PLANT) {
				int orighealth = g.getGameboard().getSquare(toCheck).getEntity().getHealth();
				int damage = ((Zombie) g.getGameboard().getSquare(curr).getEntity()).getDamage();
				if ((orighealth - damage) < 0) {
					g.getGameboard().removeEntity(g, curr);
				} else {
					g.getGameboard().getSquare(toCheck).getEntity().setHealth(orighealth - damage);
				}
			}
		}
	}
}
