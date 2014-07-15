//imports
import java.lang.*;
import java.util.*;

/*
 * ManyThreads.java
 *
 * Version:
 *     $Id: ManyThreads.java,v 1.1 2013/11/15 00:13:11 rhv5251 Exp rhv5251 $
 *
 * Revisions:
 *     $Log: ManyThreads.java,v $
 *     Revision 1.1  2013/11/15 00:13:11  rhv5251
 *     Initial revision
 *
 */

/*
 *
 *
 *@author Ryan Vogt
 * Many Threads class, used to make n threads and have them print themselves out
 */

public class ManyThreads extends Thread {
	// class variable
	private int threadNumber;

	// class contructor
	// @param threadNumber, the unique thread number that represents
	// a thread
	public ManyThreads(int threadNumber) {

		this.threadNumber = threadNumber;

	}

	// implementation of the run method from the Thread class
	public void run() {
		System.out.println("Hello I am thread " + threadNumber);

	}

	public static void main(String[] args) {

		// check if their is good imput either if it existences
		// or if it is numeric, reports errors if not, continues if
		// everything is okay.As a result prints out all threads by their
		// numbers
		if (args.length == 0) {

			System.err.println("Usage:  java ManyThreads number-of-threads");
			System.exit(0);

		}
		// fill an arraylist with threads
		try {
			ArrayList<ManyThreads> myThreads = new ArrayList<ManyThreads>();
			for (int i = 0; i <= Integer.parseInt(args[0]); i++) {
				myThreads.add(new ManyThreads(i));

			}
			// make each thread print out itself.
			Iterator<ManyThreads> it = myThreads.iterator();
			while (it.hasNext()) {
				ManyThreads thisThread = it.next();
				thisThread.start();

			}
		} catch (NumberFormatException e) {
			System.err.println("ManyThreads:  Invalid number");
		}
		// end
	}

}
