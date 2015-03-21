package boards;

import gridlock.Block;
import gridlock.Square;

import java.util.ArrayList;
import java.util.List;

public class BoardGenerator {

	public static Board getOneBlockBoard() {
		int[][] board = new int[6][6];
		board[3][1] = 1;
		board[3][0] = 1;
		List<Block> blocks = new ArrayList<Block>();
		Block b1 = new Block(1, new Square(3, 1), new Square(3, 0), true, 2);
		blocks.add(b1);
		Square finalSquare = new Square(3, 5);
		return new Board(board, blocks, finalSquare);
	}

	public static Board getTwoBlockBoard() {
		int[][] board = new int[6][6];
		board[3][1] = 1;
		board[3][0] = 1;
		board[3][5] = 2;
		board[2][5] = 2;
		List<Block> blocks = new ArrayList<Block>();
		Block b1 = new Block(1, new Square(3, 1), new Square(3, 0), true, 2);
		Block b2 = new Block(2, new Square(3, 5), new Square(2, 5), false, 2);
		blocks.add(b1);
		blocks.add(b2);
		Square finalSquare = new Square(3, 5);
		return new Board(board, blocks, finalSquare);
	}

	public static Board getSevenBlockBoard() {
		int[][] board = new int[6][6];
		board[2][1] = 1;
		board[2][2] = 1;
		board[3][2] = 2;
		board[4][2] = 2;
		board[0][0] = 3;
		board[1][0] = 3;
		board[2][0] = 3;
		board[0][3] = 4;
		board[1][3] = 4;
		board[2][3] = 4;
		board[3][3] = 5;
		board[3][4] = 5;
		board[3][5] = 5;
		board[5][2] = 6;
		board[5][3] = 6;
		board[5][4] = 6;
		board[4][5] = 7;
		board[5][5] = 7;

		List<Block> blocks = new ArrayList<Block>();

		Block b1 = new Block(1, new Square(2, 2), new Square(2, 1), true, 2);
		Block b2 = new Block(2, new Square(3, 2), new Square(4, 2), false, 2);
		Block b3 = new Block(3, new Square(0, 0), new Square(2, 0), false, 3);
		Block b4 = new Block(4, new Square(0, 3), new Square(2, 3), false, 3);
		Block b5 = new Block(5, new Square(3, 5), new Square(3, 3), true, 3);
		Block b6 = new Block(6, new Square(5, 4), new Square(5, 2), true, 3);
		Block b7 = new Block(7, new Square(4, 5), new Square(5, 5), false, 2);

		blocks.add(b1);
		blocks.add(b2);
		blocks.add(b3);
		blocks.add(b4);
		blocks.add(b5);
		blocks.add(b6);
		blocks.add(b7);

		Square finalSquare = new Square(2, 5);
		return new Board(board, blocks, finalSquare);
	}
}
