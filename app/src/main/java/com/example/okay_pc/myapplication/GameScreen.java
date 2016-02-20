package com.example.okay_pc.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class GameScreen extends AppCompatActivity {

    private GameMaster gm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_screen);

        gm = new GameMaster(this);
        gm.startLevel();
    }

    public void equationNumberPressed(View v) {
        if (GameMaster.isGameFinished()) {
            return;
        }

        Button b = (Button) v;

        gm.equationNumberPressed(b);
    }

    public void optionNumberPressed(View v) {
        if (GameMaster.isGameFinished()) {
            return;
        }

        Button b = (Button) v;

        gm.optionNumberPressed(b);


    }
}