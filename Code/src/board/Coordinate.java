package board;

/**
 * @author Sai Vikranth Desu
 *
 */
public class Coordinate {
	private int x;
	private int y;

	/**
	 * Instantiate a Coordinate with an x and y position
	 * 
	 * @param x
	 * @param y
	 * @throws IndexOutOfBoundsException
	 */
	public Coordinate(int x, int y) throws IndexOutOfBoundsException {
		if ((x < 0) || (x > 9))
			throw new IndexOutOfBoundsException("x must be between 0 and 9,inclusive");
		if ((y < 0) || (y > 4))
			throw new IndexOutOfBoundsException("y must be between 0 and 4,inclusive");

		this.x = x;
		this.y = y;
	}

	/**
	 * Get the x of this coordinate
	 * 
	 * @return the x value of this coordinate
	 */
	public int getX() {
		return x;
	}

	/**
	 * Set the x of this coordinate
	 * 
	 * @param x value to be set of this coordinate
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * Get the y of this coordinate
	 * 
	 * @return the y value of this coordinate
	 */
	public int getY() {
		return y;
	}

	/**
	 * Set the y of this coordinate
	 * 
	 * @param y value to be set of this coordinate
	 */
	public void setY(int y) {
		this.y = y;
	}
}