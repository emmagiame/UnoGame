package com.example.unogame.players;

import com.example.game.GameFramework.infoMessage.GameInfo;
import com.example.game.GameFramework.players.GameComputerPlayer;
import com.example.unogame.UnoDrawCardAction;
import com.example.unogame.UnoPlayCardAction;
import com.example.unogame.info.UnoGameState;

public class UnoSmartAIPlayer extends GameComputerPlayer {

    private int layoutId;

    /**
     * constructor
     *
     * @param name the player's name (e.g., "John")
     */
    public UnoSmartAIPlayer(String name, int id) {
            super(name);
            layoutId = id;
    }

    @Override
    protected void receiveInfo(GameInfo info) {
        if(info instanceof UnoGameState) {
            UnoGameState curGame = (UnoGameState) info;

            if (playerNum == 0) {
                for (int i = 0; i > curGame.getPlayer0Hand().size(); i++) {
                    //play first playable card
                    if ((curGame.getPlayer0Hand().get(i).getCardNumber() == curGame.getCurrentPlayableNumber()) || (curGame.getPlayer0Hand().get(i).getCardColor() == curGame.getCurrentPlayableColor())) {
                        UnoPlayCardAction actionPlay = new UnoPlayCardAction(this, 0);
                        sleep(1000);
                        game.sendAction(actionPlay);
                    }
                }
                //if it doesnt have a playable card, draw a card
                UnoDrawCardAction actionDraw = new UnoDrawCardAction(this);
                sleep(1000);
                game.sendAction(actionDraw);
            } else if (playerNum == 1) {
                for (int i = 0; i > curGame.getPlayer1Hand().size(); i++) {
                    if ((curGame.getPlayer1Hand().get(i).getCardNumber() == curGame.getCurrentPlayableNumber()) || (curGame.getPlayer1Hand().get(i).getCardColor() == curGame.getCurrentPlayableColor())) {
                        UnoPlayCardAction actionPlay = new UnoPlayCardAction(this, 0);
                        sleep(1000);
                        game.sendAction(actionPlay);
                    }
                }
                //if it doesnt have a playable card, draw a card
                UnoDrawCardAction actionDraw = new UnoDrawCardAction(this);
                sleep(1000);
                game.sendAction(actionDraw);
            } else if (playerNum == 2) {
                for (int i = 0; i > curGame.getPlayer2Hand().size(); i++) {
                    if ((curGame.getPlayer2Hand().get(i).getCardNumber() == curGame.getCurrentPlayableNumber()) || (curGame.getPlayer2Hand().get(i).getCardColor() == curGame.getCurrentPlayableColor())) {
                        UnoPlayCardAction actionPlay = new UnoPlayCardAction(this, 0);
                        sleep(1000);
                        game.sendAction(actionPlay);
                    }
                }
                //if it doesnt have a playable card, draw a card
                UnoDrawCardAction actionDraw = new UnoDrawCardAction(this);
                sleep(1000);
                game.sendAction(actionDraw);

            }
        }
    }
}
