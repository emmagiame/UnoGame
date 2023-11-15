package com.example.unogame.info;

import com.example.game.GameFramework.infoMessage.GameState;
import com.example.unogame.cards.UnoCard;
import com.example.unogame.cards.UnoCardPlus2;
import com.example.unogame.cards.UnoCardPlus4;
import com.example.unogame.cards.UnoCardReverse;
import com.example.unogame.cards.UnoCardSkip;
import com.example.unogame.cards.UnoCardWild;

import java.util.ArrayList;

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
        this.playerTurn = 0;
        this.numPlayers = numPlayers;
        this.drawPile = startDrawPile();
        this.discardPile = startDiscardPile();
        this.player0Hand = startHand(0);
        this.player1Hand = startHand(1);
        this.player2Hand = startHand(2);
        this.isReversed = false;


        //need to radomize this
        this.currentPlayableColor = 'y';
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

        this.player2Hand = new ArrayList<UnoCard>();
        for( UnoCard card : originalGame.player2Hand){
            player2Hand.add(new UnoCard(card));
        }

        //copy all player scores
        this.player0Score = originalGame.player0Score;
        this.player1Score = originalGame.player1Score;
        this.player2Score = originalGame.player2Score;

        //copy current color
        this.currentPlayableColor = originalGame.currentPlayableColor;

        //copy if reversed or not
        this.isReversed = originalGame.isReversed;
    }


    /**
     *  gives a given player their starting hand of 7 cards
     *
     * @param playerTurn - player who's turn it is
     * @return
     *      players hand
     */
    public ArrayList<UnoCard> startHand(int playerTurn) {
        ArrayList<UnoCard> playerCards = new ArrayList<UnoCard>();
        for (int i = 0; i <= 7; i++) {
            playerCards.add(new UnoCard());
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
            drawCards.add(new UnoCard());
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
     * get player 1's hand
     *
     * @return
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
        return currentPlayableColor;
    }

    /**
     * get the number that can be played
     *
     * @return
     *      returns the number that can be played
     */
    public int getCurrentPlayableNumber(){
        return currentPlayableNumber;
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
    public boolean drawCardFromDrawPile(int playerId, UnoCard card){
        //if it is not that players turn then the move is not valid so return false
        if(playerId != this.playerTurn){
            return false;
        }

        //check who's turn it is and add a card to their hand
        if(playerId == 0){
            this.player0Hand.add(card);
            return true;
        }
        else if(playerId == 1) {
            this.player1Hand.add(card);
            return true;
        }

        else if(playerId == 2){
            this.player2Hand.add(card);
            return true;
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

    /**
     * when a given player plays a card
     *
     * @param playerId - player who played a card
     * @param card - card played
     * @return
     *      return true if card is played, false otherwise
     */
    public boolean playCard(int playerId, UnoCard card) {
        //if it is not that players turn then the move is not valid so return false also
        //currently this method also changes the player turn but when we implement reverse I think we will want to change the turn outside of this method or write
        //a method to change the turn and call it in playCard instead
        if (playerId == this.playerTurn && (card.getCardColor() == this.currentPlayableColor) || (card.getCardNumber() == this.currentPlayableNumber)) {

        // remove card from players hand
        if (playerId == 0) {
            player0Hand.remove(card);
        } else if (playerId == 1) {
            player1Hand.remove(card);
        } else {
            player2Hand.remove(card);
        }

        // add card to discard pile
        this.discardPile.add(card);

        // set new playable color and number
        currentPlayableColor = card.getCardColor();
        currentPlayableNumber = card.getCardNumber();

        // if wild card
        if (card instanceof UnoCardWild) {
            //if its the wild card that adds
            if (card.getCardNumber() == 4) {
                if (this.numPlayers == 3) {
                    if (playerId == 0) {
                        drawCardFromDrawPile(1, this.drawPile.get(0));
                        drawCardFromDrawPile(1, this.drawPile.get(0));
                        drawCardFromDrawPile(1, this.drawPile.get(0));
                        drawCardFromDrawPile(1, this.drawPile.get(0));
                        this.playerTurn = 1;
                    } else if (playerId == 1) {
                        drawCardFromDrawPile(2, this.drawPile.get(0));
                        drawCardFromDrawPile(2, this.drawPile.get(0));
                        drawCardFromDrawPile(2, this.drawPile.get(0));
                        drawCardFromDrawPile(2, this.drawPile.get(0));
                        this.playerTurn = 2;
                    } else if (playerId == 2) {
                        drawCardFromDrawPile(0, this.drawPile.get(0));
                        drawCardFromDrawPile(0, this.drawPile.get(0));
                        drawCardFromDrawPile(0, this.drawPile.get(0));
                        drawCardFromDrawPile(0, this.drawPile.get(0));
                        this.playerTurn = 0;
                    }
                } else if (this.numPlayers == 2) {
                    if (playerId == 0) {
                        drawCardFromDrawPile(1, this.drawPile.get(0));
                        drawCardFromDrawPile(1, this.drawPile.get(0));
                        drawCardFromDrawPile(1, this.drawPile.get(0));
                        drawCardFromDrawPile(1, this.drawPile.get(0));
                        this.playerTurn = 1;
                    } else if (playerId == 1) {
                        drawCardFromDrawPile(0, this.drawPile.get(0));
                        drawCardFromDrawPile(0, this.drawPile.get(0));
                        drawCardFromDrawPile(0, this.drawPile.get(0));
                        drawCardFromDrawPile(0, this.drawPile.get(0));
                        this.playerTurn = 0;
                    }
                }
                //if its the wild card that adds 2
                else if (card.getCardNumber() == 2) {
                    if (this.numPlayers == 3) {
                        if (playerId == 0) {
                            drawCardFromDrawPile(1, this.drawPile.get(0));
                            drawCardFromDrawPile(1, this.drawPile.get(0));
                            this.playerTurn = 1;
                        } else if (playerId == 1) {
                            drawCardFromDrawPile(2, this.drawPile.get(0));
                            drawCardFromDrawPile(2, this.drawPile.get(0));
                            this.playerTurn = 2;
                        } else if (playerId == 2) {
                            drawCardFromDrawPile(0, this.drawPile.get(0));
                            drawCardFromDrawPile(0, this.drawPile.get(0));
                            this.playerTurn = 0;
                        }
                    } else if (this.numPlayers == 2) {
                        if (playerId == 0) {
                            drawCardFromDrawPile(1, this.drawPile.get(0));
                            drawCardFromDrawPile(1, this.drawPile.get(0));
                            this.playerTurn = 1;
                        } else if (playerId == 1) {
                            drawCardFromDrawPile(0, this.drawPile.get(0));
                            drawCardFromDrawPile(0, this.drawPile.get(0));
                            this.playerTurn = 0;
                        }
                    }
                }
            }
            //change the playable color
            this.currentPlayableColor = card.getCardColor();

            return true;

        } else if (card instanceof UnoCardPlus2) {
            if (card.getCardNumber() == 2) {
                if (this.numPlayers == 3) {
                    if (playerId == 0) {
                        drawCardFromDrawPile(1, this.drawPile.get(0));
                        drawCardFromDrawPile(1, this.drawPile.get(0));
                        this.playerTurn = 1;
                    } else if (playerId == 1) {
                        drawCardFromDrawPile(2, this.drawPile.get(0));
                        drawCardFromDrawPile(2, this.drawPile.get(0));
                        this.playerTurn = 2;
                    } else if (playerId == 2) {
                        drawCardFromDrawPile(0, this.drawPile.get(0));
                        drawCardFromDrawPile(0, this.drawPile.get(0));
                        this.playerTurn = 0;
                    }
                } else if (this.numPlayers == 2) {
                    if (playerId == 0) {
                        drawCardFromDrawPile(1, this.drawPile.get(0));
                        drawCardFromDrawPile(1, this.drawPile.get(0));
                        this.playerTurn = 1;
                    } else if (playerId == 1) {
                        drawCardFromDrawPile(0, this.drawPile.get(0));
                        drawCardFromDrawPile(0, this.drawPile.get(0));
                        this.playerTurn = 0;
                    }
                }
                return true;
            } else if (card instanceof UnoCardPlus4) {
                if (card.getCardNumber() == 4) {
                    if (this.numPlayers == 3) {
                        if (playerId == 0) {
                            drawCardFromDrawPile(1, this.drawPile.get(0));
                            drawCardFromDrawPile(1, this.drawPile.get(0));
                            drawCardFromDrawPile(1, this.drawPile.get(0));
                            drawCardFromDrawPile(1, this.drawPile.get(0));
                            this.playerTurn = 1;
                        } else if (playerId == 1) {
                            drawCardFromDrawPile(2, this.drawPile.get(0));
                            drawCardFromDrawPile(2, this.drawPile.get(0));
                            drawCardFromDrawPile(2, this.drawPile.get(0));
                            drawCardFromDrawPile(2, this.drawPile.get(0));
                            this.playerTurn = 2;
                        } else if (playerId == 2) {
                            drawCardFromDrawPile(0, this.drawPile.get(0));
                            drawCardFromDrawPile(0, this.drawPile.get(0));
                            drawCardFromDrawPile(0, this.drawPile.get(0));
                            drawCardFromDrawPile(0, this.drawPile.get(0));
                            this.playerTurn = 0;
                        }
                    } else if (this.numPlayers == 2) {
                        if (playerId == 0) {
                            drawCardFromDrawPile(1, this.drawPile.get(0));
                            drawCardFromDrawPile(1, this.drawPile.get(0));
                            drawCardFromDrawPile(1, this.drawPile.get(0));
                            drawCardFromDrawPile(1, this.drawPile.get(0));
                            this.playerTurn = 1;
                        } else if (playerId == 1) {
                            drawCardFromDrawPile(0, this.drawPile.get(0));
                            drawCardFromDrawPile(0, this.drawPile.get(0));
                            drawCardFromDrawPile(0, this.drawPile.get(0));
                            drawCardFromDrawPile(0, this.drawPile.get(0));
                            this.playerTurn = 0;
                        }
                        //change the playable color
                        this.currentPlayableColor = card.getCardColor();
                        return true;
                    }
                } else if (card instanceof UnoCardSkip) {
                    //needs an instance of the class, how do I get that?
                    if (this.numPlayers == 2) {
                        this.playerTurn = playerId;
                    } else if (this.numPlayers == 3) {
                        if (playerId == 0) {
                            this.playerTurn = 2;
                            return true;
                        } else if (playerId == 1) {
                            this.playerTurn = 0;
                            return true;
                        } else if (playerId == 2) {
                            this.playerTurn = 1;
                            return true;
                        }
                    }

                } else if (card instanceof UnoCardReverse) {
                    //for now just a boolean, not super sure how to implement
                    if (this.isReversed == true) {
                        this.isReversed = false;
                        return true;
                    } else if (this.isReversed == false) {
                        this.isReversed = true;
                        return true;
                    }

                } else {
                    if (this.currentPlayableColor != card.getCardColor()) {
                        this.currentPlayableColor = card.getCardColor();
                        return true;
                    } else if (this.currentPlayableNumber != card.getCardNumber()) {
                        this.currentPlayableNumber = card.getCardNumber();
                        return true;
                    }

                }
                //something went wrong so return false
                return false;
            }
        }

    }
        //wasn't a playable card
        return false;
}
}

