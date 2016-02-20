package com.example.okay_pc.myapplication;

import android.app.Activity;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

/**
 * Akademia SOVY project
 * <p/>
 * Copyright - 2016
 * Created by Peter Varholak on 5. 2. 2016.
 */
public class SceneBuilder implements ITimerObserver{

    private int resultValue;
    private ArrayList<Integer> optionValues;

    private ResourceHolder res;
    private EquationGenerator eg;

    public SceneBuilder(Activity activity) {
        res = new ResourceHolder(activity);
        eg = new EquationGenerator();
    }

    public int getResultValue() {
        return res.getResultValue();
    }
    public int[] getEquationNumbersValue() {
        return res.getEquationNumbersValue();
    }

    public void increaseEquationSize() {
        res.getEquationSigns().get(GameMaster.getCurrentEquationMembersAmount() - 1).setVisibility(View.VISIBLE);
        res.getEquationNumbers().get(GameMaster.getCurrentEquationMembersAmount()).setVisibility(View.VISIBLE);
        eg.resetDifficulty();
    }

    public void updateScore(int value) {
        res.getScore().setText(String.format("%d", value));
    }

    public void equationNumberPressed(Button b) {
        for (int i = 0; i < GameMaster.MAX_OPTION_AMOUNT; i++) {
            Button optionsButton = res.getOptions().get(i);

            if (!optionsButton.isEnabled()) {
                optionsButton.setText(b.getText());
                optionsButton.setBackgroundResource(R.drawable.game_button);
                optionsButton.setEnabled(true);

                b.setText("");
                b.setBackgroundResource(R.drawable.game_button_free);
                b.setEnabled(false);
                break;
            }
        }
    }

    public void optionNumberPressed(Button b) {
        for (int i = 0; i < GameMaster.getCurrentEquationMembersAmount(); i++) {
            Button equationButton = res.getEquationNumbers().get(i);

            if (!equationButton.isEnabled()) {
                equationButton.setText(b.getText());
                equationButton.setBackgroundResource(R.drawable.game_button);
                equationButton.setEnabled(true);

                b.setText("");
                b.setBackgroundResource(R.drawable.game_button_free);
                b.setEnabled(false);
                break;
            }
        }
    }

    public boolean isEquationFull() {
        for (int i = 0; i < GameMaster.getCurrentEquationMembersAmount(); i++) {
            if (!res.getEquationNumbers().get(i).isEnabled()) {
                return false;
            }
        }
        return true;
    }

    public void createLevel() {
        generateNumbers();
        setupResult();
        setupEquationNumbers();
        setupOptions();
    }

    private void generateNumbers() {
        eg.generate(GameMaster.getCurrentEquationMembersAmount());
        resultValue = eg.getResultValue();
        optionValues = eg.getOptionValues();
    }

    private void setupResult() {
        res.getResult().setText(String.format("%d", resultValue));
    }

    private void setupEquationNumbers() {
        int equationMembersAmount = GameMaster.getCurrentEquationMembersAmount();
        ArrayList<Button> equationNumbers = res.getEquationNumbers();

        for (int i = 0; i < equationMembersAmount; i++) {
            equationNumbers.get(i).setText("");
            equationNumbers.get(i).setBackgroundResource(R.drawable.game_button_free);
            equationNumbers.get(i).setEnabled(false);
        }
    }

    private void setupOptions() {
        int optionsAmount = GameMaster.MAX_OPTION_AMOUNT;
        ArrayList<Button> options = res.getOptions();

        for (int i = 0; i < optionsAmount; i++) {
            options.get(i).setText(String.format("%d", optionValues.get(i)));
            options.get(i).setBackgroundResource(R.drawable.game_button);
            options.get(i).setEnabled(true);
        }
    }

    @Override
    public void setupTimer(int maxTime) {
        res.getTimer().setMax(maxTime);
        res.getTimer().setSecondaryProgress(maxTime);
    }

    @Override
    public void updateTime(int value) {
        res.getTimer().setProgress(value);
    }
}
