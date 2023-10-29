package com.example.unogame;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.game.GameFramework.GameMainActivity;
import com.example.game.GameFramework.infoMessage.GameInfo;
import com.example.game.GameFramework.players.GameHumanPlayer;

public class UnoHumanPlayer extends GameHumanPlayer {

    //android activity that we are running
    private GameMainActivity myActivity;


    // OnClick for button
    runTest.setOnClickListener(new View.OnClickListener(){
        @Override
        public void onClick(View viewy){

        }
    });


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
        //return myActivity.findViewById(R.id.top_layout);
        return null;
    }

    @Override
    public void receiveInfo(GameInfo info) {

    }

    @Override
    public void setAsGui(GameMainActivity activity) {

    }


}
