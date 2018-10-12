package entities.zombies;

import board.Coordinate;

/**
 * @author Liam Murphy
 *
 */
public class BaseZombie extends Zombie{
	private final static int health = 100;
	private final static String name = "Base_Zombie";
	private final static int damage = 10;
	private final static int movement = 1;
	
	/**
	 * @param coordinate
	 */
	public BaseZombie(Coordinate coordinate) {
		super(health, name, damage, coordinate);
	}
	
	/* (non-Javadoc)
	 * @see entities.zombies.Zombie#move()
	 */
	public void move() {
		Coordinate c =  super.getCoordinate();
		//decrement the x coordinate by movement value
		//super.setCoordinate();  set new coordinate
		
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Z/Base";
	}
}
