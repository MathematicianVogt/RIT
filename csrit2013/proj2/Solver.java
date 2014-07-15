import java.util.ArrayList;
import java.util.Iterator;
/*
 * Solver.java
 *
 * Version:
 *    1.4
 *
 * Revisions:
 *     1.3
 *     1.2
 */
/*
 * 
 * 
 * @author Ryan Vogt
 * Solving program made to 
 * solve puzzles by finding the sortest route
 * from a start to finish with use of BFS
 */

public class Solver {
	// class varibles
	private ArrayList<ArrayList<Integer>> clockCombinations;
	private boolean found;
	private ArrayList<Integer> startingElement;
	private ArrayList<Integer> current;
	private int start;
	private int end;
	private int hour;
	private int[] refrenceArray;
	private int[] neighborArray;
	private ArrayList<Integer> tempArray;
	private String finalString;
	private int counter;

	// class constructor
	// @param myClock, the clock to be solved.
	public Solver(Clock myClock) {
		start = myClock.getStart();
		end = myClock.getGoal();
		hour = myClock.getHours();
		found = false;
		clockCombinations = new ArrayList<ArrayList<Integer>>();
		startingElement = new ArrayList<Integer>();
		current = new ArrayList<Integer>();
		makeRefrenceArray();

	}

	// find the neighbor of a single element on the clock
	// @param input, used to find neighbors of input
	private int[] findNeighbors(int input) {

		neighborArray = new int[2];
		for (int i = 0; i < refrenceArray.length; i++) {

			if (refrenceArray[i] == input) {
				int k = i - 1;
				int j = i + 1;

				if (k < 0) {
					k = refrenceArray.length - 1;
				} else if (j > refrenceArray.length - 1) {
					j = 0;

				}
				neighborArray[0] = refrenceArray[k];
				neighborArray[1] = refrenceArray[j];

			}

		}

		return neighborArray;

	}

	// create a refrence array to show the clock symbolically
	private void makeRefrenceArray() {
		refrenceArray = new int[hour];
		for (int i = 0; i < hour; i++) {
			refrenceArray[i] = i + 1;

		}

	}

	// solves the puzzle by BFS
	// @return String, the solve result of puzzle
	public String BFSSolver() {
		
		if(start==end)
		{
			
			return "Start and End are the same";
		}
		
		startingElement.add(start);
		clockCombinations.add(startingElement);
		while (!clockCombinations.isEmpty() && !found) {

			current = clockCombinations.get(0);
			clockCombinations.remove(0);
			for (int i : findNeighbors(current.get(current.size() - 1))) {
				tempArray = new ArrayList<Integer>();
				tempArray.addAll(current);
				tempArray.add(i);
				if (i == end) {
					current = tempArray;
					found = true;
					break;

				} else {

					clockCombinations.add(tempArray);
				}

			}

		}
		if (found) {
			return changeToString(current);

		} else {
			return "No Solutions";
		}

	}

	// converts an Arraylist to its string version
	// @param input, the Arraylist to be converted to a string
	private String changeToString(ArrayList<Integer> input) {
		finalString = "";
		counter = 0;
		Iterator<Integer> it = input.iterator();
		while (it.hasNext()) {

			finalString = finalString + "Step " + counter + ":" + " "
					+ it.next() + "\n";
			counter++;

		}
		return finalString;

	}

	// prints out a object of the class
	public void theList() {

		Iterator<ArrayList<Integer>> a = clockCombinations.iterator();
		while (a.hasNext()) {

			System.out.println(a.next());

		}

	}

}
