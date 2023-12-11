package com.example.unogame.action;

import com.example.game.GameFramework.actionMessage.GameAction;
import com.example.game.GameFramework.players.GamePlayer;

import java.util.ArrayList;

/*

Tags
@author - Isabella Horstmanshof
@author - Emma Giamello

 */

public class UnoAction extends GameAction {


    /**
     * constructor for GameAction
     *
     * @param player the player who created the action
     */
    public UnoAction(GamePlayer player) {
        super(player);
    }

    /**
     * checks if card being played is a valid move
     *
     * @param card - card being played
     * @return
     *      returns true if card can be played, false otherwise
     */
    public boolean isValid(ArrayList card) {
        return false;
    }

    /**
     * draws a card for a given player
     *
     * @param player - player drawing card
     * @return
     *      new hand with drawn card
     */
    public ArrayList drawCard(int player) {
        return null;
    }
}
