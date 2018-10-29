package entities.plants;

import java.util.Observable;

import controller.Game;

public class Sunflower extends Plant {

	private final static String NAME = "SunFlower";
	private final static int DAMAGE = 0;
	private final static int COOLDOWN = 2;
	private final static int COST = 50;
	private final static int SUNFREQ = 3;
	private final static int SUN = 25;
	private final static int HEALTH = 100;
	private Integer tick;

	public Sunflower() {
		super(NAME, DAMAGE, COOLDOWN, COST, HEALTH);
	}

	public String toString() {
		return NAME;
	}

	@Override
	public void update(Game g, String type) {
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
