package com.example.okay_pc.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class GameScreen extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_screen);

        GameMaster gm = new GameMaster(this);
        gm.startLevel();
    }
}