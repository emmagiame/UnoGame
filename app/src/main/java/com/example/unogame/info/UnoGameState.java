package com.example.unogame.info;

import android.util.Log;

import com.example.game.GameFramework.infoMessage.GameState;
import com.example.unogame.cards.UnoCard;
import com.example.unogame.cards.UnoCardPlus2;
import com.example.unogame.cards.UnoCardPlus4;
import com.example.unogame.cards.UnoCardReverse;
import com.example.unogame.cards.UnoCardSkip;
import com.example.unogame.cards.UnoCardWild;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/*

Tags
@author - Isabella Horstmanshof
@author - Emma Giamello
@author - Rhiannon McKinley

 */

public class UnoGameState extends GameState {
    //instance variables
    //player 0's hand
    private ArrayList<UnoCard> player0Hand;

    //player 1's hand
    private ArrayList<UnoCard> player1Hand;

    //player 2's hand
    private ArrayList<UnoCard> player2Hand;

    //list of discarded or played cards
    private ArrayList<UnoCard> discardPile;

    //list of cards to be drawn
    private ArrayList<UnoCard> drawPile;

    //the deck
    private ArrayList<UnoCard> shuffledDeck;

    //which players turn it is
    private int playerTurn;

    //location of card in the array
    protected int indexOfPlayedCard;

    //number of players
    int numPlayers;

    //player 0 score
    private int player0Score;

    //player 1 score
    private int player1Score;

    //player 2 score
    private int player2Score;

    //current color that can be played
    private char currentPlayableColor;

    //current number that can be played
    private int currentPlayableNumber;

    //if rotation is reversed
    private boolean isReversed;

    //the card color that will be used with wild cards, set by buttons
    private char changedPlayableColor;

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
        String color = "current playable color: " + this.currentPlayableColor + "\ncurrent playable number: " + this.currentPlayableNumber;

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

    /**
     * constructor for objects of class UnoGameState
     */
    public UnoGameState(int numPlayers) {
        this.shuffledDeck = unoDeck();
        Log.i("UnoGameState", "deck size is " + this.shuffledDeck.size());
        this.playerTurn = 0;
        this.numPlayers = numPlayers;
        this.drawPile = startDrawPile();
        this.discardPile = startDiscardPile();
        this.player0Hand = startHand();
        Log.i("UnoGameState", "card 0 in player 0 hand is " + this.player0Hand.get(0).getCardColor() + " " + this.player0Hand.get(0).getCardNumber());
        this.player0Score = 0;

        this.player1Hand = startHand();
        this.player1Score = 0;
        Log.i("UnoGameState", "card 0 in player 1 hand is " + this.player1Hand.get(0).getCardColor() + " " + this.player1Hand.get(0).getCardNumber());
        if(numPlayers > 2) {
            this.player2Hand = startHand();
            Log.i("UnoGameState", "card 0 in player 2 hand is " + this.player2Hand.get(0).getCardColor() + " " + this.player2Hand.get(0).getCardNumber());
            this.player2Score = 0;
        } else {
            this.player2Hand = new ArrayList<>();
        }
        this.isReversed = false;
        this.changedPlayableColor = 'r';

        //need to randomize this
        this.currentPlayableColor = 'r';
        this.currentPlayableNumber = 1;
    }

    /**
     * deep copy constructor of passed in game state
     *
     * @param originalGame
     *      current game state to be copied
     */
    public UnoGameState(UnoGameState originalGame) {

        //copy player turn
        this.playerTurn = originalGame.playerTurn;

        //copy draw and discard pile
        this.drawPile = new ArrayList<UnoCard>();
        for(UnoCard card : originalGame.drawPile){
            drawPile.add(new UnoCard(card));
        }
        this.discardPile = new ArrayList<UnoCard>();
        for(UnoCard card : originalGame.discardPile){
            discardPile.add(new UnoCard(card));
        }

        //copy all player hands
        this.player0Hand = new ArrayList<UnoCard>();
        for( UnoCard card : originalGame.player0Hand){
            player0Hand.add(new UnoCard(card));
        }
        this.player1Hand = new ArrayList<UnoCard>();
        for( UnoCard card : originalGame.player1Hand){
            player1Hand.add(new UnoCard(card));
        }

        if(this.numPlayers > 2) {
            this.player2Hand = new ArrayList<UnoCard>();
            for (UnoCard card : originalGame.player2Hand) {
                player2Hand.add(new UnoCard(card));
            }
            this.player2Score = originalGame.player2Score;
        } else {
            this.player2Hand = new ArrayList<>();
        }

        this.shuffledDeck = new ArrayList<UnoCard>();
        for( UnoCard card : originalGame.shuffledDeck){
            shuffledDeck.add(new UnoCard(card));
        }

        //copy all player scores
        this.player0Score = originalGame.player0Score;
        this.player1Score = originalGame.player1Score;

        //copy current color
        this.currentPlayableColor = originalGame.currentPlayableColor;

        //copy current number
        this.currentPlayableNumber = originalGame.currentPlayableNumber;

        //copy if reversed or not
        this.isReversed = originalGame.isReversed;

        //copy number of players
        this.numPlayers = originalGame.numPlayers;

        //copy index of played card
        this.indexOfPlayedCard = originalGame.indexOfPlayedCard;
    }


