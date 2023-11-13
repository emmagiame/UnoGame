package com.example.unogame;

import com.example.game.GameFramework.LocalGame;
import com.example.game.GameFramework.actionMessage.GameAction;
import com.example.game.GameFramework.players.GamePlayer;
import com.example.unogame.info.UnoGameState;

public class UnoLocalGame extends LocalGame {
    //needs to be like ttt, the state will have a variable keeping track of the player whose turn it it and number of players
        //(x + 2) % 3 will skip a turn
        //state.setwhosemove() = line above this
    //


    /**
     * constructor
     */
    public UnoLocalGame(){
        super();
    }

    @Override
    public void start(GamePlayer[] players)
    {
        super.start(players);
        state = new UnoGameState(getNumPlayers());
    }


    /**
     * check if it is given players turn
     */
    protected boolean canMove(int player) {
        UnoGameState gameStateRef = (UnoGameState)state;
        if(player == gameStateRef.getCurrentSetupTurn()) {
            return true;
        }

        else {
            return false;
        }
    }

    /**
     * Makes move dependant on the action passed through
     *
     * @param action
     * 			The move that the player has sent to the game
     * @return
     *      true if action was taken or false if action was invalid/illegal
     */
    @Override
    protected boolean makeMove(GameAction action) {
        return false;
    }

    /**
     * send updated state to a given player
     *
     * @param p
     * 			the player to notify
     */
    @Override
    protected void sendUpdatedStateTo(GamePlayer p) {

    }

    /**
     * check if game is over
     *
     * @return
     *      a message that tells who has won the game,
     *      or null if the game is not over
     */
    @Override
    protected String checkIfGameOver() {
        return null;
    }

    // check if passed in card has at least one
         // element of card at beginning of discard arraylist

}
