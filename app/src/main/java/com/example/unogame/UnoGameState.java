package com.example.unogame;

import static com.example.game.GameFramework.utilities.Saving.SEPARATOR;

import com.example.game.GameFramework.infoMessage.GameState;

import java.util.ArrayList;

public class UnoGameState extends GameState {
    //instance variables
    //player 0's hand
    private ArrayList<UnoCard> player0Hand;

    //player 1's hand
    private ArrayList<UnoCard> player1Hand;

    //player 2's hand
    private ArrayList<UnoCard> player2Hand;

    //a list for the discard pile
    //should be an array list !!!!!!!
    private ArrayList<UnoCard> discardPile;

    //a list to hold the draw pile
    //should be an array List !!!!!!
    private ArrayList<UnoCard> drawPile;

    //which players turn it is
    private int playerTurn;

    //player 0 score
    private int player0Score;

    //player 1 score
    private int player1Score;

    //player 2 score
    private int player2Score;

    /**
     * toString
     *
     * @return type: String
     * <p>
     * the method returns a formatted string representation of the game state, including the number of
     * setup turns,the current setup turn, player hands, discard pile, draw pile, and player's turn.
     */

    @Override
    public String toString() {
        return numSetupTurns + SEPARATOR + currentSetupTurn + "player0Hand: " + player0Hand + "player1Hand: " + player1Hand +
                "Player2Hand: " + player2Hand + "discardPile: " + discardPile + "drawPile: " + drawPile + "playerTurn" + playerTurn;
    }

    //constructor for objects of class UnoGameState
    public UnoGameState() {
        this.playerTurn = 0;
        this.drawPile = startDrawPile();
        this.discardPile = startDiscardPile();
    }

    //deep copy constructor
    public UnoGameState(UnoGameState originalGame) {

        //copy player turn
        this.playerTurn = originalGame.playerTurn;

        //copy draw and discard pile
        this.drawPile = originalGame.drawPile;
        this.discardPile = originalGame.discardPile;

        //copy all player hands
        this.player0Hand = originalGame.player0Hand;
        this.player1Hand = originalGame.player1Hand;
        this.player2Hand =  originalGame.player2Hand;

        //copy all player scores
        this.player0Score = originalGame.player0Score;
        this.player1Score = originalGame.player1Score;
        this.player2Score = originalGame.player2Score;
    }

    // creates inital arraylist of 7 cards for start of game
    ArrayList<UnoCard> startHand(int playerTurn) {
        ArrayList<UnoCard> cards = new ArrayList<UnoCard>();
        for (int i = 0; i <= 7; i++) {
            cards.add(new UnoCard());
        }
        return cards;
    }
    // creates inital draw pile of 87 (max cards used by players initally is 21 bc 7*3
    // and max num of cards is 108 so draw = 108-21) cards for start of game
    ArrayList<UnoCard> startDrawPile() {
        ArrayList<UnoCard> cards = new ArrayList<UnoCard>();

        for(int i = 0; i <= 87; i++) {
            cards.add(new UnoCard());
        }
        return cards;
    }

    //starter for discard pile
    ArrayList<UnoCard> startDiscardPile(){
        ArrayList<UnoCard> discard = new ArrayList<UnoCard>();
        return discard;
    }

    // getter methods
    public ArrayList<UnoCard> getPlayer0Hand() {
        return player0Hand;
    }

    public ArrayList<UnoCard> getPlayer1Hand() {
        return player1Hand;
    }

    public ArrayList<UnoCard> getDrawPile() {
        return drawPile;
    }

    public ArrayList<UnoCard> getDiscardPile() {
        return discardPile;
    }
}



