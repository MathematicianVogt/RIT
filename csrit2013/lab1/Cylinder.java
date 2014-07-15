import java.util.*;
public class Cylinder {
private static double height;
private static double radius;
private static double cylinderArea;
// Ryan Vogt
// CS for Tranfers
private void Getinput()
{
	Scanner input= new Scanner(System.in);
	try
	{
	System.out.println("Enter cylinder height: ");
	height=input.nextDouble();
	System.out.println("Enter cylinder radius: ");		
	radius=input.nextDouble();	
		
		
		
		
	}
	catch(InputMismatchException a)
	{
		System.out.println("Input must be a number, cannot be String");
		System.exit(0);
		
	}
	
	
	
}
//calculate area
private void findSurfaceArea()
{
	
	cylinderArea=(2*(Math.PI)*Math.pow(radius, 2)) + 2*(Math.PI)*radius*height;
	
	
	
}
// call to output
private String output()
{
	
return "The area of a cylinder with height " + height +  " and radius " + radius + " is:\n"  +cylinderArea;
		
	
	
}



public static void main(String[] args)
{	// make object of class
	Cylinder cylinderObject=new Cylinder();
	//gain input from user
	cylinderObject.Getinput();
	//use input to gain area
	cylinderObject.findSurfaceArea();
	//show user
	System.out.println(cylinderObject.output());
	
	
}
}
