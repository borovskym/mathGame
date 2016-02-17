package com.example.okay_pc.myapplication;

import android.os.CountDownTimer;
import android.widget.ProgressBar;

/**
 * Akademia SOVY project
 * <p/>
 * Copyright - 2016
 * Created by Peter Varholak on 5. 2. 2016.
 */
public class Timer {

    private GameMaster gm;
    private CountDownTimer timer;
    private ProgressBar progress;
    private int millisToFinish;

    public Timer(GameMaster gm) {
        this.gm = gm;
        progress = GameScreen.getTimer();
        progress.setIndeterminate(false);
    }

    public void stopTime() {
        timer.cancel();
    }

    public void startTime(int seconds) {
        final int maxTime = seconds * 1000;
        progress.setMax(maxTime);
        progress.setSecondaryProgress(maxTime);

        timer = new CountDownTimer(maxTime, 50) {
            public void onTick(long millisUntilFinished) {
                millisToFinish = (int)millisUntilFinished;
                progress.setProgress(maxTime - millisToFinish);
            }

            public void onFinish() {
                gm.gameOver();
            }
        }.start();
    }

    public int getScoreValue(){
        return millisToFinish / 1000;
    }
}
