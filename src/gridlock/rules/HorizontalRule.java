package gridlock.rules;

import exception.NotAppliableException;
import gps.api.GPSRule;
import gps.api.GPSState;
import gridlock.BoardState;

public class HorizontalRule implements GPSRule {

	private int blockID;
	private int moves;

	public HorizontalRule(int blockID, int moves) {
		super();
		this.blockID = blockID;
		this.moves = moves;
	}

	@Override
	public Integer getCost() {
		return 6;
	}

	@Override
	public String getName() {
		return "Move horizontally block " + blockID + " " + moves + " spaces.";
	}

	@Override
	public GPSState evalRule(GPSState state) throws NotAppliableException {
		return ((BoardState) state).move_block_horizontally(blockID, moves);
	}

}
