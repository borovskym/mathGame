package com.example.okay_pc.myapplication;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * Akademia SOVY project
 * <p/>
 * Copyright - 2016
 * Created by Peter Varholak on 5. 2. 2016.
 */
public class EquationGenerator {

    private static int difficulty;

    private int resultValue;
    private ArrayList<Integer> optionValues;
    private Random random;

    public EquationGenerator() {
        difficulty = 0;
        this.random = new Random();
    }

    public void resetDifficulty() {
        difficulty = 0;
    }

    public int getResultValue() {
        return resultValue;
    }

    public ArrayList<Integer> getOptionValues() {
        return optionValues;
    }

    public void generate(int equationSize) {
        generateEquation(equationSize);
        generateResult();
        generateFillerNumbers(equationSize);
        shuffleArrayList();
        increaseDifficulty();
    }

    private void generateEquation(int equationSize) {
        optionValues = new ArrayList<>();
        int randomNumber;

        for (int i = 0; i < equationSize; i++) {
            randomNumber = random.nextInt(10 + (5 * (difficulty / 10)) + (difficulty / 15));
            optionValues.add(randomNumber);
        }
    }

    private void generateResult() {
        switch (GameMaster.getGameMode()) {
            case ADDITION: {
                resultValue = solveAddition();
                break;
            }
            case MULTIPLICATION: {
                resultValue = solveMultiplication();
                break;
            }
            default: {
                resultValue = -1;
                break;
            }
        }
    }

    private int solveAddition() {
        int result = 0;

        for (int number : optionValues) {
            result += number;
        }

        return result;
    }

    private int solveMultiplication() {
        int result = 1;

        for (int number : optionValues) {
            result *= number;
        }

        return result;
    }

    private void generateFillerNumbers(int equationSize) {
        int size = GameMaster.MAX_OPTION_AMOUNT - equationSize;
        int randomNumber;

        for (int i = 0; i < size; i++) {
            randomNumber = random.nextInt(10 + (5 * (difficulty / 10)) + (difficulty / 15));
            optionValues.add(randomNumber);
        }
    }

    private void shuffleArrayList() {
        long seed = System.nanoTime();
        Collections.shuffle(optionValues, new Random(seed));
    }

    private void increaseDifficulty() {
        if (difficulty != 100) {
            difficulty++;
        }
    }
}
