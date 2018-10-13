package entities.zombies;

import board.Coordinate;

/**
 * @author Liam Murphy
 *
 */
public class BaseZombie extends Zombie{
	private final static int HEALTH = 100;
	private final static String NAME = "Base Zombie";
	private final static int DAMAGE = 10;
	private final static int MOVEMENT = 1;
	
	/**
	 * @param coordinate
	 */
	public BaseZombie() {
		super(HEALTH, NAME, DAMAGE);
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return NAME;
	}
}
