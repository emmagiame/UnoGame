package com.example.unogame.players;

import com.example.game.GameFramework.infoMessage.GameInfo;
import com.example.game.GameFramework.players.GameComputerPlayer;

public class UnoDumbAIPlayer extends GameComputerPlayer {
    /**
     * constructor
     *
     * @param name the player's name (e.g., "John")
     */
    public UnoDumbAIPlayer(String name) {
        super(name);
    }

    @Override
    protected void receiveInfo(GameInfo info) {
        //check if instance of UnoGameStat
        if(info instanceof UnoGameState){
            UnoGameState curGame = (UnoGameState) info;

            //if it is your turn
            if(curGame.getPlayerTurn() == playerNum){
                //if it has a playable card, play the first playable card it has
                if(playerNum == 0){
                    for(int i = 0; i > curGame.getPlayer0Hand().size(); i++){
                        if((curGame.getPlayer0Hand().get(i).cardNumber == curGame.getCurrentPlayableNumber()) || (curGame.getPlayer0Hand().get(i).cardColor == curGame.getCurrentPlayableColor())){
                            UnoPlayCardAction actionPlay = new UnoPlayCardAction(this);
                            sleep(1000);
                            game.sendAction(actionPlay);
                        }
                    }
                }
                else if(playerNum == 1){

                }
                else if(playerNum == 2){

                }
                //if it doesnt have a playable card, draw a card

                //if one card left, %50 chance to call uno, else do nothing - need to write a call uno function (in UnoGameState)
            }

        }
    }

}
