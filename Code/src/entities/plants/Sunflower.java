
public class Sunflower extends Plant{

<<<<<<< HEAD
	private int sunPerTurn;
	
	public Sunflower(String name, int damage, int coolDown, int cost, int sun, int health) {
		super(name, damage, coolDown, cost, health);
=======
	private Integer sunPerTurn;
	private final static String NAME = "SunFlower";
	private final static int DAMAGE = 0;
	private final static int COOLDOWN = 3;
	private final static int COST = 10;
	private final static int SUN = 10;
	private final static int HEALTH = 3;
	
	Sunflower(Coordinate coordinate) {
		super(NAME, DAMAGE, COOLDOWN, COST, HEALTH);
>>>>>>> origin/Abhi
		// TODO Auto-generated constructor stub
		this.sunPerTurn = SUN;
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
