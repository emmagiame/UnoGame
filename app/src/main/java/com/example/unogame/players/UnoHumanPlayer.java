package com.example.unogame.players;

import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.game.GameFramework.GameMainActivity;
import com.example.game.GameFramework.infoMessage.GameInfo;
import com.example.game.GameFramework.players.GameHumanPlayer;
import com.example.unogame.R;
import com.example.unogame.UnoDrawCardAction;
import com.example.unogame.UnoPlayCardAction;
import com.example.unogame.cards.UnoCard;
import com.example.unogame.cards.UnoCardPlus2;
import com.example.unogame.cards.UnoCardPlus4;
import com.example.unogame.cards.UnoCardReverse;
import com.example.unogame.cards.UnoCardSkip;
import com.example.unogame.cards.UnoCardWild;
import com.example.unogame.info.UnoGameState;

import java.util.ArrayList;


/*

Tags
@author - Isabella Horstmanshof
@author - Emma Giamello
@author - Malissa Chen
@author - Kaitlyn Atalig

 */

public class UnoHumanPlayer extends GameHumanPlayer implements View.OnClickListener {

    //android activity that we are running
    private GameMainActivity myActivity;

    // text to display current number of cards in AI's hand
    private TextView opponentHandNum;

    // text to display current number of cards in human's hand
    private TextView ourHand;

    // text to display current player turn
    private TextView playerTurn;

    // text to display current playable color
    private TextView currentPlayableColorInfo;

    // text to display current playable number
    private TextView currentPlayableNumInfo;

    // buttons to pick the color
    private Button redPickButton;
    private Button yellowPickButton;
    private Button greenPickButton;
    private Button bluePickButton;

    //layout id of given layout
    private final int layoutId;
    private int offset = 0;

    //image view to display uno card
    private ArrayList<ImageView> cardSlotArray = null;
    private ImageView cardImageView1;
    private ImageView cardImageView2;
    private ImageView cardImageView3;
    private ImageView cardImageView4;
    private ImageView cardImageView5;
    private ImageView discardPileImage;
    private ImageView drawPileImage;
    private ImageView aiPlayerImage;
    private ImageView drawCardPileButton = null;
    private ImageView cardSlot1 = null;
    private ImageView cardSlot2 = null;
    private ImageView cardSlot3 = null;
    private ImageView cardSlot4 = null;
    private ImageView cardSlot5 = null;
    private Button leftButton = null;
    private Button rightButton = null;
    private Button playButton = null;
    private Button drawButton = null;
    private ImageView discardCardPileButton = null;
    private Button declareUnoButton = null;
    private Button callOutUnoButton = null;
    //private Button runTestButton = null;
    //private Button yellow = null;
    //private Button green = null;
    //private Button blue = null;
    //private Button red = null;
    private int cardClickedIdx = -1;


    /**
     * constructor
     *
     * @param name - name of player
     * @param id - given player view layout id
     */
    public UnoHumanPlayer(String name, int id) {
        super(name);
        layoutId = id;
    }

    /**
     * gets top view???
     *
     * @return
     *      returns top view
     */
    @Override
    public View getTopView() {

        return myActivity.findViewById(R.id.wholeGameLayout);
    }

    /**
     *  used for testing. i think???
     *
     * @return
     *      - returns the activity
     */
    @Override
    public GameMainActivity getActivity() {
        return null;
    }

