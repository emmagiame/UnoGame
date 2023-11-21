package com.example.unogame;

import com.example.game.GameFramework.actionMessage.GameAction;
import com.example.game.GameFramework.players.GamePlayer;

public class UnoPlayCardAction extends GameAction {
    private int playerCardIdx;
    /**
     * constructor for GameAction
     *
     * @param player the player who created the action
     */
    public UnoPlayCardAction(GamePlayer player, int playerCardIdxIn) {
        super(player);
        playerCardIdx = playerCardIdxIn;
    }

    public int getPlayedCardIdx(){
        return this.playerCardIdx;
    }
}
