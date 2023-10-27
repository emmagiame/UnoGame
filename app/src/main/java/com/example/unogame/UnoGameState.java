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

    //player 3's hand
    private ArrayList<UnoCard> player2Hand;

    //a list for the discard pile
    private ArrayList<UnoCard> discardPile;

    //a list to hold the draw pile
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
     *
     * the method returns a formatted string representation of the game state, including the number of
     * setup turns,the current setup turn, player hands, discard pile, draw pile, and player's turn.
     * */

    @Override
    public String toString(){
        return numSetupTurns + SEPARATOR + currentSetupTurn + "player0Hand: " + player0Hand + "player1Hand: " + player1Hand +
                "Player2Hand: " + player2Hand + "discardPile: " + discardPile + "drawPile: " + drawPile + "playerTurn" + playerTurn;
    }
}