    /**
     * receives the info for the current game state
     *
     * @param info - state of current game
     */
    @Override
    public void receiveInfo(GameInfo info) {
        Log.i("receiveInfo", "entered receiveInfo");
        if (info instanceof UnoGameState) {
            Log.i("receiveInfo", "info is an instance of UnoGameState");
            //make an instance of UnoGame based off of info
            UnoGameState currGame = (UnoGameState) info;
            Log.i("receiveInfo", "current color " + currGame.getCurrentPlayableColor() + " current number " + currGame.getCurrentPlayableNumber());
            //only works assuming human player is player0
            //displaying human player's hand in the card slots -
            ArrayList<UnoCard> cards = currGame.getPlayer0Hand();
            ArrayList<UnoCard> currentDiscard = currGame.getDiscardPile();

            //if the deck has less than 5 cards
            if(offset > 0 && cards.size() < 6) {
                offset = 0;
            }

            // finding all the id of the text box
            opponentHandNum = myActivity.findViewById(R.id.opponentHandNum);
            ourHand = myActivity.findViewById(R.id.ourHand);
            playerTurn = myActivity.findViewById(R.id.playerTurn);
            currentPlayableColorInfo = myActivity.findViewById(R.id.currentPlayableColorInfo);
            currentPlayableNumInfo = myActivity.findViewById(R.id.currentPlayableNumInfo);

            // sends message about how many cards opponent has
            int player1HandSize = currGame.getPlayer1Hand().size() - 1;
            opponentHandNum.setText("Opponent has " + player1HandSize + " cards.");
            opponentHandNum.setTextSize(30);

            // sends message about how many cards human player has
            int player0HandSize = currGame.getPlayer0Hand().size() - 1;
            ourHand.setText("You have " + player0HandSize + " cards.");
            ourHand.setTextSize(30);
            //ourHand.setTextColor(Color.BLACK);

            // sends message about who's turn it is
            int currPlayer = currGame.getPlayerTurn();
            playerTurn.setText("It's " + allPlayerNames[currPlayer] + "'s turn.");
            playerTurn.setTextSize(30);

            // sends message about current playable color
            currentPlayableColorInfo.setTextSize(30);
            // changes from only a character to the proper color name
            if(currGame.getCurrentPlayableColor() == 'y'){
                currentPlayableColorInfo.setText("The color is yellow.");
            }
            else if(currGame.getCurrentPlayableColor() == 'r'){
                currentPlayableColorInfo.setText("The color is red.");
            }
            else if(currGame.getCurrentPlayableColor() == 'g'){
                currentPlayableColorInfo.setText("The color is green.");
            }
            else if(currGame.getCurrentPlayableColor() == 'b'){
                currentPlayableColorInfo.setText("The color is blue.");
            }

            // sends message about current playable number
            if(currGame.getCurrentPlayableNumber() == -1){
                currentPlayableNumInfo.setText("The current card value is a special card.");
            }
            else{
                currentPlayableNumInfo.setText("The current card value is " + currGame.getCurrentPlayableNumber() + ".");
            }
            currentPlayableNumInfo.setTextSize(30);




            int cardIndex1 = offset;
            if (cardIndex1 < cards.size()) {
                setImage(this.cardImageView1, cards.get(cardIndex1));
            } else { //if cards are less than 5, display a greyed out card
                Log.e("CardImageView1", "Index out of bounds: " + cardIndex1);
                this.cardImageView1.setImageResource(R.drawable.blank);
            }

            //display cards in card slots 1-5
            displayCardInImageView(cards, offset, cardImageView1);
            displayCardInImageView(cards, offset + 1, cardImageView2);
            displayCardInImageView(cards, offset + 2, cardImageView3);
            displayCardInImageView(cards, offset + 3, cardImageView4);
            displayCardInImageView(cards, offset + 4, cardImageView5);

            //display discard pile image
            setImage(this.discardPileImage, currentDiscard.get(0));

            //display default images for ai player and draw pile
            this.aiPlayerImage.setImageResource(R.drawable.back);
            this.drawPileImage.setImageResource(R.drawable.back);
        }
    }

    //method to display a card in a cardImageView
    private void displayCardInImageView(ArrayList<UnoCard> cards, int index, ImageView imageView) {
        int cardIndex = offset + index;
        if (cardIndex < cards.size()) {
            setImage(imageView, cards.get(cardIndex));
        } else {
            Log.e("CardImageView", "Index out of bounds: " + cardIndex);
            imageView.setImageResource(R.drawable.blank);
        }
    }

