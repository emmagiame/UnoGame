package com.example.unogame.action;

import com.example.game.GameFramework.actionMessage.GameAction;
import com.example.game.GameFramework.players.GamePlayer;

import java.util.ArrayList;

/*

Tags
@author - Isabella Horstmanshof

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
        /*
        if (card == gameStateRef.) {

        }
        */
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
       /*
        if(isTurn(player) == true) {
                //   onClick() will remove card from draw array
                //    and add card to player array
                //    }
        }

            //   ArrayList playCard(UnoCard card, int player) {
            //   if(isTurn(player) == true) {


                    * onClick() on card will highlight card
                    * onClick() on discard
                    * isValid()
                    * if true {
                    * onClick() will remove card from player array and insert
                    into discard array
                */


        return null;
    }


    /*
    public boolean declareUno() {

    // onClick() will prevent AI from callOut()

    }


    public ArrayList callOut(){
        // onClick() will add 2 cards to AI array

    }
    */
}
