import javax.swing.JButton;

/*
 * CardButton.java
 *
 * Version:
 *     $Id: CardButton.java,v 1.1 2013/12/06 23:55:01 rhv5251 Exp rhv5251 $
 *
 * Revisions:
 *     $Log: CardButton.java,v $
 *     Revision 1.1  2013/12/06 23:55:01  rhv5251
 *     Initial revision
 *
 */

/*
 * 
 * @author Ryan Vogt
 * A class to make a button representation of a card
 */
public class CardButton extends JButton { // class variables
	private int pos;

	// Constructor
	// @param pos the position of the card
	public CardButton(int pos) {

		this.pos = pos;

	}

	// get the position of a button
	// @return pos the position of a button
	public int getPos() {
		return pos;
	}
}
