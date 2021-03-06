package testing;

import controller.Level;
import junit.framework.TestCase;
import org.junit.Test;

public class LevelTest extends TestCase {
	private Level lv;
	
	public LevelTest() {}
	
	
	protected void setUp() {
		this.lv = new Level(1);
	}
	
	@Test
	public void testLevel() {
		assertEquals(lv.getLevelNum(), 1);
		assertEquals(lv.getNumZombies(), 5);
		assertEquals(lv.getRounds(), 10);
		
		String[] plants = lv.getPlants();
		assertEquals(plants.length, 2);
		assertEquals(plants[0], "Sunflower");
		assertEquals(plants[1], "Peashooter");
		
		String[] zombs = lv.getZombies();
		assertEquals(zombs.length, 1);
		assertEquals(zombs[0], "BaseZombie");
	}
	
	@Test
	public void TestIllegalLevel() {
		try {
			Level badLv = new Level(-1);
		}
		catch(IllegalArgumentException e) {
			System.out.print(e);
		}
	}
	
	public static void main(String[] args) {
		junit.textui.TestRunner.run(LevelTest.class);
	}
		
}
