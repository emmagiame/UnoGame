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




    /* on click method */
    public void onClick(View v){
      //clear text in edit text
      editText.setText("");

      //make new instance of game state
      UnoGameState firstInstance = new UnoGameState();

      //make deep copy from the perspective of player one (player 0 in the array)
      UnoGameState secondInstance = new UnoGameState(firstInstance);

      //call each method in game state class at least once, making a legal move and printing a
      //description of the action to multiLine EditText
        firstInstance.getPlayer0Hand();
        firstInstance.getPlayer1Hand();
        firstInstance.getDrawPile();
        firstInstance.getDiscardPile();

    }


    /**
     * constructor
     *
     * @param name the name of the player
     */
    public UnoHumanPlayer(String name) {
        super(name);
    }

    @Override
    public View getTopView() {

        return myActivity.findViewById(R.id.runTestButtonLayout);
    }

    @Override
    public void receiveInfo(GameInfo info) {

    }

    @Override
    public void setAsGui(GameMainActivity activity) {
        // setting view to run test Button
        Button runTest = myActivity.findViewById(R.id.runTest);
        myActivity.findViewById(R.id.editTextTextMultiLine);

        // OnClick for button
        runTest.setOnClickListener(this);
    }


}
