package main;

import java.util.List;

import gps.GPSNode;
import gps.api.GPSProblem;
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
		System.out.println();
	}

	public static String boardToString(int[][] board) {
		String ans = "";
		
		int x = board.length;
		int y = board[0].length;
		
		for (int i = 0; i < x; i++) {
			for (int j = 0; j < y; j++) {
				ans = ans.concat(board[i][j] + " | ");
			}
			ans = ans.concat("\n");
		}
		ans = ans.concat("\n");
		return ans;
	}

	public static void printAstarNodeList(List<GPSNode> aux, GPSProblem problem) {
		String ans = "OrderHeuristicListOpenAStar: ";
		for(GPSNode node: aux){
			Integer value = node.getCost() + problem.getHValue(node.getState());
			ans += value.toString() + "|";
		}
		System.out.println(ans);
		
	}
}
