package gridlock;

import exception.NotAppliableException;
import gps.api.GPSState;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import main.Utils;

public class BoardState implements GPSState {

	private int[][] board;
	private List<Block> blocks;

	public BoardState(int[][] board, List<Block> blocks) {
		this.board = board;
		this.blocks = blocks;
	}

	@Override
	public boolean compare(GPSState state) {
		List<Block> otherBlocks = ((BoardState) state).getBlocks();

		if (blocks.size() != otherBlocks.size()) {
			return false;
		}

		for (int i = 0; i < blocks.size(); i++) {
			Block b1 = blocks.get(i);
			Block b2 = otherBlocks.get(i);
			if (b1 == null || b2 == null) {
				System.out.println("Nullll");
			}
			if (!b1.isSamePosition(b2)) {
				return false;
			}
		}
		return true;
	}

	public GPSState move_block_horizontally(int blockID, int moves)
			throws NotAppliableException {
		/* First check for empty spaces and index out of bounds */
		Square square = moves > 0 ? blocks.get(blockID - 1).getHead() : blocks
				.get(blockID - 1).getTail();
		try {
			if (moves > 0) {
				for (int k = 1; k <= moves; k++) {
					// System.out.println("Board value: "
					// + board[square.getI()][square.getJ() + k]);
					if (board[square.getI()][square.getJ() + k] != 0) {
						throw new NotAppliableException();
					}
				}
			} else {
				for (int k = -1; k >= moves; k--) {
					if (board[square.getI()][square.getJ() + k] != 0) {
						throw new NotAppliableException();
					}
				}
			}
		} catch (IndexOutOfBoundsException e) {
			throw new NotAppliableException();
		}
		// System.out.println("Before");
		// Utils.printBoard(this.board);
		/* Create new pieces for new boardState */
		List<Block> newBlocks = new ArrayList<Block>();
		int[][] newBoard = cloneBoard();

		for (int k = 0; k < blocks.size(); k++) {
			Block b = blocks.get(k);
			if (k != (blockID - 1)) {
				// System.out.println("Lugar prohibido");
				/* Create a copy of the block */
				newBlocks.add(b.copyBlock());
			} else {
				/* Move block */
				Block movedBlock = b.moveBlock(moves);
				newBlocks.add(movedBlock);

				updateBoard(newBoard, b, 0);

				updateBoard(newBoard, movedBlock, b.getId());
			}
		}
		// System.out.println("After");
		// Utils.printBoard(newBoard);
		return new BoardState(newBoard, newBlocks);
	}

	public GPSState move_block_vertically(int blockID, int moves)
			throws NotAppliableException {
		/* First check for empty spaces and index out of bounds */
		Square square = moves > 0 ? blocks.get(blockID - 1).getHead() : blocks
				.get(blockID - 1).getTail();

		try {
			if (moves > 0) {
				for (int k = 1; k <= moves; k++) {
					// System.out.println("Board value: "
					// + board[square.getI() + k][square.getJ()]);

					if (board[square.getI() + k][square.getJ()] != 0) {
						throw new NotAppliableException();
					}
				}
			} else {
				for (int k = -1; k >= moves; k--) {
					if (board[square.getI() + k][square.getJ()] != 0) {
						throw new NotAppliableException();
					}
				}
			}
		} catch (IndexOutOfBoundsException e) {
			throw new NotAppliableException();
		}
		// System.out.println("Before");
		// Utils.printBoard(this.board);
		/* Create new pieces for new boardState */
		List<Block> newBlocks = new ArrayList<Block>();
		int[][] newBoard = cloneBoard();

		for (int k = 0; k < blocks.size(); k++) {
			Block b = blocks.get(k);
			if (k != (blockID - 1)) {
				// System.out.println("Lugar prohibido");
				/* Create a copy of the block */
				newBlocks.add(b.copyBlock());
			} else {
				/* Move block */
				Block movedBlock = b.moveBlock(moves);
				newBlocks.add(movedBlock);

				updateBoard(newBoard, b, 0);

				updateBoard(newBoard, movedBlock, b.getId());
			}
		}
		// System.out.println("After");
		// Utils.printBoard(newBoard);
		return new BoardState(newBoard, newBlocks);
	}

	public Block getMainBlock() {
		return blocks.get(0);
	}

	public List<Block> getBlocks() {
		return blocks;
	}

	public Set<Integer> blockingBlocks(int id) {
		Block b = blocks.get(id - 1);
		Set<Integer> blocking = new HashSet<Integer>();

		Square head = b.getHead();
		Square tail = b.getTail();
		int count = tail.getI();
		for (int i = head.getI() + 1; i < board.length; i++) {
			int pos = board[i][head.getJ()];
			if (pos == 0) {
				count++;
			} else {
				if (count <= blocks.get(0).getHead().getI()) {
					blocking.add(pos);
				}
				break;
			}
		}
		
		/* If there is enough space to move the block return empty */
		if (count > blocks.get(0).getHead().getI()) {
			return new HashSet<Integer>();
		}

		count = head.getI();
		for (int i = tail.getI() - 1; i >= 0; i--) {
			int pos = board[i][tail.getJ()];
			if (pos == 0) {
				count--;
			} else {
				if (count >= blocks.get(0).getHead().getI()) {
					blocking.add(pos);
				}
				break;
			}
		}
		
		/* If there is enough space to move the block return empty */
		if (count < blocks.get(0).getHead().getI()) {
			return new HashSet<Integer>();
		}
		
		return blocking;
	}

	public void updateBoard(int[][] board, Block block, int id) {
		if (block.isHorizontal()) {
			for (int j = 0; j < block.getSize(); j++) {
				// Utils.printPosition(block,j);
				board[block.getTail().getI()][block.getTail().getJ() + j] = id;
			}
		} else {
			for (int i = 0; i < block.getSize(); i++) {
				board[block.getTail().getI() + i][block.getTail().getJ()] = id;
			}
		}
	}

	private int[][] cloneBoard() {
		int[][] clonedBoard = new int[board.length][board[0].length];
		for (int i = 0; i < clonedBoard.length; i++) {
			for (int j = 0; j < clonedBoard[0].length; j++) {
				clonedBoard[i][j] = board[i][j];
			}
		}
		return clonedBoard;
	}

	@Override
	public String toString() {
		return "Board State:\n".concat(Utils.boardToString(board));
	}

	public int[][] getBoard() {
		return board;
	}

}
