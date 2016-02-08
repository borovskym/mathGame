package com.example.okay_pc.myapplication;

import android.widget.TextView;

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

    Evaluator ev;
    SceneBuilder sb;
    Timer timer;

    private int levelsCompleted;

    public GameMaster() {
        levelsCompleted = 1;
        ev = new Evaluator();
        sb = new SceneBuilder();
        timer = new Timer();
    }

    public void setGameMode(GameMode mode) {
        sb.setGameMode(mode);
        ev.setGameMode(mode);
    }

    public void startGame() {
        startLevel();
    }

    public void levelCompleted() {
        if (ev.isEquationCorrect()) {
            timer.stopTime();
            levelsCompleted++;
            updateScore();
            checkEquationMembersAmount();
            startLevel();
            EquationGenerator.increaseDifficulty();
        } else {
            gameOver();
        }
    }

    private void updateScore() {
        TextView score = GameScreen.getScore();
        score.setText(String.format("%d", levelsCompleted));
    }

    private void startLevel() {
        sb.createLevel();
        timer.rewind();
        timer.startTime();
    }

    private void gameOver() {
        //TODO: Add GAME OVER screen
        startLevel(); //TODO: Debug purposes, remove after Game over is created
    }

    private void checkEquationMembersAmount() {
        if (levelsCompleted == easy || levelsCompleted == normal || levelsCompleted == hard) {
            GameScreen.increaseDifficulty();
        }
    }

    //TODO: time listener
}
