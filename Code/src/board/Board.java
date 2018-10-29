package board;

import controller.Game;
import entities.Entity;
import enumerations.SquareType;

public class Board {
	private Square board[][];
	private static final int length = 10;
	private static final int height = 5;

	/**
	 * Constructor for a board, sets the tiles to lawn, except where there is a
	 * spawn for a zombie
	 */
	public Board() {
		this.board = new Square[length][height];
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < length; j++) {
				board[j][i] = new Square(new Coordinate(j, i), SquareType.LAWN);
			}
		}

		for (int i = 0; i < height; i++) {
			board[length - 1][i] = new Square(new Coordinate(length - 1, i), SquareType.SPAWN);
		}
	}

	/**
	 * Add an entity to this board
	 * 
	 * @param entity     to be added
	 * @param coordinate where the entity needs to be
	 */
	public void addEntity(Entity entity, Coordinate coordinate) {
		board[coordinate.getX()][coordinate.getY()].setEntity(entity);
	}

	public void removeEntity(Game g, Coordinate coordinate) {
		Entity toRemove = g.getGameboard().getSquare(coordinate).getEntity();
		g.getGameListeners().remove(toRemove);
		g.getGameboard().getSquare(coordinate).setEntity(null);
	}

	/**
	 * Return the board as a 2D array of squares
	 * 
	 * @return the board
	 */
	public Square[][] getBoard() {
		return board;
	}

	/**
	 * Set the board given a 2D array board - replaces the old board
	 * 
	 * @param board to be set
	 */
	public void setBoard(Square[][] board) {
		this.board = board;
	}

	/**
	 * Get the square specific by a coordinate
	 * 
	 * @param c is the coordinate at which the square should be retrieved
	 * @return the square with the entity on it
	 */
	public Square getSquare(Coordinate c) {
		return (board[c.getX()][c.getY()]);
	}

	/**
	 * Move the entity on the source coordinate to the destination coordinate
	 * 
	 * @param src  of the entity
	 * @param dest where the entity needs to go
	 * @return true if coordinate was mooved succesfully
	 */
	public boolean move(Coordinate src, Coordinate dest) {
		Square srcsqr = getSquare(src);
		Square destsqr = getSquare(dest);
		if (srcsqr.isEmpty())
			return false;
		destsqr.setEntity(srcsqr.getEntity());
		srcsqr.setEntity(null);
		return true;
	}

	/**
	 * Returns a string version of the board
	 */
	@Override
	public String toString() {
		String result = "";
		for (int i = height - 1; i >= 0; i--) {
			result += "{";
			for (int j = 0; j < length; j++) {
				if (j == length - 1) {
					result += this.board[j][i].toString();
				} else {
					result += this.board[j][i].toString() + ", ";
				}
			}
			result += "}\n";
		}
		return result;
	}
}
