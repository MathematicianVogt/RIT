

/*
 * @author Ryan Vogt
 * A program made to arrange store locations
 * based on x pos on a one dimensional line.
 * Resulting in print out of best approach
 * Mapping code gained from 
 * http://stackoverflow.com/questions/13852725/java-map-sort-by-value
 */

import java.awt.*;
import java.awt.List;
import java.util.*;
import java.util.Map.Entry;
import java.lang.*;

public class StoreLocation {
	// class variables
	private Scanner myInput;
	private static String[] storeArray;
	private static int[] storeArrayTwo;
	private double midpoint;
	private double median;
	private double average;
	private int arrMidpoint;
	private double meanSum;
	private double two = 2;
	private double averageDistance=0;
	private double midpointDistance=0;
	private double medianDistance=0; 

	// constructor creates input stream
	public StoreLocation() {

		myInput = new Scanner(System.in);

	}

	// reads input from line, puts it into an array
	private void read() {
		System.out.println("Enter Stores by Number");
		String theInput = myInput.nextLine();
		storeArray = theInput.split(" ");

	}

	// changes input from string to integers
	// @param arrayToSort changes elements to integers
	private void changeStringToInt(String[] arrayToSort) {
		storeArrayTwo = new int[arrayToSort.length];
		for (int i = 0; i < arrayToSort.length; i++) {
			storeArrayTwo[i] = Integer.parseInt(arrayToSort[i]);

		}

	}

	// sort elements of an array with merge sort algorithm
	// @param arrayToSort, the array to be sorted
	private void mergeSort(int[] arrayToSort) {
		if (arrayToSort.length > 1) {
			int q = arrayToSort.length / 2;

			int[] leftArray = Arrays.copyOfRange(arrayToSort, 0, q);
			int[] rightArray = Arrays.copyOfRange(arrayToSort, q,
					arrayToSort.length);

			mergeSort(leftArray);
			mergeSort(rightArray);

			merge(arrayToSort, leftArray, rightArray);
		}

	}

	// @param a, l ,r the arrays needed to be merged back together
	// which were seperated by merge sort process.
	private void merge(int[] a, int[] l, int[] r) {

		int totElem = l.length + r.length;
		int i, li, ri;
		i = li = ri = 0;
		while (i < totElem) {
			if ((li < l.length) && (ri < r.length)) {
				if (l[li] < r[ri]) {
					a[i] = l[li];
					i++;
					li++;
				} else {
					a[i] = r[ri];
					i++;
					ri++;
				}
			} else {
				if (li >= l.length) {
					while (ri < r.length) {
						a[i] = r[ri];
						i++;
						ri++;
					}
				}
				if (ri >= r.length) {
					while (li < l.length) {
						a[i] = l[li];
						li++;
						i++;
					}
				}
			}
		}

	}

	// @param numberArray
	// find midpoint of sorted array
	public void midpoint(int[] numberArray) {
		midpoint = (numberArray[numberArray.length - 1] + numberArray[0]) / two;

	}

	// @param numberArray
	// find mean of sorted array
	public void mean(int[] numberArray) {
		meanSum = 0;
		for (int e : numberArray) {

			meanSum = meanSum + (double) e;

		}

		average = meanSum / ((double) (numberArray.length));

	}

	// @param numberArray
	// find median of sorted array
	public void median(int[] numberArray) {
		if (numberArray.length % 2 == 0) {
			arrMidpoint = (numberArray.length) / 2;
			median = (numberArray[arrMidpoint - 1] + numberArray[arrMidpoint])
					/ two;

		} else {
			arrMidpoint = ((numberArray.length + 1) / 2) - 1;
			median = numberArray[arrMidpoint];

		}

	}

	// prints out the sorted array
	public void printArray() {

		for (int e : storeArrayTwo) {
			System.out.print(e + " " + "\n");

		}

	}

	// prints out the mean
	public void calcDistances()
	{
		
		for (double e: storeArrayTwo)
		{
			averageDistance=averageDistance + Math.abs(average-e);
			
		}
		for(double f: storeArrayTwo)
		{
			midpointDistance=midpointDistance + Math.abs(midpoint-f);
			
		}
		for(double g: storeArrayTwo)
		{
			
			medianDistance=medianDistance + Math.abs(median -g);
			
		}
		
	}
	
	public void printMean() {

		System.out.println(average);
	}

	// prints out the mean
	public void printMedian() {

		System.out.println(median);
	}

	// prints out the mean
	public void printMidpoint() {

		System.out.println(midpoint);
	}

	/*
	 * takes 3 calculated values mean,median, and midpoint
	 * put them into a map, sort the map then print out output
	 * 
	 */
	@SuppressWarnings("unchecked")
	public void generateStratOutput() {
		HashMap<String, Double> strats = new HashMap<String, Double>();
		strats.put("3", average);
		strats.put("1", midpoint);
		strats.put("2", median);
		ArrayList<Entry> a = new ArrayList<Map.Entry>(strats.entrySet());
		Collections.sort(a, new Comparator() {
			@SuppressWarnings("rawtypes")
			public int compare(Object o1, Object o2) {
				Map.Entry e1 = (Map.Entry) o1;
				Map.Entry e2 = (Map.Entry) o2;
				return ((Comparable) e1.getValue()).compareTo(e2.getValue());
			}
		});
		String outPutString = "";
		for (Map.Entry e : a) {
			outPutString = outPutString + " " + e.getValue() + "(Strategy "
					+ e.getKey() + " )";
		}
		System.out.println("Optimal Position: " + outPutString);
		System.out.println("sum of distance must travel for mean method " + averageDistance);
		System.out.println("sum of distance must travel for median method " + medianDistance);
		System.out.println("sum of distance must travel for midpoint method " + midpointDistance);

	}

	public static void main(String[] args) {

		StoreLocation myLocation = new StoreLocation();
		myLocation.read();
		myLocation.changeStringToInt(storeArray);
		myLocation.mergeSort(storeArrayTwo);
		myLocation.mean(storeArrayTwo);
		myLocation.median(storeArrayTwo);
		myLocation.midpoint(storeArrayTwo);
		myLocation.calcDistances();
		myLocation.generateStratOutput();

	}

}
