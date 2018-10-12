package entities.plants;

public class Sunflower extends Plant{

	private int sunPerTurn;
	
	public Sunflower(String name, int damage, int coolDown, int cost, int sun, int health) {
		super(name, damage, coolDown, cost, health);
		// TODO Auto-generated constructor stub
		this.sunPerTurn = sun;
	}

	@Override
	public void setDamage(int damage) {
		// TODO Auto-generated method stub
		this.damage = 0;
		
	}
	
	public String toString() {
		
		return ("Sun flower");
	}
	
	
}
