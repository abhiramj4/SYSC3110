package entities.plants;

import java.util.Observable;

import controller.Game;
import entities.plants.Plant;

public class PeaShooter extends Plant {

	private final static String NAME = "PEAS";
	private final static int DAMAGE = 1;
	private final static int COOLDOWN = 2;
	private final static int COST = 100;
	private final static int HEALTH = 3;

	public PeaShooter() {
		super(NAME, DAMAGE, COOLDOWN, COST, HEALTH);
		// TODO Auto-generated constructor stub
	}

	public String toString() {
		return this.getName();
	}

	@Override
	public void update(Game g, String type) {
		// TODO Auto-generated method stub
		
	}
}
