import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/*
 * GViewControl.java
 *
 * Version:
 *     $Id: GViewControl.java,v 1.1 2013/12/06 23:50:05 rhv5251 Exp rhv5251 $
 *
 * Revisions:
 *     $Log: AddressScreen.java,v $
 *     Revision 1.1  2013/12/06 23:50:05  rhv5251
 *     Initial revision
 *
 */
/*
 * 
 * @author Ryan Vogt
 * A Class to play the game concentration
 * 
 */

public class GViewControl extends JFrame implements Observer {
	// class variables
	private int moveCounter = 0;
	private int cardpickCounter = 0;
	private Color[] myColors = { Color.BLUE, Color.BLACK, Color.RED,
			Color.CYAN, Color.GRAY, Color.yellow, Color.PINK, Color.MAGENTA };
	private JPanel myPanel;
	private JPanel myPanel2;
	private JPanel myPanel3;
	private Container myContainer;
	private JButton reset;
	private JButton cheat;
	private JButton undo;
	private JLabel myLabel;
	private String first = " Select the first card";
	private String second = "Select the second card";
	private ConcentrationModel model;
	private CheatFrame cheating;
	private CardButton currentButton;
	private CardButton firstButton;
	private CardButton secondButton;
	private ArrayList<CardFace> allCards;

	// Constructor
	// @param model, the concentration board to be played
	public GViewControl(ConcentrationModel model) {

		construct(model);

	}

	// Construct the gui for the concentration game
	public void construct(ConcentrationModel model) {

		this.model = model;
		model.addObserver(this);
		myContainer = new Container();
		myContainer.setLayout(new BorderLayout());
		myPanel = new JPanel();
		myPanel2 = new JPanel(new GridLayout(4, 4));
		myPanel3 = new JPanel();
		myLabel = new JLabel("Moves: " + moveCounter + first);
		reset = new JButton("Reset");
		reset.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				reset();

			}

		});
		cheat = new JButton("Cheat");
		cheat.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				showCheat();

			}

		});
		undo = new JButton("Undo");
		undo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				undo();

			}

		});
		myPanel.add(myLabel);
		myPanel3.add(reset);
		myPanel3.add(cheat);
		myPanel3.add(undo);
		fillGrid(myPanel2);
		myContainer.add(myPanel, BorderLayout.NORTH);
		myContainer.add(myPanel2, BorderLayout.CENTER);
		myContainer.add(myPanel3, BorderLayout.SOUTH);
		this.add(myContainer);

	}

	// fill the board with card objects
	// @param myPanel, the panel holding the card objects
	public void fillGrid(JPanel myPanel) {
		int counter = 0;
		ArrayList<CardFace> myCards = model.getCards();
		Iterator<CardFace> it = myCards.iterator();
		allCards = myCards;
		while (it.hasNext()) {
			CardFace currentFace = it.next();
			if (currentFace.isFaceUp()) {
				currentButton = new CardButton(counter);
				currentButton.setText("" + currentFace.getNumber());
				currentButton.setBackground(myColors[currentFace.getNumber()]);

			} else {

				currentButton = new CardButton(counter);
				currentButton.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						int innercounter = 0;
						// TODO Auto-generated method stub
						model.selectCard(innercounter);
						innercounter++;

					}

				});
				currentButton.setBackground(Color.white);

			}

			myPanel.add(currentButton);

		}

	}

	// undo a move previously done
	public void undo() {
		model.undo();

	}

	// make a new board and start a new game
	public void reset() {
		model.reset();

	}

	// show the solution to the complete board
	public void showCheat() {
		int counter = 0;
		ArrayList<CardButton> myButtons = new ArrayList<CardButton>();
		ArrayList<CardFace> myCards = model.cheat();
		Iterator<CardFace> it = myCards.iterator();

		while (it.hasNext()) {
			CardButton myButton = new CardButton(counter);
			CardFace currentCard = it.next();
			myButton.setText("" + currentCard.getNumber());
			myButton.setBackground(myColors[currentCard.getNumber()]);
			myButtons.add(myButton);
			counter++;

		}
		cheating = new CheatFrame(myButtons, 4);

	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub

		moveCounter++;
		updateGui();
		System.out.println("Updating:Working but has not repainted the GUI");
		System.out.println("Cheat button works perfectly though");

	}

	public void updateGui() {
		fillGrid(myPanel2);
		myContainer.add(myPanel2, BorderLayout.CENTER);
		this.add(myContainer);
		/*
		 * Things should logically work but im not sure why not, Im implementing
		 * things correctly however there is a issue with my updating, the gui
		 * isnt repainting the center which is the board reflecting that things have been clicked
		 * and label isnt being remade even though movecounter has been increassed
		 *  Cant find
		 * answers online.
		 */

	}

	// main method
	public static void main(String[] args) {

		GViewControl myPad = new GViewControl(new ConcentrationModel());
		myPad.setTitle("Concentration Game");
		myPad.setSize(400, 400);
		myPad.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myPad.setVisible(true);

	}

}

//end