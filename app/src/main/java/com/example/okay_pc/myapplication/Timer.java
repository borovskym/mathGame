package com.example.okay_pc.myapplication;

import android.os.CountDownTimer;

import com.example.okay_pc.myapplication.interfaces.ITimerObserver;
import com.example.okay_pc.myapplication.interfaces.ITimerSubject;

import java.util.Vector;

/**
 * Akademia SOVY project
 * <p/>
 * Copyright - 2016
 * Created by Peter Varholak on 5. 2. 2016.
 */
public class Timer implements ITimerSubject {

    private GameMaster gm;
    private Vector<ITimerObserver> observers;
    private CountDownTimer timer;
    private int millisToFinish;

    public Timer(GameMaster gm) {
        this.gm = gm;
        observers = new Vector<>();
    }

    public void stopTime() {
        timer.cancel();
    }

    public void startTime(int seconds) {
        final int maxTime = seconds * 1000;
        setupTimer(maxTime);

        timer = new CountDownTimer(maxTime, 50) {
            public void onTick(long millisUntilFinished) {
                millisToFinish = (int) millisUntilFinished;
                updateTime(maxTime - millisToFinish);
            }

            public void onFinish() {
                gm.gameOver();
            }
        }.start();
    }

    public int getScoreValue() {
        return millisToFinish / 1000;
    }

    @Override
    public void registerObserver(ITimerObserver o) {
        observers.add(o);
    }

    @Override
    public void setupTimer(int maxTime) {
        for(ITimerObserver observer : observers){
            observer.setupTimer(maxTime);
        }
    }

    @Override
    public void updateTime(int value) {
        for(ITimerObserver observer : observers){
            observer.updateTime(value);
        }
    }
}
