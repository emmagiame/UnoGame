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


    /**
     * constructor
     *
     * @param name the name of the player
     */
    public UnoHumanPlayer(String name) {
        super(name);
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
     *
     *
     * @param activity
     */
    @Override
    public void setAsGui(GameMainActivity activity) {
        // setting view to run test Button
        Button runTest = myActivity.findViewById(R.id.runTest);
        myActivity.findViewById(R.id.editTextTextMultiLine);

        // OnClick for button
        runTest.setOnClickListener(this);
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
        UnoGameState firstInstance = new UnoGameState();

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
        UnoGameState thirdInstance = new UnoGameState();

        //new deep copy from the perspective of player one (player 0 in the array)
        UnoGameState fourthInstance = new UnoGameState(thirdInstance);

        //call to string and append to text in textview
        editText.append(secondInstance.toString());
        editText.append(fourthInstance.toString());


        }
    }

