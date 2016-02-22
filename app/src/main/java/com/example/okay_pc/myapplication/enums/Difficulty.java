package com.example.okay_pc.myapplication.enums;

/**
 * Akademia SOVY project
 * <p/>
 * Copyright - 2016
 * Created by Peter Varholak on 20. 2. 2016.
 */
public enum Difficulty {
    EASY("Easy", 0), NORMAL("Normal", 20), HARD("Hard", 50), EXTREME("Extreme", 100);

    private String name;
    private int value;

    Difficulty(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public final String getName() {
        return name;
    }

    public final int getValue() {
        return value;
    }
}
