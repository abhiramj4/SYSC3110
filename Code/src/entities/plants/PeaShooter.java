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

	public PeaShooter() {
		super(NAME, DAMAGE, COOLDOWN, COST, HEALTH);
		// TODO Auto-generated constructor stub 
	}

	public String toString() {
		return this.getName();
	}

	@Override
	public void update(Game g, String type) {
		for (int i = getPosition().getY() + 1; i < 9; i++) {
			if (g.getGameboard().getSquare(getPosition()).getEntity().getClass().getSuperclass().getName().toLowerCase().contains("zombie")) {
				Coordinate toCheck = new Coordinate(getPosition().getX(), i);
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