    /**
     * finds button and edit text in given player view
     *
     * @param activity - idk
     */
    @Override
    public void setAsGui(GameMainActivity activity) {
        Log.i("setAsGui", "entered setAsGui");
        myActivity = activity;
        myActivity.setContentView(layoutId);

        // all the onClicks for every button/ card on layout
        //this.runTestButton = myActivity.findViewById(R.id.runTestButton);
        //runTestButton.setOnClickListener(this);

        //add card slots on playing field
        cardImageView1 = myActivity.findViewById(R.id.cardSlot1);
        cardImageView2 = myActivity.findViewById(R.id.cardSlot2);
        cardImageView3 = myActivity.findViewById(R.id.cardSlot3);
        cardImageView4 = myActivity.findViewById(R.id.cardSlot4);
        cardImageView5 = myActivity.findViewById(R.id.cardSlot5);

        discardPileImage = myActivity.findViewById(R.id.discardCardPileImage);
        drawPileImage = myActivity.findViewById(R.id.drawCardPileImage);
        aiPlayerImage = myActivity.findViewById(R.id.opponentHand);

        //broken assumes player 0 is human player !!!!!!!!!!!!!!

        cardSlotArray = new ArrayList<>();
        cardSlotArray.add((myActivity.findViewById(R.id.cardSlot1)));


        this.drawCardPileButton = myActivity.findViewById(R.id.drawCardPileImage);
        drawCardPileButton.setOnClickListener(this);

        this.discardCardPileButton = myActivity.findViewById(R.id.discardCardPileImage);
        discardCardPileButton.setOnClickListener(this);

        this.declareUnoButton = myActivity.findViewById(R.id.declareUnoButton);
        declareUnoButton.setOnClickListener(this);

        this.callOutUnoButton = myActivity.findViewById(R.id.callOutUnoButton);
        callOutUnoButton.setOnClickListener(this);

        this.drawButton = myActivity.findViewById(R.id.drawButton);
        drawButton.setOnClickListener(this);

        this.playButton = myActivity.findViewById(R.id.playButton);
        playButton.setOnClickListener(this);

        //onClickListeners for left and right scroll buttons
        this.leftButton = activity.findViewById(R.id.leftButton);
        leftButton.setOnClickListener(this);
        this.rightButton = activity.findViewById(R.id.rightButton);
        rightButton.setOnClickListener(this);

        //onClickListeners for all the card slots
        this.cardSlot1 = myActivity.findViewById(R.id.cardSlot1);
        cardSlot1.setOnClickListener(this);
        this.cardSlot2 = myActivity.findViewById(R.id.cardSlot2);
        cardSlot2.setOnClickListener(this);
        this.cardSlot3 = myActivity.findViewById(R.id.cardSlot3);
        cardSlot3.setOnClickListener(this);
        this.cardSlot4 = myActivity.findViewById(R.id.cardSlot4);
        cardSlot4.setOnClickListener(this);
        this.cardSlot5 = myActivity.findViewById(R.id.cardSlot5);
        cardSlot5.setOnClickListener(this);

        //onClickListeners for all color buttons (for wild cards)
        this.yellowPickButton = myActivity.findViewById(R.id.yellowPickButton);
        yellowPickButton.setOnClickListener(this);
        this.greenPickButton = myActivity.findViewById(R.id.greenPickButton);
        greenPickButton.setOnClickListener(this);
        this.redPickButton = myActivity.findViewById(R.id.redPickButton);
        redPickButton.setOnClickListener(this);
        this.bluePickButton = myActivity.findViewById(R.id.bluePickButton);
        bluePickButton.setOnClickListener(this);

    }

