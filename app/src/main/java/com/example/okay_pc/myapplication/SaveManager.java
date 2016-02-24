package com.example.okay_pc.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import java.io.Serializable;

/**
 * Akademia SOVY project
 * <p/>
 * Copyright - 2016
 * Created by Peter Varholak on 24. 2. 2016.
 */
public class SaveManager {

    private transient SharedPreferences sharedPref;
    private int additionScore;
    private int multiplicationScore;

    private String additionKey;
    private String multiplicationKey;

    public SaveManager(Activity activity) {
        String appName = activity.getResources().getString(R.string.app_name);
        sharedPref = activity.getSharedPreferences(appName, Context.MODE_PRIVATE);
        additionKey = activity.getResources().getString(R.string.high_score_addition);
        multiplicationKey = activity.getResources().getString(R.string.high_score_multiplication);
        additionScore = sharedPref.getInt(additionKey, 0);
        multiplicationScore = sharedPref.getInt(multiplicationKey, 0);
    }

    public int getAdditionScore() {
        return additionScore;
    }

    public void setAdditionScore(int additionScore) {
        if (this.additionScore < additionScore) {
            this.additionScore = additionScore;
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putInt(additionKey, additionScore);
            editor.apply();
        }
    }

    public int getMultiplicationScore() {
        return multiplicationScore;
    }

    public void setMultiplicationScore(int multiplicationScore) {
        if (this.multiplicationScore < multiplicationScore) {
            this.multiplicationScore = multiplicationScore;
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putInt(multiplicationKey, multiplicationScore);
            editor.apply();
        }
    }
}
