
public class BattleShipCell extends Cell {
	private Battleship mainClass;
	private Boolean hasHit;
	private Ship myShip;
	private Battleship one;
	private Boolean oneHit=false;
	
	public BattleShipCell(Ship specificShip)
	{
		hasHit=false;
		mainClass=new Battleship();
		myShip=specificShip;
		one=new Battleship();
	}
	
	public String shipCellMark()
	{
		return myShip.returnShipLetter();
		
	}
	
	
	public String boardCellMark()
	{
		if(hasHit)
		{
			
			return "X";
		}
		else
			return "-";
		
	}
	
	public void fire() {
		
		if(hasHit)
		{
			one.writing("Coordinates previously fired upon.");
		
		}
		
		else{
		mainClass.writing("Hit");
		isHit(true);
		}
	}

	@Override
	public void isHit(Boolean hit) {
		hasHit=hit;
		
		
	}

	public boolean hasHit()
	{
		if(hasHit)
		{
			
			oneHit=true;
			
		}
		
		return oneHit;
	}

}
