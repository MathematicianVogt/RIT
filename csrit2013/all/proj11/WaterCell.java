
public class WaterCell extends Cell {
	private Battleship mainClass;
	private Boolean hasHit;

	protected WaterCell()
	{

		mainClass=new Battleship();
		hasHit=false;

	}

	public String shipCellMark()
	{
		return "-";
		
		
	}
	
	public String boardCellMark()
	{
		if(hasHit)
		{

			return "O";
		}
		else
			return "-";


	}
	public void fire() {
		mainClass.writing("Miss");
		isHit(true);

	}

	@Override
	public void isHit(Boolean hit) {
		hasHit=hit;


	}

	public boolean hasHit()
	{

		return hasHit;
	}
}
