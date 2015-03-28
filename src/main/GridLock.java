package main;

import gps.GPSEngine;
import gps.Heuristic;
import gps.SearchStrategy;
import gps.api.GPSProblem;
import gridlock.GridLockEngine;
import gridlock.GridLockProblem;
import boards.Board;
import boards.BoardGenerator;

public class GridLock {

	public static void main(String[] args) {
		
		Board board = BoardGenerator.getElevenBlockBoard();
		System.out.println("Initial board");
		Utils.printBoard(board.getBoard());
		System.out.println();
		
		//le pasas por constructor la heuristica en caso de que sea necesario
		GPSProblem problem = new GridLockProblem(board.getBoard(),
				board.getBlocks(), board.getFinalSquare(), Heuristic.BLOCKS_TO_MOVE);
		GPSEngine engine = new GridLockEngine();
		engine.engine(problem, SearchStrategy.AStar);
	}
}
