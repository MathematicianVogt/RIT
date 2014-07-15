//cell superclass

public abstract class Cell {

	//can fire on a cell
	public abstract void fire();
	//can check if a cell is hit
	public abstract void isHit(Boolean hit);
	
	//can check if a cell has been hit
	public abstract boolean hasHit();
 // show a cells ship command representation
	public String shipCellMark() {
		// TODO Auto-generated method stub
		return null;
	}
	//show a cells board command representation
	public String boardCellMark() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
