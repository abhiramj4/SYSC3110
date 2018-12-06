package entities.plants;

import controller.Game;

/**
 * @author Everett Soldaat
 *
 */
public class Sunflower extends Plant {

	private final static String NAME = "FLWR";
	private final static int DAMAGE = 0;
	private final static int COOLDOWN = 2;
	private final static int COST = 50;
	private final static int SUNFREQ = 3;
	private final static int SUN = 25;
	private final static int HEALTH = 4;
	private Integer tick;
	private final static String IMAGEPATH = "resources/images/plants/sunflower.png";

	/**
	 * Constructor for sunflowers
	 */
	public Sunflower() {
		super(NAME, DAMAGE, COOLDOWN, COST, HEALTH, IMAGEPATH);
	}

	/**
	 * String representation of this
	 */
	public String toString() {
		return NAME;
	}

	/**
	 * Update method for sunflower
	 * 
	 * Gets sun every so often
	 */
	@Override
	public void update(Game g, String type) {
		System.out.println("yes");
		if (type == "TICK") {
			if (tick == null) {
				tick = new Integer(0);
			} else if (tick == 0) {
				tick++;
			} else if (tick == 1) {
				tick++;
			} else if (tick == 2) {
				g.setSun(g.getSun() + SUN);
				tick = 0;
			}
		}
	}
}
