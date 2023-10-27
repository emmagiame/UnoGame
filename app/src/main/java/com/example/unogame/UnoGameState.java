package com.example.unogame;

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
}
