package testing;

import entities.zombies.BaseZombie;
import entities.Entity.EntityType;
import junit.framework.TestCase;
import board.Coordinate;
import org.junit.Test;
import static org.junit.Assert.*;

public class BaseZombieTest extends TestCase{
	private BaseZombie z = null;
	private Coordinate c = null;
	private Coordinate c2 = null;
	
	public BaseZombieTest() {
	}
	
	protected void setUp() {
		this.z = new BaseZombie();
		this.c = new Coordinate(0,0);
		this.c2 = new Coordinate(1,1);
		z.setPosition(c);
	}
	
	@Test
	public void testBaseZombie() {
		
		assertEquals(this.z.getName(), "BZMB");
		z.setName("TEST");
		assertEquals(z.getName(), "TEST");	

		assertEquals(z.getHealth(), 3);
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
