package com.example.unogame;

import com.example.game.GameFramework.actionMessage.GameAction;
import com.example.game.GameFramework.players.GamePlayer;

/*

Tags
@author - Isabella Horstmanshof

 */

public class UnoDrawCardAction extends GameAction {
    /**
     * constructor for GameAction
     *
     * @param player the player who created the action
     */
    public UnoDrawCardAction(GamePlayer player) {
        super(player);
    }
}
