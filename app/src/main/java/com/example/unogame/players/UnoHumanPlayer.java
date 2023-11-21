package com.example.unogame.players;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
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

public class UnoHumanPlayer extends GameHumanPlayer implements View.OnClickListener {

    //android activity that we are running
    private GameMainActivity myActivity;
    private EditText editText;

    private TextView textViewFun;

    private UnoGameState firstInstance;
    //layout id of given layout
    private int layoutId;
    private int offset = 0;

    //image view to display uno card
    private ArrayList<ImageView> cardSlotArray = null;
    private ImageView cardImageView1;
    private ImageView cardImageView2;
    private ImageView cardImageView3;
    private ImageView cardImageView4;
    private ImageView cardImageView5;
    private ImageView cardImageView6;

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
    private Button runTestButton = null;

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
            //make an instance of UnoGame based off of info
            UnoGameState currGame = (UnoGameState) info;

            ArrayList<UnoCard> cards = currGame.getPlayer0Hand();
            if (cards.size() > 0) {
                setImage(this.cardImageView1, cards.get(offset+0));
                setImage(this.cardImageView2, cards.get(offset+1));
                setImage(this.cardImageView3, cards.get(offset+2));
                setImage(this.cardImageView4, cards.get(offset+3));
                setImage(this.cardImageView5, cards.get(offset+4));
            }
        }
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
        this.runTestButton = myActivity.findViewById(R.id.runTestButton);
        runTestButton.setOnClickListener(this);

        //add cardslots on playing field
        cardImageView1 = myActivity.findViewById(R.id.cardSlot1);
        cardImageView2 = myActivity.findViewById(R.id.cardSlot2);
        cardImageView3 = myActivity.findViewById(R.id.cardSlot3);
        cardImageView4 = myActivity.findViewById(R.id.cardSlot4);
        cardImageView5 = myActivity.findViewById(R.id.cardSlot5);

        cardImageView6 = myActivity.findViewById(R.id.discardCardPileImage);
        //broken assumes player 0 is human player

        cardSlotArray = new ArrayList<>();
        cardSlotArray.add((myActivity.findViewById(R.id.cardSlot1)));


//        editText = myActivity.findViewById(R.id.editTextTextMultiLine);
//        textViewFun = myActivity.findViewById(R.id.textViewFun);
//        textViewFun.setText("Your hand: ");

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
            return;
        }
        else if(view.getId() == R.id.drawButton){
            UnoDrawCardAction actionDraw = new UnoDrawCardAction(this);
            game.sendAction(actionDraw);
            view.invalidate();
            return;
        }
        else if(view.getId() == R.id.playButton){
            UnoPlayCardAction actionPlay = new UnoPlayCardAction(this);
            game.sendAction(actionPlay);
            //String inputS = editTextFun.getText().toString();
            //int input = Integer.parseInt(inputS);
            UnoGameState gamie = (UnoGameState) game.getGameState();
            gamie.setIndexOfPlayedCard(0);
            view.invalidate();
            return;
        }
        else{

        }

    }

}

