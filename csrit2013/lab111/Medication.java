/*
 * Medication.java
 * 
 * Version: 
 *     $Id: Medication.java,v 1.1 2003/09/22 20:27:49 tmh Exp tmh $
 * 
 * Revisions: 
 *  $Log: Medication.java,v $
 *  Revision 1.1  2003/09/22 20:27:49  tmh
 *  Initial revision
 *
 *
 *
 */

import java.util.*;

/**
 * A class to simulate medications prescribed to patients in a doctor's 
 * office. 
 *
 * @author Trudy Howles tmh@cs.rit.edu
 */

public class Medication {

    /**
     * The name of the medication.
     */
    private String name; 
    
    /**
     * Indicates if this drug is generic
     */
    private boolean generic;


    /** 
     * Constructor for this object.
     *
     * @param name	the name of the medication
     * @param generic	a boolean indicating if a generic drug
     */

    public Medication( String name, boolean generic ) {
	this.name = name;
        this.generic = generic;
    }

    /**
     * Return the medication name
     * 
     * @return the medication's name
     */

    public String getName() {
        return name;
    }

    /** 
     * Return true if a generic drug, otherwise false.
     * 
     * @return true if a generic drug
     */

    public boolean isGeneric () {
        return generic; 
    }



    /**
     * Produce a printable version of this Medication object
     * the medication name, one space, then "Generic Dispensed" if
     * this was a generic medication or "Not Generic" if not.
     * 
     * @return a String version of this Medication.
     */

    public String toString() {
        String ret = name + " " ;
        if (generic) 
		ret += "Generic Dispensed";
	else 
		ret += "Not Generic";
        return ret;
    }

} // Medication 
