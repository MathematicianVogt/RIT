//import statements
import java.io.*;
public class Battleship {
	
	private static int boardDimensions;
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
	private static String inputLine;
	private static int counter=0;
	protected static char[] alphabet;
	private static WaterCell water;
	private static boolean gameNotOver=true;
	private String line;
	private int counter1=1;
	private int maxShipNumber;
	private static String[] ConstantArray;
	private static boolean isConstantx=false;
	private static int[] elementNumbers;
	private static int startX;
	private static int startY;
	private static int endX;
	private static int endY;
	private static int alphCount=0;
	private static String theFiles;
	private static int[] numberOfElements;
	private static int shipNumber=0;
//play battleship game 
	//reads input
	
	public Battleship()
	{
		
		
		
	}
	
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
	/*	System.out.println(startX + " u");
		System.out.println(endX + " i");
		System.out.println(startY + " p");
		System.out.println(endY + " l");
		*/
		
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
				gameBoard[k][i]=new BattleShipCell(new Ship(alphabet[alphCount]));
				//System.out.println("x " + k + " y" + i);
				numberOfElements[shipNumber]= numberOfElements[shipNumber]+1;
				
			}
			
			
			
		}
		
		shipNumber++;
		}
		catch(ArrayIndexOutOfBoundsException e)
		{
			System.err.println("Overlapping or out-of-bounds ships in file " + theFiles);
			System.exit(0);
			
			
		}
		alphCount++;
	}
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
				 numberOfElements=new int[maxShipNumber];
				 counter1++;
				   
				   
				   
			   }
			   else
			   {
				   convertLine(line);
				   setXY();
				   buildShip();
				   
				   
				   
			   }
				
				
			}
			brFile.close();
		
		
		} catch (FileNotFoundException e) {
			writing("Cannot open file filename.");
			System.exit(0);
		}  
		
		
		
		} 
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
				if(ConstantArray[i].equals(Character.toString(alphabet[k])))
			{
				elementNumbers[i]=k;
				
			}
			}	
			
		}

		/*for(int a: elementNumbers)
		{
			
			System.out.println(a);
			
			
		}*/
		
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
	protected void writing(String output)
	{
		
	out.println(output);
	out.flush();
		
	}
	private void createInAndOutStreams() throws IOException
	{
		out=new PrintWriter(System.out);
		in=System.in;
		read = new InputStreamReader(in);
		theInput=new StringBuilder();
		br = new BufferedReader(read);
		
		
	}
	
		
		
	
	private void createBoard(int dimension) {
	
	gameBoard=new Cell[dimension][dimension];
	
	
	
	}

	

		public static void main(String[] args) throws IOException
		{
			Battleship game=new Battleship();
			
	
			//System.out.println(args.length + "hi");
		try{
			
			
			
			game.createInAndOutStreams();	
			game.makeCharArray();
			
			
			
			//make sure user input is good
			
			if(args.length==2)
		{	
		game.createBoard(Integer.parseInt(args[0]));
		game.fillWithWater();
		game.readInputFile(args[1]);	
		GameCommands userInput = new GameCommands(numberOfElements);
		userInput.setAlphabet(alphabet);
		userInput.setDimension(Integer.parseInt(args[0]));
		
		
		//create game
		
		
		
		//show initial board
		userInput.input("board",gameBoard);
		
		//start game
		
		while(gameNotOver)
		{
			userInput.input(game.reading(),gameBoard);
			
			
			
			
		
		
		
		}
		
		
		
		}
			
		else
		{
		
		System.err.println("Usage: Battleship N config-file");
		System.err.println("Usage: java Battleship N config-file");
		game.writing("Expecting two parameters, dimension size and file containing appropiate data");	
		
		
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


		


