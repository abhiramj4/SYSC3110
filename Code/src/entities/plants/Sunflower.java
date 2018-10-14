package entities.plants;

public class Sunflower extends Plant {

	private int sunPerTurn;
	private final static String NAME = "SunFlower";
	private final static int DAMAGE = 0;
	private final static int COOLDOWN = 3;
	private final static int COST = 10;
	private final static int SUN = 10;
	private final static int HEALTH = 3;

	public Sunflower() {
		super(NAME, DAMAGE, COOLDOWN, COST, HEALTH);
		this.sunPerTurn = SUN;
	}

	@Override
	public void setDamage(int damage) {
		// TODO Auto-generated method stub
		this.damage = 0;
	}

	public String toString() {
		return NAME;
	}
}
