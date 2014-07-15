

//import statements
import java.io.*;
//class to handle the Battleship Game
public class Battleship {
	
	private static PrintWriter out;
	private static InputStream in;
	private static InputStreamReader read;
	private static BufferedReader br;
	private static StringBuilder theInput;
	private static String input;
	private static Cell gameBoard[][];
	private static BufferedReader brFile;
	private static File inputFile;
	private static FileReader theFile;
	protected static char[] alphabet;
	private static boolean gameNotOver=true;
	private String line;
	private int counter1=1;
	private static int maxShipNumber=0;
	private static String[] ConstantArray;
	private static int[] elementNumbers;
	private static int startX;
	private static int startY;
	private static int endX;
	private static int endY;
	private static int alphCount=0;
	private static String theFiles;
	private static int counterin;
	
	
//play battleship game 
	//reads input
	
	private String reading() throws IOException
	{ 		theInput.setLength(0);
			
			input =br.readLine();
		    theInput.append(input);
			
		 return theInput.toString();
		
		
	}
	//makes alphabet char array
	private void makeCharArray()
	{
		
		 alphabet = new char[26]; // new array
        
        
        for(char ch = 'a'; ch <= 'z'; ++ch)// fills alphabet array with the lowercase alphabet
        {
            alphabet[ch-'a']=ch;
        } 
		//make alphabet uppercase
        for(int i = 0; i<alphabet.length; i++ )
        {
        	alphabet[i]=Character.toUpperCase(alphabet[i]);
        	
        }
        
        
	}
	//reads file input
	
	private static void buildShip()
	{
	
	Ship myUniqueShip=null;
	if(endY-startY==0)
	{
		myUniqueShip=new Ship(alphabet[alphCount],(endX-startX +1));
		
		
		
	}
	else
	{
		
	 myUniqueShip=new Ship(alphabet[alphCount],(endY-startY +1));
		
	}
		try{
		
		for(int i=startY; i<=endY; i++)
		{
			
			
			for(int k=startX; k<=endX; k++)
			{
				
				if(gameBoard[k][i].getClass().getName()=="BattleShipCell")
				{
					
					System.err.println("Overlapping ships");
					
					System.exit(0);
					
				}
				gameBoard[k][i]=new BattleShipCell(myUniqueShip);
				//System.out.println("x " + k + " y" + i);
				//numberOfElements[shipNumber]= numberOfElements[shipNumber]+1;
				
			}
			
		}
		}
		catch(ArrayIndexOutOfBoundsException e)
		{
			System.err.println("Overlapping or out-of-bounds ships in file " + theFiles);
			System.exit(0);
			
			
		}
		alphCount++;
	}
	
	//reads the input file to gain both number of max ships and coordinates for each ship.
	//if more ship coorinates are given the max ship number, will only make ships up to number given
	//@ param filename, the filename of the file to read for input
	private void readInputFile(String filename) throws IOException
	{	theFiles=filename;
		inputFile=new File(filename);
	
		
			
		try {
			theFile=new FileReader(inputFile);
			brFile=new BufferedReader(theFile);
		
		
			
			while ((line = brFile.readLine()) != null) {
			
				
				if(counter1==1)
			   {
				   
				 maxShipNumber= Integer.parseInt(line);
				
				 counter1++;
				   
				   
				   
			   }
			  
				else if(counterin>maxShipNumber)
				{
					
					break;
				}
				else
			   {
				   convertLine(line);
				   setXY();
				   buildShip();
			   }
				
			counterin++;	
			}
			brFile.close();
		
		
		} catch (FileNotFoundException e) {
			writing("Cannot open file filename.");
			System.exit(0);
		}  
		
		
		
		} 
	//sets coordinates for ship such that ship will be made either left to right 
	//or up to down based on ship positon on board
	private static void setXY() 
	{
		
		
		if(elementNumbers[0] == elementNumbers[2])
		{
			
			startX=elementNumbers[0];
			endX=elementNumbers[0];
			
			
		}
		else if(elementNumbers[0] < elementNumbers[2])
		{
			startX=elementNumbers[0];
			endX=elementNumbers[2];
			
			
		}
		else{
			startX=elementNumbers[2];
			endX=elementNumbers[0];
		}
		
		
		if(elementNumbers[1] == elementNumbers[3])
		{
			startY=elementNumbers[1];
			endY=elementNumbers[1];
			
			
		}
		else if(elementNumbers[1] < elementNumbers[3])
		{
			startY=elementNumbers[1];
			endY=elementNumbers[3];
			
			
		}
		else{
			startY=elementNumbers[3];
			endY=elementNumbers[1];
		}
		
		
		
		
		
	}
	//converts a line from input file into coordinates so that a cast from 
	//a character to a numberic element in the array
	//@ param line- the line that is to be converted
	private static void convertLine(String line)
	{
		ConstantArray=line.trim().split("\\s+");
		/*for(String e: ConstantArray)
		{
			
			System.out.println(e);
			
		}*/
		elementNumbers=new int[ConstantArray.length];
		for(int i =0; i<ConstantArray.length; i++)
		{
			
			
			for(int k=0; k<alphabet.length; k++)
			{
				if(ConstantArray[i].toUpperCase().equals(Character.toString(alphabet[k])))
			{
				elementNumbers[i]=k;
				
			}
			}	
			
		}

		
	}
	
