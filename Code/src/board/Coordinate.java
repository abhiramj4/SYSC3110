package board;

public class Coordinate {
	private int x;
	private int y;

	public Coordinate(int x, int y) throws IndexOutOfBoundsException {
		if ((x < 0) || (x > 9))
			throw new IndexOutOfBoundsException("x must be between 0 and 9,inclusive");
		if ((y < 0) || (y > 4))
			throw new IndexOutOfBoundsException("y must be between 0 and 4,inclusive");

		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
}
