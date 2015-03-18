package gps.api;

import java.util.List;

/**
 * GPSProblem interface.
 */
public interface GPSProblem {

	/**
	 * Provides the initial state for the GPS to start from.
	 * @return The initial state of the problem to be solved.
	 */
	GPSState getInitState();

	/**
	 * Determines if a state is a goalState. It is used to allow
	 * multiple goal states
	 * @param state
	 * @return
	 */
	boolean isGoalState(GPSState state);
	/**
	 * Provides the list of all the rules that the problem involves. 
	 * These rules are state independent.
	 * @return The initial state of the problem to be solved.
	 */	
	List<GPSRule> getRules();
	
	/**
	 * Computes the value of the Heuristic for the given state.
	 * @param state The state where the Heuristic should be computed.
	 * @return The value of the Heuristic.
	 * DO NOT USE
	 */
	Integer getHValue(GPSState state);

}
