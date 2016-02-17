package com.example.okay_pc.myapplication;

import android.widget.TextView;
import android.widget.Toast;

/**
 * Akademia SOVY project
 * <p/>
 * Copyright - 2016
 * Created by Peter Varholak on 5. 2. 2016.
 */
public class GameMaster {

    private static final int easy = 20;
    private static final int normal = 50;
    private static final int hard = 100;

    private Evaluator ev;
    private SceneBuilder sb;
    private Timer timer;

    private int levelsCompleted;

    public GameMaster() {
        levelsCompleted = 1;
        ev = new Evaluator();
        sb = new SceneBuilder();
        timer = new Timer(this);
    }

    public void levelCompleted() {
        if (ev.isEquationCorrect()) {
            timer.stopTime();
            levelsCompleted++;
            updateScore();
            checkEquationMembersAmount();
            EquationGenerator.increaseDifficulty();
            startLevel();
        } else {
            gameOver();
        }
    }

    private void updateScore() {
        TextView score = GameScreen.getScore();
        score.setText(String.format("%d", levelsCompleted));
    }

    public void startLevel() {
        sb.createLevel();
        timer.startTime(10);
    }

    protected void gameOver() {
        //TODO: Add GAME OVER screen
        //TODO: Debug purposes, remove after Game over is created
        timer.stopTime();
        Toast.makeText(GameScreen.getContext(), "GAME OVER", Toast.LENGTH_SHORT).show();
    }

    private void checkEquationMembersAmount() {
        if (levelsCompleted == easy || levelsCompleted == normal || levelsCompleted == hard) {
            GameScreen.increaseDifficulty();
        }
    }

    //TODO: time listener
}
