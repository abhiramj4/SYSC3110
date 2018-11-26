package testing;

import controller.Game;

import junit.framework.TestCase;
import board.Coordinate;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * 
 * @author Everett Soldaat
 *
 */

public class CoordinateTest extends TestCase{
	private Coordinate c;
	private int x1;
	private int y1;
	private int x2;
	private int y2;
	private int x3;
	private int y3;
	
	public CoordinateTest() {
	}
	
	protected void setUp() {
		x1 = 3;
		x2 = 8;
		y1 = 1;
		y2 = 2;
		x3 = 10;
		y3 = 10;
		
		c = new Coordinate(x1,y1);
	}
	
	@Test
	public void testCoordinate() {
		assertEquals(c.getX(),x1);
		
		assertEquals(c.getY(),y1);
		c.setX(x2);
		assertEquals(c.getX(),x2);
		c.setY(y2);;
		assertEquals(c.getY(),y2);
		
	}
	
	@Test(expected=IndexOutOfBoundsException.class)
	public void testOutofBounds() {
		c = new Coordinate(x3,y3);
	}

	public static void main(String[] args) {
		junit.textui.TestRunner.run(CoordinateTest.class);
	}
}
