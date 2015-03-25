package gridlock.rules;

import exception.NotAppliableException;
import gps.api.GPSRule;
import gps.api.GPSState;
import gridlock.BoardState;

public class VerticalRule implements GPSRule {

	private int blockID;
	private int moves;

	public VerticalRule(int blockID, int moves) {
		super();
		this.blockID = blockID;
		this.moves = moves;
	}

	@Override
	public Integer getCost() {
		return 1;
	}

	@Override
	public String getName() {
		return "Move vertically block " + blockID + " " + moves + " spaces.";
	}

	@Override
	public String toString() {
		return "Move vertically block " + blockID + " " + moves + " spaces.";
	}

	@Override
	public GPSState evalRule(GPSState state) throws NotAppliableException {
		return ((BoardState) state).move_block_vertically(blockID, moves);
	}

}
