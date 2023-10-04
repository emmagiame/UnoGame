package com.example.unogame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.game.GameFramework.GameMainActivity;
import com.example.game.GameFramework.LocalGame;
import com.example.game.GameFramework.gameConfiguration.GameConfig;
import com.example.game.GameFramework.infoMessage.GameState;

public class MainActivity extends GameMainActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    @Override
    public GameConfig createDefaultConfig(){
        return null;
    }
    @Override
    public LocalGame createLocalGame(GameState gameState){
        return null;
    }
}