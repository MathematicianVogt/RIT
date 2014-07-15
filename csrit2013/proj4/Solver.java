import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

public class Solver {
	// class varibles
	private Puzzle<ArrayList<Integer>> myPuzzle;
	private boolean found=false;
	private ArrayList<ArrayList<ArrayList<Integer>>> puzzleCombinations;
	private ArrayList<ArrayList<Integer>> current;
	private ArrayList<ArrayList<Integer>> tempArray;
	private HashSet<ArrayList<Integer>> thisConfig;
	// class constructor
	public Solver(Puzzle thePuzzle) {
		myPuzzle=thePuzzle;
		thisConfig=new HashSet<ArrayList<Integer>>();
	}
	// solves the puzzle by BFS
	// @return String, the solve result of puzzle
	
	
	public String BFSSolver(ArrayList<ArrayList<Integer>> originalConfig) {

		
		if(myPuzzle.isGoal(originalConfig))
		{
			
			return myPuzzle.showResults(originalConfig);
		}
		current = new ArrayList<ArrayList<Integer>>();
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
					if (myPuzzle.isGoal(tempArray)) {
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