/*
 * Heap.java
 *
 * $Id$
 *
 * $Log$
 */

/**
 * A template for classes that want to support heap functionality.
 *
 * @author: R. Duncan (rwd@cs.rit.edu)
 */


public interface Heap<T>
{
    public T peek() ;             // return the value at the top of the heap
    public T remove() ;           // remove and return the value at the top of the heap
    public void insert(T value) ; // insert a new value into the heap
}
