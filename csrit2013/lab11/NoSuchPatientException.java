//  Trudy Howles
//  Solution for CSX labs 4 and 5 
//  20041005 rwd added default constructor, per Javadoc

public class NoSuchPatientException extends Exception {
    public NoSuchPatientException () {
        super () ;
    }

    public NoSuchPatientException (String msg) {
        super (msg);
    }
}
