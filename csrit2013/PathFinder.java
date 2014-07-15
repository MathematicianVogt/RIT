

import java.io.*;
import java.util.Scanner;


public class PathFinder {
	private String[] keyVal;
	private BufferedReader br;
	private String line;
	private static DFSGraph myGraph;
	
	public PathFinder(String arg) throws FileNotFoundException
	{
		
		br = new BufferedReader(new FileReader(arg));

	}
	private void Read() throws IOException
	{
	    System.out.println("hi");
		while ((line = br.readLine()) != null) {
		   keyVal=line.split(" ");
		   myGraph.add(keyVal[0], keyVal[1]);
		}
		br.close();
	}

	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(System.in);
		boolean done=false;
		String start;
		String finish;
		PathFinder myPath=new PathFinder(args[0]);
		myGraph=new DFSGraph();
		myPath.Read();
		System.out.println(myGraph.toString());
		System.out.println("gfdg");
		System.exit(0);
		while(!done)
		{
			System.out.println("Source:");
			start=in.nextLine();
			if(start.equals("quit"))
			{
				System.exit(0);
			}
			System.out.println("Destination:");
			finish=in.nextLine();
			if(finish.equals("quit"))
			{
				
				System.exit(0);
				
			}
			System.out.println(myGraph.findPath(start,finish));
					
			
			
			
			
		}
		
	}
}
