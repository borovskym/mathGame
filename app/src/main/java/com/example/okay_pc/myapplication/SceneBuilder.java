package com.example.okay_pc.myapplication;

import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Akademia SOVY project
 * <p/>
 * Copyright - 2016
 * Created by Peter Varholak on 5. 2. 2016.
 */
public class SceneBuilder {

    private TextView result;
    private ArrayList<Button> equationNumbers;
    private ArrayList<Button> options;

    private int resultValue;
    private ArrayList<Integer> optionValues;

    private EquationGenerator eg;

    public SceneBuilder() {
        eg = new EquationGenerator();
        result = GameScreen.getResult();
        equationNumbers = GameScreen.getEquationNumbers();
        options = GameScreen.getOptions();
    }

    public void createLevel() {
        generateNumbers();
        setupResult();
        setupEquationNumbers();
        setupOptions();
    }

    private void generateNumbers() {
        eg.generate(GameScreen.getCurrentEquationMembersAmount());
        resultValue = eg.getResultValue();
        optionValues = eg.getOptionValues();
    }

    private void setupResult() {
        result.setText(String.format("%d", resultValue));
    }

    private void setupEquationNumbers() {
        int equationMembersAmount = GameScreen.getCurrentEquationMembersAmount();

        for (int i = 0; i < equationMembersAmount; i++) {
            equationNumbers.get(i).setText("");
            equationNumbers.get(i).setEnabled(false);
        }
    }

    private void setupOptions() {
        int optionsAmount = GameScreen.getOptionAmount();

        for (int i = 0; i < optionsAmount; i++) {
            options.get(i).setText(String.format("%d", optionValues.get(i)));
            options.get(i).setEnabled(true);
        }
    }
}
