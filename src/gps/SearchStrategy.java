package gps;

import gps.api.GPSProblem;

import java.util.ArrayList;
import java.util.List;

public enum SearchStrategy {
	BFS,
	DFS,
	IDDFS,
	GREEDY,
	AStar;

	public static boolean isHeuristic(SearchStrategy strategy) {
		switch(strategy){
		case GREEDY : case AStar:
			return true;
		default:
			return false;
		}
	}

	public List<GPSNode> addNode(List<GPSNode> heuristic, GPSNode newNode, GPSProblem problem) {
		List<GPSNode> aux = new ArrayList<GPSNode>();
		boolean add=false;
		if(this.equals(GREEDY)){
			if(!heuristic.isEmpty()){
				for(GPSNode hnode: heuristic){
					if(hasWorstHeurisitc(newNode, problem, hnode) && !add){
						aux.add(newNode);
						add = true;
					}
					aux.add(hnode);
				}
				if(!add){
					aux.add(newNode);
				}
			} else{
				aux.add(newNode);
			}
		}
		return aux;
	}

	private boolean hasWorstHeurisitc(GPSNode newNode, GPSProblem problem,
			GPSNode hnode) {
		Integer newNodevalueH = problem.getHValue(newNode.getState());
		Integer hnodevalueH =  problem.getHValue(hnode.getState());
		return problem.getHValue(newNode.getState()) > problem.getHValue(hnode.getState());
	}
}
