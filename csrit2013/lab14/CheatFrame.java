import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JFrame;
import javax.swing.JPanel;

/*
 * CheatFrame.java
 *
 * Version:
 *     $Id: CheatFrame.java,v 1.1 2013/12/06 23:52:07 rhv5251 Exp rhv5251 $
 *
 * Revisions:
 *     $Log: CheatFrame.java,v $
 *     Revision 1.1  2013/12/06 23:52:07  rhv5251
 *     Initial revision
 *
 */
/*
 * 
 * @author Ryan Vogt
 *A class that shows a cheat form of a concentration game
 */
public class CheatFrame extends JFrame {
	// class variables
	JPanel myPanel;
	Container myContainer;
	ArrayList<CardButton> allButtons;

	// class constructor
	// @param carbuttons, the list of cards to be shown in cheat
	// @param size the size of the grid
	public CheatFrame(ArrayList<CardButton> cardButtons, int size) {
		allButtons = cardButtons;
		myPanel = new JPanel(new GridLayout(size, size));
		myContainer = new Container();
		myContainer.setLayout(new BorderLayout());
		fillPanel(myPanel);
		myContainer.add(myPanel);
		this.add(myContainer);
		this.setTitle("Cheat Concentration Game");
		this.setSize(300, 300);
		this.setVisible(true);
	}

	// fills a cheat board with buttons
	// @param myPanel, the panel to be holding the cheatubttons
	public void fillPanel(JPanel myPanel) {
		Iterator<CardButton> it = allButtons.iterator();

		while (it.hasNext()) {

			CardButton currentButton = it.next();
			myPanel.add(currentButton);
		}

	}
}
