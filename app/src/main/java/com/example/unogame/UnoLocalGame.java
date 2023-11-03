package com.example.unogame;

import com.example.unogame.UnoGameState;

import java.util.ArrayList;

public class UnoLocalGame {
    //needs to be like ttt, the state will have a variable keeping track of the player whose turn it it and number of players
        //(x + 2) % 3 will skip a turn
        //state.setwhosemove() = line above this
    //
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