    /**
     *  gives a given player their starting hand of 7 cards
     *
     * @return
     *      players hand
     */
    public ArrayList<UnoCard> startHand() {
        ArrayList<UnoCard> playerCards = new ArrayList<UnoCard>();
        for (int i = 0; i <= 7; i++) {
            Log.i("UnoGameState", "card 0 in shuffled deck is " + this.getShuffledDeck().get(0).getCardColor() + " " + this.getShuffledDeck().get(0).getCardNumber());
            playerCards.add(this.getShuffledDeck().get(0));
            this.getShuffledDeck().remove(0);
        }
        return playerCards;
    }
    //
    //

    /**
     * creates initial draw pile of 87
     * (max cards used by players initially is 21 bc 7*3 and max num of cards is 108 so draw = 108-21)
     * cards for start of game
     *
     * @return
     *      returns list of cards that is draw pile
     */
    public ArrayList<UnoCard> startDrawPile() {
        ArrayList<UnoCard> drawCards = new ArrayList<UnoCard>();

        for(int i = 0; i <= 87; i++) {
            drawCards.add(this.getShuffledDeck().get(0));
            this.getShuffledDeck().remove(0);
        }
        return drawCards;
    }

    /**
     * starter for discard pile
     *
     * @return
     *     list of cards that represent the discard pile
     */
    public ArrayList<UnoCard> startDiscardPile(){
        ArrayList<UnoCard> discard = new ArrayList<UnoCard>();
        discard.add(this.getShuffledDeck().get(0));
        this.getShuffledDeck().remove(0);
        return discard;
    }

    // getter methods

    /**
     *  gets location of card in the array
     *
     * @return
     *      - card index in array
     */
    public int getIndexOfPlayedCard() { return indexOfPlayedCard; }

    public int getNumPlayers() { return numPlayers;}

    /**
     * gets player 0's hand
     *
     * @return
     *      player 0's hand
     */
    public ArrayList<UnoCard> getPlayer0Hand() {

        return player0Hand;
    }

    /**
     *
     *      return player 1's hand
     */
    public ArrayList<UnoCard> getPlayer1Hand() {
        return player1Hand;
    }

    /**
     * get player 2's hand
     *
     * @return
     *      reutrn player 2's hand
     */
    public ArrayList<UnoCard> getPlayer2Hand() {
        return player2Hand;
    }

    /**
     * get draw pile
     *
     * @return
     *      return list of cards that is draw pile
     */
    public ArrayList<UnoCard> getDrawPile() {
        return drawPile;
    }

    /**
     * gets discard pile
     *
     * @return
     *      returns list of cards that make discard pile
     */
    public ArrayList<UnoCard> getDiscardPile() {

        return discardPile;
    }

    public ArrayList<UnoCard> getShuffledDeck(){
        return shuffledDeck;
    }

    /**
     * gets player 0's score
     *
     * @return
     *      return player 0's score
     */
    public int getPlayer0Score(){
        return player0Score;
    }

    /**
     * gets player 1's score
     *
     * @return
     *      return player 1's score
     */
    public int getPlayer1Score(){
        return player1Score;
    }

    /**
     * get player 2's score
     *
     * @return
     *      return player 2's score
     */
    public int getPlayer2Score(){
        return player2Score;
    }

    /**
     * get player who's turn it is
     *
     * @return
     *      returns the player who's turn it is
     */
    public int getPlayerTurn(){
        return playerTurn;
    }

    /**
     * gets the color that can be played
     *
     * @return
     *      returns the color that can be played
     */
    public char getCurrentPlayableColor(){
        currentPlayableColor = this.getDiscardPile().get(0).getCardColor();
        return currentPlayableColor;
    }

    /**
     * get the number that can be played
     *
     * @return
     *      returns the number that can be played
     */
    public int getCurrentPlayableNumber(){
        currentPlayableNumber = this.getDiscardPile().get(0).getCardNumber();
        return currentPlayableNumber;
    }

    public void setCurrentPlayableColor(char color){
        this.currentPlayableColor = color;
    }

    public void setCurrentPlayableNumber(int num){
        this.currentPlayableNumber = num;
    }

    /**
     * get if the rotation of turns is reversed
     *
     * @return
     *      returns if the rotation is reversed
     */
    public boolean getIsReversed(){
        return isReversed;
    }

    // setters

