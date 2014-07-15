import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
/*
 * Solver.java
 *
 * Version:
 *     $Id: Solver.java,v 1.1 2013/11/21 23:51:29 rhv5251 Exp rhv5251 $
 *
 * Revisions:
 *     $Log: Solver.java,v $
 *     Revision 1.1  2013/11/21 23:51:29  rhv5251
 *     Initial revision
 *
 */
 //@author Ryan Vogt
 //A class that will solve anything that implements
 //the puzzle interface.
public class Solver {
	// class varibles
	private Puzzle<ArrayList<Integer>> myPuzzle;
	private boolean found=false;
	private ArrayList<ArrayList<ArrayList<Integer>>> puzzleCombinations;
	private ArrayList<ArrayList<Integer>> current;
	private ArrayList<ArrayList<Integer>> tempArray;
	private HashSet<ArrayList<Integer>> thisConfig;
	// class constructor
	//@param thePuzzle, a refrence to the puzzle for which is being solver for
	public Solver(Puzzle thePuzzle) {
		myPuzzle=thePuzzle;
		thisConfig=new HashSet<ArrayList<Integer>>();
	}
	// solves the puzzle by BFS
	// @return String, the solve result of puzzle
	
	//@param original config, takes a config of a class that implements puzzle
	//solves a solution for the puzzle based on a BSF algorithm. If a solution is solved
	//return a solution, if no solution is found then no solution returned. The solver will
	//find no solution by itself. 
	public String BFSSolver(ArrayList<ArrayList<Integer>> originalConfig) {
		
		
		if(myPuzzle.getStart()==myPuzzle.getGoal())
		{
			
			return myPuzzle.showResults(originalConfig);
		}
		
		puzzleCombinations = new ArrayList<ArrayList<ArrayList<Integer>>>();
		puzzleCombinations.add(originalConfig);
		
		while (!puzzleCombinations.isEmpty() && !found) {

			current = puzzleCombinations.get(0);
			puzzleCombinations.remove(0);
			
			
			Iterator<ArrayList<Integer>> myIt = myPuzzle.getNeighbors(current.get(current.size() - 1)).iterator();
			
			while(myIt.hasNext())
			{	ArrayList<Integer> tempar=myIt.next();
					tempArray = new ArrayList<ArrayList<Integer>>();
					tempArray.addAll(current);
					tempArray.add(tempar);
					if (tempar.contains(myPuzzle.getGoal())) {
						current = tempArray;
						found = true;
						break;

					} 
					else {

						
						
						if(thisConfig.add(tempar))
						{
						puzzleCombinations.add(tempArray);
						}
						
					}
				
				
				
			}
			
			
		
		}
		
		if (found) {
			return myPuzzle.showResults(current);

		} else {
			return "No Solutions";
		}
		
	
		
	}


	
}
