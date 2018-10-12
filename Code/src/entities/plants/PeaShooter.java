package entities.plants;

import entities.plants.Plant;

public class PeaShooter extends Plant{
	
	private final static String NAME = "peaShooter";
	private final static int DAMAGE = 1;
	private final static int COOLDOWN = 3;
	private final static int COST = 10;
	private final static int HEALTH = 3;
	
	PeaShooter(String name, Integer damage, Integer coolDown, Integer cost, Integer health) {
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
