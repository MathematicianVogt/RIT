/**
 * TextViewControl.java
 *
 * File:
 *	$Id$
 *
 * Revisions:
 *	$Log$
 */

import java.util.Scanner;
import java.util.ArrayList;

/**
 * Class definition for the textual view and controller.
 *
 * @author: Arthur Nunes-Harwitt
 */

public class TextViewControl {

    /**
     * The model for the view and controller.
     */
    private ConcentrationModel model;

    /**
     * Construct a TextViewControl object.
     * 
     * @param model The model for the view and controller.
     */
    public TextViewControl(ConcentrationModel model) {
	this.model = model;
	startGame();
    }

    /**
     * Start the concentration game.
     * 
     */    
    private void startGame() {
	commandLoop();
    }

    /**
     * Read a command and execute loop.
     * 
     */    
    private void commandLoop() {
	Scanner in = new Scanner(System.in); 
	for(;;) {
	    displayBoard(this.model.getMoveCount(), 
			 this.model.howManyCardsUp(), 
			 this.model.getCards());
	    System.out.print("game command: ");
	    String line = in.nextLine();
	    String[] words = line.split("\\s+");
	    if (words.length > 0) {
		if (words[0].equals("quit")) {
		    break;
		} else if (words[0].equals("reset")) {
		    this.model.reset();
		} else if (words[0].equals("cheat")) {
		    displayBoard(this.model.getMoveCount(), 
				 this.model.howManyCardsUp(), 
				 this.model.cheat());
		} else if (words[0].equals("undo")) {
		    this.model.undo();
		} else if (words[0].equals("select")) {
		    int n = Integer.parseInt(words[1]);
		    this.model.selectCard(n);
		} else {
		    displayHelp();
		}
	    }
	}
    }


    /**
     * Print on standard out the cards as a grid , the move count, and
     * brief directions.
     * 
     * @param n An integer that represents the number of moves.
     * @param up An integer that represents the number of cards
     * selected.
     * @param faces An ArrayList of CardFace that represents the board.
     */    
    private void displayBoard(int n, int up, ArrayList<CardFace> faces) {
	System.out.println("Move count: " + n);
	switch(up) {
	case 0: System.out.println("Select the first card."); break;
	case 1: System.out.println("Select the second card."); break;
	case 2: System.out.println("No Match: Undo or select a card."); break;
	}
	int pos = 1;
	for (CardFace f : faces) {
	    System.out.print(f);
	    if (pos%4 == 0){
		System.out.println();
	    } else {
		System.out.print(" | ");
	    }
	    ++pos;
	}
    }

    /**
     * Print on standard out help for the game.
     *
     */
    private void displayHelp() {
	System.out.println(" 00 | 01 | 02 | 03");
	System.out.println(" 04 | 05 | 06 | 07");
	System.out.println(" 08 | 09 | 10 | 11");
	System.out.println(" 12 | 13 | 14 | 15");
	System.out.println("select n  -- select the card n to flip");
	System.out.println("undo      -- undo last flip");
	System.out.println("quit      -- quit the game");
	System.out.println("reset     -- start a new game");
	System.out.println("cheat     -- see where the cards are");
    }

    /**
     * The main method used to play a game.
     *
     * @param args Command line arguments -- unused
     */
    public static void main(String[] args) {
	TextViewControl game = new TextViewControl(new ConcentrationModel());
    }
}