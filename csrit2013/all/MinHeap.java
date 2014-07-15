/*
@author Ryan Vogt
Program makes a min heap and changes based on what is inserted and removed. The min structure will be maintained. 
*/

    import java.util.ArrayList;
    import java.util.Arrays;
    import java.lang.Comparable;

    public class MinHeap<T extends Comparable<? super T>> implements Heap<T> {
       private ArrayList<T> elements;
  
    public MinHeap () {
        //makes elements an arraylist, add a null element at 0 so tree works correctly when accessing, removing, and adding
	//children
        this.elements = new ArrayList<T>();
        this.elements.add( null );
        
    }
    //@param array the array given to make an original heap
    public MinHeap ( T[] array ) {
        
        this.elements = new ArrayList<T>();
        
        for ( T element : array ) {
            
            this.insert( element );
            
        }
        
        this.heapify();
        
    }
    
    public T peek () {
	//show first element in heap        
        return this.elements.get( 1 );
    }
    //removes top element of heap, then restructures to make sure
    //heap is maintained
    public T remove () {
        
        T minimum = this.elements.get( 1 );
	if (minimum==null)
	{
	minimum=this.elements.get(2);
	}
        T element = this.elements.get( elements.size() - 1 );
        
        // Remove last element
        if ( this.elements.size() > 0 ) {
            
            this.elements.set( 1,element );
            
            this.heapify();
            
        }
        else {
            
            return null;
            
        }
        
        return element;
        
    }
    //@param value, what is to be interested into the heap
    public void insert ( T value ) {
        
        // Adding a new node that contains nothing
        this.elements.add( null );
        
	//gain index of this null node
        int index = this.elements.size() - 1;
        
        //Move parents that are larger than our new element down our heap
        while ( index > 1 && this.parent( index ).compareTo( value ) > 0 ) {
            
            this.elements.set( index, this.parent( index ) );
            index = this.parentIndex( index );
            
        }
        
        // Place our new element into the new slot
        this.elements.set( index, value );
        
    }
    
    
    // Validates the min heap condition
    private void heapify () {
        
        T root = this.elements.get( 1 );
        
        int lastIndex = this.elements.size() - 1;
        
        int index = 1;
        boolean moreNodes = true;
        
        while ( moreNodes ) {
            
            int childIndex = leftChildIndex( index );
            
            if ( this.leftChildIndex( index ) <= lastIndex ) {
                
                //Determine the smaller child
                
                T child = leftChild( index );
                
                //If right child is smallest, use right child.
                if ( rightChildIndex( index ) <= lastIndex &&
                rightChild( index ).compareTo( child ) < 0 ) {
                    
                    childIndex = rightChildIndex( index );
                    child = rightChild( index );
                    
                }
                
                //Check if larger child is smaller than root
                if ( child.compareTo( root ) < 0 ) {
                    
                    this.elements.set( index, child );
                    index = childIndex;
                    
                }
                else {
                    
                    //Roots is smaller than both children
                    moreNodes = false;
                    
                }
                
            }
            
            //Store root in newly vacant slot
            this.elements.set( index, root );
            
        }
        
        
    }
    
    // For convenience
    //@return leftchild of parent index
    //@param index, the index of element
    private int leftChildIndex ( int index ) {
        
        return 2 * index;
        
    }
    //@return right child of parent index
    //@param index, the index of element
    private int rightChildIndex ( int index ) {
        
        return 2 * index + 1;
        
    }
    //@return find the parent for a element
    //@param index, the index of element
    private int parentIndex ( int index ) {
        
        return index / 2;
        
    }
    //@return gives left child of a node index
    //@param index, the index of element
    private T leftChild ( int index ) {
        
        return this.elements.get( 2 * index );
        
    }
    //@return give right child of a node index
    //@param index, the index of element
    private T rightChild ( int index ) {
        
        return this.elements.get( 2 * index + 1 );
        
    }
    //@return find parent of element
    //@param index, the index of element
    private T parent ( int index ) {
        
        return this.elements.get( index / 2 );
        
    }
    //@return makes a arraylist of t objects into string form 
    public String toString () {
        
        return this.elements.toString();
        
    }
    public static void main(String[] args) {
        //make array to create into a sorted 
        Integer[] array = { 1,1,17,25,34, 75,24,234325,343425,34 };
        //makes a heap based on given array
        MinHeap<Integer> ourHeap = new MinHeap<Integer>( array );
        //use methods on heap
        ourHeap.insert( 100 );
	    ourHeap.insert( 20 );
	    ourHeap.insert( 30 );
	    System.out.println(ourHeap);
	    System.out.println("Expected: [1, 1, 17, 24, 34, 20, 25, 234325, 343425, 34, 100, 75, 30]");	
	    //remove one	
	    ourHeap.remove();
	    //show
        System.out.println(ourHeap);
	    System.out.println("Expected: [1, 17, 20, 24, 34, 30, 25, 234325, 343425, 34, 100, 75, 30]");
	    //make new heap with no original data
	    MinHeap<Integer> ourHeapTwo = new MinHeap<Integer>();
	    //fill it up
	    ourHeapTwo.insert( 100 );
	    ourHeapTwo.insert( 20 );
	    ourHeapTwo.insert( 30 );
	    ourHeapTwo.insert( 1000 );
	    ourHeapTwo.insert( 200 );
	    ourHeapTwo.insert( 300 );
	    System.out.println(ourHeapTwo);
	    System.out.println("Expected:[null, 20, 100, 30, 1000, 200, 300]");
	    //take one off	
	    ourHeapTwo.remove();
	    //show changes	
	    System.out.println(ourHeapTwo);
	    System.out.println("Expected: [null, 30, 100, 300, 1000, 200, 300]");
	    }
    
}


