package com.example.unogame;

/**
 * This is a constructor for a new Deck that uses another one as reference. It can be used
 * for copying or creating a new deck from an existing one
 *
 * @param drawPile Deck to use as basis for new Deck
 *
 */
public class Deck {
    private Deck drawPile;

    public Deck(Deck drawPile) {
        this.drawPile = drawPile;
    }
}
