package boards;

import gridlock.Block;
import gridlock.Square;

import java.util.List;

public class Board {
	private int[][] board;
	private List<Block> blocks;
	private Square finalSquare;

	public Board(int[][] board, List<Block> blocks, Square finalSquare) {
		super();
		this.board = board;
		this.blocks = blocks;
		this.finalSquare = finalSquare;
	}

	public int[][] getBoard() {
		return board;
	}

	public List<Block> getBlocks() {
		return blocks;
	}

	public Square getFinalSquare() {
		return finalSquare;
	}

}
