// Ryan Vogt
// CS for Tranfers
import java.util.*;
public class UnixAccount {
private static String usrInput;
private static String usrAccount="";
private static String[] usrParts=new String[4];


private void removeDashes()
{
	usrInput=usrInput.replace("-", "");
	
	
	
}
public void parseInput()
{
	
	usrParts=usrInput.split(" ");
	
	
	
	
}

public String buildUsrName()
{
	
	for(int i=0; i<=usrParts.length-1;i++)
	{
		if(i==0 || i==1 || i==2)
		{
			
			usrAccount=usrAccount+ usrParts[i].substring(0,1).toLowerCase();
			
			
			
		}
		else
		{
			usrAccount=usrAccount+usrParts[i].substring(usrParts[i].length()-4);
			
		}
		
		
		
		
	}
		
	return "Unix Account Name: " + usrAccount;
	
	
}



public static void main(String[] args)
{
	Scanner input=new Scanner(System.in);
	System.out.println("Enter First Name, Middle Name, Last Name, and SSN: ");
	usrInput=input.nextLine();
	UnixAccount uObject=new UnixAccount();
	uObject.removeDashes();
	uObject.parseInput();
	System.out.println(uObject.buildUsrName());
	
}


}
