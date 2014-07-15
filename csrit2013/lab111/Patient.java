/*
 * Patient.java
 * 
 * Version: 
 *     $Id: Patient.java,v 1.2 2003/09/22 23:47:10 tmh Exp tmh $
 * 
 * Revisions: 
 *     $Log: Patient.java,v $
 *     Revision 1.2  2003/09/22 23:47:10  tmh
 *     *** empty log message ***
 *
 *     Revision 1.1  2003/09/22 20:27:49  tmh
 *     Initial revision
 *
 *
 */

import java.util.*;

/**
 * A class to simulate a patient in a doctor's office. Objects of this
 * type know the first and last name and age of the patient, and will
 * contain a medication history.
 *
 * The medication history will contain Medication objects and must be
 * maintained in the same order the medication is prescribed.
 * 
 * @author Lois Rixner
 * @author Trudy Howles tmh@cs.rit.edu
 */

public class Patient {
	private String lastName;
	private String firstName;
	private int myAge;
	private ArrayList<Medication> myMeds;
	Medication patientMed;

/** 
* Constructor for this object.
*
* @param myLast the last name of this patient
* @param myFirst the first name of this patient
* @age age - the age of this patient
*/

    public Patient( String myLast, String myFirst, int age) {
    	lastName=myLast;
    	firstName=myFirst;
    	myAge=age;
    	myMeds=new ArrayList<Medication>();
    
    }

    /**
     * Return the patient's full name in the form: 
     * <br>
     *     last name comma first name.
     * 
     * @return the patient's full name.
     */

    public String getName() {
        return  lastName + "," + firstName ;
    }

    /** 
     * Return this patient's age.
     * 
     * @return the patient's age
     */

    public int getAge() {
        return myAge;
    }


    /**
    * Get the number of medications in this patient's history.
    *
    * @return the number of medications in this patient's history
    *
    */

    public int getNumberOfMeds() {
        int medCount=0;
    	Iterator<Medication> it = myMeds.iterator();
        while(it.hasNext())
        {
        	
        	medCount++;
        }
        
    	
    	return medCount;
    }
 

    /**
    * Record a new medication for this patient.
    *
    * @param Name	the name of this medication 
    * @param generic	boolean true = generic otherwise false
    *
    */

    public void recordNewMed(String name, boolean generic ) {                          
    	patientMed=new Medication(name,generic);
    	myMeds.add(patientMed);
    	
    	
    
    }

    /**
    * Remove this medication for this patient.
    *
    * @param med     The medication to remove for this patient.
    *			If this medication is not found on this patient's
    *			medication list, return false.  Otherwise,
    *			remove the first occurrence of this medication.
    *
    * @return true if this medication was removed, otherwise false
    *
    */

    public boolean removeMed(String med) {
    	Iterator<Medication> it = myMeds.iterator();
    	while(it.hasNext())
    	{
    		
    		if(it.next().getName().equals(med))
    		{
    			
    			return myMeds.remove(it.next());
    			
    		}
    		
    	}
    	
    	
    	return false;
    }



    /**
    * Print all medications in this patient's medication history
    * in the order the medication was prescribed.
    *
    */

    public void printMedicationHistory () {    
    	String medList="";
    	Iterator<Medication> it= myMeds.iterator();
    	while(it.hasNext())
    	{
    		
    		medList=medList + it.next().getName() + "\n";
    		
    	}
    }


    /**
     * Produce a printable version of this Patient object.
     * The format must be: the first and last names as formatted in 
     * the getName() method followed by a single space, then the age
     * followed by a new line.
     *
     * Format each medication recorded for this patient.  
     * Format medications by calling your toString() method in the Medication
     * class  and append a new line after each one.
     * Format medications in the order prescribed.
     * 
     * @return a String version of this Patient.
     */

    public String toString() {
        String medList="";
        Iterator<Medication> it =myMeds.iterator();
        while(it.hasNext())
        {
        	medList=medList + it.next().toString() + "\n";
        	
        }
    	
    	return getName() + " " +"\n" + medList;
     }

} // Patient
