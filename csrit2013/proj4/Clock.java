import java.util.ArrayList;
import java.util.Iterator;

/*
 * 
 * 
 * @author Ryan Vogt
 * Program made to drive a simple puzzle solver for a clock
 */

public class Clock implements Puzzle<ArrayList<Integer>> {
	private int clockHour;
	private int clockStart;
	private int clockGoal;
	private ArrayList<Integer> refrenceArray;
	private ArrayList<Integer> leftneighborArray;
	private ArrayList<Integer> rightneighborArray;
	

	public Clock(int hour, int start, int goal) {
		clockHour = hour;
		clockStart = start;
		clockGoal = goal;
		makeRefrence();

	}

	
	public void makeRefrence()
	{
		
		refrenceArray = new ArrayList<Integer>();
		for (int i = 0; i <clockHour; i++) {
			refrenceArray.add(i,i+1);

		}
		
	}
	@Override
	public boolean isGoal(ArrayList<ArrayList<Integer>> myList) {
		// TODO Auto-generated method stub
		Iterator<ArrayList<Integer>> it = myList.iterator();
		while(it.hasNext())
		{
			ArrayList<Integer> current = it.next();
			Iterator<Integer> it1 = current.iterator();
			while(it1.hasNext())
			{
				int currentInt=it1.next();
				if(currentInt==clockGoal)
				{
					return true;
				}
				
				
			}
			
			
		}
		return false;
	}

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

	@Override
	public int getStart() {
		// TODO Auto-generated method stub
		return clockStart;
	}
	public static void main(String[] args)
	{
		if(args.length<3)
		{
			
			System.out.println("Not correct input");
			System.exit(0);
			
		}
		
		Clock thisClock= new Clock(Integer.parseInt(args[0]),Integer.parseInt(args[1]),Integer.parseInt(args[2]));
		Solver mySolver=new Solver(thisClock);
		System.out.println(mySolver.BFSSolver(thisClock.baseConfig()));
		
		
		
	}

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