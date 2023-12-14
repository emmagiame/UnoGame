package com.example.unogame.action;

import com.example.game.GameFramework.actionMessage.GameAction;
import com.example.game.GameFramework.players.GamePlayer;

public class UnoWildCardColorChange extends GameAction
{
    private char changedColor;

    /**
     * constructor for GameAction
     *
     * @param player the player who created the action
     */
    public UnoWildCardColorChange(GamePlayer player, char newColor) {
        super(player);
        this.changedColor = newColor;
    }

    public char getChangedColor(){
        return this.changedColor;
    }
}
