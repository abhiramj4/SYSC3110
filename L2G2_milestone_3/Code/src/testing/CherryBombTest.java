package testing;

import entities.plants.CherryBomb;
import entities.Entity;
import entities.Entity.EntityType;
import junit.framework.TestCase;
import board.Coordinate;
import org.junit.Test;



public class CherryBombTest extends TestCase {
	private CherryBomb f = null;
	private Coordinate c = null;
	
	public CherryBombTest() {
	}
	
	protected void setUp() {
		this.f = new CherryBomb();
		this.c = new Coordinate(0,0);
		f.setPosition(c);
	}
	
	@Test
	public void testSunflower() {
		assertEquals(f.getName(), "CBMB");
		f.setName("cherrybomb");
		assertEquals(f.getName(), "cherrybomb");
		
		assertEquals(f.getCost(), 150);
		f.setCost(100);
		assertEquals(f.getCost(), 100);
		
		assertEquals(f.getCoolDown(), 3);
		f.setCoolDown(1);
		assertEquals(f.getCoolDown(), 1);
		
		assertEquals(f.getDamage(), 50);
		f.setDamage(5);
		assertEquals(f.getDamage(), 5);
		
		assertEquals(f.getPosition(), c);
		
		assertEquals(EntityType.PLANT, f.getEntityType());
	}
	
	
	public static void main(String[] args) {
		junit.textui.TestRunner.run(SunflowerTest.class);
	}

}



