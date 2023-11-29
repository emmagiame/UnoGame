package com.example.unogame;

import com.example.game.GameFramework.GameMainActivity;
import com.example.game.GameFramework.LocalGame;
import com.example.game.GameFramework.gameConfiguration.GameConfig;
import com.example.game.GameFramework.gameConfiguration.GamePlayerType;
import com.example.game.GameFramework.infoMessage.GameState;
import com.example.game.GameFramework.players.GamePlayer;
import com.example.unogame.players.UnoDumbAIPlayer;
import com.example.unogame.players.UnoHumanPlayer;
import com.example.unogame.players.UnoSmartAIPlayer;

import java.util.ArrayList;

public class MainActivity extends GameMainActivity {
    //port number to use when playing game over network
    //used same num as lab idk if its right
    private static final int PORT_NUMBER = 2278;

    /**
     create default config for Uno
     -1 Human player vs 1 Computer player
     -minimum players = 1
     -maximum players = 3

     @return new default config

     */
    @Override
    public GameConfig createDefaultConfig(){
        //define allowed player types
        ArrayList<GamePlayerType> playerTypes = new ArrayList<>();

        // add players types
        playerTypes.add(new GamePlayerType("Human Player") {
            @Override
            public GamePlayer createPlayer(String name) {
                return new UnoHumanPlayer(name, R.layout.uno_human_player);
            }
        });
        // add dumb ai player type
        playerTypes.add(new GamePlayerType("Dory Ai") {
            @Override
            public GamePlayer createPlayer(String name) {
                return new UnoDumbAIPlayer(name, R.layout.uno_human_player);
            }
        });

        // add smart ai player type
        playerTypes.add(new GamePlayerType("Marlin Ai") {
            @Override
            public GamePlayer createPlayer(String name) {
                return new UnoSmartAIPlayer(name, R.layout.uno_human_player);
            }
        });

        //create game configuration
        GameConfig defaultConfig = new GameConfig(playerTypes, 1, 3, "Uno", PORT_NUMBER);
        defaultConfig.addPlayer("Human", 0); //player 1: human player
        defaultConfig.addPlayer("Dory Ai", 1); // player 2: dumb ai

        return defaultConfig;
    }

    /**
     * Create a local game
     *
     * @param gameState
     *              The desired gameState to start at or null for new game
     *
     * @return
     *      the local game, Uno
     */
    @Override
    public LocalGame createLocalGame(GameState gameState){return new UnoLocalGame();}
}