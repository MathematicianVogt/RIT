//ship class
public class Ship {
private int maxCells;
private String myShipLetter;
private Battleship myShip;

//ship object has a letter to represent each element and has a max size
public Ship(char letter, int maxDim)
{
	maxCells=maxDim;
	myShipLetter=Character.toString(letter);
	myShip=new Battleship();
}
//returns unique letter
public String returnShipLetter()
{
	return myShipLetter;
	
	
}

//when a ship element is hit, this method is called in the fire command, updating the object. When the ship
//is sunk then stats are updated and user is told. 
public void hitShip()
{
maxCells--;
if(maxCells==0)
{
	
	myShip.writing("Sunk!");
	GameCommands.updateSunk();
	
	
}
	
	
	
	
}
}
	

