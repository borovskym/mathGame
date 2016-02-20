package com.example.okay_pc.myapplication;

/**
 * Akademia SOVY project
 * <p/>
 * Copyright - 2016
 * Created by Peter Varholak on 20. 2. 2016.
 */
public interface ITimerSubject {
    void registerObserver(ITimerObserver o);
    void removeObserver(ITimerObserver o);
    void setupTimer(int maxTime);
    void updateTime(int value);
}
