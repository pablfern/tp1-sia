package gridlock;

import gps.api.GPSProblem;
import gps.api.GPSRule;
import gps.api.GPSState;
import gridlock.rules.HorizontalRule;
import gridlock.rules.VerticalRule;

import java.util.ArrayList;
import java.util.List;

public class GridLockProblem implements GPSProblem {

	private Square finalSquare;

	/* We use a square board */
	private int[][] board;
	private List<Block> blocks;


	public GridLockProblem(int[][] board, List<Block> blocks, Square finalSquare) {
		this.board = board;
		this.blocks = blocks;
		this.finalSquare = finalSquare;
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
		// TODO We should add this to the constructor
		return 0;
	}

}
