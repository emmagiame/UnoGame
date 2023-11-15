package com.example.unogame.players;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

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

public class UnoHumanPlayer extends GameHumanPlayer implements View.OnClickListener {

    //android activity that we are running
    private GameMainActivity myActivity;
    private EditText editText;

    private EditText editTextFun;

    //layout id of given layout
    private int layoutId;

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

        return myActivity.findViewById(R.id.runTestButtonLayout);
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
        if (info instanceof UnoGameState) {

            //similar to onClick below maybe goes there?
            UnoGameState firstInstance = (UnoGameState) info;
            //setCardView();
            //setPlayedCard();
            System.out.println(((UnoGameState) info).getPlayerTurn());
            editText.setText("");
        }
    }

    /**
     *  sends a message to the player
     *
     * @param info - game info
     */
    @Override
    public void sendInfo(GameInfo info) {

    }

    /**
     * finds button and edit text in given player view
     *
     * @param activity
     */
    @Override
    public void setAsGui(GameMainActivity activity) {
        myActivity = activity;
        myActivity.setContentView(layoutId);

        // all the onClicks for every button/ card on layout
        Button runTestButton = myActivity.findViewById(R.id.runTestButton);
        runTestButton.setOnClickListener(this);

        editText = myActivity.findViewById(R.id.editTextTextMultiLine);
        editTextFun = myActivity.findViewById(R.id.editTextFun);

        ImageView drawCardPileButton = myActivity.findViewById(R.id.drawCardPileImage);
        drawCardPileButton.setOnClickListener(this);

        ImageView discardCardPileButton = myActivity.findViewById(R.id.discardCardPileImage);
        discardCardPileButton.setOnClickListener(this);

        Button declareUnoButton = myActivity.findViewById(R.id.declareUnoButton);
        declareUnoButton.setOnClickListener(this);

        Button callOutUnoButton = myActivity.findViewById(R.id.callOutUnoButton);
        callOutUnoButton.setOnClickListener(this);

        Button drawButton = myActivity.findViewById(R.id.drawButton);
        drawButton.setOnClickListener(this);

        Button playButton = myActivity.findViewById(R.id.playButton);
        playButton.setOnClickListener(this);
    }

    /**
     *  sets card image based on passed in card
     *
     * @param cardImage - card View to change
     * @param card - card that we are setting the card image to
     */
    public void setImage(ImageView cardImage, UnoCard card) {
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
     * @param view
     */
    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.runTestButton){
            //clear text in edit text
            //editText.setText("");
            editText.getText().clear();

            //make new instance of game state
            UnoGameState firstInstance = new UnoGameState(2);

            //make deep copy from the perspective of player one (player 0 in the array)
            UnoGameState secondInstance = new UnoGameState(firstInstance);

            //call each method in game state class at least once, making a legal move and printing a
            //description of the action to multiLine EditText

            //draw a card to player 0
            firstInstance.drawCardFromDrawPile(0, firstInstance.getDrawPile().get(0));
            editText.append("Player 0 played the first card in their hand (card at index 0)\n");

            //player 0 plays a card
            firstInstance.playCard(0, firstInstance.getPlayer0Hand().get(0));
            editText.append("Player 0 drew a card from the draw pile \n");

            //new instance of game state
            UnoGameState thirdInstance = new UnoGameState(2);

            //new deep copy from the perspective of player one (player 0 in the array)
            UnoGameState fourthInstance = new UnoGameState(thirdInstance);

            //call to string and append to text in textview
            editText.append(secondInstance.toString());
            editText.append(fourthInstance.toString());
        }
        else if(view.getId() == R.id.drawButton){
            UnoDrawCardAction actionDraw = new UnoDrawCardAction(this);
            game.sendAction(actionDraw);
            String inputS = editTextFun.getText().toString();
            int input = Integer.parseInt(inputS);
            GameState gamie = game.getGameState();
        }
        else if(view.getId() == R.id.playButton){
            UnoPlayCardAction actionPlay = new UnoPlayCardAction(this);
            game.sendAction(actionPlay);
        }
        else{

        }

    }

}

