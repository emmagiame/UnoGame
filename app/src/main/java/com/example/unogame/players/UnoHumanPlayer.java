package com.example.unogame.players;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.game.GameFramework.GameMainActivity;
import com.example.game.GameFramework.infoMessage.GameInfo;
import com.example.game.GameFramework.players.GameHumanPlayer;
import com.example.unogame.R;
import com.example.unogame.cards.UnoCard;
import com.example.unogame.cards.UnoCardSkip;
import com.example.unogame.info.UnoGameState;

import java.util.logging.Handler;

public class UnoHumanPlayer extends GameHumanPlayer implements View.OnClickListener {

    //android activity that we are running
    private GameMainActivity myActivity;
    private EditText editText;

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
            firstInstance = (UnoGameState) info;
            setCardView();
            setPlayedCard();
            System.out.println(((UnoGameState) info).getPlayerTurn());
            editText.setText("");
            for(int k = 0; k < firstInstance.getPlayerNum();k++){
                String playerText = "Player " + k + " hand size: " + firstInstance.getHandArray().get(k).size();
                editText.append(playerText + "\n");
            }

            /* we don't want this because we want the uno button visible at all times
            if (firstInstance.getHandArray().get(playerNum).size() <= 2){
                unoButton.setVisibility(View.VISIBLE);
            }
            else{
                unoButton.setVisibility(View.INVISIBLE);
            }
             */
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
     *  whether this player requires a GUI
     *
     * @return
     *      - true or false
     */
    @Override
    public boolean requiresGui() {
        return false;
    }

    /**
     *  whether this player supports a GUI
     *
     * @return
     *      - true or false
     */
    @Override
    public boolean supportsGui() {
        return false;
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

        // setting view to run test Button
        Button runTestButton = myActivity.findViewById(R.id.runTestButton);
        editText = myActivity.findViewById(R.id.editTextTextMultiLine);


        // OnClick for button
        runTestButton.setOnClickListener(this);

    }

