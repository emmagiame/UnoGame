package com.example.unogame;

import static com.example.game.GameFramework.utilities.Saving.SEPARATOR;

import com.example.game.GameFramework.infoMessage.GameState;

import java.util.ArrayList;

public class UnoGameState extends GameState {

    /*needs methods:
    getplayerhand (0, 1, and 2)
    getdiscardpile
    getdrawpiletop
     */

    //instance variables
    //player 0's hand
    private ArrayList<UnoCard> player0Hand;

    //player 1's hand
    private ArrayList<UnoCard> player1Hand;

    //player 2's hand
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
     * <p>
     * the method returns a formatted string representation of the game state, including the number of
     * setup turns,the current setup turn, player hands, discard pile, draw pile, and player's turn.
     */

    @Override
    //print all variables in the current game state
    public String toString() {
        //print scores of all players
        String p0s = "player 0 score: " + this.player0Score + "\n";
        String p1s = "player 1 score: " + this.player1Score + "\n";
        String p2s = "player 2 score: " + this.player2Score + "\n";

        //print player turn
        String pt = "player turn: " + this.playerTurn + "\n";

        //loop through and print player 0 hand
        String p0h = "player 0 hand: \n";
        for(int count = 0; count < this.player0Hand.size(); count++){
            p0h = p0h +  this.player0Hand.get(count) + "\n";
        }

        //loop through and print player 1 hand
        String p1h = "player 1 hand: \n";
        for(int count = 0; count < this.player1Hand.size(); count++){
            p1h = p1h = this.player1Hand.get(count) + "\n";
        }

        //loop through and print player 2 hand
        String p2h = "player 2 hand: \n";
        for(int count = 0; count < this.player2Hand.size(); count++){
            p2h = p2h + this.player2Hand.get(count) + "\n";
        }

        //loop through and print discard pile
        String disp = "contents of discard pile: \n";
        for(int count = 0; count < this.discardPile.size(); count++){
            disp = disp + this.discardPile.get(count) + "\n";
        }

        //loop through and print draw pile
        String drawp = "contents of draw pile: \n";
        for(int count = 0; count < this.drawPile.size(); count++){
            drawp = drawp + this.drawPile.get(count) + "\n";
        }

        return p0s + p1s + p2s + pt + p0h + p1h + p2h + disp + drawp;
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



