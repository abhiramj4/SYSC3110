package board;

import controller.Game;
import entities.Entity;
import enumerations.SquareType;

public class Board {
	private Square board[][];
	private static final int length = 10;
	private static final int height = 5;

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

	public void addEntity(Entity entity, Coordinate coordinate) {
		board[coordinate.getX()][coordinate.getY()].setEntity(entity);
	}
	
	public void removeEntity(Game g, Coordinate coordinate) {
		Entity toRemove = g.getGameboard().getSquare(coordinate).getEntity();
		g.getGameListeners().remove(toRemove);
		g.getGameboard().getSquare(coordinate).setEntity(null);
	}

	public Square[][] getBoard() {
		return board;
	}

	public void setBoard(Square[][] board) {
		this.board = board;
	}
	
	public Square getSquare(Coordinate c) {
		return (board[c.getX()][c.getY()]);
    }
	
	public boolean move(Coordinate src, Coordinate dest) {
		Square srcsqr = getSquare(src);
		Square destsqr = getSquare(dest);
		if (srcsqr.isEmpty()) return false;
		destsqr.setEntity(srcsqr.getEntity());
		srcsqr.setEntity(null);
		return true;
	}

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
