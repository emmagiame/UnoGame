package com.example.unogame.players;

import android.util.Log;

import com.example.game.GameFramework.infoMessage.GameInfo;
import com.example.game.GameFramework.players.GameComputerPlayer;
import com.example.unogame.UnoDrawCardAction;
import com.example.unogame.UnoPlayCardAction;
import com.example.unogame.action.UnoDeclareUnoAction;
import com.example.unogame.info.UnoGameState;

import java.util.Random;

/*

Tags
@author - Isabella Horstmanshof

 */

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
        Log.i("dumb ai", "entered dumb ai receive info");
        //check if instance of UnoGameStat
        if (info instanceof UnoGameState) {
            UnoGameState curGame = (UnoGameState) info;
            Log.i("dumb ai", "entered instance of unogame state");

            //if it is your turn
            if (curGame.getPlayerTurn() == this.playerNum) {
                Log.i("dumb ai", "entered if playerNum if statement");
                //if it has a playable card and more than one card, play the first playable card it has
                //if one card left, %50 chance to call uno, else do nothing - need to write a call uno function (in UnoGameState)
                if (((this.playerNum == 0) && (curGame.getPlayer0Hand().size() == 1)) || ((this.playerNum == 1) && ((curGame.getPlayer1Hand().size() == 1))) || ((this.playerNum == 2) && (curGame.getPlayer2Hand().size() == 1))) {
                    // cant be done until call uno action is done
                    Log.i("dumb ai", "entered if hand size is 0");
                    Random rand = new Random();
                    int randomNum = rand.nextInt(5);
                    if (randomNum % 2 == 0) {
                        Log.i("dumb ai", "calling declareUno ");
                        UnoDeclareUnoAction declareUno = new UnoDeclareUnoAction(this);
                        sleep(1);
                        game.sendAction(declareUno);
                    } else {
                        Log.i("dumb ai", "not calling declare uno");
                        return;
                    }

                } else {
                    if (this.playerNum == 0) {
                        Log.i("dumb ai0", "player 0");
                        for (int i = 0; i < curGame.getPlayer0Hand().size(); i++) {
                            if ((curGame.getPlayer0Hand().get(i).getCardNumber() == curGame.getCurrentPlayableNumber()) || (curGame.getPlayer0Hand().get(i).getCardColor() == curGame.getCurrentPlayableColor())) {
                                Log.i("dumb ai0", "playing a card");
                                UnoPlayCardAction actionPlay = new UnoPlayCardAction(this, i);
                                sleep(1);
                                game.sendAction(actionPlay);
                                Log.i("dumb ai0", "sent a play card action for a " + curGame.getPlayer0Hand().get(i).getCardColor() + " " + curGame.getPlayer0Hand().get(i).getCardNumber());
                                return;

                            }
                        }
                        //if it doesnt have a playable card, draw a card
                        UnoDrawCardAction actionDraw = new UnoDrawCardAction(this);
                        sleep(1);
                        game.sendAction(actionDraw);
                        Log.i("dumb ai0", "sent a draw card action");
                        return;

                    } else if (this.playerNum == 1) {
                        Log.i("dumb ai1", "player 1");
                        for (int i = 0; i < curGame.getPlayer1Hand().size(); i++) {
                            if ((curGame.getPlayer1Hand().get(i).getCardNumber() == curGame.getCurrentPlayableNumber()) || (curGame.getPlayer1Hand().get(i).getCardColor() == curGame.getCurrentPlayableColor())) {
                                Log.i("dumb ai1", "playing a card");
                                UnoPlayCardAction actionPlay = new UnoPlayCardAction(this, i);
                                sleep(1);
                                game.sendAction(actionPlay);
                                Log.i("dumb ai1", "sent a play card action for a " + curGame.getPlayer1Hand().get(i).getCardColor() + " " + curGame.getPlayer1Hand().get(i).getCardNumber());
                                return;
                            }
                        }
                        //if it doesnt have a playable card, draw a card
                        UnoDrawCardAction actionDraw = new UnoDrawCardAction(this);
                        sleep(1);
                        game.sendAction(actionDraw);
                        Log.i("dumb ai1", "sent a draw card action");
                        return;
                    } else if (this.playerNum == 2) {
                        Log.i("dumb ai2", "player 2");
                        for (int i = 0; i < curGame.getPlayer2Hand().size(); i++) {
                            if ((curGame.getPlayer2Hand().get(i).getCardNumber() == curGame.getCurrentPlayableNumber()) || (curGame.getPlayer2Hand().get(i).getCardColor() == curGame.getCurrentPlayableColor())) {
                                Log.i("dumb ai2", "playing a card");
                                UnoPlayCardAction actionPlay = new UnoPlayCardAction(this, i);
                                sleep(1);
                                game.sendAction(actionPlay);
                                Log.i("dumb ai2", "sent a play card action for a " + curGame.getPlayer2Hand().get(i).getCardColor() + " " + curGame.getPlayer2Hand().get(i).getCardNumber());
                                return;
                            }
                        }
                        //if it doesnt have a playable card, draw a card
                        UnoDrawCardAction actionDraw = new UnoDrawCardAction(this);
                        sleep(1);
                        game.sendAction(actionDraw);
                        Log.i("dumb ai2", "sent a draw card action");
                        return;


                    }
                }

            }
        }
    }
}
