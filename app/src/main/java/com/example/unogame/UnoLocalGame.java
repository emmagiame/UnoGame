package com.example.unogame;

import com.example.game.GameFramework.LocalGame;
import com.example.game.GameFramework.actionMessage.GameAction;
import com.example.game.GameFramework.players.GamePlayer;
import com.example.unogame.info.UnoGameState;
import com.example.unogame.players.UnoHumanPlayer;

public class UnoLocalGame extends LocalGame {

    //needs to be like ttt, the state will have a variable keeping track of the player whose turn it it and number of players
    //(x + 2) % 3 will skip a turn
    //state.setwhosemove() = line above this

    private UnoGameState unoGameStateRef;
    //private UnoGameState refOfficialGame;

    /**
     * constructor
     */
    public UnoLocalGame() { //not sure if the casting works, replaced refOfficialGame with unoGameStateRef, not sure why we had 2
        unoGameStateRef = (UnoGameState)super.state;
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
    protected boolean canMove(int player) {//may need ((UnoGameState)super.state) instead
        UnoGameState gameStateRef = (UnoGameState) state;
        if (player == gameStateRef.getCurrentSetupTurn()) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    protected String checkIfGameOver() {

        if(unoGameStateRef.gameOver() == 0)  {
            return "Player 0 won!";
        }

       else if(unoGameStateRef.gameOver() == 1)  {
            return "Player 1 won!";
        }

        else if(unoGameStateRef.gameOver() == 2)  {
            return "Player 2 won!";
        }

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
                //
                int numPlayers = ((UnoGameState)super.state).getNumPlayers();

                // get player id
                int id = ((UnoGameState)super.state).getPlayerTurn();
                // play a card
                // change the current player to the next player
                // if two players
                if (numPlayers == 2) {
                    // if id 0 turn
                    if (id == 0) {
                        //get index that card is at
                        ((UnoGameState)super.state).playCard(id, ((UnoGameState)super.state).getPlayer0Hand().get(((UnoGameState)super.state).getIndexOfPlayedCard()));
                        ((UnoGameState)super.state).setPlayerTurn(1);
                        return true;
                    }
                    // if id 1 turn
                    else if (id == 1) {
                        ((UnoGameState)super.state).playCard(id, ((UnoGameState)super.state).getPlayer1Hand().get(((UnoGameState)super.state).getIndexOfPlayedCard()));
                        ((UnoGameState)super.state).setPlayerTurn(0);
                        return true;
                    }

                }

                // if three players
                if (numPlayers == 3) {
                    // if id 0 turn
                    if (id == 0) {
                        ((UnoGameState)super.state).playCard(id, ((UnoGameState)super.state).getPlayer0Hand().get(((UnoGameState)super.state).getIndexOfPlayedCard()));
                        ((UnoGameState)super.state).setPlayerTurn(1);
                        return true;
                    }
                    // if id 1 turn
                    else if (id == 1) {
                        ((UnoGameState)super.state).playCard(id, ((UnoGameState)super.state).getPlayer1Hand().get(((UnoGameState)super.state).getIndexOfPlayedCard()));
                        ((UnoGameState)super.state).setPlayerTurn(2);
                        return true;
                    }

                    // if id 2 turn
                    else if (id == 2) {
                        ((UnoGameState)super.state).playCard(id, ((UnoGameState)super.state).getPlayer2Hand().get(((UnoGameState)super.state).getIndexOfPlayedCard()));
                        ((UnoGameState)super.state).setPlayerTurn(0);
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
                int id = ((UnoGameState)super.state).getPlayerTurn();
                // draw card
                // unoGameStateRef.drawCardFromDrawPile(id,card);

                // change the current player to the next player

                // if two players
                if (numPlayers == 2) {
                    // if id 0 turn
                    if (id == 0) {
                        ((UnoGameState)super.state).drawCardFromDrawPile(id, ((UnoGameState)super.state).getDrawPile().get(0));
                        ((UnoGameState)super.state).setPlayerTurn(1);
                        return true;
                    }
                    // if id 1 turn
                    else if (id == 1) {
                        ((UnoGameState)super.state).drawCardFromDrawPile(id, ((UnoGameState)super.state).getDrawPile().get(0));
                        ((UnoGameState)super.state).setPlayerTurn(0);
                        return true;
                    }

                }

                // if three players
                if (numPlayers == 3) {
                    // if id 0 turn
                    if (id == 0) {
                        ((UnoGameState)super.state).drawCardFromDrawPile(id, ((UnoGameState)super.state).getDrawPile().get(0));
                        ((UnoGameState)super.state).setPlayerTurn(1);
                        return true;
                    }
                    // if id 1 turn
                    else if (id == 1) {
                        ((UnoGameState)super.state).drawCardFromDrawPile(id, ((UnoGameState)super.state).getDrawPile().get(0));
                        ((UnoGameState)super.state).setPlayerTurn(2);
                        return true;
                    }

                    // if id 2 turn
                    else if (id == 2) {
                        ((UnoGameState)super.state).drawCardFromDrawPile(id, ((UnoGameState)super.state).getDrawPile().get(0));
                        ((UnoGameState)super.state).setPlayerTurn(0);
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