	private void fillWithWater()
	{
		
		for(int i =0 ; i<gameBoard[0].length; i++)
		{
			
			for (int k=0; k<gameBoard[1].length; k++)
				
				gameBoard[i][k]=new WaterCell();
			
			
			
		}
		
	}
	//writes output
	//@param output-what is going to be sent to user.
	protected void writing(String output)
	{
		
	out.println(output);
	out.flush();
		
	}
	//creates streams in program to gain and use information in the program
	private void createInAndOutStreams() throws IOException
	{
		out=new PrintWriter(System.out);
		in=System.in;
		read = new InputStreamReader(in);
		theInput=new StringBuilder();
		br = new BufferedReader(read);
		
		
	}
	//checks if the dimension input was good
	//@param x -the dimension of board wanted
	private void goodDimensions(int x)
	{
		if(x<5)
		{
			writing("Board must be at least 5 by 5.");
			System.exit(0);
		}
		else if (x>26)
		{
			
			writing("Board must be at most 26 by 26.");
			System.exit(0);
		}
		
	}
		
	//creates the game board
	//@param dimension- the dimension of the board disired by user
	private void createBoard(int dimension) {
	
	gameBoard=new Cell[dimension][dimension];
	
	
	
	}
	//see if dimension is castable to a int
	//@param x - the string that is attempting to be cast to a int
	private void testDimCast(String x)
	{
		try{
			int y= Integer.parseInt(x);
			
		}
		
		catch(NumberFormatException e)
		{
			
			System.err.println("Usage: Battleship N config-file");
			System.exit(0);
			
		}
		
	}

	

		public static void main(String[] args) throws IOException
		{
			Battleship game=new Battleship();
			
	

		try{
			
			
			
			game.createInAndOutStreams();	
			game.makeCharArray();
			
			
			
			//make sure user input is good
			
			if(args.length==2)
		{	
		
		//create game		
		game.testDimCast(args[0]);
		game.goodDimensions(Integer.parseInt(args[0]));
		game.createBoard(Integer.parseInt(args[0]));
		game.fillWithWater();
		game.readInputFile(args[1]);	
		GameCommands userInput = new GameCommands();
		userInput.setAlphabet(alphabet);
		userInput.setDimension(Integer.parseInt(args[0]));
		
		
	
		
		
		
		//show initial board
		userInput.input("board",gameBoard);
		
		//start game
		
		while(gameNotOver)
		{	//get user commands to play game
			userInput.input(game.reading(),gameBoard);
			
			//see if game is done
			if(maxShipNumber==userInput.getSunkShips())
			{
				
				gameNotOver=false;
				
			}
			
			
			
		
		
		
		}
		game.writing("You Win!");
		//show user stats before ending game
		userInput.input("stats",gameBoard);
		userInput.input("quit",gameBoard);
		//end game
		
		}
			
		else
		{
		
		System.err.println("Usage: Battleship N config-file");
		System.err.println("Usage: java Battleship N config-file");	
		
		
		}
		}
		
		catch(ClassCastException e)
		{
		game.writing("Bad input by user to construct instance of game");
			
		}
		
		
		
		
		
			
			
		in.close();
		out.close();
		}
}


		


