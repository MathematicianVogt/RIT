  /*
     * DFSGraph.java
     *
     * Version:
     *     1.4
     *
     * Revisions:
     * 1.1 1.2 1.3    
     */ 


/*
 * 
 * 
 * @author Ryan Vogt
 * A program made to go through a graph
 * to find a path from a starting location
 * to another. Returns no path if path doesnt
 * exist
 * 
 */

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class DFSGraph implements Graph {
	// class variables
	private HashMap<String, ArrayList<String>> myGraph;
	private String neighborList = "";
	private String finalString = "";
	private String path = "";

	// constructor
	public DFSGraph() {
		myGraph = new HashMap<String, ArrayList<String>>();

	}

	// @param key - the key for the hashmap
	// @param value - the value that will be put into the value list
	// adds a key and value to a hasmap, if key exists only adds value
	// to list
	@Override
	public void add(String key, String value) {
		// TODO Auto-generated method stub
		if (!myGraph.containsKey(key)) {
			myGraph.put(key, new ArrayList<String>());
			myGraph.get(key).add(value);

		} else {

			myGraph.get(key).add(value);

		}
		if (!myGraph.containsKey(value)) {
			myGraph.put(value, new ArrayList<String>());
			myGraph.get(value).add(key);

		} else {

			myGraph.get(value).add(key);
		}

	}

	// @param start- the starting node of the graph
	// @param visted- the list of points from start to current place
	// @param finish - the hopeful endpoint of where to find a path to
	// tells if a path is possible or not, continually adds
	// nodes to the list that it goes through if path is found
	// if no path is found returns false, if path is found returns true
	private boolean DFS(String start, ArrayList<String> visted, String finish) {

		if (start.equals(finish)) {

			return true;

		}
		for (String newNode : myGraph.get(start)) {
			if (!visted.contains(newNode)) {
				visted.add(newNode);
				if (!DFS(newNode, visted, finish) == false) {
					return true;
				}
			}
		}

		return false;

	}

	/*
	 * @param start- the starting node
	 * 
	 * @param finish - the ending node
	 * 
	 * @return - the path that was found method attempts to find a path in the
	 * graph should it exist, if it does will give a good path if not will
	 * return no path exists.
	 */
	public String findPath(String start, String finish) {
		// TODO Auto-generated method stub
		ArrayList<String> visted = new ArrayList<String>();
		visted.add(start);
		boolean path = DFS(start, visted, finish);
		if (path == false) {

			return "\n" + start + " to " + finish + " is: \n" + "None" + "\n";

		} else {
			return "\n" + start
					+ " to "
					+ finish
					+ " is: \n"
					+ visted.toString().substring(1,
							visted.toString().length() - 1) + "\n";
		}
	}

	/*
	 * Override of toString method Make a graph into its string representation
	 */
	public String toString() {
		for (Entry<String, ArrayList<String>> entry : myGraph.entrySet()) {
			String key = entry.getKey();
			for (String neighbor : myGraph.get(key)) {

				neighborList = neighborList + " " + neighbor + ",";

			}
			neighborList = neighborList.substring(0, neighborList.length() - 1);
			finalString = finalString + key + ":" + neighborList + "\n";
			neighborList = "";

		}
		return finalString.substring(0, finalString.length() - 1) + "\n";
	}

}
