package main;

import gps.GPSEngine;
import gps.SearchStrategy;
import gps.api.GPSProblem;
import gridlock.Block;
import gridlock.GridLockEngine;
import gridlock.GridLockProblem;
import gridlock.Square;

import java.util.ArrayList;
import java.util.List;

public class GridLock {

	public static void main(String[] args) {

		System.out.println("Loading...");
		long start = System.currentTimeMillis();
		int[][] board = new int[6][6];
		board[3][1] = 1;
		board[3][0] = 1;
		board[3][5] = 2;
		board[2][5] = 2;
		List<Block> blocks = new ArrayList<Block>();
		Block b1 = new Block(1, new Square(3, 1), new Square(3, 0), true, 2);
		Block b2 = new Block(2, new Square(3, 5), new Square(2, 5), false, 2);
		blocks.add(b1);
		blocks.add(b2);
		Square finalSquare = new Square(3, 5);
        Utils.printBoard(board);
		GPSProblem problem = new GridLockProblem(board, blocks, finalSquare);
		GPSEngine engine = new GridLockEngine();
		engine.engine(problem, SearchStrategy.BFS);
		long end = System.currentTimeMillis();
		System.out.println("Fin. Tiempo estimado: " + (end - start) / 1000
				+ " segundos");

	}
}
