package main;

import gridlock.Block;

public class Utils {

	public static void printPosition(Block block, int move) {
		System.out.println("Position: (" + block.getTail().getI() + ","
				+ (block.getTail().getJ() + move) + ")");
	}

	public static void printBoard(int[][] board) {
		int x = board.length;
		int y = board[0].length;
		// System.out.println("x = " + x);
		// System.out.println("y = " + y);
		for (int i = 0; i < x; i++) {
			String line = new String();
			for (int j = 0; j < y; j++) {
				line += "" + board[i][j] + " | ";
			}
			System.out.println(line);
		}
	}
}
