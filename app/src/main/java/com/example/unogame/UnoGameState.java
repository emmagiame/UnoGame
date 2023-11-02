package com.example.unogame;

import static com.example.game.GameFramework.utilities.Saving.SEPARATOR;

import com.example.game.GameFramework.infoMessage.GameState;

import java.util.ArrayList;

public class UnoGameState extends GameState {

    /*needs methods:
    getplayerhand
    getdiscardpile
    getdrawpiletop
     */

    //instance variables
    //player 0's hand
    private ArrayList<UnoCard> player0Hand;

    //player 1's hand
    private ArrayList<UnoCard> player1Hand;

    /*
    //should be an int of who's turn it is instead of array!!!!!!!
    private ArrayList<Integer> players;
     */

    //player 3's hand
    private ArrayList<UnoCard> player2Hand;

    //a list for the discard pile
    //should be an array list !!!!!!!
    private Deck discardPile;

    //a list to hold the draw pile
    //should be an array List !!!!!!
    private Deck drawPile;

    //which players turn it is
    private int playerToMove;

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
        // i don't think this is done right !!!!!

        this.playerToMove = 0;
        this.drawPile = ;
        this.discardPile = discardPile;
    }

    //copy constructor for specific player
    //should copy game rather than player !!!!!!
    public UnoGameState(UnoGameState originalGame,int playerToMove) {

        /* this is what it looks like in TTT

        //create a new 3x3 array, and copy the values from the original
        board = new char[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = original.board[i][j];
            }
        }
        // copy the player-to-move information
        playerToMove = original.playerToMove;
        super.numSetupTurns = original.numSetupTurns;
        super.currentSetupTurn = original.currentSetupTurn;

        */

        //deep copy of the draw & discard piles
        this.drawPile = new Deck(originalGame.drawPile);
        this.discardPile = new Deck(originalGame.discardPile);

        //deep copy of list of players
        this.players = new ArrayList<>();
        for (int player : originalGame.players) {
            if (player == specificPlayer) {
                //copy only the specific player data
                this.players.add(specificPlayer);
            } else {
                //if other players, create a player placeholder
                int otherPlayer = player;
                this.players.add(otherPlayer);
            }
        }
    }

    // creates inital arraylist of 7 cards for start of game
    ArrayList startHand(int playerTurn) {
        ArrayList<UnoCard> cards = new ArrayList<UnoCard>();
        for (int i = 0; i <= 7; i++) {
            cards.add(new UnoCard());
        }
        return cards;
    }
    // creates inital draw pile of 87 (max cards used by players initally is 21 bc 7*3
    // and max num of cards is 108 so draw = 108-21) cards for start of game
    ArrayList startDrawPile() {
        ArrayList<UnoCard> cards = new ArrayList<UnoCard>();

        for(int i = 0; i <= 87; i++) {
            cards.add(new UnoCard());
        }
        return cards;
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



