package com.example.okay_pc.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.widget.Button;
import android.widget.Toast;

/**
 * Akademia SOVY project
 * <p/>
 * Copyright - 2016
 * Created by Peter Varholak on 5. 2. 2016.
 */
public class GameMaster {

    //RECOMPILE IF CHANGED!
    public static final int MAX_OPTION_AMOUNT = 5;
    public static final int MAX_EQUATION_MEMBERS_AMOUNT = 4;

    private static int currentEquationMembersAmount;
    private int levelsCompleted;
    private static GameMode gameMode;
    private static boolean gameFinished;

    private Activity activity;
    private Evaluator ev;
    private SceneBuilder sb;
    private Timer timer;
    private Handler handler;

    public GameMaster(Activity activity) {
        this.activity = activity;
        currentEquationMembersAmount = 2;
        levelsCompleted = 0;
        setGameMode();
        gameFinished = false;

        ev = new Evaluator();
        sb = new SceneBuilder(activity);
        timer = new Timer(this);
        timer.registerObserver(sb);
        handler = new Handler();
    }

    public static int getCurrentEquationMembersAmount() {
        return currentEquationMembersAmount;
    }
    public static GameMode getGameMode() {
        return gameMode;
    }

    public static boolean isGameFinished() {
        return gameFinished;
    }

    private void setGameMode() {
        Intent intent = activity.getIntent();
        gameMode = (GameMode) intent.getSerializableExtra("gameMode");
    }

    public void startLevel() {
        sb.createLevel();
        timer.startTime(10);
    }

    public void levelCompleted() {
        if (ev.isEquationCorrect(sb.getResultValue(), sb.getEquationNumbersValue())) {
            levelsCompleted++;
            updateScore();
            checkDifficultyIncrease();
            startLevel();
        } else {
            gameOver();
        }
    }

    private void updateScore() {
        sb.updateScore(levelsCompleted + timer.getScoreValue());
    }

    protected void gameOver() {
        //TODO: Add GAME OVER screen
        //TODO: Debug purposes, remove after Game over is created
        //TODO: SceneBuilder?
        Toast.makeText(activity.getBaseContext(), "GAME OVER", Toast.LENGTH_SHORT).show();
    }

    private void checkDifficultyIncrease() {
        if (levelsCompleted == Difficulty.NORMAL.getValue() ||
            levelsCompleted == Difficulty.HARD.getValue() ||
            levelsCompleted == Difficulty.EXTREME.getValue())
        {
            sb.increaseEquationSize();
            currentEquationMembersAmount++;
        }
    }

    public void equationNumberPressed(Button b) {
        if (gameFinished) {
            return;
        }

        sb.equationNumberPressed(b);
    }

    public void optionNumberPressed(Button b) {
        if (gameFinished) {
            return;
        }

        sb.optionNumberPressed(b);

        if (sb.isEquationFull()) {
            evaluateLevel();
        }
    }

    private void evaluateLevel() {
        gameFinished = true;
        timer.stopTime();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                levelCompleted();
                gameFinished = false;
            }
        }, 500);
    }
}
