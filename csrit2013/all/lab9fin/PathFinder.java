  /*
     * PathFinder.java
     *
     * Version:
     *     1.4
     *
     * Revisions:
     * 1.1 1.2 1.3    
     */ 

/*
@author Ryan Vogt
Program Driver for graph searching program
Reads a file, creates a graph and is used as a
interface for finding a possible path within the graph
*/
import java.io.*;
import java.util.Scanner;

public class PathFinder {
	// class variables
	private String[] keyVal;
	private BufferedReader br;
	private String line;
	private static DFSGraph myGraph;

	/*
	 * @param arg - the arguement send through commandline to read a file public
	 * constructor to be able to make a buffereader to read the file
	 */
	public PathFinder(String arg) throws FileNotFoundException {

		br = new BufferedReader(new FileReader(arg));

	}

	/*
	 * Reads a given file, takes the tuple of two values and adds them to the
	 * graph in as a key and value.
	 */
	private void Read() throws IOException {

		while ((line = br.readLine()) != null) {
			keyVal = line.split(" ");
			myGraph.add(keyVal[0], keyVal[1]);
		}
		br.close();
	}

	// main method
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(System.in);
		boolean done = false;
		String start;
		String finish;
		// make object of classes
		PathFinder myPath = new PathFinder(args[0]);
		myGraph = new DFSGraph();
		myPath.Read();
		System.out.println(myGraph.toString());
		System.out.println("Enter source (or 'quit') and destination when prompted.");

		// loop to find different paths in graph
		while (!done) {
			System.out.print("Source:");
			start = in.nextLine();
			if (start.equals("quit") || start.equals("Quit")) {
				System.exit(0);
			}
			System.out.print("Destination:");
			finish = in.nextLine();
			if (finish.equals("quit") || finish.equals("Quit")) {

				System.exit(0);

			}
			System.out.println(myGraph.findPath(start, finish));

		}

	}
}