    /**
     *  sets the card image to match a specific card
     *
     * @param cardSlot
     * @param card -
     * @param color - color of the card
     * @param num - number of the card
     * @param ability -
     */
    public void setImage(ImageButton cardSlot, UnoCard card, char color, int num, int ability) {
        if (color == card.getCardColor() && ability == -1) {
            switch (num) {
                case 0:
                    cardSlot.setImageResource(R.drawable.red0);
                    break;
                case 1:
                    cardSlot.setImageResource(R.drawable.red1);
                    break;
                case 2:
                    cardSlot.setImageResource(R.drawable.red2);
                    break;
                case 3:
                    cardSlot.setImageResource(R.drawable.red3);
                    break;
                case 4:
                    cardSlot.setImageResource(R.drawable.red4);
                    break;
                case 5:
                    cardSlot.setImageResource(R.drawable.red5);
                    break;
                case 6:
                    cardSlot.setImageResource(R.drawable.red6);
                    break;
                case 7:
                    cardSlot.setImageResource(R.drawable.red7);
                    break;
                case 8:
                    cardSlot.setImageResource(R.drawable.red8);
                    break;
                case 9:
                    cardSlot.setImageResource(R.drawable.red9);
                    break;
            }
        } else if (color == card.getCardColor()) {
            switch (ability) {
                case card.UnoCardSkip:
                    cardSlot.setImageResource(R.drawable.redskip);
                    break;
                case UnoSpecialCard.DRAWTWO:
                    cardSlot.setImageResource(R.drawable.reddraw2);
                    break;
                case UnoSpecialCard.REVERSE:
                    cardSlot.setImageResource(R.drawable.redreverse);
                    break;
            }
        }

        // wouldnt need all these because getCardColor() gets the color so we dont need to check for every color
        /*
        else if (color == card.getCardColor() && ability == -1) {
            switch (num) {
                case 0:
                    cardSlot.setImageResource(R.drawable.green0);
                    break;
                case 1:
                    cardSlot.setImageResource(R.drawable.green1);
                    break;
                case 2:
                    cardSlot.setImageResource(R.drawable.green2);
                    break;
                case 3:
                    cardSlot.setImageResource(R.drawable.green3);
                    break;
                case 4:
                    cardSlot.setImageResource(R.drawable.green4);
                    break;
                case 5:
                    cardSlot.setImageResource(R.drawable.green5);
                    break;
                case 6:
                    cardSlot.setImageResource(R.drawable.green6);
                    break;
                case 7:
                    cardSlot.setImageResource(R.drawable.green7);
                    break;
                case 8:
                    cardSlot.setImageResource(R.drawable.green8);
                    break;
                case 9:
                    cardSlot.setImageResource(R.drawable.green9);
                    break;
            }
        } else if (color == card.getCardColor()) {
            switch (ability) {
                case UnoSpecialCard.SKIP:
                    cardSlot.setImageResource(R.drawable.greenskip);
                    break;
                case UnoSpecialCard.DRAWTWO:
                    cardSlot.setImageResource(R.drawable.greendraw2);
                    break;
                case UnoSpecialCard.REVERSE:
                    cardSlot.setImageResource(R.drawable.greenreverse);
                    break;
            }
        } else if (color == card.getCardColor() && ability == -1) {
            switch (num) {
                case 0:
                    cardSlot.setImageResource(R.drawable.blue0);
                    break;
                case 1:
                    cardSlot.setImageResource(R.drawable.blue1);
                    break;
                case 2:
                    cardSlot.setImageResource(R.drawable.blue2);
                    break;
                case 3:
                    cardSlot.setImageResource(R.drawable.blue3);
                    break;
                case 4:
                    cardSlot.setImageResource(R.drawable.blue4);
                    break;
                case 5:
                    cardSlot.setImageResource(R.drawable.blue5);
                    break;
                case 6:
                    cardSlot.setImageResource(R.drawable.blue6);
                    break;
                case 7:
                    cardSlot.setImageResource(R.drawable.blue7);
                    break;
                case 8:
                    cardSlot.setImageResource(R.drawable.blue8);
                    break;
                case 9:
                    cardSlot.setImageResource(R.drawable.blue9);
                    break;
            }
        } else if (color == card.getCardColor()) {
            switch (ability) {
                case UnoSpecialCard.SKIP:
                    cardSlot.setImageResource(R.drawable.blueskip);
                    break;
                case UnoSpecialCard.DRAWTWO:
                    cardSlot.setImageResource(R.drawable.bluedraw2);
                    break;
                case UnoSpecialCard.REVERSE:
                    cardSlot.setImageResource(R.drawable.bluereverse);
                    break;
            }
        } else if (color == UnoCard.YELLOW && ability == -1) {
            switch (num) {
                case 0:
                    cardSlot.setImageResource(R.drawable.yellow0);
                    break;
                case 1:
                    cardSlot.setImageResource(R.drawable.yellow1);
                    break;
                case 2:
                    cardSlot.setImageResource(R.drawable.yellow2);
                    break;
                case 3:
                    cardSlot.setImageResource(R.drawable.yellow3);
                    break;
                case 4:
                    cardSlot.setImageResource(R.drawable.yellow4);
                    break;
                case 5:
                    cardSlot.setImageResource(R.drawable.yellow5);
                    break;
                case 6:
                    cardSlot.setImageResource(R.drawable.yellow6);
                    break;
                case 7:
                    cardSlot.setImageResource(R.drawable.yellow7);
                    break;
                case 8:
                    cardSlot.setImageResource(R.drawable.yellow8);
                    break;
                case 9:
                    cardSlot.setImageResource(R.drawable.yellow9);
                    break;
            }
        } else if (color == UnoCard.YELLOW) {
            switch (ability) {
                case UnoSpecialCard.SKIP:
                    cardSlot.setImageResource(R.drawable.yellowskip);
                    break;
                case UnoSpecialCard.DRAWTWO:
                    cardSlot.setImageResource(R.drawable.yellowdraw2);
                    break;
                case UnoSpecialCard.REVERSE:
                    cardSlot.setImageResource(R.drawable.yellowreverse);
                    break;
            }
        } else if (color == UnoCard.COLORLESS) {
            switch (ability) {
                case UnoSpecialCard.WILD:
                    cardSlot.setImageResource(R.drawable.wild);
                    break;
                case UnoSpecialCard.DRAWFOUR:
                    cardSlot.setImageResource(R.drawable.draw4);
                    break;
            }
        }
        else{
            cardSlot.setImageResource(R.drawable.backgrey);
        }
         */
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
        //clear text in edit text
        editText.setText("");

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
    }

