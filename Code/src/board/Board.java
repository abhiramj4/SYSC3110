package board;

import entities.Entity;
import entities.plants.Sunflower;
import entities.zombies.BaseZombie;
import enumerations.SquareType;

public class Board {
	private Square board[][];
	private static final int length = 10;
	private static final int height = 5;

	public Board() {
		this.board = new Square[height][length];
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < length - 1; j++) {
				board[i][j] = new Square(new Coordinate(i, j), SquareType.LAWN);
			}
		}

		for (int i = 0; i < height; i++) {
			board[i][length - 1] = new Square(new Coordinate(i, length - 1), SquareType.SPAWN);
		}
	}

	public void addEntity(Entity entity, Coordinate coordinate) {
		board[coordinate.getX()][coordinate.getY()].setEntity(entity);
	}

	public Square[][] getBoard() {
		return board;
	}

	public void setBoard(Square[][] board) {
		this.board = board;
	}

	@Override
	public String toString() {
		String result = "";
		for (int i = height - 1; i >= 0; i--) {
			result += "{";
			for (int j = 0; j < length; j++) {
				if (j == length - 1) {
					result += this.board[i][j].toString();
				} else {
					result += this.board[i][j].toString() + ", ";
				}
			}
			result += "}\n";
		}
		return result;
	}
}
