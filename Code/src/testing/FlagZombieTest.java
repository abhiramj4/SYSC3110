package testing;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

import board.Coordinate;
import entities.Entity.EntityType;
import entities.zombies.FlagZombie;

public class FlagZombieTest {
	private FlagZombie z;
	private Coordinate c;
	private Coordinate c2;

	public FlagZombieTest() {
		
	}
	
	protected void setUp() {
		this.z = new FlagZombie();
		this.c = new Coordinate(0,0);
		this.c2 = new Coordinate(1,1);
		z.setPosition(c);
	}
	
	@Test
	public void testFlagZombie() {
		
		assertEquals(this.z.getName(), "FLZB");
		z.setName("TEST");
		assertEquals(z.getName(), "TEST");	

		assertEquals(z.getHealth(), 4);
		z.setHealth(5);
		assertEquals(z.getHealth(), 5);
		
		assertEquals(z.getDamage(), 1);
		z.setDamage(5);
		assertEquals(z.getDamage(), 5);
		
		assertEquals(z.getPosition(), c);
		z.setPosition(c2);
		assertNotEquals(z.getPosition(), c);
		assertEquals(z.getPosition(), c2);
		
		assertEquals(EntityType.ZOMBIE, z.getEntityType());
	}
	
	public static void main(String[] args) {
		junit.textui.TestRunner.run(SunflowerTest.class);
	}
}
