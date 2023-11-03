package com.example.unogame;

import com.example.unogame.UnoGameState;

import java.util.ArrayList;

public class UnoLocalGame {

        UnoGameState gameStateRef;

        public UnoLocalGame(){
            gameStateRef = new UnoGameState();
        }

        // check if it is the players turn
        boolean canMove(int player) {
            if(player == gameStateRef.getCurrentSetupTurn()) {
                return true;
            }

            else {
                return false;
            }
        }

         // check if passed in card has at least one
         // element of card at beginning of discard arraylist

}
