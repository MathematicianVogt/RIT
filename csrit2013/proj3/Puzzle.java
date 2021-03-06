import java.util.ArrayList;

/*
 * Puzzle.java
 *
 * Version:
 *     $Id: Puzzle.java,v 1.1 2013/11/21 23:51:13 rhv5251 Exp rhv5251 $
 *
 * Revisions:
 *     $Log: Puzzle.java,v $
 *     Revision 1.1  2013/11/21 23:51:13  rhv5251
 *     Initial revision
 *
 */
 //a generic puzzle class
public interface Puzzle<T> {

	
	 public int getGoal(); 
     //Get the goal config for this puzzle.
	 public java.util.ArrayList<T>getNeighbors(T config);
     //For an incoming config, generate and return all direct neighbors to this config.
	 public int getStart();
     //Get the starting config for this puzzle.	
	 public String showResults(ArrayList<T> input);
	
	
	
	
}