    /**
     *  sets card image based on passed in card
     *
     * @param cardImage - card View to change
     * @param card - card that we are setting the card image to
     */
    public void setImage(ImageView cardImage, UnoCard card) {
        Log.i("setImage", "entered setImage");
        if(card instanceof UnoCardSkip){
            if(card.getCardColor() == 'r') {
                cardImage.setImageResource(R.drawable.redskip);
            }
            else if(card.getCardColor() =='y'){
                cardImage.setImageResource(R.drawable.yellowskip);
            }
            else if(card.getCardColor() =='g'){
                cardImage.setImageResource(R.drawable.greenskip);
            }
            else if(card.getCardColor() =='b'){
                cardImage.setImageResource(R.drawable.blueskip);
            }
        }
        else if(card instanceof UnoCardPlus2){
            if(card.getCardColor() == 'r') {
                cardImage.setImageResource(R.drawable.reddraw2);
            }
            else if(card.getCardColor() =='y'){
                cardImage.setImageResource(R.drawable.yellowdraw2);
            }
            else if(card.getCardColor() =='g'){
                cardImage.setImageResource(R.drawable.greendraw2);
            }
            else if(card.getCardColor() =='b'){
                cardImage.setImageResource(R.drawable.bluedraw2);
            }
        }
        else if(card instanceof UnoCardWild){
            cardImage.setImageResource(R.drawable.wild);
        }
        else if(card instanceof UnoCardPlus4){
            cardImage.setImageResource(R.drawable.draw4);
        }
        else if(card instanceof UnoCardReverse){
            if(card.getCardColor() == 'r') {
                cardImage.setImageResource(R.drawable.redreverse);
            }
            else if(card.getCardColor() =='y'){
                cardImage.setImageResource(R.drawable.yellowreverse);
            }
            else if(card.getCardColor() =='g'){
                cardImage.setImageResource(R.drawable.greenreverse);
            }
            else if(card.getCardColor() =='b'){
                cardImage.setImageResource(R.drawable.bluereverse);
            }
        }
        else if(card instanceof UnoCard){
            if(card.getCardColor() == 'r') {
                if(card.getCardNumber() == 0){
                    cardImage.setImageResource(R.drawable.red0);
                }
                if(card.getCardNumber() == 1){
                    cardImage.setImageResource(R.drawable.red1);
                }
                if(card.getCardNumber() == 2){
                    cardImage.setImageResource(R.drawable.red2);
                }
                if(card.getCardNumber() == 3){
                    cardImage.setImageResource(R.drawable.red3);
                }
                if(card.getCardNumber() == 4){
                    cardImage.setImageResource(R.drawable.red4);
                }
                if(card.getCardNumber() == 5){
                    cardImage.setImageResource(R.drawable.red5);
                }
                if(card.getCardNumber() == 6){
                    cardImage.setImageResource(R.drawable.red6);
                }
                if(card.getCardNumber() == 7){
                    cardImage.setImageResource(R.drawable.red7);
                }
                if(card.getCardNumber() == 8){
                    cardImage.setImageResource(R.drawable.red8);
                }
                if(card.getCardNumber() == 9){
                    cardImage.setImageResource(R.drawable.red9);
                }

            }
            else if(card.getCardColor() =='y'){
                if(card.getCardNumber() == 0){
                    cardImage.setImageResource(R.drawable.yellow0);
                }
                if(card.getCardNumber() == 1){
                    cardImage.setImageResource(R.drawable.yellow1);
                }
                if(card.getCardNumber() == 2){
                    cardImage.setImageResource(R.drawable.yellow2);
                }
                if(card.getCardNumber() == 3){
                    cardImage.setImageResource(R.drawable.yellow3);
                }
                if(card.getCardNumber() == 4){
                    cardImage.setImageResource(R.drawable.yellow4);
                }
                if(card.getCardNumber() == 5){
                    cardImage.setImageResource(R.drawable.yellow5);
                }
                if(card.getCardNumber() == 6){
                    cardImage.setImageResource(R.drawable.yellow6);
                }
                if(card.getCardNumber() == 7){
                    cardImage.setImageResource(R.drawable.yellow7);
                }
                if(card.getCardNumber() == 8){
                    cardImage.setImageResource(R.drawable.yellow8);
                }
                if(card.getCardNumber() == 9){
                    cardImage.setImageResource(R.drawable.yellow9);
                }
            }
            else if(card.getCardColor() =='g'){
                if(card.getCardNumber() == 0){
                    cardImage.setImageResource(R.drawable.green0);
                }
                if(card.getCardNumber() == 1){
                    cardImage.setImageResource(R.drawable.green1);
                }
                if(card.getCardNumber() == 2){
                    cardImage.setImageResource(R.drawable.green2);
                }
                if(card.getCardNumber() == 3){
                    cardImage.setImageResource(R.drawable.green3);
                }
                if(card.getCardNumber() == 4){
                    cardImage.setImageResource(R.drawable.green4);
                }
                if(card.getCardNumber() == 5){
                    cardImage.setImageResource(R.drawable.green5);
                }
                if(card.getCardNumber() == 6){
                    cardImage.setImageResource(R.drawable.green6);
                }
                if(card.getCardNumber() == 7){
                    cardImage.setImageResource(R.drawable.green7);
                }
                if(card.getCardNumber() == 8){
                    cardImage.setImageResource(R.drawable.green8);
                }
                if(card.getCardNumber() == 9){
                    cardImage.setImageResource(R.drawable.green9);
                }
            }
            else if(card.getCardColor() =='b'){
                if(card.getCardNumber() == 0){
                    cardImage.setImageResource(R.drawable.blue0);
                }
                if(card.getCardNumber() == 1){
                    cardImage.setImageResource(R.drawable.blue1);
                }
                if(card.getCardNumber() == 2){
                    cardImage.setImageResource(R.drawable.blue2);
                }
                if(card.getCardNumber() == 3){
                    cardImage.setImageResource(R.drawable.blue3);
                }
                if(card.getCardNumber() == 4){
                    cardImage.setImageResource(R.drawable.blue4);
                }
                if(card.getCardNumber() == 5){
                    cardImage.setImageResource(R.drawable.blue5);
                }
                if(card.getCardNumber() == 6){
                    cardImage.setImageResource(R.drawable.blue6);
                }
                if(card.getCardNumber() == 7){
                    cardImage.setImageResource(R.drawable.blue7);
                }
                if(card.getCardNumber() == 8){
                    cardImage.setImageResource(R.drawable.blue8);
                }
                if(card.getCardNumber() == 9){
                    cardImage.setImageResource(R.drawable.blue9);
                }
            }
        }
        else{
          cardImage.setImageResource(R.drawable.blank);
        }
        cardImage.invalidate();
    }

