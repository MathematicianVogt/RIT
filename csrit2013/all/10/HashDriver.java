import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class HashDriver{
	private BufferedReader br;
	private String line;
	private HashTable myHash;
	private String[] keyVal;
	
	public HashDriver(String arg,HashTable input) throws FileNotFoundException
	{
	
		br = new BufferedReader(new FileReader(arg));
		myHash=input;
	}
	private void ReadandOrder(String arg) throws IOException
	{
	 
		while ((line = br.readLine()) != null) {
		   keyVal=line.split(" ");
		   for(int i=0; i<keyVal.length; i++)
		   {
			  myHash.put(keyVal[i]); 
			   
		   }
		}
		br.close();
	}

	public static void main(String[] args) throws IOException {
		HashTable myTable=new HashTable(89,Hash.Htype.SIMPLE);
		HashTable myTable2=new HashTable(89,Hash.Htype.CUSTOM);
		HashDriver drive1 =new HashDriver(args[0],myTable);
		HashDriver drive2 =new HashDriver(args[0],myTable2);
		System.out.println(myTable.imbalance());
		System.out.println(myTable2.imbalance());
		
		
		
		
	}
}
