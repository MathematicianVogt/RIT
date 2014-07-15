import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.*;

/*
 * HashTable.java
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
 *A program made to insert n strings into an hashtable 
 *in linear time by minimizing conflicts 
 * 
 * 
 */


public class HashTable implements Hash {
	
	//class varibles
	private boolean simple = false;
	private boolean custom = false;
	private HashMap<String,Integer>[] myList;
	private double totalPut = 0;
	private double totalSize;
	private double counter;
	//@param Size, Type, the size of the hash talbe and type of Htype
	public HashTable(int Size, Htype type) {

		myList = new HashMap[Size];
		for (int i = 0; i < myList.length; i++) {

			myList[i] = new HashMap<String, Integer>();

		}

		if (type == Htype.SIMPLE) {
			simple = true;
			

		}
		if (type == Htype.CUSTOM) {

			custom = true;
			

		}

	}
	//Increases size of Array to gain linear time
	private void increaseSize() {

		HashMap<String,Integer>[] myNewList=new HashMap[2*myList.length +1];
		for(int i=0; i< myNewList.length;i++)
		{
			myNewList[i]=new HashMap<String,Integer>();
			
		}
		
		for(int i =0; i < myList.length;i++)
		{
			Set<String> a = myList[i].keySet();
			Iterator<String> one = a.iterator();
			while(one.hasNext())
			{
				if (simple) {

					simpleHash( one.next(),myNewList.length,myNewList,0);
				}
				if (custom) {
					customHash( one.next(),myNewList.length,myNewList,0);

				}
				
			}
			
		}
		myList=myNewList;
	
	
	
	}
	//@param input,length,list,value
	//used as a custom hash code, used to gain 0 balance
	private void customHash(String input,int length, HashMap<String,Integer>[] list,int value) {
		
		char[] charArray;
		int mInt=0;
		totalPut++;
		if (totalPut / ((double) length) > .75) {
			
			rehash();
		}

		charArray=input.toCharArray();
		for(int i=0; i<charArray.length;i++)
		{
			mInt=mInt + (int)charArray[i];
			
		}
		mInt = (int) ( Math.pow(mInt, 2)+ Math.random()*Math.pow(mInt, 2));
		int element = mInt % length;
		if (!list[element].containsKey(input)) {
			
			if(value==0)
			{
			list[element].put(input, 1);
			}
			else
			{
				list[element].put(input, value);	
				
			}
		} else {

			if(value==0)
			{
			list[element].put(input, (int) (list[element].get(input)) + 1);
			}
			else
			{
				list[element].put(input, value);	
				
			}
		}

	}
	//@param input,length,list,value
	//Basic hash, converting to ascii values and dividing
	// by mod length
	private void simpleHash(String input,int length, HashMap<String,Integer>[] list,int value) {
		
		char[] charArray;
		int mInt=0;
		totalPut++;
		if (totalPut / ((double) length) > .75) {
			rehash();
		}
		
		//
		charArray=input.toCharArray();
		for(int i=0; i<charArray.length;i++)
		{
			mInt=mInt + (int)charArray[i];
			
		}
		
		
		int element = mInt % length;

		//
		if (!list[element].containsKey(input)) {
			list[element].put(input, 1);

		} else {

			list[element].put(input, (int) (list[element].get(input)) + 1);

		}

	}

	//inserts a string into the hashmap
	public void put(String input) {
		// TODO Auto-generated method stub

		if (simple) {

			simpleHash(input,myList.length,myList,0);
		}
		if (custom) {
			customHash(input,myList.length,myList,0);

		}

	}

	@Override
	//@param input the input to hashtable and count the original value
	//inserts element with a specific original value
	public void put(String input, int count) {
		// TODO Auto-generated method stub
		if (simple) {

			simpleHash(input,myList.length,myList,count);
		}
		if (custom) {
			customHash(input,myList.length,myList,count);

		}

		
	
	}

	@Override
	//@param key, finding the value that assosicates with key in the 
	//hashtable
	public int get(String key) {
		// TODO Auto-generated method stub
		for (int i = 0; i < myList.length; i++) {

			if (myList[i].containsKey(key)) {
				return (int) myList[i].get(key);

			}

		}
		return 0;
	}

	@Override
	
	//Gives a number representation of the balance of the table
	public int imbalance() {
		// TODO Auto-generated method stub
		totalSize = 0;
		counter=0;
		for (int i = 0; i < myList.length; i++) {
			if (myList[i].size() > 0) {
				totalSize = totalSize + myList[i].size();
				counter++;
			}
			
		}
		return (int)(totalSize/counter) - 1;
	}

	@Override
	//called when hashtable reaches 75% maximum hold.
	public void rehash() {
		// TODO Auto-generated method stub
		increaseSize();

	}

}
