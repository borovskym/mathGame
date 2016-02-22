package com.example.okay_pc.myapplication.enums;

/**
 * Akademia SOVY project
 * <p/>
 * Copyright - 2016
 * Created by Peter Varholak on 20. 2. 2016.
 */
public enum Difficulty {
    EASY("Easy", 0, 5), NORMAL("Normal", 20, 10), HARD("Hard", 50, 15), EXTREME("Extreme", 100, 15);

    private String name;
    private int value;
    private int seconds;

    Difficulty(String name, int value, int seconds) {
        this.name = name;
        this.value = value;
        this.seconds = seconds;
    }

    public final String getName() {
        return name;
    }

    public final int getValue() {
        return value;
    }

    public final int getSeconds() {
        return seconds;
    }
}
