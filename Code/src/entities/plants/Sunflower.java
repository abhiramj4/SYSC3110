package entities.plants;

public class Sunflower extends Plant{

	private Integer sunPerTurn;
	
	Sunflower(String name, Integer damage, Integer coolDown, Integer cost, Integer sun, Integer health) {
		super(name, damage, coolDown, cost, health);
		// TODO Auto-generated constructor stub
		this.sunPerTurn = sun;
	}

	@Override
	public void setDamage(Integer damage) {
		// TODO Auto-generated method stub
		this.damage = 0;
		
	}
	
	public String toString() {
		
		return (this.getName() + "Sun flower");
	}
	
	
}
