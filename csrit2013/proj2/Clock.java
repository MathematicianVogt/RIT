import java.util.ArrayList;


/*
 * Clock.java
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
 * Program made to drive a simple puzzle solver for a clock
 */

public class Clock implements Puzzle {
	private int clockHour;
	private int clockStart;
	private int clockGoal;
	private ArrayList<ArrayList<Integer>> clockCombinations;
	private boolean found;
	private ArrayList<Integer> startingElement;

	public Clock(int hour, int start, int goal) {
		clockHour = hour;
		clockStart = start;
		clockGoal = goal;

	}

	// Get the goal config for this puzzle.

	public int getHours() {
		return clockHour;

	}

	@Override
	public int getGoal() {
		// TODO Auto-generated method stub
		return clockGoal;
	}

	// For an incoming config, generate and return all direct neighbors to this
	// config.
	@Override
	public ArrayList<Integer> getNeighbors(int config) {
		// TODO Auto-generated method stub
		return null;
	}

	// Get the starting config for this puzzle.
	@Override
	public int getStart() {
		// TODO Auto-generated method stub
		return clockStart;
	}

	public static void main(String[] args) { // main function
		if (args.length < 3 || args.length > 3) {
			System.out.println("Usage: java Clock hours start goal");

		} else {
			Solver myPuzzleSolver = new Solver(new Clock(
					Integer.parseInt(args[0]), Integer.parseInt(args[1]),
					Integer.parseInt(args[2])));
			System.out.println(myPuzzleSolver.BFSSolver());

		}

	}
}
