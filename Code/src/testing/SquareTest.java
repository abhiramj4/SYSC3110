package testing;

import controller.Game;
import entities.zombies.BaseZombie;
import enumerations.SquareType;
import junit.framework.TestCase;
import board.Coordinate;
import board.Square;

import org.junit.Test;
import static org.junit.Assert.*;

public class SquareTest extends TestCase{
	
	Coordinate c;
	Coordinate c2;
	BaseZombie zombie;
	Square square;

	public SquareTest() {
		
	}
	
	protected void setUp() {
		this.zombie = new BaseZombie();
		this.c = new Coordinate(1,1);
		this.c2 = new Coordinate(2,2);
		this.square = new Square(c,SquareType.LAWN);
	}
	
	@Test
	public void testSquare() {
		assertTrue(square.isEmpty());
		square.setEntity(zombie);
		assertEquals(square.getEntity(),zombie);
		square.setCoordinate(c2);
		assertEquals(square.getCoordinate(), c2);
		square.removeEntity(c2);
		assertTrue(square.isEmpty());
		square.setType(SquareType.SPAWN);
		assertEquals(square.getType(),SquareType.SPAWN);
		square.setLawnMower();
		assertTrue(square.getLawnMower());
	}
	
	public static void main(String[] args) {
		junit.textui.TestRunner.run(SquareTest.class);
	}
}
