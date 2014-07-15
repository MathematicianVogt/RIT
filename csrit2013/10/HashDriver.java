/*
 * HashDriver.java
 *
 * Version:
 *     1.4
 *
 * Revisions:
 *     1.3
 *     1.2
 */
/*
 *@author Ryan Vogt
 * A program that runs the HashTable program
 * By reading info and sending it to the class
 * 
 */

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class HashDriver{
	
	//class variables
	private BufferedReader br;
	private String line;
	private HashTable myHash;
	private String[] keyVal;
	//constructor
	//@param arg the file arguement
	//@param input the hashtable assinged to the driver
	public HashDriver(String arg,HashTable input) throws FileNotFoundException
	{
	
		br = new BufferedReader(new FileReader(arg));
		myHash=input;
	}
	//@param arg, the file to be read
	//Reads a file and puts all elements into a hashtable
	private void ReadandOrder(String arg) throws IOException
	{
	 
		while ((line = br.readLine()) != null) {
		   keyVal=line.split(" ");
		   for(int i=0; i<keyVal.length; i++)
		   {
			  myHash.put(keyVal[i]);
			  
			   
		   }
		}
		br.close();
	}
	//main method to run the program to find imblances between different runs
	public static void main(String[] args) throws IOException {
		HashTable myTable=new HashTable(89,Hash.Htype.SIMPLE);
		HashTable myTable2=new HashTable(89,Hash.Htype.CUSTOM);
		HashDriver drive1 =new HashDriver(args[0],myTable);
		drive1.ReadandOrder(args[0]);
		HashDriver drive2 =new HashDriver(args[0],myTable2);
		drive2.ReadandOrder(args[0]);
		System.out.println(myTable.imbalance());
		System.out.println(myTable2.imbalance());
		
		
		
		
	}
}
