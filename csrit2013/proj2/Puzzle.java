/*
 * Puzzle.java
 *
 * Version:
 *    1.4
 *
 * Revisions:
 *     1.3
 *     1.2
 */
public interface Puzzle {

	
	 public int getGoal(); 
     //Get the goal config for this puzzle.
	 public java.util.ArrayList<java.lang.Integer>	getNeighbors(int config);
     //For an incoming config, generate and return all direct neighbors to this config.
	 public int getStart();
     //Get the starting config for this puzzle.	
	
	
}
