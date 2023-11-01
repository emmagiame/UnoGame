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

    //list of players
    private ArrayList<Integer> players;

    //player 3's hand
    private ArrayList<UnoCard> player2Hand;

    //a list for the discard pile
    private Deck discardPile;

    //a list to hold the draw pile
    private Deck drawPile;

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

    //constructor for objects of class UnoGameState
    public UnoGameState() {
        // i dont think this is done right
        this.players = players;
        this.drawPile = drawPile;
        this.discardPile = discardPile;
    }

    //copy constructor for specific player
    public UnoGameState(UnoGameState originalGame, int specificPlayer) {
        //googled deep copy constructor and this was the suggestion not right though
        /*
        this(originalGame.discardPile);
        this(originalGame.drawPile);
        */

        //deep copy of the draw & discard piles
        this.drawPile = new Deck(originalGame.drawPile);
        this.discardPile = new Deck(originalGame.discardPile);

        //deep copy of list of players
        this.players = new ArrayList<>();
        for (int player : originalGame.players) {
            if (player == specificPlayer){
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
}



