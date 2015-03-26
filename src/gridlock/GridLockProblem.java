package gridlock;

import gps.Heuristic;
import gps.api.GPSProblem;
import gps.api.GPSRule;
import gps.api.GPSState;
import gridlock.rules.HorizontalRule;
import gridlock.rules.VerticalRule;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import main.Utils;

public class GridLockProblem implements GPSProblem {

	private Square finalSquare;
	/* We use a square board */
	private int[][] board;
	private List<Block> blocks;
	private Heuristic heuristic;

	public GridLockProblem(int[][] board, List<Block> blocks, Square finalSquare) {
		this.board = board;
		this.blocks = blocks;
		this.finalSquare = finalSquare;
		this.heuristic = null;
	}

	public GridLockProblem(int[][] board, List<Block> blocks,
			Square finalSquare, Heuristic heuristic) {
		this.board = board;
		this.blocks = blocks;
		this.finalSquare = finalSquare;
		this.heuristic = heuristic;
	}

	@Override
	public GPSState getInitState() {
		return new BoardState(board, blocks);
	}

	@Override
	public boolean isGoalState(GPSState state) {
		Square head = ((BoardState) state).getMainBlock().getHead();
		Square tail = ((BoardState) state).getMainBlock().getTail();
		if ((head.getI() == finalSquare.getI() && head.getJ() == finalSquare
				.getJ())
				|| (tail.getI() == finalSquare.getI() && tail.getJ() == finalSquare
						.getJ())) {
			return true;
		}
		return false;
	}

	@Override
	public List<GPSRule> getRules() {
		List<GPSRule> rules = new ArrayList<GPSRule>();
		for (Block b : blocks) {
			for (int i = 1; i <= board[0].length - b.getSize(); i++) {
				if (b.isHorizontal()) {
					rules.add(new HorizontalRule(b.getId(), i));
					rules.add(new HorizontalRule(b.getId(), -i));
				} else {
					rules.add(new VerticalRule(b.getId(), i));
					rules.add(new VerticalRule(b.getId(), -i));
				}
			}
		}
		return rules;
	}

	@Override
	public Integer getHValue(GPSState state) {

		if (heuristic == null) {
			return 0;
		}
				
		switch (heuristic) {
		case BLOCKS_TO_MOVE_ENH:
			int hValue = blocksToMoveEnhancedHeuristic((BoardState) state);
			//System.out.println("HValue: " + hValue);
			//System.out.println(state.toString());
			return hValue;
		case BLOCKS_TO_MOVE:
			System.out.println(state.toString());
			int hValue2 = blocksToMoveHeuristic((BoardState) state);
			System.out.println("HValue: " + hValue2);
			return hValue2;
		default:
			return 0;
		}
	}

	/*
	 * This heuristic uses the distance of the head of the block to the final
	 * square. Keep in mind that the final square MUST be on the right side or
	 * at the bottom of the board. This way we always use the distance of the
	 * head of the block to the goal. THIS HEURISTIC DOES NOT CONTEMPLATE THE
	 * CASE WHERE THE GOAL AND THE MAIN BLOCK ARE PLACED INCORRECTYL.
	 */
	private Integer blocksToMoveEnhancedHeuristic(BoardState state) {
		
		if(isGoalState(state)){
			return 0;
		}
		
		Block block = state.getBlocks().get(0);
		Set<Integer> blocks = new HashSet<Integer>();
		Set<Integer> blocking = new HashSet<Integer>();
		
		for (int j = block.getHead().getJ() + 1; j < board[0].length; j++) {
			if (state.getBoard()[block.getHead().getI()][j] != 0) {
				int id = state.getBoard()[block.getHead().getI()][j];
				blocks.add(id);
				blocking.addAll(state.blockingBlocks(id));
			}
		}
		
		blocks.addAll(blocking);
		
		return blocks.size() + 1;
			
	}

	/*
	 * This heuristic uses the amount of blocks between the goal and the main
	 * block. THIS HEURISTIC DOES NOT CONTEMPLATE THE CASE WHERE THE GOAL AND
	 * THE MAIN BLOCK ARE PLACED INCORRECTLY.
	 */
	private Integer blocksToMoveHeuristic(BoardState state) {
		Block block = state.getBlocks().get(0);
		Set<Integer> blocks = new HashSet<Integer>();
		
		if(isGoalState(state)){
			return 0;
		}

		for (int j = block.getHead().getJ() + 1; j < board[0].length; j++) {
			if (state.getBoard()[block.getHead().getI()][j] != 0) {
				blocks.add(state.getBoard()[block.getHead().getI()][j]);
			}
		}

		return blocks.size() + 1;

	}
}
