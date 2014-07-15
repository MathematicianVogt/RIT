
public class GameCommands {
	private static int misslesFired;
	private static int misslesMissed;
	private static int misslesHit;
	private static int shipsSunk;
	private static double hitRatio;
	private String theCommand;
	private String[] splitInput;
	private Cell[][] copy;
	private String output;
	private Battleship myShip;
	private char[] alphabet;
	private int dimension;
	private String letterRow="";
	private int[] myShipNumbers;
	private int[] targetArray;
	private boolean goodFire=true;

	public GameCommands(int[] shipNumbers)
	{
		misslesFired=0;
		misslesMissed=0;
		misslesHit=0;
		shipsSunk=0;
		hitRatio=0;
		output="";
		myShip=new Battleship();
		myShipNumbers=shipNumbers;
		targetArray=new int[2];

	}

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
					
					//System.out.println(splitInput[i+1] + " l " + alphabet[k]);
				targetArray[i]=k;
				
			}
			}	
			
		}
		
		/*for(int e : targetArray)
		{
			
			System.out.println(e + " l");
			
		}
		*/
		}
		
		catch(ArrayIndexOutOfBoundsException e)
		{
			
			myShip.writing("Illegal coordinates.");
			goodFire=false;
		}
		
		
		
	}
	protected void setDimension(int theDimension)
	{

		dimension=theDimension;


	}
	protected void setAlphabet(char[] theAlphabet)
	{
		alphabet=theAlphabet;



	}
	protected void input(String command, Cell[][] board)
	{
		copy=board;
		theCommand=command;
		splitInput=command.split(" ");
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

	private void fire()
	{
		
		try{
		changeLettersToCor();
		
		if(goodFire)
		{
			
			
			
			
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

			
			
			
			copy[targetArray[1]][targetArray[0]].fire();
		
				
		}
		
		
		
		goodFire=true;
		
		}
		catch(ArrayIndexOutOfBoundsException e)
		{
			
			myShip.writing("Illegal Command");
			
		}

	}
	private void stats()
	{
		if(misslesFired!=0)
		{myShip.writing("Number of missiles fired: " + misslesFired + "\n"  + "Number of hits: " + misslesHit + "\n" + "Number of misses: " + misslesMissed + "\n" + "Hit ratio: " 
		                + hitRatio + "\n" + "Number of ships sunk: " + shipsSunk );
		}
		else
		{
			
			myShip.writing("Stats NA");
			
		}

	}
	private void help()
	{

		myShip.writing("Possible commands:\n" + "board - displays the user's board\n" + "ships - displays the placement of the ships fire r c - fires a missile at the\n" + "cell at [r,c]\n" + "stats - prints out the game statistics\n" + "quit - exits the game");

	}
	private  static void updateStats(int hasMissleFired, int hasMissleMissed,int hasMissleHit, int HasShipSunk)
	{
		
		misslesFired=misslesFired+hasMissleFired;
		misslesMissed=misslesMissed+hasMissleMissed;
		misslesHit=misslesHit+hasMissleHit;
		shipsSunk=shipsSunk+HasShipSunk;
		
		//System.out.println(misslesHit);
		//System.out.println(misslesMissed);
		hitRatio=100.0*((double)misslesHit/(double)(misslesFired));


	}

	private void quit()
	{
		System.exit(0);

	}
	private void invalidInput()
	{
		myShip.writing("Illegal command.");

	}


}
