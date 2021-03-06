package testing;

import entities.plants.Sunflower;
import entities.Entity;
import entities.Entity.EntityType;
import junit.framework.TestCase;
import board.Coordinate;
import org.junit.Test;



public class SunflowerTest extends TestCase {
	private Sunflower f = null;
	private Coordinate c = null;
	
	public SunflowerTest() {
	}
	
	protected void setUp() {
		this.f = new Sunflower();
		this.c = new Coordinate(0,0);
		f.setPosition(c);
	}
	
	@Test
	public void testSunflower() {
		assertEquals(f.getName(), "FLWR");
		f.setName("sunflower");
		assertEquals(f.getName(), "sunflower");
		
		assertEquals(f.getCost(), 50);
		f.setCost(100);
		assertEquals(f.getCost(), 100);
		
		assertEquals(f.getCoolDown(), 2);
		f.setCoolDown(1);
		assertEquals(f.getCoolDown(), 1);
		
		assertEquals(f.getDamage(), 0);
		f.setDamage(5);
		assertEquals(f.getDamage(), 5);
		
		assertEquals(f.getPosition(), c);
		
		assertEquals(EntityType.PLANT, f.getEntityType());
	}
	
	
	public static void main(String[] args) {
		junit.textui.TestRunner.run(SunflowerTest.class);
	}

}
