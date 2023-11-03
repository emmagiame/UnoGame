package com.example.unogame;

import static com.example.game.GameFramework.utilities.Saving.SEPARATOR;

import com.example.game.GameFramework.LocalGame;
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

    private char currentplayableColor;

    private int currentPlayableNumber;

    private boolean isReversed;

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
        //print current playable color and num
        String color = "current playable color: " + this.currentplayableColor + "\ncurrent playable number: " + this.currentPlayableNumber;

        //print if the order is reversed or not
        String isR = "the turn order is ";
        if(this.isReversed == true){
            isR = isR + "reversed\n";
        }
        else{
            isR = isR + "not reversed\n";
        }

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

        return color + isR + p0s + p1s + p2s + pt + p0h + p1h + p2h + disp + drawp;
    }

    //constructor for objects of class UnoGameState
    public UnoGameState() {
        this.playerTurn = 0;
        this.drawPile = startDrawPile();
        this.discardPile = startDiscardPile();
        this.isReversed = false;

        //need to radomize this
        this.currentplayableColor = 'y';
        this.currentPlayableNumber = 1;
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

        //copy current color
        this.currentplayableColor = originalGame.currentplayableColor;

        //copy if reversed or not
        this.isReversed = originalGame.isReversed;
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

    public ArrayList<UnoCard> getPlayer2Hand() {
        return player2Hand;
    }

    public ArrayList<UnoCard> getDrawPile() {
        return drawPile;
    }

    public ArrayList<UnoCard> getDiscardPile() {
        return discardPile;
    }

    public int getPlayer0Score(){
        return player0Score;
    }

    public int getPlayer1Score(){
        return player1Score;
    }

    public int getPlayer2Score(){
        return player2Score;
    }

    public int getPlayerTurn(){
        return playerTurn;
    }

    public char getCurrentPlayableColor(){
        return currentplayableColor;
    }

    public int getCurrentPlayableNumber(){
        return currentPlayableNumber;
    }

    public boolean getIsReversed(){
        return isReversed;
    }

    //setters
    public void setPlayer0Score(int score0){
        this.player0Score = score0;
    }

    public void setPlayer1Score(int score1){
        this.player1Score = score1;
    }

    public void setPlayer2Score(int score2){
        this.player2Score = score2;
    }

    public void setPlayerTurn(int turn){
        this.playerTurn = turn;
    }

    //do the array lists need setters?

    //method for adding one card to the given players hand
    boolean drawCardFromDrawPile(int playerid, UnoCard card){
        //if it is not that players turn then the move is not valid so return false
        if(playerid != this.playerTurn){
            return false;
        }

        //check who's turn it is and add a card to their hand
        if(playerid == 0){
            this.player0Hand.add(card);
            return true;
        }
        else if(playerid == 1){
            this.player1Hand.add(card);
            return true;
        }

        else if(playerid == 2){
            this.player2Hand.add(card);
            return true;
        }
    }

    boolean playCard(int playerid, UnoCard card){
        //if it is not that players turn then the move is not valid so return false also
        //update whose turn it is
        if((playerid != this.playerTurn) || (card.cardColor != this.currentplayableColor) || (card.cardNumber != this.currentPlayableNumber)){
            return false;
        }
        this.discardPile.add(card);

        //if wild card
        if(card instanceof UnoCardWild){
            //if its the wild card that adds
            if(card.cardNumber == 4){
                if(playerid == 0){
                    drawCardFromDrawPile(1, this.drawPile.get(0));
                    drawCardFromDrawPile(1, this.drawPile.get(0));
                    drawCardFromDrawPile(1, this.drawPile.get(0));
                    drawCardFromDrawPile(1, this.drawPile.get(0));
                    this.playerTurn = 1;
                }
                else if(playerid == 1){
                    drawCardFromDrawPile(2, this.drawPile.get(0));
                    drawCardFromDrawPile(2, this.drawPile.get(0));
                    drawCardFromDrawPile(2, this.drawPile.get(0));
                    drawCardFromDrawPile(2, this.drawPile.get(0));
                    this.playerTurn = 2;
                }
                else if(playerid == 2){
                    drawCardFromDrawPile(0, this.drawPile.get(0));
                    drawCardFromDrawPile(0, this.drawPile.get(0));
                    drawCardFromDrawPile(0, this.drawPile.get(0));
                    drawCardFromDrawPile(0, this.drawPile.get(0));
                    this.playerTurn = 0;
                }
            }
            //if its the wild card that adds 2
            else if(card.cardNumber == 2){
                if(playerid == 0){
                    drawCardFromDrawPile(1, this.drawPile.get(0));
                    drawCardFromDrawPile(1, this.drawPile.get(0));
                    this.playerTurn = 1;
                }
                else if(playerid == 1){
                    drawCardFromDrawPile(2, this.drawPile.get(0));
                    drawCardFromDrawPile(2, this.drawPile.get(0));
                    this.playerTurn = 2;
                }
                else if(playerid == 2){
                    drawCardFromDrawPile(0, this.drawPile.get(0));
                    drawCardFromDrawPile(0, this.drawPile.get(0));
                    this.playerTurn = 0;
                }
            }
            //change the playable color
            this.currentplayableColor = card.cardColor;

            return true;

        }
        else if(card instanceof UnoCardPlus2){
            if(card.cardNumber == 2){
                if(playerid == 0){
                    drawCardFromDrawPile(1, this.drawPile.get(0));
                    drawCardFromDrawPile(1, this.drawPile.get(0));
                    this.playerTurn = 1;
                }
                else if(playerid == 1){
                    drawCardFromDrawPile(2, this.drawPile.get(0));
                    drawCardFromDrawPile(2, this.drawPile.get(0));
                    this.playerTurn = 2;
                }
                else if(playerid == 2){
                    drawCardFromDrawPile(0, this.drawPile.get(0));
                    drawCardFromDrawPile(0, this.drawPile.get(0));
                    this.playerTurn = 0;
                }
            }
            return true;
        }
        else if(card instanceof UnoCardPlus4){
            if(card.cardNumber == 4){
                if(playerid == 0){
                    drawCardFromDrawPile(1, this.drawPile.get(0));
                    drawCardFromDrawPile(1, this.drawPile.get(0));
                    drawCardFromDrawPile(1, this.drawPile.get(0));
                    drawCardFromDrawPile(1, this.drawPile.get(0));
                    this.playerTurn = 1;
                }
                else if(playerid == 1){
                    drawCardFromDrawPile(2, this.drawPile.get(0));
                    drawCardFromDrawPile(2, this.drawPile.get(0));
                    drawCardFromDrawPile(2, this.drawPile.get(0));
                    drawCardFromDrawPile(2, this.drawPile.get(0));
                    this.playerTurn = 2;
                }
                else if(playerid == 2){
                    drawCardFromDrawPile(0, this.drawPile.get(0));
                    drawCardFromDrawPile(0, this.drawPile.get(0));
                    drawCardFromDrawPile(0, this.drawPile.get(0));
                    drawCardFromDrawPile(0, this.drawPile.get(0));
                    this.playerTurn = 0;
                }
                //change the playable color
                this.currentplayableColor = card.cardColor;
                return true;
            }
        }
        else if(card instanceof UnoCardSkip){
            //needs an instance of the class, how do I get that?
            if(super.state.getNumPlayers()  == 2){
                if(playerid == 0){
                    this.playerTurn = 0;
                    return true;
                }
                else if(playerid == 1){
                    this.playerTurn = 1;
                    return true;
                }
            }
            else if(super.state.getNumPlayers() == 3){
                if(playerid == 0){
                    this.playerTurn = 2;
                    return true;
                }
                else if(playerid == 1){
                    this.playerTurn = 0;
                    return true;
                }
                else if(playerid == 2){
                    this.playerTurn = 1;
                    return true;
                }
            }

        }
        else if(card instanceof UnoCardReverse){
            //for now just a boolean, not super sure how to implement
            if(this.isReversed == true){
                this.isReversed = false;
                return true;
            }
            else if(this.isReversed == false){
                this.isReversed = true;
                return true;
            }

        }
        else{
            if(this.currentplayableColor != card.cardColor){
                this.currentplayableColor = card.cardColor;
                return true;
            }
            else if(this.currentPlayableNumber != card.cardNumber){
                this.currentPlayableNumber = card.cardNumber;
                return true;
            }

        }
        //something went wrong so return false
        return false;
    }
}



