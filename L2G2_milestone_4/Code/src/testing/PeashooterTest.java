package testing;

import entities.plants.PeaShooter;
import entities.Entity;
import entities.Entity.EntityType;
import junit.framework.TestCase;
import board.Coordinate;
import org.junit.Test;
import static org.junit.Assert.*;


public class PeashooterTest extends TestCase {
	private PeaShooter p = null;
	private Coordinate c = null;
	private Coordinate c2 = null;
	
	public PeashooterTest() {
	}
	
	protected void setUp() {
		this.p = new PeaShooter();
		this.c = new Coordinate(0,0);
		this.c2 = new Coordinate(1,1);
		p.setPosition(c);
		
	}
	
	@Test
	public void testPeashooter() {
		assertEquals(p.getName(), "PEAS");
		p.setName("sunflower");
		assertEquals(p.getName(), "sunflower");
		
		assertEquals(p.getCost(), 100);
		p.setCost(50);
		assertEquals(p.getCost(), 50);
		
		assertEquals(p.getCoolDown(), 2);
		p.setCoolDown(1);
		assertEquals(p.getCoolDown(), 1);
		
		assertEquals(p.getDamage(), 1);
		p.setDamage(5);
		assertEquals(p.getDamage(), 5);
		
		assertEquals(p.getPosition(), c);
		p.setPosition(c2);
		assertNotEquals(p.getPosition(), c);
		assertEquals(p.getPosition(), c2);
		
		assertEquals(EntityType.PLANT, p.getEntityType());
	}
	
	
	public static void main(String[] args) {
		junit.textui.TestRunner.run(SunflowerTest.class);
	}

}
