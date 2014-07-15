

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class DFSGraph implements Graph {
	HashMap<String, ArrayList<String>> myGraph;
	String neighborList = "";
	String finalString="";

	public DFSGraph() {
		myGraph = new HashMap<String, ArrayList<String>>();

	}

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

	@Override
	public String findPath(String start, String finish) {
		// TODO Auto-generated method stub
		return "";
	}

	
	public String toString() {
		for (Entry<String, ArrayList<String>> entry : myGraph.entrySet()) {
			String key = entry.getKey();
			for (String neighbor : myGraph.get(key)) {

				neighborList = neighborList + " " + neighbor + ",";

			}
			neighborList = neighborList.substring(0, neighborList.length() - 1);
			finalString= finalString +  key + ":" + neighborList + "\n";
			neighborList = "";

		}
		return finalString;
	}

}
