package com.example.unogame.action;

import com.example.game.GameFramework.actionMessage.GameAction;
import com.example.game.GameFramework.players.GamePlayer;

public class UnoCallOutAction extends GameAction {
    /**
     * constructor for GameAction
     *
     * @param player the player who created the action
     */
    public UnoCallOutAction(GamePlayer player) {
        super(player);
    }
}
