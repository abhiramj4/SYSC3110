package entities.plants;

import board.Coordinate;
import controller.Game;
import entities.plants.Plant;

public class PeaShooter extends Plant {

	private final static String NAME = "PEAS";
	private final static int DAMAGE = 1;
	private final static int COOLDOWN = 2;
	private final static int COST = 100;
	private final static int HEALTH = 4;

	/**
	 * Constructor of peashooter
	 */
	public PeaShooter() {
		super(NAME, DAMAGE, COOLDOWN, COST, HEALTH);
		// TODO Auto-generated constructor stub
	}

	/**
	 * String representation of this
	 */
	public String toString() {
		return this.getName();
	}

	/**
	 * Update method for peashooter
	 */
	@Override
	public void update(Game g, String type) {
		for (int i = getPosition().getX() + 1; i < 9; i++) {
			Coordinate temp = new Coordinate(i, getPosition().getY());
			if (g.getGameboard().getSquare(temp).isEmpty()) {

			} else if (g.getGameboard().getSquare(temp).getEntity().getClass().getSuperclass().getName().toLowerCase()
					.contains("zombie")) {
				int orighealth = g.getGameboard().getSquare(temp).getEntity().getHealth();
				System.out.println(orighealth);
				if ((orighealth - getDamage()) < 0) {
					g.getGameboard().removeEntity(g, temp);
				} else {
					g.getGameboard().getSquare(temp).getEntity().setHealth(orighealth - getDamage());
				}
			}
		}
	}
}
