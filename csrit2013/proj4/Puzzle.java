import java.util.ArrayList;


public interface Puzzle<T> {

	
	 public boolean isGoal(ArrayList<ArrayList<Integer>> list); 
     //Get the goal config for this puzzle.
	 public java.util.ArrayList<T>getNeighbors(T config);
     //For an incoming config, generate and return all direct neighbors to this config.
	 public int getStart();
     //Get the starting config for this puzzle.	
	 public String showResults(ArrayList<T> input);
	
	
	
	
}