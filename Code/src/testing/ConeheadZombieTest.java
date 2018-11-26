package testing;

import entities.Entity.EntityType;
import entities.zombies.BaseZombie;
import entities.zombies.ConeheadZombie;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

import board.Coordinate;

public class ConeheadZombieTest {
	private ConeheadZombie z;
	private Coordinate c;
	private Coordinate c2;
	
	public ConeheadZombieTest() {
		
	}

	protected void setUp() {
		this.z = new ConeheadZombie();
		this.c = new Coordinate(0,0);
		this.c2 = new Coordinate(1,1);
		z.setPosition(c);
	}
	
	@Test
	public void testConeheadZombie() {
		
		assertEquals(this.z.getName(), "CHZB");
		z.setName("TEST");
		assertEquals(z.getName(), "TEST");	

		assertEquals(z.getHealth(), 8);
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
