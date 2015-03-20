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
		Block b1 = new Block(1, new Square(2, 2), new Square(2, 1), true, 2);
		Block b2 = new Block(2, new Square(0, 1), new Square(0, 0), true, 2);
		Block b3 = new Block(3, new Square(1, 0), new Square(3, 0), false, 3);
		Block b4 = new Block(4, new Square(4, 0), new Square(5, 0), false, 2);
		Block b5 = new Block(5, new Square(3, 3), new Square(3, 1), true, 3);
		Block b6 = new Block(6, new Square(0, 3), new Square(2, 3), false, 3);
		Block b7 = new Block(7, new Square(1, 4), new Square(3, 4), false, 3);
		Block b8 = new Block(8, new Square(0, 5), new Square(1, 5), false, 2);
		Block b9 = new Block(9, new Square(2, 5), new Square(3, 5), false, 2);
		Block b10 = new Block(10, new Square(4, 5), new Square(4, 4), true, 2);
		Block b11 = new Block(11, new Square(5, 5), new Square(5, 4), true, 2);
		
		
		blocks.add(b1); blocks.add(b2);
		blocks.add(b3); blocks.add(b4);
		blocks.add(b5); blocks.add(b6);
		blocks.add(b7); blocks.add(b8);
		blocks.add(b9); blocks.add(b10); 
		blocks.add(b11); 
		
		Square finalSquare = new Square(2, 5);
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
