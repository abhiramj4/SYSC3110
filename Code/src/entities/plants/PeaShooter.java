package entities.plants;

import entities.plants.Plant;

public class PeaShooter extends Plant{
	
	private final static String NAME = "peaShooter";
	private final static int DAMAGE = 1;
	private final static int COOLDOWN = 2;
	private final static int COST = 100;
	private final static int HEALTH = 3;
	
	public PeaShooter() {
		super(NAME, DAMAGE, COOLDOWN, COST, HEALTH);
		// TODO Auto-generated constructor stub
	}
	
	public String toString() {
		return (this.getName() + "pea shooter");
	}

	@Override
	public void setDamage(int damage) {
		// TODO Auto-generated method stub
		//for more complex damage algorithms
	}
}
