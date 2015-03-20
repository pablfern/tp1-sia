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
		board[2][1] = 1; board[2][2] = 1;
		board[0][0] = 2; board[0][1] = 2;
		board[1][0] = 3; board[2][0] = 3; board[3][0] = 3;
		board[4][0] = 4; board[5][0] = 4; 
		board[3][1] = 5; board[3][2] = 5; board[3][3] = 5;
		board[2][3] = 6; board[1][3] = 6; board[0][3] = 6;
		board[1][4] = 7; board[2][4] = 7; board[3][4] = 7;
		board[0][5] = 8; board[1][5] = 8;
		board[2][5] = 9; board[3][5] = 9;
		board[4][4] = 10; board[4][5] = 10;
		board[5][4] = 11; board[5][5] = 11;
		
		printBoard(board);
		
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
	
	public static void printBoard(int[][] myBoard) {
		for (int i = 0; i < myBoard[0].length; i++) {
			for (int j = 0; j < myBoard.length; j++) {
				System.out.print(myBoard[i][j] + " ");
			}
			System.out.println();
		}
	}
}
