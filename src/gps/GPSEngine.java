package gps;

import exception.NotAppliableException;
import gps.api.GPSProblem;
import gps.api.GPSRule;
import gps.api.GPSState;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public abstract class GPSEngine {

	protected List<GPSNode> open = new LinkedList<GPSNode>();

	private List<GPSNode> closed = new ArrayList<GPSNode>();

	private GPSProblem problem;

	private int repeteadStates = 0;
	// Use this variable in the addNode implementation
	protected SearchStrategy strategy;

	public void engine(GPSProblem myProblem, SearchStrategy myStrategy) {
		long start = System.currentTimeMillis();
		int answerDepth = -1;
		problem = myProblem;
		strategy = myStrategy;

		GPSNode rootNode = new GPSNode(problem.getInitState(), 0, 1);
		boolean finished = false;
		boolean failed = false;
		long explosionCounter = 0;

		open.add(rootNode);
		if (strategy != SearchStrategy.IDDFS
				&& !SearchStrategy.isHeuristic(strategy)) {
			while (!failed && !finished) {
				if (open.size() <= 0) {
					failed = true;
				} else {
					GPSNode currentNode = getCurrentNode();
					if (isGoal(currentNode)) {
						finished = true;
						answerDepth = currentNode.getDepth();
						System.out.println(currentNode.getSolution(problem));
					} else {
						explosionCounter++;
						explode(currentNode);
					}
				}
			}
		} else if (!SearchStrategy.isHeuristic(strategy)) {
			// While for each iteration in IDDFS
			int iddfsIteration = 1;
			try {
				while (!finished) {
					// TODO Open should always be empty at this point. Just in
					// case
					// I reset the list
					open = new ArrayList<GPSNode>();
					closed = new ArrayList<GPSNode>();
					open.add(rootNode);
					while (open.size() > 0 && !finished) {
						GPSNode currentNode = getCurrentNode();
						if (isGoal(currentNode)) {
							finished = true;
							answerDepth = currentNode.getDepth();
							System.out
									.println(currentNode.getSolution(problem));
						} else {
							// Only expand depending on the depth of the current
							// node
							if (currentNode.getDepth() < iddfsIteration) {
								explosionCounter++;
								explode(currentNode);
							}
						}
					}
					iddfsIteration++;
				}
			} catch (OutOfMemoryError e) {
				finished = false;
				System.out.println("Out of memory!!");
			}
			if (!finished) {
				failed = true;
			}
		} else {
			// Estrategia con heuristica
			while (!failed && !finished) {
				if (open.size() <= 0) {
					failed = true;
				} else {
					GPSNode currentNode = getCurrentNode();
					if (isGoal(currentNode)) {
						finished = true;
						answerDepth = currentNode.getDepth();
						System.out.println(currentNode.getSolution(problem));
					} else {
						explosionCounter++;
						explodeHeuristic(currentNode);
					}
				}
			}
		}

		if (finished) {
			System.out.println("OK! solution found!");
		} else if (failed) {
			System.err.println("FAILED! solution not found!");
		}
		long end = System.currentTimeMillis();
		System.out.println("Tiempo de procesamiento: " + (end - start)
				+ " milisegundos");
		if (answerDepth != -1) {
			System.out.println("Profundiad de la solución: " + answerDepth);
		}
		int nodeCount = open.size() + closed.size();
		System.out
				.println("Cantidad de estados únicos generados: " + nodeCount);
		nodeCount += repeteadStates;
		System.out.println("Cantidad de estados generados: " + nodeCount);
		System.out.println("Número de nodos frontera: " + open.size());
		System.out.println("Número de nodos expandidos: " + explosionCounter);
	}

	private GPSNode getCurrentNode() {
		GPSNode currentNode = open.get(0);
		closed.add(currentNode);
		open.remove(0);
		return currentNode;
	}

	private boolean isGoal(GPSNode currentNode) {
		// Change code to allow multiple goalStates
		return currentNode.getState() != null
				&& problem.isGoalState(currentNode.getState());
	}

	private boolean explode(GPSNode node) {
		if (problem.getRules() == null) {
			System.err.println("No rules!");
			return false;
		}

		for (GPSRule rule : problem.getRules()) {
			// System.out.println(rule.getName());
			GPSState newState = null;
			try {
				newState = rule.evalRule(node.getState());
			} catch (NotAppliableException e) {
				// Do nothing
			}
			if (newState != null
					&& !checkBranch(node, newState)
					&& !checkOpenAndClosed(node.getCost() + rule.getCost(),
							newState)) {
				GPSNode newNode = new GPSNode(newState, node.getCost()
						+ rule.getCost(), node.getDepth() + 1);
				newNode.setParent(node);
				addNode(newNode);
			} else if (newState != null) {
				repeteadStates++;
			}
		}
		return true;
	}

	private boolean checkOpenAndClosed(Integer cost, GPSState state) {
		for (GPSNode openNode : open) {
			if (openNode.getState().compare(state)
					&& openNode.getCost() <= cost) {
				return true;
			}
		}
		for (GPSNode closedNode : closed) {
			if (closedNode.getState().compare(state)
					&& closedNode.getCost() <= cost) {
				return true;
			}
		}
		return false;
	}

	private boolean checkBranch(GPSNode parent, GPSState state) {
		if (parent == null) {
			return false;
		}
		return checkBranch(parent.getParent(), state)
				|| state.compare(parent.getState());
	}

	public abstract void addNode(GPSNode node);

	public List<GPSNode> getClosedNodes() {
		return this.closed;
	}

	public List<GPSNode> getOpenNodes() {
		return this.open;
	}

	private boolean explodeHeuristic(GPSNode node) {
		if (problem.getRules() == null) {
			System.err.println("No rules!");
			return false;
		}
		List<GPSNode> heuristic = new ArrayList<GPSNode>();
		;

		for (GPSRule rule : problem.getRules()) {
			// System.out.println(rule.getName());
			GPSState newState = null;
			try {
				newState = rule.evalRule(node.getState());
			} catch (NotAppliableException e) {
				// Do nothing
			}
			if (newState != null
					&& !checkBranch(node, newState)
					&& !checkOpenAndClosed(node.getCost() + rule.getCost(),
							newState)) {
				GPSNode newNode = new GPSNode(newState, node.getCost()
						+ rule.getCost(), node.getDepth() + 1);
				newNode.setParent(node);
				heuristic = strategy.addNode(heuristic, newNode, problem);
			} else if (newState != null) {
				repeteadStates++;
			}
		}
		if (!heuristic.isEmpty()) {
			for (GPSNode hNode : heuristic) {
				addNode(hNode);
			}
		}
		return true;
	}
}
