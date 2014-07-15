import java.lang.*;
import java.util.*;

/*
 * Woolie.java
 *
 * Version:
 *     $Id: Woolie.java,v 1.3 2013/11/18 15:39:38 rhv5251 Exp rhv5251 $
 *
 * Revisions:
 *     $Log: Woolie.java,v $
 *     Revision 1.3  2013/11/18 15:39:38  rhv5251
 *     done.
 *
 *     Revision 1.2  2013/11/18 15:37:38  rhv5251
 *     done
 *
 *     Revision 1.1  2013/11/15 21:38:49  rhv5251
 *     Initial revision
 *
 */
/*
 * 
 * @author Ryan Vogt
 * Woolie.java
 * A class made to track the journey
 * of multiple woolies crosssing a bridge
 * 
 */

public class Woolie implements Runnable {
	// class variables
	private String myName;
	private int myCrossingTime;
	private String myDestination;
	private int counter = 1;
	private Bridge myBridge;

	// Constructor
	// @param myName, the woolies name
	// @param myCrossingTime, the woolies cross time amount in seconds
	// @param myDestination, the woolies destination
	public Woolie(String myName, int myCrossingTime, String myDestination,Bridge myBridge) {

		this.myName = myName;
		this.myCrossingTime = myCrossingTime;
		this.myDestination = myDestination;
		this.myBridge=myBridge;

	}

	// implementation of the runnable interfaces run method
	public void run() 
	 {  try{
		System.out.println(myName + " has arrived at the bridge");
		myBridge.enterBridge();
		System.out.println(myName + " is starting to cross.");
		while (counter <= myCrossingTime) {

			System.out.println("    " + myName + " " + counter + " "
					+ "seconds.");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {

			}

			counter++;
		}
		System.out.println(myName + " leaves at " + myDestination + ".");
		myBridge.leaveBridge();
        }
        catch(InterruptedException e)
        {
        }
	}

	public static void main(String[] args) {
		// information to create wookies
		String[] names = { "cindy", "robert", "ben", "sally" };
		
		String[] Destinations = { "car", "troy", "zoo", "farm" };
		
		int[] CrossingTimes = { 1, 5, 3, 4 };
		Bridge thisBridge = new Bridge(2);
		
		
		ArrayList<Thread> myThreads = new ArrayList<Thread>();
		
		
		for (int i = 0; i < names.length; i++) {
			// add wooolie objects to a list
			myThreads.add(new Thread(new Woolie(names[i], CrossingTimes[i],
					Destinations[i],thisBridge)));

		}
		Iterator<Thread> it = myThreads.iterator();
		while (it.hasNext()) {
			// start every woolies journey in the list.
			Thread thisThread = it.next();
			thisThread.start();

		}

	}

	// end
}
