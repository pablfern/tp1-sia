package gridlock;

import gps.GPSEngine;
import gps.GPSNode;

import javax.management.RuntimeErrorException;

public class GridLockEngine extends GPSEngine {

	@Override
	public void addNode(GPSNode node) {
	
		switch (strategy) {
		case BFS:
			open.add(node);
			break;
		case DFS:
			open.add(0, node);
			break;
		case IDDFS:
			open.add(0,node);
			break;
		case GREEDY: case ASTAR:
			open.add(0,node);
			break;
		default:
			throw new RuntimeErrorException(null);
		}
	}

}
