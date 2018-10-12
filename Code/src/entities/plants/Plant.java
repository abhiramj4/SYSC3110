package entities.plants;

public abstract class Plant {

	protected int cost;
	protected int coolDown;
	protected int damage;
	protected String  name;
	//protected Coordinate coordinate;
	protected int health; //in number of turns
	
	Plant(String name){
		
	}
	
	Plant (String name, int damage, int coolDown, int cost, int health){
		this.name = name;
		this.damage = damage;
		this.coolDown = coolDown;
		this.cost = cost;
		this.health = health;
	}

	/**
	 * @return the health
	 */
	public int getHealth() {
		return health;
	}

	/**
	 * @param health the health to set
	 */
	public void setHealth(int health) {
		this.health = health;
	}

	/**
	 * @return the cost
	 */
	public int getCost() {
		return cost;
	}

	/**
	 * @param cost the cost to set
	 */
	public void setCost(int cost) {
		this.cost = cost;
	}

	/**
	 * @return the coolDown
	 */
	public int getCoolDown() {
		return coolDown;
	}

	/**
	 * @param coolDown the coolDown to set
	 */
	public void setCoolDown(int coolDown) {
		this.coolDown = coolDown;
	}

	/**
	 * @return the damage
	 */
	public int getDamage() {
		return damage;
	}

	/**
	 * @param damage the damage to set
	 */
	public abstract void setDamage(int damage);
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the coordinate
	 */
	//public Coordinate getCoordinate() {
	//	return coordinate;
	//}

	/**
	 * @param coordinate the coordinate to set
	 */
	//public void setCoordinate(Coordinate coordinate) {
	//	this.coordinate = coordinate;
	//}	
	
}
