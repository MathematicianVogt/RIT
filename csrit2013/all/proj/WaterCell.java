//watercell class used to control water cell objects
public class WaterCell extends Cell {
	private Battleship mainClass;
	private Boolean hasHit;
 //create a watercell object
	protected WaterCell()
	{

		mainClass=new Battleship();
		hasHit=false;

	}
	//how a watercell behaves when printed out with the ship command
	//@return ship water mark
	public String shipCellMark()
	{
		return "-";
		
		
	}
	//how a watercell behaves when printed with board  command
	//@return board water mark
	public String boardCellMark()
	{
		if(hasHit)
		{

			return "O";
		}
		else
			return "-";


	}
	//fires on a watercell, updates stats 
	public void fire() {
		mainClass.writing("Miss");
		isHit(true);

	}

	@Override
	//fire change the case of being hit
	public void isHit(Boolean hit) {
		hasHit=hit;


	}
	//checks if the object has been hit
	//@return hasHit the boolean associated with the object
	public boolean hasHit()
	{

		return hasHit;
	}
}
