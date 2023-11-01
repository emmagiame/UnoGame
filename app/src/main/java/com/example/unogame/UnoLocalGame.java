package com.example.unogame;

import com.example.unogame.UnoGameState;

import java.util.ArrayList;

public class UnoLocalGame {

        UnoGameState gameStateRef;

        public UnoLocalGame(){
            gameStateRef = new UnoGameState();
        }

        boolean isTurn(int player) {
            if(player == gameStateRef.getCurrentSetupTurn()) {
                return true;
            }

            else {
                return false;
            }
        }

        /* boolean isValid(ArrayList card)
         * check if passed in card has at least one
         * element of card at begnning of discard arraylist
         */

        ArrayList drawCard(int player) {
            if(isTurn(player) == true) {
                //   * onClick() will remove card from draw array
                //    and add card to player array
                //    }
            }

         //   ArrayList playCard(UnoCard card, int player) {
             //   if(isTurn(player) == true) {

                /*
                    * onClick() on card will highlight card
                    * onClick() on discard
                    * isValid()
                    * if true {
                    * onClick() will remove card from player array and insert
                    into discard array
    }
            }
        }



    /* declareUno()

    * onClick() will prevent AI from callout()

     */


                    /* callout()

                     * onClick() will add 2 cards to AI array

                     */
                }
}
