package com.example.okay_pc.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.widget.Button;
import android.widget.Toast;

import com.example.okay_pc.myapplication.enums.Difficulty;
import com.example.okay_pc.myapplication.enums.GameMode;
import com.example.okay_pc.myapplication.interfaces.IInputObserver;

/**
 * Akademia SOVY project
 * <p/>
 * Copyright - 2016
 * Created by Peter Varholak on 5. 2. 2016.
 */
public class GameMaster implements IInputObserver{

    //RECOMPILE IF CHANGED!
    public static final int MAX_OPTION_AMOUNT = 5;
    public static final int MAX_EQUATION_MEMBERS_AMOUNT = 4;

    private static int currentEquationMembersAmount;
    private int levelsCompleted;
    private static GameMode gameMode;
    private static boolean gameFinished;

    private Activity activity;
    private Evaluator ev;
    private SceneManager sm;
    private InputHandler ih;
    private Timer timer;
    private Handler handler;

    public GameMaster(Activity activity) {
        this.activity = activity;
        currentEquationMembersAmount = 2;
        levelsCompleted = 0;
        setGameMode();
        gameFinished = false;

        ev = new Evaluator();
        ih = new InputHandler();
        sm = new SceneManager(activity, ih);
        timer = new Timer(this);
        handler = new Handler();

        setObservers();
    }

    public static int getCurrentEquationMembersAmount() {
        return currentEquationMembersAmount;
    }

    public static GameMode getGameMode() {
        return gameMode;
    }

    public void startLevel() {
        sm.createLevel();
        timer.startTime(10);
    }

    public void levelCompleted() {
        if (ev.isEquationCorrect(sm.getResultValue(), sm.getEquationNumbersValue())) {
            levelsCompleted++;
            updateScore();
            checkDifficultyIncrease();
            startLevel();
        } else {
            gameOver();
        }
    }

    private void setGameMode() {
        Intent intent = activity.getIntent();
        gameMode = (GameMode) intent.getSerializableExtra("gameMode");
    }

    private void setObservers() {
        timer.registerObserver(sm);
        ih.registerObserver(this);
    }

    private void updateScore() {
        sm.updateScore(levelsCompleted + timer.getScoreValue());
    }

    protected void gameOver() {
        //TODO: Add GAME OVER screen
        //TODO: Debug purposes, remove after Game over is created
        //TODO: SceneManager?
        Toast.makeText(activity.getBaseContext(), "GAME OVER", Toast.LENGTH_SHORT).show();
    }

    private void checkDifficultyIncrease() {
        if (levelsCompleted == Difficulty.NORMAL.getValue() ||
            levelsCompleted == Difficulty.HARD.getValue() ||
            levelsCompleted == Difficulty.EXTREME.getValue())
        {
            sm.increaseEquationSize();
            currentEquationMembersAmount++;
        }
    }

    @Override
    public void equationNumberPressed(Button b) {
        if (gameFinished) {
            return;
        }

        sm.equationNumberPressed(b);
    }

    @Override
    public void optionNumberPressed(Button b) {
        if (gameFinished) {
            return;
        }

        sm.optionNumberPressed(b);

        if (sm.isEquationFull()) {
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
