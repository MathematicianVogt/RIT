import java.util.ArrayList;
import java.util.Iterator;
/*
 * Water.java
 *
 * Version:
 *     $Id: Water.java,v 1.1 2013/11/21 23:51:42 rhv5251 Exp rhv5251 $
 *
 * Revisions:
 *     $Log: Water.java,v $
 *     Revision 1.1  2013/11/21 23:51:42  rhv5251
 *     Initial revision
 *
 */
 //@author Ryan Vogt
 //Implementation of solving a water puzzle
 
public class Water implements Puzzle<ArrayList<Integer>> {
	//class variables
	private int amount;
	private ArrayList<Integer> thebasicConfig;
	private ArrayList<ArrayList<Integer>> basicConfig;
	private ArrayList<Integer> baseConfig;
	private ArrayList<Integer> tempArray;
    
    //class constructor
	public Water(int amount, ArrayList<Integer> originalConfig) {
		thebasicConfig= new ArrayList<Integer>();
		thebasicConfig.addAll(originalConfig);
		this.amount=amount;

	}

	@Override
	public int getGoal() {
		// TODO Auto-generated method stub
		return amount;
	}
    //@param config, the config to be manipulated into multiple other configs based 
    //on distributing water to each jug and exausting all possilities
	public ArrayList<ArrayList<Integer>> xfer(ArrayList<Integer> config) {
		ArrayList<ArrayList<Integer>> xferPart = new ArrayList<ArrayList<Integer>>();

		for (int i = 0; i < config.size(); i++) {

			for (int k = 0; k < config.size(); k++) {
				ArrayList<Integer> empty = new ArrayList<Integer>();
				empty.addAll(config);
				if (Math.abs(thebasicConfig.get(k) - empty.get(k)) > 0) {
					int changeWaterLevel = Math.abs(thebasicConfig.get(k)
							- empty.get(k));
					if (empty.get(i) - changeWaterLevel < 0) {
						changeWaterLevel = empty.get(i);
						empty.add(i, 0);
						empty.remove(i+1);
						empty.add(k, empty.get(k) + changeWaterLevel);
						empty.remove(k+1);
						xferPart.add(empty);

					} else {
						empty.add(i, empty.get(i) - changeWaterLevel);
						empty.remove(i+1);
						empty.add(k, empty.get(k) + changeWaterLevel);
						empty.remove(k+1);
						xferPart.add(empty);
						
					}

				}

			}

		}
		return xferPart;

	}
    //@param config, the config that all possible situations of a jug to be emptied
    //from a given config
	public ArrayList<ArrayList<Integer>> empty(ArrayList<Integer> config) {
		ArrayList<ArrayList<Integer>> emptyPart = new ArrayList<ArrayList<Integer>>();
		for (int i = 0; i < config.size(); i++) {
			ArrayList<Integer> empty = new ArrayList<Integer>();
			empty.addAll(config);
			empty.add(i, 0);
			empty.remove(i+1);
			emptyPart.add(empty);

		}
		
		return emptyPart;
	}
    //@param config, the config that all possible situations of a jug to be filled
    //to full
	public ArrayList<ArrayList<Integer>> fill(ArrayList<Integer> config) {
		ArrayList<ArrayList<Integer>> filledPart = new ArrayList<ArrayList<Integer>>();
		for (int i = 0; i < config.size(); i++) {
			ArrayList<Integer> filled = new ArrayList<Integer>();
			filled.addAll(config);
			filled.add(i,thebasicConfig.get(i));
			filled.remove(i+1);
			filledPart.add(filled);

		}
		
		return filledPart;
	}
	//@config given a config, will return all possibilites of a givens config having water
	//being distributed, being filled, being emptied, for each jug
	@Override
	public ArrayList<ArrayList<Integer>> getNeighbors(ArrayList<Integer> config) {
		
		// TODO Auto-generated method stub
		ArrayList<ArrayList<Integer>> neighbors = new ArrayList<ArrayList<Integer>>();
		ArrayList<ArrayList<Integer>> emptyPart = empty(config);
		ArrayList<ArrayList<Integer>> fillPart = fill(config);
		ArrayList<ArrayList<Integer>> xferPart = xfer(config);
		neighbors.addAll(emptyPart);
		neighbors.addAll(fillPart);
		neighbors.addAll(xferPart);
		return neighbors;

	}
    //@return int the start of the puzzle
	@Override
	public int getStart() {
		// TODO Auto-generated method stub
		return 0;
	}
    //@return arraylist of an arraylist of integers, 
    //the starting point of the tupple of 0s
	public ArrayList<ArrayList<Integer>> baseCon() {
		basicConfig = new ArrayList<ArrayList<Integer>>();
		baseConfig = new ArrayList<Integer>();
		for (int i = 0; i < thebasicConfig.size(); i++) {

			baseConfig.add(0);

		}
		basicConfig.add(baseConfig);
		return basicConfig;

	}

	public static void main(String[] args) {

		ArrayList<Integer> baseConfig = new ArrayList<Integer>();
		for (int i = 1; i < args.length; i++) {
			baseConfig.add(Integer.parseInt(args[i]));

		}

		Water myWater = new Water(Integer.parseInt(args[0]), baseConfig);
		Solver mySolver = new Solver(myWater);
		System.out.println(mySolver.BFSSolver(myWater.baseCon()));

	}

	//@param input, the toString of this class for a solved puzzle
	@Override
	public String showResults(ArrayList<ArrayList<Integer>> input) {
		// TODO Auto-generated method stub
		String finalstr = "";
		String numberLine = "";
		int counter = 0;
		Iterator<ArrayList<Integer>> it = input.iterator();
		while (it.hasNext()) {
			ArrayList<Integer> temp = it.next();

			Iterator<Integer> myIt = temp.iterator();
			while (myIt.hasNext()) {

				int tempint = myIt.next();
				numberLine = numberLine + " " + tempint;

			}
			finalstr = finalstr + "Step " + counter + ":" + "" + numberLine
					+ "\n";
			numberLine = "";
			counter++;

		}
		return finalstr;

	}

}
