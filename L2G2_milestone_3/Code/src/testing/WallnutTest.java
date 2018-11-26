package testing;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import board.Coordinate;
import entities.Entity.EntityType;
import entities.plants.Wallnut;

public class WallnutTest {
	private Wallnut w;
	private Coordinate c;
	
	public WallnutTest() {
	}
	
	protected void setUp() {
		this.w = new Wallnut();
		this.c = new Coordinate(0,0);
		w.setPosition(c);
	}
	
	@Test
	public void testWallnut() {
		assertEquals(w.getName(), "WALL");
		w.setName("wallnut");
		assertEquals(w.getName(), "wallnut");
		
		assertEquals(w.getCost(), 50);
		w.setCost(100);
		assertEquals(w.getCost(), 100);
		
		assertEquals(w.getCoolDown(), 1);
		w.setCoolDown(2);
		assertEquals(w.getCoolDown(), 2);
		
		assertEquals(w.getDamage(), 0);
		w.setDamage(5);
		assertEquals(w.getDamage(), 5);
		
		assertEquals(w.getPosition(), c);
		
		assertEquals(EntityType.PLANT, w.getEntityType());
	}
	
	public static void main(String[] args) {
		junit.textui.TestRunner.run(SunflowerTest.class);
	}

}
