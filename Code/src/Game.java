import board.*;
import entities.plants.*;
import entities.zombies.*;


public class Game {
	
	private int currlevel;

	public static void main(String args[]) {
		Board b = new Board();
		System.out.println(b.toString());
		b.addEntity(new Sunflower(), new Coordinate(2, 0));
		System.out.println(b.toString());
		b.addEntity(new BaseZombie(), new Coordinate(3, 9));
		System.out.println(b.toString());
	}
}
