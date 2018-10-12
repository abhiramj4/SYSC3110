package entities.plants;
public class PeaShooter extends Plant{

	PeaShooter(String name, Integer damage, Integer coolDown, Integer cost, Integer health) {
		super(name, damage, coolDown, cost, health);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void setDamage(Integer damage) {
		// TODO Auto-generated method stub
		//basic damage algorithm
		this.damage = damage;
	}
	
	public String toString() {
		
		return (this.getName() + "pea shooter");
	}

}
