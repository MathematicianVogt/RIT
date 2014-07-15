
public class GameCommands {
	//class variables
	private static int misslesFired;
	private static int misslesMissed;
	private static int misslesHit;
	private static int shipsSunk;
	private static double hitRatio;
	private String[] splitInput;
	private Cell[][] copy;
	private String output;
	private Battleship myShip;
	private char[] alphabet;
	private int dimension;
	private String letterRow="";
	private int[] targetArray;
	private boolean goodFire=true;

	
	//constructor, begins command
	public GameCommands(int[] shipNumbers)
	{
		misslesFired=0;
		misslesMissed=0;
		misslesHit=0;
		shipsSunk=0;
		hitRatio=0;
		output="";
		myShip=new Battleship();	
		targetArray=new int[2];

	}
	//for a fire command, changes characters to array position equals
	protected void changeLettersToCor()
	{
		try{
	
		
		
		for(int i =0; i<targetArray.length; i++)
		{
			
			
			
			
			for(int k=0; k<alphabet.length; k++)
			{
			
				if((splitInput[i+1].length()>1) || (splitInput[i+1].length()<1) || (splitInput.length<3))
				{
					
					myShip.writing("Illegal command");
					goodFire=false;
					break;
				}
				
			if(splitInput[i+1].toUpperCase().equals(Character.toString(alphabet[k])))
			{
					
				
				targetArray[i]=k;
				
			}
			}	
			
		}
		
	
		}
		
		catch(ArrayIndexOutOfBoundsException e)
		{
			
			myShip.writing("Illegal coordinates.");
			goodFire=false;
		}
		
		
		
	}
	//set board dimension for the class
	protected void setDimension(int theDimension)
	{

		dimension=theDimension;


	}
	//set alphabet array for the class
	protected void setAlphabet(char[] theAlphabet)
	{
		alphabet=theAlphabet;



	}
	//method called when gaining input from the user
	protected void input(String command, Cell[][] board)
	{	//locally save the gameboard
		copy=board;
		
		splitInput=command.split(" ");
		
		//check what command was
		if(splitInput[0].equals("Board") || splitInput[0].equals("board"))
		{
			board();

		}
		else if(splitInput[0].equals("Ships") || splitInput[0].equals("ship"))
		{

			ships();


		}
		else if (splitInput[0].equals("fire") || splitInput[0].equals("Fire"))
		{

			fire();
		}

		else if (splitInput[0].equals("Stats") || splitInput[0].equals("stats"))
		{

			stats();

		}
		else if (splitInput[0].equals("Help") || splitInput[0].equals("help"))
		{

			help();

		}
		else if (splitInput[0].equals("Quit") || splitInput[0].equals("quit"))
		{

			quit();

		}

		else
		{

			invalidInput();

		}
	}
// prints board to user as a game
	private void board()
	{	
		for(int i =0; i<dimension; i++)
		{
			if(i==0)	
			{letterRow=letterRow + "  " +alphabet[i] + " ";	
			}
			else{

				letterRow=letterRow +alphabet[i] + " ";	

			}


		}
		myShip.writing(letterRow);
		letterRow="";

		for(int i =0 ; i<copy[0].length; i++)
		{

			for (int k=0; k<copy[1].length; k++)
			{//this

				if(k==0)
				{
					output= output+ alphabet[i] +  " " + copy[i][k].boardCellMark();	

				}
				else{
					output=output+ " " + copy[i][k].boardCellMark();
				}
				if(k==copy[1].length-1)
				{
					myShip.writing(output);
					output="";


				}

			}

		}

	}
// shows user the board with ships
	private  void ships()
	{
		for(int i =0; i<dimension; i++)
		{
			if(i==0)	
			{letterRow=letterRow + "  " +alphabet[i] + " ";	
			}
			else{

				letterRow=letterRow +alphabet[i] + " ";	

			}


		}
		myShip.writing(letterRow);
		letterRow="";

		for(int i =0 ; i<copy[0].length; i++)
		{

			for (int k=0; k<copy[1].length; k++)
			{//this

				if(k==0)
				{
					output= output+ alphabet[i] +  " " + copy[i][k].shipCellMark();	

				}
				else{
					output=output+ " " + copy[i][k].shipCellMark();
				}
				if(k==copy[1].length-1)
				{
					myShip.writing(output);
					output="";


				}

			}

		}


	}
 //fires on a cell and based on which object of cell class works as a result
	private void fire()
	{
		
		try{
		changeLettersToCor();
		
		if(goodFire)
		{
			
			
			
			//update stats
			if(!copy[targetArray[1]][targetArray[0]].hasHit() && copy[targetArray[1]][targetArray[0]].getClass().getName()=="BattleShipCell")
			{
				updateStats(1,0 ,1 , 0);
				
			
			}
			
			else if (copy[targetArray[1]][targetArray[0]].hasHit() && copy[targetArray[1]][targetArray[0]].getClass().getName()=="BattleShipCell")
			{
				updateStats(1, 1, 0, 0);
				
				
			}
			else
			{
				updateStats(1, 1, 0, 0);
				
			}

			
			
			//hit the ship
			copy[targetArray[1]][targetArray[0]].fire();
		
				
		}
		
		
		
		goodFire=true;
		
		}
		catch(ArrayIndexOutOfBoundsException e)
		{
			
			myShip.writing("Illegal Coordinates");
			
		}

	}
	//show user personal stats
	private void stats()
	{
		if(misslesFired!=0)
		{myShip.writing("Number of missiles fired: " + misslesFired + "\n"  + "Number of hits: " + misslesHit + "\n" 
						+ "Number of misses: " + misslesMissed + "\n" + "Hit ratio: " 
		                + hitRatio + "\n" + "Number of ships sunk: " + shipsSunk );
		}
		else
		{
			
			myShip.writing("Stats NA");
			
		}

	}
	//update when a ship is sunk
	public static void updateSunk()
	{
		
		shipsSunk++;
		
	}
	//gives the user helpful commands
	private void help()
	{

		myShip.writing("Possible commands:\n" + "board - displays the user's board\n" + "ships - displays the placement of "
						+ "the ships fire r c - fires a missile at the\n" + 
						"cell at [r,c]\n" + "stats - prints out the game statistics\n" + "quit - exits the game");

	}
	//call to update stats of user
	public static void updateStats(int hasMissleFired, int hasMissleMissed,int hasMissleHit, int HasShipSunk)
	{
		
		misslesFired=misslesFired+hasMissleFired;
		misslesMissed=misslesMissed+hasMissleMissed;
		misslesHit=misslesHit+hasMissleHit;
		//shipsSunk=shipsSunk+HasShipSunk;
		
		//System.out.println(misslesHit);
		//System.out.println(misslesMissed);
		hitRatio=100.0*((double)misslesHit/(double)(misslesFired));


	}
	//quits out of the game
	private void quit()
	{
		System.exit(0);

	}
	//return number of ships sunk
	public int getSunkShips()
	{
		
		return shipsSunk;
	}
	//user gave bad input
	private void invalidInput()
	{
		myShip.writing("Illegal command.");

	}


}
