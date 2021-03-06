/**
 * PatientComparator.java
 *
 *  1.4
 *
 *  Revisions:
 *  1.3
 *  1.2
 *
 *
 *@author Ryan Vogt
 *A program that will compare two object
 *return their comprison values.
 *
 **/
import java.util.*;
//comparator object which compares two objects, object of this class 
//will be a comparator

public class PatientComparator implements Comparator {
	private Patient patient1;
	private Patient patient2;

	public int compare(Object p1, Object p2) throws ClassCastException {

		patient1 = (Patient) p1;
		patient2 = (Patient) p2;

		return patient1.getName().compareTo(patient2.getName());

	}

	public boolean equal(Object o1) {
		return true;

	}

}
