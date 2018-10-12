package board;

public class Board {
	private Square board[][];
	private static final int length = 10;
	private static final int height = 5;
	
	public Board() {
		this.board = new Square[height][length];
	}
	
	@Override
	public String toString() {
		String result = "";
		for (int i = height; i > 0; i --) {
			result += "{";
			for (int j = 0; j < length; j++) {
				result += "0, ";
			}
			result += "}\n";
		}
		return result;
	}
	
	public Square[][] getBoard() {
		return board;
	}

	public void setBoard(Square[][] board) {
		this.board = board;
	}

	public static void main(String args[]) {
		Board b = new Board();
		System.out.println(b.toString());
	}

}
