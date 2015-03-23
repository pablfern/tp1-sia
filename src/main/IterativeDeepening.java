package main;

import java.util.Stack;

public class IterativeDeepening {

	private Stack<Integer> stack;
	private int numberOfNodes;
	private int depth;
	private int maxDepth;
	private boolean goalFound = false;

	public IterativeDeepening() {
		stack = new Stack<Integer>();
	}

	public void iterativeDeepening(int adjMatrix[][], int destination) {

		numberOfNodes = adjMatrix[1].length;
		while (!goalFound) {
			depthLimitedSearch(adjMatrix, 1, destination);
			maxDepth++;
		}
		System.out.println("\nGoal found at depth " + depth);
	}

	private void depthLimitedSearch(int[][] adjMatrix, int source, int goal) {

		int element, destination = 1;
		int[] visited = new int[numberOfNodes+1];
		stack.push(source);
		depth = 0;
		System.out.println("\nAt depth: " + maxDepth);
		System.out.print(source + "\t");

		while (!stack.isEmpty()) {
			element = stack.peek();
			while (destination <= numberOfNodes) {
				if (depth < maxDepth) {
					if (adjMatrix[element-1][destination-1] == 1) {
						stack.push(destination);
						visited[destination] = 1;
						System.out.print(destination + "\t");
						depth++;
						if (goal == destination) {
							goalFound = true;
							return;
						}
						element = destination;
						destination = 1;
						continue;
					}
				} else {
					break;
				}
				destination++;
			}
			destination = stack.pop() + 1;
			depth--;
		}
	}

	public static void main(String[] args) {
		int destination = 7;
		int adjacency_matrix[][] = { { 0, 1, 1, 0, 0, 0, 0 },
				{ 0, 0, 0, 1, 1, 0, 0 }, { 0, 0, 0, 0, 0, 1, 1 },
				{ 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0 } };
		Utils.printBoard(adjacency_matrix);
		IterativeDeepening id = new IterativeDeepening();
		id.iterativeDeepening(adjacency_matrix, destination);
	}
}
