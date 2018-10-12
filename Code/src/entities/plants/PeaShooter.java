package entities.plants;
public class PeaShooter extends Plant{

	PeaShooter(String name, int damage, int coolDown, int cost, int health) {
		super(name, damage, coolDown, cost, health);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void setDamage(int damage) {
		// TODO Auto-generated method stub
		//basic damage algorithm
		this.damage = damage;
	}
	
	public String toString() {
		
		return (this.getName() + "pea shooter");
	}

}