    /**
     *
     */
    @Override
    protected void initAfterReady() {
        super.initAfterReady();
    }


    /**
     * onClick method
     *
     * @param view - game view
     */
    @Override
    public void onClick(View view) {
        Log.i("onClick", "entered onClick");

        if(view.getId() == R.id.drawButton){
            Log.i("action was clicked", "sending a draw card action");
            flash(Color.GREEN, 100);
            UnoDrawCardAction actionDraw = new UnoDrawCardAction(this);
            game.sendAction(actionDraw);
            return;
        }
        else if(view.getId() == R.id.cardSlot1){
            cardClickedIdx = offset;
            flash(Color.GREEN, 100);
            Log.i("changed card clicked id", "card clicked id is " + cardClickedIdx);
        }
        else if(view.getId() == R.id.cardSlot2){
            cardClickedIdx = 1 + offset;
            flash(Color.GREEN, 100);
            Log.i("changed card clicked id", "card clicked id is " + cardClickedIdx);
        }
        else if(view.getId() == R.id.cardSlot3){
            cardClickedIdx = 2 + offset;
            flash(Color.GREEN, 100);
            Log.i("changed card clicked id", "card clicked id is " + cardClickedIdx);
        }
        else if(view.getId() == R.id.cardSlot4){
            cardClickedIdx = 3 + offset;
            flash(Color.GREEN, 100);
            Log.i("changed card clicked id", "card clicked id is " + cardClickedIdx);
        }
        else if(view.getId() == R.id.cardSlot5){
            cardClickedIdx = 4 + offset;
            flash(Color.GREEN, 100);
            Log.i("changed card clicked id", "card clicked id is " + cardClickedIdx);
        }
        else if(view.getId() == R.id.playButton){
            if(cardClickedIdx == -1){
                //error
                Log.w("card clicked error", "card clicked id is -1");
                return;
            }
            Log.i("action was clicked", "sending a play card action, card clicked id is " + cardClickedIdx);
            UnoPlayCardAction actionPlay = new UnoPlayCardAction(this, cardClickedIdx);
            game.sendAction(actionPlay);
            flash(Color.GREEN, 100);
            return;
        }
        else if(view.getId() == R.id.callOutUnoButton){
            UnoGameState gameState = (UnoGameState) game.getGameState();
            gameState.callOut(gameState.getPlayerTurn());
        }
        else if(view.getId() == R.id.declareUnoButton){
            UnoGameState gameState = (UnoGameState) game.getGameState();
            gameState.declareUno(gameState.getPlayerTurn());
        }
        //scroll left and right buttons
        else if (view.getId() == R.id.leftButton) {
            if (offset <= 0) {
                flash(Color.RED,100);
            }
            else{
                offset--;
                receiveInfo(game.getGameState());
            }
        } else if (view.getId() == R.id.rightButton) {
            UnoGameState gameState = (UnoGameState) game.getGameState();
            ArrayList<UnoCard> hand = gameState.getPlayer0Hand();
            if (offset + 1 >= hand.size() - 5) {
                flash(Color.RED, 100);
            } else {
                offset++;
                receiveInfo(gameState);
            }
        }
        //color picker buttons for wild cards
        else if (view.getId() == R.id.yellowPickButton) {
            UnoGameState gameState = (UnoGameState) game.getGameState();
            gameState.setChangedPlayableColor('y');
            Log.i("yellow button clicked id", "color clicked id is yellow") ;
        }
        else if (view.getId() == R.id.greenPickButton) {
            UnoGameState gameState = (UnoGameState) game.getGameState();
            gameState.setChangedPlayableColor('g');
            Log.i("green button clicked id", "color clicked id is green") ;

        }
        else if (view.getId() == R.id.redPickButton) {
            UnoGameState gameState = (UnoGameState) game.getGameState();
            gameState.setChangedPlayableColor('r');
            Log.i("red button clicked id", "color clicked id is red") ;
        }

        else if (view.getId() == R.id.bluePickButton) {
            UnoGameState gameState = (UnoGameState) game.getGameState();
            gameState.setChangedPlayableColor('b');
            Log.i("blue button clicked id", "color clicked id is blue") ;
        }

        //flash red if button is invalid
        else {
            flash(Color.RED, 100);
        }
    }

}
