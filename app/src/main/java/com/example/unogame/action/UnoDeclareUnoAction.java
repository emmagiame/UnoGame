package com.example.unogame.action;

import com.example.game.GameFramework.actionMessage.GameAction;
import com.example.game.GameFramework.players.GamePlayer;

public class UnoDeclareUnoAction extends GameAction {
    /**
     * constructor for GameAction
     *
     * @param player the player who created the action
     */
    public UnoDeclareUnoAction(GamePlayer player) {
        super(player);
    }
}
