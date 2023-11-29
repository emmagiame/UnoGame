package com.example.unogame.players;

import com.example.game.GameFramework.infoMessage.GameInfo;
import com.example.game.GameFramework.players.GameComputerPlayer;
import com.example.unogame.UnoDrawCardAction;
import com.example.unogame.UnoPlayCardAction;
import com.example.unogame.action.UnoDeclareUnoAction;
import com.example.unogame.info.UnoGameState;

import java.util.Random;

public class UnoDumbAIPlayer extends GameComputerPlayer {
    //dory will only play first card in her hand right now

    //layout id of given layout
    private int layoutId;

    /**
     * constructor
     * This Ai's name is Dory
     *
     * @param name the player's name (e.g., "John")
     */
    public UnoDumbAIPlayer(String name, int id) {
        super(name);
        layoutId = id;
    }

    @Override
    protected void receiveInfo(GameInfo info) {
        //check if instance of UnoGameStat
        if(info instanceof UnoGameState){
            UnoGameState curGame = (UnoGameState) info;

            //if it is your turn
            if(curGame.getPlayerTurn() == playerNum) {
                //if it has a playable card and more than one card, play the first playable card it has
                //if one card left, %50 chance to call uno, else do nothing - need to write a call uno function (in UnoGameState)
                if (((playerNum == 0) && (curGame.getPlayer0Hand().size() == 1)) || ((playerNum == 1) && ((curGame.getPlayer1Hand().size() == 1))) || ((playerNum == 2) && (curGame.getPlayer2Hand().size() == 1))) {
                    // cant be done until call uno action is done
                    Random rand = new Random();
                    int randomNum = rand.nextInt(5);
                    if(randomNum % 2 == 0) {
                        UnoDeclareUnoAction declareUno = new UnoDeclareUnoAction(this);
                        sleep(1000);
                        game.sendAction(declareUno);
                    }
                    else{
                        return;
                    }

                } else {
                    if (playerNum == 0) {
                        for (int i = 0; i > curGame.getPlayer0Hand().size(); i++) {
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

        }
    }
