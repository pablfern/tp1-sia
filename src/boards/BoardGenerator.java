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

	public static Board getThreeBlockBoard() {
		int[][] board = new int[6][6];

		board[3][1] = 1;
		board[3][0] = 1;

		board[3][5] = 2;
		board[2][5] = 2;

		board[3][4] = 3;
		board[2][4] = 3;
		board[1][4] = 3;

		List<Block> blocks = new ArrayList<Block>();
		Block b1 = new Block(1, new Square(3, 1), new Square(3, 0), true, 2);
		Block b2 = new Block(2, new Square(3, 5), new Square(2, 5), false, 2);
		Block b3 = new Block(3, new Square(3, 4), new Square(1, 4), false, 3);
		blocks.add(b1);
		blocks.add(b2);
		blocks.add(b3);
		Square finalSquare = new Square(3, 5);
		return new Board(board, blocks, finalSquare);
	}

	public static Board getSixBlockBoard() {
		int[][] board = new int[6][6];
		
		board[2][1] = 1;
		board[2][2] = 1;
		
		board[3][1] = 2;
		board[3][2] = 2;
		
		board[4][1] = 3;
		board[5][1] = 3;
		
		board[5][2] = 4;
		board[5][3] = 4;
		
		board[2][3] = 5;
		board[3][3] = 5;
		board[4][3] = 5;
	
		board[3][5] = 6;
		board[4][5] = 6;
		board[5][5] = 6;
		
		List<Block> blocks = new ArrayList<Block>();

		Block b1 = new Block(1, new Square(2, 2), new Square(2, 1), true, 2);
		Block b2 = new Block(2, new Square(3, 2), new Square(3, 1), true, 2);
		Block b3 = new Block(3, new Square(5, 1), new Square(4, 1), false, 2);
		Block b4 = new Block(4, new Square(5, 3), new Square(5, 2), true, 2);
		Block b5 = new Block(5, new Square(4, 3), new Square(2, 3), false, 3);
		Block b6 = new Block(6, new Square(5, 5), new Square(3, 5), false, 3);
		
		blocks.add(b1);
		blocks.add(b2);
		blocks.add(b3);
		blocks.add(b4);
		blocks.add(b5);
		blocks.add(b6);
		
		Square finalSquare = new Square(2, 5);
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
		Block b2 = new Block(2, new Square(4, 2), new Square(3, 2), false, 2);
		Block b3 = new Block(3, new Square(2, 0), new Square(0, 0), false, 3);
		Block b4 = new Block(4, new Square(2, 3), new Square(0, 3), false, 3);
		Block b5 = new Block(5, new Square(3, 5), new Square(3, 3), true, 3);
		Block b6 = new Block(6, new Square(5, 4), new Square(5, 2), true, 3);
		Block b7 = new Block(7, new Square(5, 5), new Square(4, 5), false, 2);

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

	public static Board getElevenBlockBoard() {
		int[][] board = new int[6][6];
		List<Block> blocks = new ArrayList<Block>();
		
		board[2][1] = 1;
		board[2][2] = 1;
		board[0][0] = 2;
		board[0][1] = 2;
		board[1][0] = 3;
		board[2][0] = 3;
		board[3][0] = 3;
		board[4][0] = 4;
		board[5][0] = 4;
		board[3][1] = 5;
		board[3][2] = 5;
		board[3][3] = 5;
		board[2][3] = 6;
		board[1][3] = 6;
		board[0][3] = 6;
		board[1][4] = 7;
		board[2][4] = 7;
		board[3][4] = 7;
		board[0][5] = 8;
		board[1][5] = 8;
		board[2][5] = 9;
		board[3][5] = 9;
		board[4][4] = 10;
		board[4][5] = 10;
		board[5][4] = 11;
		board[5][5] = 11;

		Block b1 = new Block(1, new Square(2, 2), new Square(2, 1), true, 2);
		Block b2 = new Block(2, new Square(0, 1), new Square(0, 0), true, 2);
		Block b3 = new Block(3, new Square(3, 0), new Square(1, 0), false, 3);
		Block b4 = new Block(4, new Square(5, 0), new Square(4, 0), false, 2);
		Block b5 = new Block(5, new Square(3, 3), new Square(3, 1), true, 3);
		Block b6 = new Block(6, new Square(2, 3), new Square(0, 3), false, 3);
		Block b7 = new Block(7, new Square(3, 4), new Square(1, 4), false, 3);
		Block b8 = new Block(8, new Square(1, 5), new Square(0, 5), false, 2);
		Block b9 = new Block(9, new Square(3, 5), new Square(2, 5), false, 2);
		Block b10 = new Block(10, new Square(4, 5), new Square(4, 4), true, 2);
		Block b11 = new Block(11, new Square(5, 5), new Square(5, 4), true, 2);

		blocks.add(b1);
		blocks.add(b2);
		blocks.add(b3);
		blocks.add(b4);
		blocks.add(b5);
		blocks.add(b6);
		blocks.add(b7);
		blocks.add(b8);
		blocks.add(b9);
		blocks.add(b10);
		blocks.add(b11);

		Square finalSquare = new Square(2, 5);
		
		return new Board(board, blocks, finalSquare);
	}

	public static Board getTwelveBlockBoard() {
		int[][] board = new int[6][6];
		List<Block> blocks = new ArrayList<Block>();
		
		board[2][0] = 1;
		board[2][1] = 1;
		
		board[0][1] = 2;
		board[1][1] = 2;
		
		board[0][2] = 3;
		board[0][3] = 3;
		
		board[0][4] = 4;
		board[0][5] = 4;
		
		board[1][4] = 5;
		board[1][5] = 5;
		
		board[1][3] = 6;
		board[2][3] = 6;
		
		board[4][2] = 7;
		board[5][2] = 7;
		
		board[2][5] = 8;
		board[3][5] = 8;
		
		board[4][5] = 9;
		board[5][5] = 9;
		
		board[3][0] = 10;
		board[4][0] = 10;
		board[5][0] = 10;
		
		board[3][1] = 11;
		board[3][2] = 11;
		board[3][3] = 11;
		
		board[2][4] = 12;
		board[3][4] = 12;
		board[4][4] = 12;
		
		Block b1 = new Block(1, new Square(2, 1), new Square(2, 0), true, 2);
		Block b2 = new Block(2, new Square(1, 1), new Square(0, 1), false, 2);
		Block b3 = new Block(3, new Square(0, 3), new Square(0, 2), true, 2);
		Block b4 = new Block(4, new Square(0, 5), new Square(0, 4), true, 2);
		Block b5 = new Block(5, new Square(1, 5), new Square(1, 4), true, 2);
		Block b6 = new Block(6, new Square(2, 3), new Square(1, 3), false, 2);
		Block b7 = new Block(7, new Square(5, 2), new Square(4, 2), false, 2);
		Block b8 = new Block(8, new Square(3, 5), new Square(2, 5), false, 2);
		Block b9 = new Block(9, new Square(5, 5), new Square(4, 5), false, 2);
		Block b10 = new Block(10, new Square(5, 0), new Square(3, 0), false, 3);
		Block b11 = new Block(11, new Square(3, 3), new Square(3, 1), true, 3);
		Block b12 = new Block(12, new Square(4, 4), new Square(2, 4), false, 3);
		
		blocks.add(b1);
		blocks.add(b2);
		blocks.add(b3);
		blocks.add(b4);
		blocks.add(b5);
		blocks.add(b6);
		blocks.add(b7);
		blocks.add(b8);
		blocks.add(b9);
		blocks.add(b10);
		blocks.add(b11);
		blocks.add(b12);

		Square finalSquare = new Square(2, 5);
		
		return new Board(board, blocks, finalSquare);

	}
}
