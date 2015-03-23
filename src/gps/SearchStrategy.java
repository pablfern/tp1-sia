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
		if(!heuristic.isEmpty()){
			for(GPSNode hnode: heuristic){
				if(hasWorstHeuristic(newNode, problem, hnode) && !add){
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
		
		return aux;
	}

	private boolean hasWorstHeuristic(GPSNode newNode, GPSProblem problem,
			GPSNode hnode) {
		Integer newNodevalueH = null;
		Integer hnodevalueH = null;
		if(this.equals(GREEDY)){
			newNodevalueH = getHeuristic(problem, newNode);
			hnodevalueH =  getHeuristic(problem, hnode);
		}else if(this.equals(AStar)){
			newNodevalueH = getCostAstar(newNode, problem);
			hnodevalueH = getCostAstar(hnode, problem);
			//TODO no estoy teniendo en cuenta que si son iguales
			// en ese caso no se si hay que tener en cta el de menor cost o 
			// el de menor h
		}
		return hasWorstHeuristic(newNodevalueH, hnodevalueH);
	}

	private boolean hasWorstHeuristic(Integer newNodevalueH, Integer hnodevalueH) {
		return newNodevalueH > hnodevalueH;
	}

	private Integer getHeuristic(GPSProblem problem, GPSNode hnode) {
		return problem.getHValue(hnode.getState());
	}
	
	private Integer getCostAstar(GPSNode node, GPSProblem problem){
		return node.getCost() + getHeuristic(problem, node);
	}
	

}
