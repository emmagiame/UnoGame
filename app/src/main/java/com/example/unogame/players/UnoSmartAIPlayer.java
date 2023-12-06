package com.example.unogame.players;

import android.util.Log;

import com.example.game.GameFramework.infoMessage.GameInfo;
import com.example.game.GameFramework.players.GameComputerPlayer;
import com.example.unogame.UnoDrawCardAction;
import com.example.unogame.UnoPlayCardAction;
import com.example.unogame.cards.UnoCardPlus2;
import com.example.unogame.cards.UnoCardPlus4;
import com.example.unogame.cards.UnoCardReverse;
import com.example.unogame.cards.UnoCardSkip;
import com.example.unogame.cards.UnoCardWild;
import com.example.unogame.info.UnoGameState;

/*

Tags
@author - Isabella Horstmanshof

 */

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
        if (info instanceof UnoGameState) {
            UnoGameState curGame = (UnoGameState) info;

            if (playerNum == 0) {
                //if you have a plus two or plus 4
                for (int i = 0; i < curGame.getPlayer0Hand().size(); i++) {
                    if (curGame.getPlayer0Hand().get(i) instanceof UnoCardPlus2 || curGame.getPlayer0Hand().get(i) instanceof UnoCardPlus4) {
                        Log.i("SmartAI0", "played a +2 or +4 card");
                        UnoPlayCardAction actionPlay = new UnoPlayCardAction(this, i);
                        sleep(3);
                        game.sendAction(actionPlay);
                        return;
                    }
                }

                //check if you're playing a wild card
                for (int i = 0; i < curGame.getPlayer0Hand().size(); i++) {
                    if (curGame.getPlayer0Hand().get(i) instanceof UnoCardWild) {
                        int red = 0;
                        int blue = 0;
                        int yellow = 0;
                        int green = 0;
                        //check how many of each color you have
                        for (int j = 0; j < curGame.getPlayer0Hand().size(); i++) {
                            if (curGame.getPlayer0Hand().get(j).getCardColor() == 'r') {
                                red = red + 1;
                            } else if (curGame.getPlayer0Hand().get(j).getCardColor() == 'b') {
                                blue = blue + 1;
                            } else if (curGame.getPlayer0Hand().get(j).getCardColor() == 'y') {
                                yellow = yellow + 1;
                            } else if (curGame.getPlayer0Hand().get(j).getCardColor() == 'g') {
                                green = green + 1;
                            }
                        }
                        //if you have more cards of one color than cards of the current playable color, then switch the color to the one you have the most of
                        if ((curGame.getCurrentPlayableColor() == 'r') && ((blue > red) || (yellow > red) || (green > red))) {
                            if (blue > red) {
                                //im not sure if this modifies the actual game
                                curGame.setChangedPlayableColor('b');
                            } else if (yellow > red) {
                                curGame.setChangedPlayableColor('y');
                            } else if (green > red) {
                                curGame.setChangedPlayableColor('g');
                            }
                        } else if ((curGame.getCurrentPlayableColor() == 'b') && ((red > blue) || (yellow > blue) || (green > blue))) {
                            if (red > blue) {
                                //im not sure if this modifies the actual game
                                curGame.setChangedPlayableColor('r');
                            } else if (yellow > blue) {
                                curGame.setChangedPlayableColor('y');
                            } else if (green > blue) {
                                curGame.setChangedPlayableColor('g');
                            }
                        } else if ((curGame.getCurrentPlayableColor() == 'y') && ((blue > yellow) || (red > yellow) || (green > yellow))) {
                            if (red > yellow) {
                                //im not sure if this modifies the actual game
                                curGame.setChangedPlayableColor('r');
                            } else if (blue > yellow) {
                                curGame.setChangedPlayableColor('b');
                            } else if (green > yellow) {
                                curGame.setChangedPlayableColor('g');
                            }
                        } else if ((curGame.getCurrentPlayableColor() == 'g') && ((blue > green) || (yellow > green) || (red > green))) {
                            if (red > green) {
                                //im not sure if this modifies the actual game
                                curGame.setChangedPlayableColor('r');
                            } else if (blue > green) {
                                curGame.setChangedPlayableColor('b');
                            } else if (yellow > green) {
                                curGame.setChangedPlayableColor('y');
                            }
                        }
                        //play the card
                        Log.i("SmartAI0", "played a wildcard and set new playable color to " + curGame.getCurrentPlayableColor());
                        UnoPlayCardAction actionPlay = new UnoPlayCardAction(this, i);
                        sleep(3);
                        game.sendAction(actionPlay);
                        return;
                    }
                }

                //check if you should play a skip card or reverse
                for (int i = 0; i < curGame.getPlayer0Hand().size(); i++) {
                    if (curGame.getPlayer0Hand().get(i) instanceof UnoCardSkip || curGame.getPlayer0Hand().get(i) instanceof UnoCardReverse) {
                        //if the next player has less cards than this
                        if (curGame.getPlayer0Hand().size() <= curGame.getPlayer1Hand().size()) {
                            Log.i("SmartAI0", "played a skip or reverse card");
                            UnoPlayCardAction actionPlay = new UnoPlayCardAction(this, i);
                            sleep(3);
                            game.sendAction(actionPlay);
                            return;
                        }
                    }
                }

                //if you have no special cards play first playable card
                for (int i = 0; i < curGame.getPlayer0Hand().size(); i++) {
                    if ((curGame.getPlayer0Hand().get(i).getCardNumber() == curGame.getCurrentPlayableNumber()) || (curGame.getPlayer0Hand().get(i).getCardColor() == curGame.getCurrentPlayableColor())) {
                        if ((!(curGame.getPlayer0Hand().get(i) instanceof UnoCardPlus2)) && (!(curGame.getPlayer0Hand().get(i) instanceof UnoCardPlus4)) && (!(curGame.getPlayer0Hand().get(i) instanceof UnoCardReverse)) && (!(curGame.getPlayer0Hand().get(i) instanceof UnoCardSkip)) && (!(curGame.getPlayer0Hand().get(i) instanceof UnoCardWild))) {
                            Log.i("SmartAI0", "played a normal card");
                            UnoPlayCardAction actionPlay = new UnoPlayCardAction(this, i);
                            sleep(3);
                            game.sendAction(actionPlay);
                            return;
                        }
                    }
                }

                //if it doesnt have a playable card, draw a card
                Log.i("SmartAI0", "drew a card");
                UnoDrawCardAction actionDraw = new UnoDrawCardAction(this);
                sleep(3);
                game.sendAction(actionDraw);
                return;

                //////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            } else if (playerNum == 1) {
                //if you have a plus two or plus 4
                for (int i = 0; i < curGame.getPlayer1Hand().size(); i++) {
                    if (curGame.getPlayer1Hand().get(i) instanceof UnoCardPlus2 || curGame.getPlayer1Hand().get(i) instanceof UnoCardPlus4) {
                        Log.i("SmartAI1", "played a +2 or +4 card");
                        UnoPlayCardAction actionPlay = new UnoPlayCardAction(this, i);
                        sleep(3);
                        game.sendAction(actionPlay);
                        return;
                    }
                }

                //check if you're playing a wild card
                for (int i = 0; i < curGame.getPlayer1Hand().size(); i++) {
                    if (curGame.getPlayer1Hand().get(i) instanceof UnoCardWild) {
                        int red = 0;
                        int blue = 0;
                        int yellow = 0;
                        int green = 0;
                        //check how many of each color you have
                        for (int j = 0; j < curGame.getPlayer1Hand().size(); i++) {
                            if (curGame.getPlayer1Hand().get(j).getCardColor() == 'r') {
                                red = red + 1;
                            } else if (curGame.getPlayer1Hand().get(j).getCardColor() == 'b') {
                                blue = blue + 1;
                            } else if (curGame.getPlayer1Hand().get(j).getCardColor() == 'y') {
                                yellow = yellow + 1;
                            } else if (curGame.getPlayer1Hand().get(j).getCardColor() == 'g') {
                                green = green + 1;
                            }
                        }
                        //if you have more cards of one color than cards of the current playable color, then switch the color to the one you have the most of
                        if ((curGame.getCurrentPlayableColor() == 'r') && ((blue > red) || (yellow > red) || (green > red))) {
                            if (blue > red) {
                                //im not sure if this modifies the actual game
                                curGame.setChangedPlayableColor('b');
                            } else if (yellow > red) {
                                curGame.setChangedPlayableColor('y');
                            } else if (green > red) {
                                curGame.setChangedPlayableColor('g');
                            }
                        } else if ((curGame.getCurrentPlayableColor() == 'b') && ((red > blue) || (yellow > blue) || (green > blue))) {
                            if (red > blue) {
                                //im not sure if this modifies the actual game
                                curGame.setChangedPlayableColor('r');
                            } else if (yellow > blue) {
                                curGame.setChangedPlayableColor('y');
                            } else if (green > blue) {
                                curGame.setChangedPlayableColor('g');
                            }
                        } else if ((curGame.getCurrentPlayableColor() == 'y') && ((blue > yellow) || (red > yellow) || (green > yellow))) {
                            if (red > yellow) {
                                //im not sure if this modifies the actual game
                                curGame.setChangedPlayableColor('r');
                            } else if (blue > yellow) {
                                curGame.setChangedPlayableColor('b');
                            } else if (green > yellow) {
                                curGame.setChangedPlayableColor('g');
                            }
                        } else if ((curGame.getCurrentPlayableColor() == 'g') && ((blue > green) || (yellow > green) || (red > green))) {
                            if (red > green) {
                                //im not sure if this modifies the actual game
                                curGame.setChangedPlayableColor('r');
                            } else if (blue > green) {
                                curGame.setChangedPlayableColor('b');
                            } else if (yellow > green) {
                                curGame.setChangedPlayableColor('y');
                            }
                        }
                        //play the card
                        Log.i("SmartAI1", "played a wildcard and set new playable color to " + curGame.getCurrentPlayableColor());
                        UnoPlayCardAction actionPlay = new UnoPlayCardAction(this, i);
                        sleep(3);
                        game.sendAction(actionPlay);
                        return;
                    }
                }

                //check if you should play a skip card or reverse
                for (int i = 0; i < curGame.getPlayer1Hand().size(); i++) {
                    if (curGame.getPlayer1Hand().get(i) instanceof UnoCardSkip || curGame.getPlayer1Hand().get(i) instanceof UnoCardReverse) {
                        //if the next player has less cards than this
                        if (curGame.getNumPlayers() == 2) {
                            if (curGame.getPlayer1Hand().size() <= curGame.getPlayer0Hand().size()) {
                                Log.i("SmartAI1", "played a skip or reverse card");
                                UnoPlayCardAction actionPlay = new UnoPlayCardAction(this, i);
                                sleep(3);
                                game.sendAction(actionPlay);
                                return;
                            }
                        }
                        if (curGame.getNumPlayers() == 3) {
                            if (curGame.getPlayer1Hand().size() <= curGame.getPlayer2Hand().size()) {
                                Log.i("SmartAI1", "played a skip or reverse card");
                                UnoPlayCardAction actionPlay = new UnoPlayCardAction(this, i);
                                sleep(3);
                                game.sendAction(actionPlay);
                                return;
                            }
                        }
                    }
                }

                //if you have no special cards play first playable card
                for (int i = 0; i < curGame.getPlayer1Hand().size(); i++) {
                    if ((curGame.getPlayer1Hand().get(i).getCardNumber() == curGame.getCurrentPlayableNumber()) || (curGame.getPlayer1Hand().get(i).getCardColor() == curGame.getCurrentPlayableColor())) {
                        if ((!(curGame.getPlayer1Hand().get(i) instanceof UnoCardPlus2)) && (!(curGame.getPlayer1Hand().get(i) instanceof UnoCardPlus4)) && (!(curGame.getPlayer1Hand().get(i) instanceof UnoCardReverse)) && (!(curGame.getPlayer1Hand().get(i) instanceof UnoCardSkip)) && (!(curGame.getPlayer1Hand().get(i) instanceof UnoCardWild))) {
                            Log.i("SmartAI1", "played a normal card");
                            UnoPlayCardAction actionPlay = new UnoPlayCardAction(this, i);
                            sleep(3);
                            game.sendAction(actionPlay);
                            return;
                        }
                    }
                }


                //if it doesnt have a playable card, draw a card
                Log.i("SmartAI1", "no playable cards so drew a card");
                UnoDrawCardAction actionDraw = new UnoDrawCardAction(this);
                sleep(3);
                game.sendAction(actionDraw);
                return;

                //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            } else if (playerNum == 2) {
                //if you have a plus two or plus 4
                for (int i = 0; i < curGame.getPlayer2Hand().size(); i++) {
                    if (curGame.getPlayer2Hand().get(i) instanceof UnoCardPlus2 || curGame.getPlayer2Hand().get(i) instanceof UnoCardPlus4) {
                        Log.i("SmartAI2", "played a +2 or +4 card");
                        UnoPlayCardAction actionPlay = new UnoPlayCardAction(this, i);
                        sleep(3);
                        game.sendAction(actionPlay);
                        return;
                    }
                }

                //check if you're playing a wild card
                for (int i = 0; i < curGame.getPlayer2Hand().size(); i++) {
                    if (curGame.getPlayer2Hand().get(i) instanceof UnoCardWild) {
                        int red = 0;
                        int blue = 0;
                        int yellow = 0;
                        int green = 0;
                        //check how many of each color you have
                        for(int j = 0; j < curGame.getPlayer2Hand().size(); i++){
                            if(curGame.getPlayer2Hand().get(j).getCardColor() == 'r'){
                                red = red + 1;
                            }
                            else if(curGame.getPlayer2Hand().get(j).getCardColor() == 'b'){
                                blue = blue + 1;
                            }
                            else if(curGame.getPlayer2Hand().get(j).getCardColor() == 'y'){
                                yellow = yellow + 1;
                            }
                            else if(curGame.getPlayer2Hand().get(j).getCardColor() == 'g'){
                                green = green + 1;
                            }
                        }
                        //if you have more cards of one color than cards of the current playable color, then switch the color to the one you have the most of
                        if((curGame.getCurrentPlayableColor() == 'r') && ((blue > red) || (yellow > red) || (green > red))){
                            if(blue > red){
                                //im not sure if this modifies the actual game
                                curGame.setChangedPlayableColor('b');
                            }
                            else if(yellow > red){
                                curGame.setChangedPlayableColor('y');
                            }
                            else if(green > red){
                                curGame.setChangedPlayableColor('g');
                            }
                        }
                        else if((curGame.getCurrentPlayableColor() == 'b') && ((red > blue) || (yellow > blue) || (green > blue))){
                            if(red > blue){
                                //im not sure if this modifies the actual game
                                curGame.setChangedPlayableColor('r');
                            }
                            else if(yellow > blue){
                                curGame.setChangedPlayableColor('y');
                            }
                            else if(green > blue){
                                curGame.setChangedPlayableColor('g');
                            }
                        }
                        else if((curGame.getCurrentPlayableColor() == 'y') && ((blue > yellow) || (red > yellow) || (green > yellow))){
                            if(red > yellow){
                                //im not sure if this modifies the actual game
                                curGame.setChangedPlayableColor('r');
                            }
                            else if(blue > yellow){
                                curGame.setChangedPlayableColor('b');
                            }
                            else if(green > yellow){
                                curGame.setChangedPlayableColor('g');
                            }
                        }
                        else if((curGame.getCurrentPlayableColor() == 'g') && ((blue > green) || (yellow > green) || (red > green))){
                            if(red > green){
                                //im not sure if this modifies the actual game
                                curGame.setChangedPlayableColor('r');
                            }
                            else if(blue > green){
                                curGame.setChangedPlayableColor('b');
                            }
                            else if(yellow > green){
                                curGame.setChangedPlayableColor('y');
                            }
                        }
                        //play the card
                        Log.i("SmartAI2", "played a wildcard and set new playable color to " + curGame.getCurrentPlayableColor());
                        UnoPlayCardAction actionPlay = new UnoPlayCardAction(this, i);
                        sleep(3);
                        game.sendAction(actionPlay);
                        return;
                    }
                }

                //check if you should play a skip card or reverse
                for (int i = 0; i < curGame.getPlayer2Hand().size(); i++) {
                    if (curGame.getPlayer2Hand().get(i) instanceof UnoCardSkip || curGame.getPlayer2Hand().get(i) instanceof UnoCardReverse) {
                        //if the next player has less cards than this oly with three players because player 2 is only used with three players
                        if (curGame.getNumPlayers() == 3) {
                            if (curGame.getPlayer2Hand().size() <= curGame.getPlayer0Hand().size()) {
                                Log.i("SmartAI2", "played a skip or reverse card");
                                UnoPlayCardAction actionPlay = new UnoPlayCardAction(this, i);
                                sleep(3);
                                game.sendAction(actionPlay);
                                return;
                            }
                        }
                    }
                }

                //if you have no special cards play first playable card
                for (int i = 0; i < curGame.getPlayer2Hand().size(); i++) {
                    if ((curGame.getPlayer2Hand().get(i).getCardNumber() == curGame.getCurrentPlayableNumber()) || (curGame.getPlayer2Hand().get(i).getCardColor() == curGame.getCurrentPlayableColor())) {
                        if((!(curGame.getPlayer2Hand().get(i) instanceof UnoCardPlus2)) && (!(curGame.getPlayer2Hand().get(i) instanceof UnoCardPlus4)) && (!(curGame.getPlayer2Hand().get(i) instanceof UnoCardReverse)) && (!(curGame.getPlayer2Hand().get(i) instanceof UnoCardSkip)) && (!(curGame.getPlayer2Hand().get(i) instanceof UnoCardWild))){
                            Log.i("SmartAI2", "played a normal card");
                            UnoPlayCardAction actionPlay = new UnoPlayCardAction(this, i);
                            sleep(3);
                            game.sendAction(actionPlay);
                            return;
                        }
                    }
                }

                //if it doesnt have a playable card, draw a card
                Log.i("SmartAI2", "drew a card");
                UnoDrawCardAction actionDraw = new UnoDrawCardAction(this);
                sleep(3);
                game.sendAction(actionDraw);
                return;


            }
        }
    }
}
