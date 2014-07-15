import java.util.* ;

public interface Hash
{
    // For this assignment, the put(), get() and rehash() functions
    // should each have two different ways to calculate the hash
    // key for the String type key. The SIMPLE calculation will be
    // used for hash tables created with an Htype of 'SIMPLE' and
    // a custom calculation will be used for hash tables created
    // with an Htype of 'CUSTOM'


    public enum Htype { SIMPLE, CUSTOM } ;

    // Constructor is not part of the interface, but is given here
    // as guidance. 'size' is the initial size, 'type' is either
    // 'SIMPLE' or CUSTOM', which will determine which hashing 
    // function the hash table will use.
    // public HashTable( int size, Htype type ) ;

    // Method to put values into the table. It will
    // create the key if it doesn't already exist and set its
    // value to 1. If the key already exists, it will increment
    // the value.
    public void put( String key ) ;

    // Additional put method, allows you to set
    // the value associated with a key
    public void put( String key, int count ) ;

    // Returns the value associated with 'key' from the table
    public int get( String key ) ;

    // Returns the imbalance of the current table
    public int imbalance( ) ;

    // Creates a new table of 2*size + 1 elements and
    // rehashes the current table entries to the new
    // table. Makes the new table the hash table.
    public void rehash( ) ;
}
