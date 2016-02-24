package com.example.okay_pc.myapplication;

import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.okay_pc.myapplication.enums.GameMode;

import java.util.ArrayList;

/**
 * Akademia SOVY project
 * <p/>
 * Copyright - 2016
 * Created by Peter Varholak on 20. 2. 2016.
 */
public class ResourceHolder {

    private Activity activity;
    private TextView result;
    private TextView score;
    private ArrayList<Button> equationNumbers;
    private ArrayList<TextView> equationSigns;
    private ArrayList<Button> options;
    private ProgressBar timer;

    private InputHandler ih;

    public ResourceHolder(Activity activity, InputHandler ih) {
        this.activity = activity;
        this.ih = ih;
        setReferences();
        setButtons();
        setSigns();
    }

    //handlers block
    public TextView getResult() {
        return result;
    }
    public TextView getScore() {
        return score;
    }
    public ArrayList<Button> getEquationNumbers() {
        return equationNumbers;
    }
    public ArrayList<TextView> getEquationSigns() {
        return equationSigns;
    }
    public ArrayList<Button> getOptions() {
        return options;
    }
    public ProgressBar getTimer() {
        return timer;
    }

    //values block
    public int getResultValue() {
        return Integer.parseInt(result.getText().toString());
    }
    public int getScoreValue() {
        return Integer.parseInt(score.getText().toString());
    }
    public int[] getEquationNumbersValue() {
        int[] values = new int[GameMaster.MAX_EQUATION_MEMBERS_AMOUNT];

        for (int i = 0; i < values.length; i++) {
            String value = equationNumbers.get(i).getText().toString();
            values[i] = Integer.parseInt(value);
        }

        return values;
    }
    public int[] getOptionsValue() {
        int[] values = new int[GameMaster.MAX_OPTION_AMOUNT];

        for (int i = 0; i < values.length; i++) {
            String value = options.get(i).getText().toString();
            values[i] = Integer.parseInt(value);
        }

        return values;
    }

    //setter block
    private void setReferences() {
        result = (TextView) activity.findViewById(R.id.tv_result);
        score = (TextView) activity.findViewById(R.id.tv_score_value);
        equationNumbers = getEquationNumbersReference();
        equationSigns = getEquationSignsReference();
        options = getOptionsReference();
        timer = (ProgressBar) activity.findViewById(R.id.circularProgressbar);
    }

    private ArrayList<Button> getEquationNumbersReference() {
        ArrayList<Button> equationNumbers = new ArrayList<>();

        for (int i = 1; i <= GameMaster.MAX_EQUATION_MEMBERS_AMOUNT; i++) {
            String buttonID = "b_equation_element" + i;
            int resID = activity.getResources().getIdentifier(buttonID, "id", "com.example.okay_pc.myapplication");
            equationNumbers.add((Button) activity.findViewById(resID));
            equationNumbers.get(i - 1).setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    ih.equationNumberPressed(v);
                }
            });
        }

        return equationNumbers;
    }

    private ArrayList<TextView> getEquationSignsReference() {
        ArrayList<TextView> equationSigns = new ArrayList<>();
        GameMode gameMode = GameMaster.getGameMode();
        TextView sign;

        for (int i = 1; i <= GameMaster.MAX_EQUATION_MEMBERS_AMOUNT - 1; i++) {
            String signID = "tv_equation_sign" + i;
            int resID = activity.getResources().getIdentifier(signID, "id", "com.example.okay_pc.myapplication");
            equationSigns.add((TextView) activity.findViewById(resID));
        }

        return equationSigns;
    }

    private ArrayList<Button> getOptionsReference() {
        ArrayList<Button> options = new ArrayList<>();

        for (int i = 1; i <= GameMaster.MAX_OPTION_AMOUNT; i++) {
            String buttonID = "b_option" + i;
            int resID = activity.getResources().getIdentifier(buttonID, "id", "com.example.okay_pc.myapplication");
            options.add((Button) activity.findViewById(resID));
            options.get(i - 1).setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    ih.optionNumberPressed(v);
                }
            });
        }

        return options;
    }

    private void setButtons() {
        for (Button button : equationNumbers) {
            if (GameMaster.getGameMode() == GameMode.ADDITION) {
                button.setText(String.format("%d", 0));
            } else if (GameMaster.getGameMode() == GameMode.MULTIPLICATION) {
                button.setText(String.format("%d", 1));
            }
        }
    }

    private void setSigns() {
        for (TextView sign : equationSigns) {
            if (GameMaster.getGameMode() == GameMode.ADDITION) {
                sign.setText(activity.getResources().getString(R.string.equation_plus));
            } else if (GameMaster.getGameMode() == GameMode.MULTIPLICATION) {
                sign.setText(activity.getResources().getString(R.string.equation_multiply));
            }
        }
    }
}
