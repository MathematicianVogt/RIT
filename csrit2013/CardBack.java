/**
 * CardBack.java
 *
 * File:
 *	$Id$
 *
 * Revisions:
 *	$Log$
 */

/**
 * Class definition for the back of a card in the concentration card
 * game.
 *
 * @author: Arthur Nunes-Harwitt
 */

public class CardBack implements CardFace {

    /**
     * Get the constant indicating that the card is face-down.
     *
     * @return The boolean indicating the card is face-down.
     */
    @Override
    public boolean isFaceUp() {
	return false;
    }

    /**
     * This method is required by the interface, but this class cannot
     * return a value.  Instead, an exception is thrown.
     *
     * @return Technically an integer, but no integer is ever
     * returned.
     */
    @Override
    public int getNumber() {
	throw new RuntimeException("The number is hidden.");
    }

    /**
     * Get the String representing the back of a card.
     *
     * @return A String representing the back of a card.
     */
    @Override
    public String toString() {
	return "***";
    }

}