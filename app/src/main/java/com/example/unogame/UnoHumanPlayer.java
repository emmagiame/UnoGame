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
        // clear text in edit text
      editText.setText("");
      UnoGameState firstInstance;
      UnoGameState secondInstance;
      // copy of game;
      firstInstance = new UnoGameState();
      // copy of copy from perspective of player 1 not sure if using playerNum is right
      secondInstance = new UnoGameState(firstInstance, playerNum);
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
