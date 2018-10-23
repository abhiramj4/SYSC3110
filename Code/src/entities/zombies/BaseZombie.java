package entities.zombies;

import java.util.Observable;

import board.Coordinate;
import controller.Game;

/**
 * @author Liam Murphy
 *
 */
public class BaseZombie extends Zombie {
	private final static int HEALTH = 100;
	private final static String NAME = "Base Zombie";
	private final static int DAMAGE = 10;
	private final static int MOVEMENT = 1;

	public BaseZombie() {
		super(HEALTH, NAME, DAMAGE);
	}

	@Override
	public String toString() {
		return NAME;
	}

	@Override
	public void update(Observable o, Object arg) {
		Coordinate curr = this.getPosition();
		((Game) o).getGameboard().move(curr, new Coordinate(curr.getX() - 1, curr.getY()));

	}
}
