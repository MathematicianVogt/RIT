//class made to handle BattleShipCellObjects
public class BattleShipCell extends Cell {
	//public variables
	
	private Battleship mainClass;
	private Boolean hasHit;
	private Ship myShip;
	private Battleship one;
	private Boolean oneHit=false;
	//make a battleshipcell that is connected to a unique ship
	//@param specificShip - the refrence each battleship cell has
	//to a unique ship
	public BattleShipCell(Ship specificShip)
	{
		hasHit=false;
		mainClass=new Battleship();
		myShip=specificShip;
		one=new Battleship();
	}
	
//returns the unique ships signature for ships command
//@return letter of ship	
	public String shipCellMark()
	{
		return myShip.returnShipLetter();
		
	}
	
	//returns unique signature for board command
	//@return signature of ship
	public String boardCellMark()
	{
		if(hasHit)
		{
			
			return "X";
		}
		else
			return "-";
		
	}
	//if fire is called on a battleship cell, updates states, tells its ship, and notifies user of hit
	public void fire() {
		
		if(hasHit)
		{
			one.writing("Coordinates previously fired upon.");
		
		}
		
		else{
		mainClass.writing("Hit");
		isHit(true);
		myShip.hitShip();
		}
	}


	//set value for a shipcell
	public void isHit(Boolean hit) {
		hasHit=hit;
		
		
	}
	//see if the ship has been hit once before
	//@return has the ship been hit before
	public boolean hasHit()
	{
		if(hasHit)
		{
			
			oneHit=true;
			
		}
		
		return oneHit;
	}

}
