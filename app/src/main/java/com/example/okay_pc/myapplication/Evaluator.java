package com.example.okay_pc.myapplication;

import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Akademia SOVY project
 * <p/>
 * Copyright - 2016
 * Created by Peter Varholak on 5. 2. 2016.
 */
public class Evaluator {

    private TextView result;
    private ArrayList<Button> equationNumbers;
    private GameMode gameMode;

    public Evaluator() {
        result = GameScreen.getResult();
        equationNumbers = GameScreen.getEquationNumbers();
    }

    public void setGameMode(GameMode mode) {
        this.gameMode = mode;
    }

    public boolean isEquationCorrect() {
        int resultValue = Integer.parseInt(result.getText().toString());
        ArrayList<Integer> equationNumberValues = getEquationNumberValues();

        switch (gameMode) {
            case ADDITION: {
                return isAdditionCorrect(resultValue, equationNumberValues);
            }
            case MULTIPLICATION: {
                return isMultiplicationCorrect(resultValue, equationNumberValues);
            }
            default: {
                return false;
            }
        }
    }

    private boolean isAdditionCorrect(int result, ArrayList<Integer> numbers) {
        int equationResult = 0;
        for (int number : numbers) {
            equationResult += number;
        }

        return (result == equationResult);
    }

    private boolean isMultiplicationCorrect(int result, ArrayList<Integer> numbers) {
        int equationResult = 1;
        for (int number : numbers) {
            equationResult *= number;
        }

        return (result == equationResult);
    }

    private ArrayList<Integer> getEquationNumberValues() {
        ArrayList<Integer> equationNumberValues = new ArrayList<>();

        for (int i = 0; i < GameScreen.getCurrentEquationMembersAmount(); i++) {
            String buttonText = equationNumbers.get(i).getText().toString();
            equationNumberValues.add(Integer.parseInt(buttonText));
        }

        return equationNumberValues;
    }
}
