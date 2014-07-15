/**
 * ConcentrationModel.java
 *
 * File:
 *	$Id$
 *
 * Revisions:
 *	$Log$
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.Observable;

/**
 * Class definition for the model of a concentration card game.
 *
 * @author: Arthur Nunes-Harwitt
 */


public class ConcentrationModel extends Observable {

    // The default size (of one side) of the board.
    public static final int BOARD_SIZE = 4;

    // The total number of cards assuming a square board.
    public static final int NUM_CARDS = BOARD_SIZE * BOARD_SIZE;

    // The number of pairs.
    public static final int NUM_PAIRS = NUM_CARDS / 2;

    // The object representing the back of a card.
    public static final CardBack SINGLETON_BACK = new CardBack();

    /**
     * The undo stack for the game.
     */
    private ArrayList<Card> undoStack;

    /**
     * The cards for the game.
     */
    private ArrayList<Card> cards;
    
    /**
     * The number of moves made in the game, where a move is a card
     * selection.
     */
    private int moveCount;

    
    /**
     * Construct a ConcentrationModel object.
     * 
     */
    public ConcentrationModel() {
	this.cards = new ArrayList<Card>();

	for (int n = 0; n < NUM_PAIRS; ++n) {
	    Card card1 = new Card(n);
	    Card card2 = new Card(n);
	    this.cards.add(card1);
	    this.cards.add(card2);
	}

	reset();
    }

    /**
     * Push a card onto the undo stack.
     * 
     * @param card The card to push.
     */
    private void push(Card card) {
	undoStack.add(card);
    }

    /**
     * Pop a card from the undo stack.
     * 
     * @param toggle Flag to indicate whether or not to toggle whether
     * the card is face-up or face-down.
     */
    private void pop(boolean toggle) {
	int s = undoStack.size();
	if (s > 0) {
	    Card card = undoStack.get(s-1);
	    undoStack.remove(s-1);
	    if (toggle) card.toggleFace();
	}
    }
    
    /**
     * Pop a card from the undo stack. (There are no parameters.)
     * 
     */    
    private void pop() {
	pop(false);
    }

    /**
     * Undo selecting a card.
     * 
     */    
    public void undo(){
	pop(true);
	setChanged();
	notifyObservers();

    }


    /**
     * Turn a card up.
     * 
     * @param n An integer referring to the nth card.
     *
     */
    private void add(int n) {
	Card card = cards.get(n);
	if (!card.isFaceUp()) {
	    card.toggleFace();
	    push(card);
	    ++this.moveCount;
	}
    }

    /**
     * Check to see if the two cards on the top of the undo stack have
     * the same value.
     * 
     */    
    private void checkMatch() {
	if (undoStack.size() == 2 && 
	    undoStack.get(0).getNumber() == undoStack.get(1).getNumber()) {
	    pop();
	    pop();
	}
    }

    /**
     * Select a card to turn face up from cards.  If there are already
     * two cards selected, turn those back over.
     * 
     * @param n An integer referring to the nth card.
     *
     */
    public void selectCard(int n) {
	if (0 <= n && n < NUM_CARDS) {
	    switch (undoStack.size()){
	    case 2:
		undo();
		undo();
	    case 0:
		add(n);
		break;
	    case 1:
		add(n);
		checkMatch();
		break;
	    default:
		throw new RuntimeException("Internal Error: undoStack too big.");
	    }
	    setChanged();
	    notifyObservers();
	} else {

	}
    }

    /**
     * Get the cards but only showing those that are face-up.
     *
     * @return An ArrayList containing the cards on the board.
     */    
    public ArrayList<CardFace> getCards() {
	ArrayList<CardFace> faces = new ArrayList<CardFace>();

	for (Card card : cards) {
	    if (card.isFaceUp()) {
		faces.add(card);
	    } else {
		faces.add(SINGLETON_BACK);
	    }
	}
	return faces;
    }

    /**
     * Get the cards showing them all.
     *
     * @return An ArrayList containing the cards on the board.
     */
    public ArrayList<CardFace> cheat() {
	ArrayList<CardFace> faces = new ArrayList<CardFace>();

	for (Card card : cards) {
	    faces.add(card);
	}
	return faces;
    }

    /**
     * Get the number of moves, i.e., the number of card
     * selections.
     *
     * @return An integer that represents the number of moves.
     */
    public int getMoveCount() {
	return this.moveCount;
    }


    /**
     * Reset the board.  All the cards are turned face-down and are
     * shuffled.  The undo stack and the number of moves are cleared.
     *
     */
    public void reset() {
	
	for (Card card : cards) {
	    if (card.isFaceUp()) {
		card.toggleFace();
	    }
	}

	Collections.shuffle(cards);

	this.undoStack = new ArrayList<Card>();

	this.moveCount = 0;

	setChanged();
	notifyObservers();
    }

    /**
     * Return the number of cards currently selected.
     *
     * @return An integer that represents the number of cards
     * selected.
     */
    public int howManyCardsUp() {
	return undoStack.size();
    }
}