package gps;

import gps.api.GPSProblem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public enum SearchStrategy {
	BFS,
	DFS,
	IDDFS,
	GREEDY,
	ASTAR;

	public static boolean isHeuristic(SearchStrategy strategy) {
		switch(strategy){
		case GREEDY : case ASTAR:
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
		}else if(this.equals(ASTAR)){
			newNodevalueH = getCostAstar(newNode, problem);
			hnodevalueH = getCostAstar(hnode, problem);
			if(newNodevalueH.equals(hnodevalueH)){
				newNodevalueH = getHeuristic(problem, newNode);
				hnodevalueH =  getHeuristic(problem, hnode);
			}

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

	public List<GPSNode> getNodeList(List<GPSNode> open, GPSProblem problem) {
		if(this.equals(GREEDY)){
			return new ArrayList<GPSNode>();
		}else if(this.equals(ASTAR)){
			return order(open, problem);
		}
		return null;
	}

	private List<GPSNode> order(List<GPSNode> open, final GPSProblem problem) {
		List<GPSNode> aux = open;
		Collections.sort(aux, new Comparator<GPSNode>() {
	        @Override
	        public int compare(GPSNode  node1, GPSNode  node2)
	        {
	        	Integer node1value = getCostAstar(node1,problem);
	        	Integer node2value = getCostAstar(node2, problem);
				if(node1value.equals(node2value)){
					node1value = getHeuristic(problem, node1);
					node2value =  getHeuristic(problem, node2);
				}
	            return  node2value.compareTo(node1value);
	        }
	    });
		
//		Utils.printAstarNodeList(aux,problem);
		return aux;
	}

	public boolean isAstar() {
		return this.equals(ASTAR);
	}
	

}
