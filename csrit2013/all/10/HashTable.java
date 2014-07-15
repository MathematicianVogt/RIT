import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.*;



public class HashTable implements Hash {
	private boolean simple = false;
	private boolean custom = false;
	private HashMap<String,Integer>[] myList;
	private double totalPut = 0;
	private double totalSize;

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

	private void increaseSize() {

		HashMap[] myNewList=new HashMap[2*myList.length +1];
		for(int i =0; i < myList.length;i++)
		{
			Set a = myList[i].keySet();
			java.util.Iterator one = a.iterator();
			while(one.hasNext())
			{
				if (simple) {

					simpleHash((String) one.next(),myNewList.length,myNewList);
				}
				if (custom) {
					customHash((String) one.next(),myNewList.length,myNewList);

				}
				
			}
			
		}
		myList=myNewList;
	
	
	
	}
	
	private void customHash(String input,int length, HashMap[] list) {
		totalPut++;
		if (totalPut / ((double) length) > .75) {
			rehash();
		}

		StringBuilder sb = new StringBuilder();
		for (char c : input.toCharArray())
			sb.append((int) c);

		Integer mInt = new Integer(sb.toString());
		mInt = (int) (mInt + (Math.random()) * Math.pow(4, 6));
		int element = mInt % length;
		if (!list[element].containsKey(input)) {
			list[element].put(input, 1);

		} else {

			list[element].put(input, (int) (list[element].get(input)) + 1);

		}

	}

	private void simpleHash(String input,int length, HashMap[] list) {

		totalPut++;
		if (totalPut / ((double) length) > .75) {
			rehash();
		}
		StringBuilder sb = new StringBuilder();
		for (char c : input.toCharArray())
			sb.append((int) c);

		Integer mInt = new Integer(sb.toString());
		int element = mInt % length;
		if (!list[element].containsKey(input)) {
			list[element].put(input, 1);

		} else {

			list[element].put(input, (int) (list[element].get(input)) + 1);

		}

	}

	public void put(String input) {
		// TODO Auto-generated method stub

		if (simple) {

			simpleHash(input,myList.length,myList);
		}
		if (custom) {
			customHash(input,myList.length,myList);

		}

	}

	@Override
	public void put(String input, int count) {
		// TODO Auto-generated method stub

	}

	@Override
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
	public int imbalance() {
		// TODO Auto-generated method stub
		totalSize = 0;
		for (int i = 0; i < myList.length; i++) {
			if (myList[i].size() > 0) {
				totalSize = totalSize + myList[i].size();
			}

		}

		return (int) ((totalSize / ((double) myList.length)) - 1);
	}

	@Override
	public void rehash() {
		// TODO Auto-generated method stub
		increaseSize();

	}

}
