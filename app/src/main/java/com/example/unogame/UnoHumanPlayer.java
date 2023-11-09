package com.example.unogame;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.game.GameFramework.GameMainActivity;
import com.example.game.GameFramework.infoMessage.GameInfo;
import com.example.game.GameFramework.players.GameHumanPlayer;

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
     * receives the info for the current game state
     *
     * @param info - state of current game
     */
    @Override
    public void receiveInfo(GameInfo info) {

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

