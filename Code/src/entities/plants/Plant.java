package entities.plants;

public abstract class Plant {

	protected Integer cost;
	protected Integer coolDown;
	protected Integer damage;
	protected String  name;
	//protected Coordinate coordinate;
	protected Integer health; //in number of turns
	
	Plant(String name){
		
	}
	
	Plant (String name, Integer damage, Integer coolDown, Integer cost, Integer health){
		this.name = name;
		this.damage = damage;
		this.coolDown = coolDown;
		this.cost = cost;
		this.health = health;
	}

	
	/**
	 * @return the health
	 */
	public Integer getHealth() {
		return health;
	}

	/**
	 * @param health the health to set
	 */
	public void setHealth(Integer health) {
		this.health = health;
	}

	/**
	 * @return the cost
	 */
	public Integer getCost() {
		return cost;
	}

	/**
	 * @param cost the cost to set
	 */
	public void setCost(Integer cost) {
		this.cost = cost;
	}

	/**
	 * @return the coolDown
	 */
	public Integer getCoolDown() {
		return coolDown;
	}

	/**
	 * @param coolDown the coolDown to set
	 */
	public void setCoolDown(Integer coolDown) {
		this.coolDown = coolDown;
	}

	/**
	 * @return the damage
	 */
	public Integer getDamage() {
		return damage;
	}

	/**
	 * @param damage the damage to set
	 */
	public abstract void setDamage(Integer damage);
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
