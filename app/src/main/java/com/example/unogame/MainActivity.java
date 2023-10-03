package com.example.unogame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.game.GameFramework.GameMainActivity;

public class MainActivity extends GameMainActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}