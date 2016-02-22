package com.example.okay_pc.myapplication;

import android.app.Activity;
import android.view.View;
import android.widget.Button;

import com.example.okay_pc.myapplication.interfaces.ITimerObserver;

import java.util.ArrayList;

/**
 * Akademia SOVY project
 * <p/>
 * Copyright - 2016
 * Created by Peter Varholak on 5. 2. 2016.
 */
public class SceneManager implements ITimerObserver{

    private ResourceHolder res;
    private SceneBuilder sb;

    public SceneManager(Activity activity, InputHandler ih) {
        res = new ResourceHolder(activity, ih);
        sb = new SceneBuilder();
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
        sb.resetDifficulty();
    }

    public void updateScore(int value) {
        res.getScore().setText(String.format("%d", value));
    }

    public void equationNumberPressed(Button b) {
        for (int i = 0; i < GameMaster.MAX_OPTION_AMOUNT; i++) {
            Button optionsButton = res.getOptions().get(i);

            if (!optionsButton.isEnabled()) {
                fillButton(optionsButton, b.getText());
                clearButton(b);
                break;
            }
        }
    }

    public void optionNumberPressed(Button b) {
        for (int i = 0; i < GameMaster.getCurrentEquationMembersAmount(); i++) {
            Button equationButton = res.getEquationNumbers().get(i);

            if (!equationButton.isEnabled()) {
                fillButton(equationButton, b.getText());
                clearButton(b);
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
        sb.createLevel();
    }

    private void fillButton(Button b, CharSequence text) {
        b.setText(text);
        b.setBackgroundResource(R.drawable.game_button);
        b.setEnabled(true);
    }

    private void clearButton(Button b) {
        b.setText("");
        b.setBackgroundResource(R.drawable.game_button_free);
        b.setEnabled(false);
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


    /**
     * Akademia SOVY project
     * <p/>
     * Copyright - 2016
     * Created by Peter Varholak on 22. 2. 2016.
     */
    public class SceneBuilder {

        private int resultValue;
        private ArrayList<Integer> optionValues;

        private EquationGenerator eg;

        public SceneBuilder() {
            eg = new EquationGenerator();
        }

        public void createLevel() {
            generateNumbers();
            setupResult();
            setupEquationNumbers();
            setupOptions();
        }

        public void resetDifficulty() {
            eg.resetDifficulty();
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
                clearButton(equationNumbers.get(i));
            }
        }

        private void setupOptions() {
            int optionsAmount = GameMaster.MAX_OPTION_AMOUNT;
            ArrayList<Button> options = res.getOptions();

            for (int i = 0; i < optionsAmount; i++) {
                fillButton(options.get(i), String.format("%d", optionValues.get(i)));
            }
        }
    }
}