    /**
     *  sets the index of the card to the variable
     *
     * @param indexOfPlayedCard - index of played card
     */
    public void setIndexOfPlayedCard(int indexOfPlayedCard) { this.indexOfPlayedCard = indexOfPlayedCard; }

    /**
     * sets player 0's score
     *
     * @param score0 - player 0's score
     */
    //setters
    public void setPlayer0Score(int score0){
        this.player0Score = score0;
    }

    /**
     * sets player 1's score
     *
     * @param score1 - player 1's score
     */
    public void setPlayer1Score(int score1){
        this.player1Score = score1;
    }

    /**
     * sets player 2's score
     *
     * @param score2 - player 2's score
     */
    public void setPlayer2Score(int score2){
        this.player2Score = score2;
    }

    public void setChangedPlayableColor(char colorIn){
        this.changedPlayableColor = colorIn;
    }

    public char getChangedPlayableColor(){
        return this.changedPlayableColor;
    }


    /**
     * sets who's turn it is
     *
     * @param turn - player who's turn it is
     */
    public void setPlayerTurn(int turn){
        this.playerTurn = turn;
    }

    //do the array lists need setters?

    /**
     * adds one card to the given players hand because they draw a card
     *
     * @param playerId - player drawing a card
     * @param card - card added to their hand
     * @return
     *      return true if card is added, false otherwise
     */
    public boolean drawCardFromDrawPile(int playerId, UnoCard card) {
        //if it is not that players turn then the move is not valid so return false
        if (playerId != this.playerTurn) {
            return false;
        }

        //check who's turn it is and add a card to their hand
        if (playerId == 0) {
            this.player0Hand.add(card);
            this.drawPile.remove(0);
        } else if (playerId == 1) {
            this.player1Hand.add(card);
            this.drawPile.remove(0);
        } else if (playerId == 2) {
            this.player2Hand.add(card);
            this.drawPile.remove(0);
        }

        //change turn
        if (this.isReversed == false) {
            if (this.getNumPlayers() == 2) {
                if (this.getPlayerTurn() == 0) {
                    this.setPlayerTurn(1);
                    return true;
                } else {
                    this.setPlayerTurn(0);
                    return true;
                }
            } else if (this.getNumPlayers() == 3) {
                if (this.getPlayerTurn() == 0) {
                    this.setPlayerTurn(1);
                    return true;
                } else if (this.getPlayerTurn() == 1) {
                    this.setPlayerTurn(2);
                    return true;
                } else {
                    this.setPlayerTurn(0);
                    return true;
                }
            }
        } else if (this.isReversed == true) {
            if (this.getNumPlayers() == 2) {
                if (this.getPlayerTurn() == 0) {
                    this.setPlayerTurn(1);
                    return true;
                } else {
                    this.setPlayerTurn(0);
                    return true;
                }
            } else if (this.getNumPlayers() == 3) {
                if (this.getPlayerTurn() == 0) {
                    this.setPlayerTurn(2);
                    return true;
                } else if (this.getPlayerTurn() == 1) {
                    this.setPlayerTurn(0);
                    return true;
                } else {
                    this.setPlayerTurn(1);
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * allows player to declare uno
     *
     * @param playerId - player who's turn it is
     * @return
     *      return true if uno was declared
     */

    public boolean declareUno(int playerId) {

        if(playerId == this.playerTurn) {

            if(playerId == 0 && player0Hand.size() == 1){
                return true;

            }
            else if(playerId == 1 && player1Hand.size() == 1){
                return true;
            }

           else if (playerId == 2 && player2Hand.size() == 1){
                return true;
            }
        }
        return false;
    }

    /**
     * allows current player to "call out" previous player if
     * they have uno but did not declare it on their turn
     *
     * @param playerId - player who's turn it is
     * @param card - card on top of draw pile
     * @return
     *      return true if prev player was called out
     */

    public boolean callOut(int playerId, UnoCard card) {

        // get id of prev player
        int prevPlayerId = getPrevPlayer(playerId);

        // if the prev player had uno but did not declare it
        if (playerId == this.playerTurn) {
            if (prevPlayerId == 0 && player0Hand.size() == 1 && declareUno(prevPlayerId) == false) {
                // add two cards to their hand
                this.player0Hand.add(card);
                this.player0Hand.add(card);
                return true;
            } else if (prevPlayerId == 1 && player1Hand.size() == 1 && declareUno(prevPlayerId) == false) {
                this.player1Hand.add(card);
                this.player1Hand.add(card);
                return true;
            } else if (prevPlayerId == 2 && player1Hand.size() == 2 && declareUno(prevPlayerId) == false) {
                this.player2Hand.add(card);
                this.player2Hand.add(card);
                return true;
            }

        }
        return false;
    }

    /**
     * gets id of player who played immedietly before current player
     *
     * @param playerId - player who's turn it is
     * @return
     *      return id of prev player
     */
    public int getPrevPlayer(int playerId){

        int prevPlayerId = playerId - 1;

        if(prevPlayerId == -1) {
            prevPlayerId = 2;
        }

        return prevPlayerId;
    }

    public int gameOver(){
        if(this.getPlayer0Hand().size() == 0)  {
            return 0;
        }

        else if(this.getPlayer1Hand().size() == 0)  {
            return 1;
        }

        if(this.getNumPlayers() > 2) {
            if (this.getPlayer2Hand().size() == 0) {
                return 2;
            }
        }

        return -1;
    }


    /**
     * when a given player plays a card
     *
     * @param playerId - player who played a card
     * @param card - card played
     * @return
     *      return true if card is played, false otherwise
     */
    public boolean playCard(int playerId, UnoCard card) {
        Log.i("playCard", "current player turn: " + this.getPlayerTurn());
        //if number is -1 that means any card number can be played because a wild card or special card was played
        //if it is not that players turn then the move is not valid so return false also
        //currently this method also changes the player turn but when we implement reverse I think we will want to change the turn outside of this method or write
        //a method to change the turn and call it in playCard instead
        if ((playerId == this.getPlayerTurn()) && ((card.getCardColor() == this.getCurrentPlayableColor()) || (card.getCardNumber() == this.getCurrentPlayableNumber()) || (card.getCardNumber() == -1) || (card.getCardColor() == 'n'))) {
        Log.i("playCard", "current color " + this.getCurrentPlayableColor() + " current number " + this.getCurrentPlayableNumber());
        // remove card from players hand
        if (playerId == 0) {
            player0Hand.remove(card);
        } else if (playerId == 1) {
            player1Hand.remove(card);
        } else {
            player2Hand.remove(card);
        }

        // add card to discard pile
        this.discardPile.add(0, card);

        // set new playable color and number
        this.setCurrentPlayableColor(card.getCardColor());
        this.setCurrentPlayableNumber(card.getCardNumber());

        // if wild card
        if (card instanceof UnoCardWild) {
            //if its the wild card
                if (this.numPlayers == 3) {
                    if (playerId == 0) {
                        drawCardFromDrawPile(1, this.drawPile.get(0));
                        drawCardFromDrawPile(1, this.drawPile.get(0));
                        drawCardFromDrawPile(1, this.drawPile.get(0));
                        drawCardFromDrawPile(1, this.drawPile.get(0));
                        this.setCurrentPlayableColor(changedPlayableColor);
                        this.setCurrentPlayableNumber(-1);
                        //this.playerTurn = 1;
                    } else if (playerId == 1) {
                        drawCardFromDrawPile(2, this.drawPile.get(0));
                        drawCardFromDrawPile(2, this.drawPile.get(0));
                        drawCardFromDrawPile(2, this.drawPile.get(0));
                        drawCardFromDrawPile(2, this.drawPile.get(0));
                        this.setCurrentPlayableColor(changedPlayableColor);
                        this.setCurrentPlayableNumber(-1);
                        //this.playerTurn = 2;
                    } else if (playerId == 2) {
                        drawCardFromDrawPile(0, this.drawPile.get(0));
                        drawCardFromDrawPile(0, this.drawPile.get(0));
                        drawCardFromDrawPile(0, this.drawPile.get(0));
                        drawCardFromDrawPile(0, this.drawPile.get(0));
                        this.setCurrentPlayableColor(changedPlayableColor);
                        this.setCurrentPlayableNumber(-1);
                        //this.playerTurn = 0;
                    }
                } else if (this.numPlayers == 2) {
                    if (playerId == 0) {
                        drawCardFromDrawPile(1, this.drawPile.get(0));
                        drawCardFromDrawPile(1, this.drawPile.get(0));
                        drawCardFromDrawPile(1, this.drawPile.get(0));
                        drawCardFromDrawPile(1, this.drawPile.get(0));
                        this.setCurrentPlayableColor(changedPlayableColor);
                        this.setCurrentPlayableNumber(-1);
                        //this.playerTurn = 1;
                    } else if (playerId == 1) {
                        drawCardFromDrawPile(0, this.drawPile.get(0));
                        drawCardFromDrawPile(0, this.drawPile.get(0));
                        drawCardFromDrawPile(0, this.drawPile.get(0));
                        drawCardFromDrawPile(0, this.drawPile.get(0));
                        this.setCurrentPlayableColor(changedPlayableColor);
                        this.setCurrentPlayableNumber(-1);
                        //this.playerTurn = 0;
                    }
                }

        } else if (card instanceof UnoCardPlus2) {
                if (this.numPlayers == 3) {
                    if (playerId == 0) {
                        drawCardFromDrawPile(1, this.drawPile.get(0));
                        drawCardFromDrawPile(1, this.drawPile.get(0));
                        //this.playerTurn = 1;
                    } else if (playerId == 1) {
                        drawCardFromDrawPile(2, this.drawPile.get(0));
                        drawCardFromDrawPile(2, this.drawPile.get(0));
                        //this.playerTurn = 2;
                    } else if (playerId == 2) {
                        drawCardFromDrawPile(0, this.drawPile.get(0));
                        drawCardFromDrawPile(0, this.drawPile.get(0));
                        //this.playerTurn = 0;
                    }
                    //change the playable color
                    this.currentPlayableColor = card.getCardColor();
                } else if (this.numPlayers == 2) {
                    if (playerId == 0) {
                        drawCardFromDrawPile(1, this.drawPile.get(0));
                        drawCardFromDrawPile(1, this.drawPile.get(0));
                        //this.playerTurn = 1;
                    } else if (playerId == 1) {
                        drawCardFromDrawPile(0, this.drawPile.get(0));
                        drawCardFromDrawPile(0, this.drawPile.get(0));
                        //this.playerTurn = 0;
                    }
                    //change the playable color
                    this.currentPlayableColor = card.getCardColor();
                }
                //return true;
             else if (card instanceof UnoCardPlus4) {
                    if (this.numPlayers == 3) {
                        if (playerId == 0) {
                            drawCardFromDrawPile(1, this.drawPile.get(0));
                            drawCardFromDrawPile(1, this.drawPile.get(0));
                            drawCardFromDrawPile(1, this.drawPile.get(0));
                            drawCardFromDrawPile(1, this.drawPile.get(0));
                            //this.playerTurn = 1;
                        } else if (playerId == 1) {
                            drawCardFromDrawPile(2, this.drawPile.get(0));
                            drawCardFromDrawPile(2, this.drawPile.get(0));
                            drawCardFromDrawPile(2, this.drawPile.get(0));
                            drawCardFromDrawPile(2, this.drawPile.get(0));
                            //this.playerTurn = 2;
                        } else if (playerId == 2) {
                            drawCardFromDrawPile(0, this.drawPile.get(0));
                            drawCardFromDrawPile(0, this.drawPile.get(0));
                            drawCardFromDrawPile(0, this.drawPile.get(0));
                            drawCardFromDrawPile(0, this.drawPile.get(0));
                            //this.playerTurn = 0;
                        }
                        //change the playable color
                        this.currentPlayableColor = this.getChangedPlayableColor();

                    } else if (this.numPlayers == 2) {
                        if (playerId == 0) {
                            drawCardFromDrawPile(1, this.drawPile.get(0));
                            drawCardFromDrawPile(1, this.drawPile.get(0));
                            drawCardFromDrawPile(1, this.drawPile.get(0));
                            drawCardFromDrawPile(1, this.drawPile.get(0));
                            //this.playerTurn = 1;
                        } else if (playerId == 1) {
                            drawCardFromDrawPile(0, this.drawPile.get(0));
                            drawCardFromDrawPile(0, this.drawPile.get(0));
                            drawCardFromDrawPile(0, this.drawPile.get(0));
                            drawCardFromDrawPile(0, this.drawPile.get(0));
                            //this.playerTurn = 0;
                        }
                        //change the playable color
                        this.currentPlayableColor = this.getChangedPlayableColor();
                        //return true;
                    }
                } else if (card instanceof UnoCardSkip) {//keep the returns here
                    if (this.numPlayers == 2) {
                        this.setPlayerTurn(playerId);
                        this.currentPlayableColor = card.getCardColor();
                    } else if (this.numPlayers == 3) {
                        if (playerId == 0) {
                            this.setPlayerTurn(2);
                            this.currentPlayableColor = card.getCardColor();
                            return true;
                        } else if (playerId == 1) {
                            this.setPlayerTurn(0);
                            this.currentPlayableColor = card.getCardColor();
                            return true;
                        } else if (playerId == 2) {
                            this.setPlayerTurn(1);
                            this.currentPlayableColor = card.getCardColor();
                            return true;
                        }
                    }

                } else if (card instanceof UnoCardReverse) {
                    //for now just a boolean, not super sure how to implement
                    if (this.isReversed == true) {
                        this.currentPlayableColor = card.getCardColor();
                        this.isReversed = false;
                    } else if (this.isReversed == false) {
                        this.currentPlayableColor = card.getCardColor();
                        this.isReversed = true;
                    }

                }
            }
        }
        //change turn
        if(this.isReversed == false){
            if(this.getNumPlayers() == 2){
                if(this.getPlayerTurn() == 0){
                    this.setPlayerTurn(1);
                    return true;
                }
                else{
                    this.setPlayerTurn(0);
                    return true;
                }
            }
            else if (this.getNumPlayers() == 3) {
                    if(this.getPlayerTurn() == 0){
                        this.setPlayerTurn(1);
                        return true;
                    }
                    else if(this.getPlayerTurn() == 1){
                        this.setPlayerTurn(2);
                        return true;
                    }
                    else {
                        this.setPlayerTurn(0);
                        return true;
                    }
            }
        }
        else if(this.isReversed == true){
            if(this.getNumPlayers() == 2){
                if(this.getPlayerTurn() == 0){
                    this.setPlayerTurn(1);
                    return true;
                }
                else{
                    this.setPlayerTurn(0);
                    return true;
                }
            }
            else if (this.getNumPlayers() == 3) {
                if(this.getPlayerTurn() == 0){
                    this.setPlayerTurn(2);
                    return true;
                }
                else if(this.getPlayerTurn() == 1){
                    this.setPlayerTurn(0);
                    return true;
                }
                else {
                    this.setPlayerTurn(1);
                    return true;
                }
            }
        }

        //wasn't a playable card
        return false;

    }

    //make the deck
    public ArrayList<UnoCard> unoDeck(){
        ArrayList<UnoCard> deck = new ArrayList<UnoCard>();
        UnoCard[] holder = new UnoCard[108];

        //make red cards
        UnoCard r0 = new UnoCard();
        r0.setCard('r', 0);
        holder[0] = r0;

        UnoCard r1 = new UnoCard();
        r1.setCard('r', 1);
        holder[1] = r1;

        UnoCard r11 = new UnoCard();
        r11.setCard('r', 1);
        holder[2] = r11;

        UnoCard r2 = new UnoCard();
        r2.setCard('r', 2);
        holder[3] = r2;

        UnoCard r22 = new UnoCard();
        r22.setCard('r', 2);
        holder[4] = r22;

        UnoCard r3 = new UnoCard();
        r3.setCard('r', 3);
        holder[5] = r3;

        UnoCard r33 = new UnoCard();
        r33.setCard('r', 3);
        holder[6] = r33;

        UnoCard r4 = new UnoCard();
        r4.setCard('r', 4);
        holder[7] = r4;

        UnoCard r44 = new UnoCard();
        r44.setCard('r', 4);
        holder[8] = r44;

        UnoCard r5 = new UnoCard();
        r5.setCard('r', 5);
        holder[9] = r5;

        UnoCard r55 = new UnoCard();
        r55.setCard('r', 5);
        holder[10] = r55;

        UnoCard r6 = new UnoCard();
        r6.setCard('r', 6);
        holder[11] = r6;

        UnoCard r66 = new UnoCard();
        r66.setCard('r', 6);
        holder[12] = r66;

        UnoCard r7 = new UnoCard();
        r7.setCard('r', 7);
        holder[13] = r7;

        UnoCard r77 = new UnoCard();
        r77.setCard('r', 7);
        holder[14] = r77;

        UnoCard r8 = new UnoCard();
        r8.setCard('r', 8);
        holder[15] = r8;

        UnoCard r88 = new UnoCard();
        r88.setCard('r', 8);
        holder[16] = r88;

        UnoCard r9 = new UnoCard();
        r9.setCard('r', 9);
        holder[17] = r9;

        UnoCard r99 = new UnoCard();
        r99.setCard('r', 9);
        holder[18] = r99;

        //add special red cards
        UnoCard temp = new UnoCard();
        UnoCardSkip rS = new UnoCardSkip(temp);
        rS.setCard('r', -1);
        holder[19] = rS;

        UnoCardSkip rSS = new UnoCardSkip(temp);
        rSS.setCard('r', -1);
        holder[20] = rSS;

        UnoCardReverse rR = new UnoCardReverse(temp);
        rR.setCard('r', -1);
        holder[21] = rR;

        UnoCardReverse rRR = new UnoCardReverse(temp);
        rRR.setCard('r', -1);
        holder[22] = rRR;

        UnoCardPlus2 rP = new UnoCardPlus2(temp);
        rP.setCard('r', -1);
        holder[23] = rP;

        UnoCardPlus2 rPP = new UnoCardPlus2(temp);
        rPP.setCard('r', -1);
        holder[24] = rPP;

        //make blue cards
        UnoCard b0 = new UnoCard();
        b0.setCard('b', 0);
        holder[25] = b0;

        UnoCard b1 = new UnoCard();
        b1.setCard('b', 1);
        holder[26] = b1;

        UnoCard b11 = new UnoCard();
        b11.setCard('b', 1);
        holder[27] = b11;

        UnoCard b2 = new UnoCard();
        b2.setCard('b', 2);
        holder[28] = b2;

        UnoCard b22 = new UnoCard();
        b22.setCard('b', 2);
        holder[29] = b22;

        UnoCard b3 = new UnoCard();
        b3.setCard('b', 3);
        holder[30] = b3;

        UnoCard b33 = new UnoCard();
        b33.setCard('b', 3);
        holder[31] = b33;

        UnoCard b4 = new UnoCard();
        b4.setCard('b', 4);
        holder[32] = b4;

        UnoCard b44 = new UnoCard();
        b44.setCard('b', 4);
        holder[33] = b44;

        UnoCard b5 = new UnoCard();
        b5.setCard('b', 5);
        holder[34] = b5;

        UnoCard b55 = new UnoCard();
        b55.setCard('b', 5);
        holder[35] = b55;

        UnoCard b6 = new UnoCard();
        b6.setCard('b', 6);
        holder[36] = b6;

        UnoCard b66 = new UnoCard();
        b66.setCard('b', 6);
        holder[37] = b66;

        UnoCard b7 = new UnoCard();
        b7.setCard('b', 7);
        holder[38] = b7;

        UnoCard b77 = new UnoCard();
        b77.setCard('b', 7);
        holder[39] = b77;

        UnoCard b8 = new UnoCard();
        b8.setCard('b', 8);
        holder[40] = b8;

        UnoCard b88 = new UnoCard();
        b88.setCard('b', 8);
        holder[41] = b88;

        UnoCard b9 = new UnoCard();
        b9.setCard('b', 9);
        holder[42] = b9;

        UnoCard b99 = new UnoCard();
        b99.setCard('b', 9);
        holder[43] = b99;

        //add special blue cards
        UnoCard tempb = new UnoCard();
        UnoCardSkip bS = new UnoCardSkip(tempb);
        bS.setCard('b', -1);
        holder[44] = bS;

        UnoCardSkip bSS = new UnoCardSkip(tempb);
        bSS.setCard('b', -1);
        holder[45] = bSS;

        UnoCardReverse bR = new UnoCardReverse(tempb);
        bR.setCard('b', -1);
        holder[46] = bR;

        UnoCardReverse bRR = new UnoCardReverse(tempb);
        bRR.setCard('b', -1);
        holder[47] = bRR;

        UnoCardPlus2 bP = new UnoCardPlus2(tempb);
        bP.setCard('b', -1);
        holder[48] = bP;

        UnoCardPlus2 bPP = new UnoCardPlus2(tempb);
        bPP.setCard('b', -1);
        holder[49] = bPP;

        //make green cards
        UnoCard g0 = new UnoCard();
        g0.setCard('g', 0);
        holder[50] = g0;

        UnoCard g1 = new UnoCard();
        g1.setCard('g', 1);
        holder[51] = g1;

        UnoCard g11 = new UnoCard();
        g11.setCard('g', 1);
        holder[52] = g11;

        UnoCard g2 = new UnoCard();
        g2.setCard('g', 2);
        holder[53] = g2;

        UnoCard g22 = new UnoCard();
        g22.setCard('g', 2);
        holder[54] = g22;

        UnoCard g3 = new UnoCard();
        g3.setCard('g', 3);
        holder[55] = g3;

        UnoCard g33 = new UnoCard();
        g33.setCard('g', 3);
        holder[56] = g33;

        UnoCard g4 = new UnoCard();
        g4.setCard('g', 4);
        holder[57] = g4;

        UnoCard g44 = new UnoCard();
        g44.setCard('g', 4);
        holder[58] = g44;

        UnoCard g5 = new UnoCard();
        g5.setCard('g', 5);
        holder[59] = g5;

        UnoCard g55 = new UnoCard();
        g55.setCard('g', 5);
        holder[60] = g55;

        UnoCard g6 = new UnoCard();
        g6.setCard('g', 6);
        holder[61] = g6;

        UnoCard g66 = new UnoCard();
        g66.setCard('g', 6);
        holder[62] = g66;

        UnoCard g7 = new UnoCard();
        g7.setCard('g', 7);
        holder[63] = g7;

        UnoCard g77 = new UnoCard();
        g77.setCard('g', 7);
        holder[64] = g77;

        UnoCard g8 = new UnoCard();
        g8.setCard('g', 8);
        holder[65] = g8;

        UnoCard g88 = new UnoCard();
        g88.setCard('g', 8);
        holder[66] = g88;

        UnoCard g9 = new UnoCard();
        g9.setCard('g', 9);
        holder[67] = g9;

        UnoCard g99 = new UnoCard();
        g99.setCard('g', 9);
        holder[68] = g99;

        //add special green cards
        UnoCard tempg = new UnoCard();
        UnoCardSkip gS = new UnoCardSkip(tempg);
        gS.setCard('g', -1);
        holder[69] = gS;

        UnoCardSkip gSS = new UnoCardSkip(tempg);
        gSS.setCard('g', -1);
        holder[70] = gSS;

        UnoCardReverse gR = new UnoCardReverse(tempg);
        gR.setCard('g', -1);
        holder[71] = gR;

        UnoCardReverse gRR = new UnoCardReverse(tempg);
        gRR.setCard('g', -1);
        holder[72] = gRR;

        UnoCardPlus2 gP = new UnoCardPlus2(tempg);
        gP.setCard('g', -1);
        holder[73] = gP;

        UnoCardPlus2 gPP = new UnoCardPlus2(tempg);
        gPP.setCard('g', -1);
        holder[74] = gPP;

        //make yellow cards
        UnoCard y0 = new UnoCard();
        y0.setCard('y', 0);
        holder[75] = y0;

        UnoCard y1 = new UnoCard();
        y1.setCard('y', 1);
        holder[76] = y1;

        UnoCard y11 = new UnoCard();
        y11.setCard('y', 1);
        holder[77] = y11;

        UnoCard y2 = new UnoCard();
        y2.setCard('y', 2);
        holder[78] = y2;

        UnoCard y22 = new UnoCard();
        y22.setCard('y', 2);
        holder[79] = y22;

        UnoCard y3 = new UnoCard();
        y3.setCard('y', 3);
        holder[80] = y3;

        UnoCard y33 = new UnoCard();
        y33.setCard('y', 3);
        holder[81] = y33;

        UnoCard y4 = new UnoCard();
        y4.setCard('y', 4);
        holder[82] = y4;

        UnoCard y44 = new UnoCard();
        y44.setCard('y', 4);
        holder[83] = y44;

        UnoCard y5 = new UnoCard();
        y5.setCard('y', 5);
        holder[84] = y5;

        UnoCard y55 = new UnoCard();
        y55.setCard('y', 5);
        holder[85] = y55;

        UnoCard y6 = new UnoCard();
        y6.setCard('y', 6);
        holder[86] = y6;

        UnoCard y66 = new UnoCard();
        y66.setCard('y', 6);
        holder[87] = y66;

        UnoCard y7 = new UnoCard();
        y7.setCard('y', 7);
        holder[88] = y7;

        UnoCard y77 = new UnoCard();
        y77.setCard('y', 7);
        holder[89] = y77;

        UnoCard y8 = new UnoCard();
        y8.setCard('y', 8);
        holder[90] = y8;

        UnoCard y88 = new UnoCard();
        y88.setCard('y', 8);
        holder[91] = y88;

        UnoCard y9 = new UnoCard();
        y9.setCard('y', 9);
        holder[92] = y9;

        UnoCard y99 = new UnoCard();
        y99.setCard('y', 9);
        holder[93] = y99;

        //add special yellow cards
        UnoCard tempy = new UnoCard();
        UnoCardSkip yS = new UnoCardSkip(tempy);
        yS.setCard('y', -1);
        holder[94] = yS;

        UnoCardSkip ySS = new UnoCardSkip(tempy);
        ySS.setCard('y', -1);
        holder[95] = ySS;

        UnoCardReverse yR = new UnoCardReverse(tempy);
        yR.setCard('y', -1);
        holder[96] = yR;

        UnoCardReverse yRR = new UnoCardReverse(tempy);
        yRR.setCard('y', -1);
        holder[97] = yRR;

        UnoCardPlus2 yP = new UnoCardPlus2(tempy);
        yP.setCard('y', -1);
        holder[98] = yP;

        UnoCardPlus2 yPP = new UnoCardPlus2(tempy);
        yPP.setCard('y', -1);
        holder[99] = yPP;

        //add special no color cards
        UnoCard tempa = new UnoCard();
        UnoCardPlus4 p4 = new UnoCardPlus4(tempa);
        UnoCardPlus4 pp4 = new UnoCardPlus4(tempa);
        UnoCardPlus4 ppp4 = new UnoCardPlus4(tempa);
        UnoCardPlus4 pppp4 = new UnoCardPlus4(tempa);
        p4.setCard('n', -1);
        pp4.setCard('n', -1);
        ppp4.setCard('n', -1);
        pppp4.setCard('n', -1);
        holder[100] = p4;
        holder[101] = pp4;
        holder[102] = ppp4;
        holder[103] = pppp4;

        UnoCardWild w = new UnoCardWild(tempa);
        UnoCardWild ww = new UnoCardWild(tempa);
        UnoCardWild www = new UnoCardWild(tempa);
        UnoCardWild wwww = new UnoCardWild(tempa);
        w.setCard('n', -1);
        ww.setCard('n', -1);
        www.setCard('n', -1);
        wwww.setCard('n', -1);
        holder[104] = w;
        holder[105] = ww;
        holder[106] = www;
        holder[107] = wwww;

        for(int i = 0; i < 108; i++){
            deck.add(holder[i]);
        }
        Collections.shuffle(deck);

        Log.i("UnoGameState", "deck size is " + deck.size());
        return deck;
    }
}




