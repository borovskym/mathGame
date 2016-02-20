package com.example.okay_pc.myapplication;

/**
 * Akademia SOVY project
 * <p/>
 * Copyright - 2016
 * Created by Peter Varholak on 5. 2. 2016.
 */
public class Evaluator {

    public Evaluator() {
    }

    public boolean isEquationCorrect(int resultValue, int[] equationNumberValues) {

        switch (GameMaster.getGameMode()) {
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

    private boolean isAdditionCorrect(int result, int[] numbers) {
        int equationResult = 0;
        for (int number : numbers) {
            equationResult += number;
        }

        return (result == equationResult);
    }

    private boolean isMultiplicationCorrect(int result, int[] numbers) {
        int equationResult = 1;
        for (int number : numbers) {
            equationResult *= number;
        }

        return (result == equationResult);
    }
}
