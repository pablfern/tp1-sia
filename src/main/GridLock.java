package main;

import gps.GPSEngine;
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

		GPSProblem problem = new GridLockProblem(board.getBoard(),
				board.getBlocks(), board.getFinalSquare());
		GPSEngine engine = new GridLockEngine(13);
		engine.engine(problem, SearchStrategy.IDDFS);
		long start = System.currentTimeMillis();
		long end = System.currentTimeMillis();
		System.out.println("Fin. Tiempo estimado: " + (end - start)
				+ " milisegundos");
	}
}
