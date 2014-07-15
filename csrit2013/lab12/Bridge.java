    /*
    * Bridge.java
    *
    * Version:
    *     $Id: Bridge.java,v 1.4 2013/11/18 15:37:47 rhv5251 Exp rhv5251 $
    *
    * Revisions:
    *     $Log: Bridge.java,v $
    *     Revision 1.4  2013/11/18 15:37:47  rhv5251
    *     done.
    *
    *     Revision 1.3  2013/11/18 14:54:31  rhv5251
    *     done
    *
    */
 
    /*
    *@Author Ryan Vogt
    *A class to represent a Bridge to
    *be crossed
    */
    
    
   
    import java.util.concurrent.Semaphore;
    public class Bridge
    {   //class variables
        private Semaphore thisLock;
	    //class contstructor
	    public Bridge(int maxWoolieNumber)
	    {
		    thisLock=new Semaphore(maxWoolieNumber);
		   
		
		
	    }
	    //locks the bridge so only 'n' troll can
	    //travel
	    public void enterBridge() throws InterruptedException
	    {
		    thisLock.acquire();
		
		
		
	    }
	    //unlocks the bridge so another troll
	    //can cross up to max of n trolls
	    public void leaveBridge()
	    {
		
		    thisLock.release();
	    }
	
    }
