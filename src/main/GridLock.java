package main;

import gps.GPSEngine;
import gps.Heuristic;
import gps.SearchStrategy;
import gps.api.GPSProblem;
import gridlock.GridLockEngine;
import gridlock.GridLockProblem;
import boards.Board;
import boards.BoardParser;

public class GridLock {

	public static void main(String[] args) {

		// Control de argumentos
		if (args.length < 2) {
			System.out.println("Error: Missing arguments.");
			return;
		}

		// Control de estrategia
		if (!(args[0].equalsIgnoreCase("DFS")
				|| args[0].equalsIgnoreCase("BFS")
				|| args[0].equalsIgnoreCase("IDDFS")
				|| args[0].equalsIgnoreCase("GREEDY") || args[0]
					.equalsIgnoreCase("ASTAR"))) {
			System.out.println("Error: Invalid strategy.");
			System.out.println("Use one of the following: DFS, BFS, IDDFS, GREEDY or ASTAR");
			return;
		}

		// Parseo la Heuristica
		if (args.length == 2
				&& (args[0].equalsIgnoreCase("GREEDY") || args[0]
						.equalsIgnoreCase("ASTAR"))) {
			System.out
					.println("Error: Greedy and Astar need a third argument, the heuristic: h1 or h2.");
			return;
		}
		Heuristic heuristic = Heuristic.BLOCKS_TO_MOVE;
		if (args.length == 3) {
			if (args[2].equals("h1")) {
				heuristic = Heuristic.BLOCKS_TO_MOVE;
			} else if (args[2].equals("h2")) {
				heuristic = Heuristic.BLOCKS_TO_MOVE_ENH;
			}else{
				System.out.println("Error: Invalid heuristic. Choose h1 or h2");
				return;
			}
		}

		String path = args[1];
		String strategy_parameter = args[0];
		SearchStrategy strategy = null;

		switch (strategy_parameter) {
		case "BFS":
			strategy = SearchStrategy.BFS;
			break;
		case "DFS":
			strategy = SearchStrategy.DFS;
			break;
		case "IDDFS":
			strategy = SearchStrategy.IDDFS;
			break;
		case "GREEDY":
			strategy = SearchStrategy.GREEDY;
			break;
		case "ASTAR":
			strategy = SearchStrategy.ASTAR;
			break;
		default:
			System.out.println("Error. Invalid strategy");
			return;
		}
		Board board = BoardParser.parseFile(path);

		// le pasas por constructor la heuristica en caso de que sea necesario
		GPSProblem problem = new GridLockProblem(board.getBoard(),
				board.getBlocks(), board.getFinalSquare(), heuristic);
		GPSEngine engine = new GridLockEngine();
		engine.engine(problem, strategy);

	}
}
