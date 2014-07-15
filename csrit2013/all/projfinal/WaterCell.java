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
	public String shipCellMark()
	{
		return "-";
		
		
	}
	//how a watercell behaves when printed with board  command
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
	public void isHit(Boolean hit) {
		hasHit=hit;


	}
	//checks if the object has been hit
	public boolean hasHit()
	{

		return hasHit;
	}
}
