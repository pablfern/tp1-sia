package gridlock;

import gps.GPSEngine;
import gps.GPSNode;

import javax.management.RuntimeErrorException;

public class GridLockEngine extends GPSEngine {

	@Override
	public void addNode(GPSNode node) {
		/* Check for duplicate nodes */
		if (isDuplicateNode(node))
			return;

		switch (strategy) {
		case BFS:
			open.add(node);
			break;
		case DFS:
			open.add(0, node);
			break;
		default:
			throw new RuntimeErrorException(null);
		}
	}

	private boolean isDuplicateNode(GPSNode node) {
		for (GPSNode n : getClosedNodes()) {
			if (n.getState().compare(node.getState())) {
				return true;
			}
		}
		return false;
	}
}
