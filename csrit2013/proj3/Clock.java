import java.util.ArrayList;
import java.util.Iterator;

/*
 * Clock.java
 *
 * Version:
 *     $Id: Clock.java,v 1.1 2013/11/21 23:50:05 rhv5251 Exp rhv5251 $
 *
 * Revisions:
 *     $Log: Clock.java,v $
 *     Revision 1.1  2013/11/21 23:50:05  rhv5251
 *     Initial revision
 *
 */
 

/*
 * 
 * 
 * @author Ryan Vogt
 * Program made to drive a simple puzzle solver for a clock
 */

public class Clock implements Puzzle<ArrayList<Integer>> {
	//class variables
	private int clockHour;
	private int clockStart;
	private int clockGoal;
	private ArrayList<Integer> refrenceArray;
	private ArrayList<Integer> leftneighborArray;
	private ArrayList<Integer> rightneighborArray;
	
    //class constructor
    //@param hour, the maximum amount of hours on a clock
    //@param start, the starting place on the clock
    //@param goal, the goal wanting to be reached from the start;
	public Clock(int hour, int start, int goal) {
		clockHour = hour;
		clockStart = start;
		clockGoal = goal;
		makeRefrence();

	}

	//make a refrence array that represents the clock 
	public void makeRefrence()
	{
		
		refrenceArray = new ArrayList<Integer>();
		for (int i = 0; i <clockHour; i++) {
			refrenceArray.add(i,i+1);

		}
		
	}
	//@return clockGoal, the goal of the clock
	@Override
	public int getGoal() {
		// TODO Auto-generated method stub
		return clockGoal;
	}

	
	//@param config, for a given config, return neighbors of the config on the clock
	@Override
	public ArrayList<ArrayList<Integer>> getNeighbors(ArrayList<Integer> config) {
		leftneighborArray = new ArrayList<Integer>();
		rightneighborArray = new ArrayList<Integer>();
		for(int t =0; t<config.size(); t++)
		{
		for (int i = 0; i < refrenceArray.size(); i++) {

			if (refrenceArray.get(i) == config.get(t)) {
				int k = i - 1;
				int j = i + 1;

				if (k < 0) {
					k = refrenceArray.size() - 1;
				} else if (j > refrenceArray.size() - 1) {
					j = 0;

				}
				
				leftneighborArray.add(refrenceArray.get(k));
				rightneighborArray.add(refrenceArray.get(j));
				
			}
			}
			}
				ArrayList<ArrayList<Integer>> finalNeighbor = new ArrayList<ArrayList<Integer>>();
				
				finalNeighbor.add(leftneighborArray);
				finalNeighbor.add(rightneighborArray);
				
			
				return finalNeighbor;
		
	
			
	}
	public ArrayList<ArrayList<Integer>> baseConfig()
	{
		ArrayList<Integer> base = new ArrayList<Integer>();
		ArrayList<ArrayList<Integer>> basicSetup = new ArrayList<ArrayList<Integer>>();
		
		base.add(clockStart);
		basicSetup.add(base);
		return basicSetup;
		
		
	}

	//@return clockStart the starting point of the clock
	@Override
	public int getStart() {
		// TODO Auto-generated method stub
		return clockStart;
	}
	public static void main(String[] args)
	{
		
		Clock thisClock= new Clock(Integer.parseInt(args[0]),Integer.parseInt(args[1]),Integer.parseInt(args[2]));
		Solver mySolver=new Solver(thisClock);
		System.out.println(mySolver.BFSSolver(thisClock.baseConfig()));
		
		
		
	}

	//@param input, the toString for a Clock puzzle, converting a Clock puzzle solution to
	//output
	@Override
	public String showResults(ArrayList<ArrayList<Integer>> input) {
		String finalString = "";
		int counter = 0;
		Iterator<ArrayList<Integer>> it = input.iterator();
		while (it.hasNext()) {

			ArrayList<Integer> temp = it.next();
			Iterator<Integer> myIt = temp.iterator();
			while(myIt.hasNext())
			{
			
			finalString = finalString + "Step " + counter + ":" + " "
					+ myIt.next() + "\n";
			counter++;
			}
			
		}
		return finalString;
	}


	
	
	
}
