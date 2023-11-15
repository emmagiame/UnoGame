package com.example.unogame;

import com.example.game.GameFramework.LocalGame;
import com.example.game.GameFramework.actionMessage.GameAction;
import com.example.game.GameFramework.players.GamePlayer;
import com.example.unogame.info.UnoGameState;
import com.example.unogame.players.UnoHumanPlayer;

public class UnoLocalGame extends LocalGame {

    UnoGameState unoGameStateRef;
    //needs to be like ttt, the state will have a variable keeping track of the player whose turn it it and number of players
    //(x + 2) % 3 will skip a turn
    //state.setwhosemove() = line above this
    //

    private UnoGameState refOfficialGame;

    /**
     * constructor
     */
    public UnoLocalGame() { //this wont work past alpha
        refOfficialGame = new UnoGameState(2);
    }

    @Override
    public void start(GamePlayer[] players) {
        super.start(players);
        state = new UnoGameState(getNumPlayers());
    }

    @Override
    protected void sendUpdatedStateTo(GamePlayer p) {

    }


    /**
     * check if it is given players turn
     */
    protected boolean canMove(int player) {
        UnoGameState gameStateRef = (UnoGameState) state;
        if (player == gameStateRef.getCurrentSetupTurn()) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    protected String checkIfGameOver() {
        return null;
    }

    /**
     * Makes move dependant on the action passed through
     *
     * @param action The move that the player has sent to the game
     * @return true if action was taken or false if action was invalid/illegal
     */
    @Override
    protected boolean makeMove(GameAction action) {

        // if action is play card
        if (action instanceof UnoPlayCardAction) {
            // check if it's that players turn
            if (canMove(getPlayerIdx(action.getPlayer())) == true) {
                // get num players
                int numPlayers = getNumPlayers();

                // get player id
                int id = unoGameStateRef.getPlayerTurn();
                // play a card
                // change the current player to the next player
                // if two players
                if (numPlayers == 2) {
                    // if id 0 turn
                    if (id == 0) {
                        //get index that card is at
                        unoGameStateRef.playCard(id, unoGameStateRef.getPlayer0Hand().get(unoGameStateRef.getIndexOfPlayedCard()));
                        unoGameStateRef.setPlayerTurn(1);
                        return true;
                    }
                    // if id 1 turn
                    else if (id == 1) {
                        unoGameStateRef.playCard(id, unoGameStateRef.getPlayer1Hand().get(unoGameStateRef.getIndexOfPlayedCard()));
                        unoGameStateRef.setPlayerTurn(0);
                        return true;
                    }

                }

                // if three players
                if (numPlayers == 3) {
                    // if id 0 turn
                    if (id == 0) {
                        unoGameStateRef.playCard(id, unoGameStateRef.getPlayer0Hand().get(unoGameStateRef.getIndexOfPlayedCard()));
                        unoGameStateRef.setPlayerTurn(1);
                        return true;
                    }
                    // if id 1 turn
                    else if (id == 1) {
                        unoGameStateRef.playCard(id, unoGameStateRef.getPlayer1Hand().get(unoGameStateRef.getIndexOfPlayedCard()));
                        unoGameStateRef.setPlayerTurn(2);
                        return true;
                    }

                    // if id 2 turn
                    else if (id == 2) {
                        unoGameStateRef.playCard(id, unoGameStateRef.getPlayer2Hand().get(unoGameStateRef.getIndexOfPlayedCard()));
                        unoGameStateRef.setPlayerTurn(0);
                        return true;
                    }

                }
            }
        }

        // if action is draw card
        if (action instanceof UnoDrawCardAction) {
            // check if it's that players turn
            if (canMove(getPlayerIdx(action.getPlayer())) == true) {
                // get num players
                int numPlayers = getNumPlayers();

                // get player id
                int id = unoGameStateRef.getPlayerTurn();
                // draw card
                // unoGameStateRef.drawCardFromDrawPile(id,card);

                // change the current player to the next player

                // if two players
                if (numPlayers == 2) {
                    // if id 0 turn
                    if (id == 0) {
                        unoGameStateRef.drawCardFromDrawPile(id, unoGameStateRef.getDrawPile().get(0));
                        unoGameStateRef.setPlayerTurn(1);
                        return true;
                    }
                    // if id 1 turn
                    else if (id == 1) {
                        unoGameStateRef.drawCardFromDrawPile(id, unoGameStateRef.getDrawPile().get(0));
                        unoGameStateRef.setPlayerTurn(0);
                        return true;
                    }

                }

                // if three players
                if (numPlayers == 3) {
                    // if id 0 turn
                    if (id == 0) {
                        unoGameStateRef.drawCardFromDrawPile(id, unoGameStateRef.getDrawPile().get(0));
                        unoGameStateRef.setPlayerTurn(1);
                        return true;
                    }
                    // if id 1 turn
                    else if (id == 1) {
                        unoGameStateRef.drawCardFromDrawPile(id, unoGameStateRef.getDrawPile().get(0));
                        unoGameStateRef.setPlayerTurn(2);
                        return true;
                    }

                    // if id 2 turn
                    else if (id == 2) {
                        unoGameStateRef.drawCardFromDrawPile(id, unoGameStateRef.getDrawPile().get(0));
                        unoGameStateRef.setPlayerTurn(0);
                        return true;
                    }

                }
            }
        }
            //no unos for alpha <3
            return false;
    }


        // check if passed in card has at least one
        // element of card at beginning of discard arraylist



}
