/**
 * CardFace.java
 *
 * File:
 *	$Id$
 *
 * Revisions:
 *	$Log$
 */

/**
 * The interface that unites Cards and CardBacks.
 *
 * @author: Arthur Nunes-Harwitt
 */

public interface CardFace {
    
    /**
     * Get the value indicating whether or not the card is face-up.
     *
     * @return A boolean indicating whether or not the card is face-up.
     */
    public boolean isFaceUp();
    
    /**
     * Get the number on the card.
     *
     * @return An integer that is the number on the card.
     */
    public int getNumber();

}