import java.util.ArrayList;
import java.util.Iterator;

public class Water implements Puzzle<ArrayList<Integer>> {
	private int amount;
	private ArrayList<Integer> thebasicConfig;
	private ArrayList<ArrayList<Integer>> basicConfig;
	private ArrayList<Integer> baseConfig;
	private ArrayList<Integer> tempArray;

	public Water(int amount, ArrayList<Integer> originalConfig) {
		thebasicConfig= new ArrayList<Integer>();
		thebasicConfig.addAll(originalConfig);
		this.amount=amount;

	}

	@Override
	public boolean isGoal(ArrayList<ArrayList<Integer>> myList) {
		Iterator<ArrayList<Integer>> it = myList.iterator();
		while(it.hasNext())
		{
			ArrayList<Integer> current = it.next();
			Iterator<Integer> it1 = current.iterator();
			while(it1.hasNext())
			{
				int currentInt=it1.next();
				if(currentInt==amount)
				{
					return true;
				}
				
				
			}
			
			
		}
		return false;
	}

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
	//arraylist<integer> config
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

	@Override
	public int getStart() {
		// TODO Auto-generated method stub
		return 0;
	}

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