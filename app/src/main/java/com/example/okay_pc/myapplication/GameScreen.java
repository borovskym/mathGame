package com.example.okay_pc.myapplication;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

public class GameScreen extends AppCompatActivity {

    //TODO: check if can be made into singleton
    private static TextView result;
    private static TextView score;
    private static ArrayList<Button> equationNumbers;
    private static ArrayList<TextView> equationSigns;
    private static ArrayList<Button> options;

    private static final int optionAmount = 5;
    private static final int equationMembersAmount = 4;
    private static int currentEquationMembersAmount = 2;
    private boolean gameFinished;

    private GameMaster gm;
    private Handler handler;


    public static TextView getResult() {
        return result;
    }

    public static TextView getScore() {
        return score;
    }

    public static ArrayList<Button> getEquationNumbers() {
        return equationNumbers;
    }

    public static ArrayList<Button> getOptions() {
        return options;
    }

    public static int getOptionAmount() {
        return optionAmount;
    }

    public static int getCurrentEquationMembersAmount() {
        return currentEquationMembersAmount;
    }

    public static void increaseDifficulty() {
        equationSigns.get(currentEquationMembersAmount - 1).setVisibility(View.VISIBLE);
        equationNumbers.get(currentEquationMembersAmount).setVisibility(View.VISIBLE);
        currentEquationMembersAmount++;
        EquationGenerator.resetDifficulty();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_screen);

        result = (TextView) findViewById(R.id.tv_result);
        score = (TextView) findViewById(R.id.tv_score_value);
        equationNumbers = getEquationNumbersReference();
        equationSigns = getEquationSignsReference();
        options = getOptionsReference();
        gameFinished = false;
        gm = new GameMaster();
        handler = new Handler();
        gm.setGameMode(GameMode.ADDITION);

        gm.startGame();
    }

    private ArrayList<Button> getEquationNumbersReference() {
        ArrayList<Button> equationNumbers = new ArrayList<>();

        for (int i = 1; i <= equationMembersAmount; i++) {
            String buttonID = "b_equation_element" + i;
            int resID = getResources().getIdentifier(buttonID, "id", "com.example.okay_pc.myapplication");
            equationNumbers.add((Button) findViewById(resID));
            equationNumbers.get(i - 1).setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    equationNumberPressed(v);
                }
            });

        }

        return equationNumbers;
    }

    private ArrayList<TextView> getEquationSignsReference() {
        ArrayList<TextView> equationSigns = new ArrayList<>();

        for (int i = 1; i <= equationMembersAmount - 1; i++) {
            String signID = "tv_equation_sign" + i;
            int resID = getResources().getIdentifier(signID, "id", "com.example.okay_pc.myapplication");
            equationSigns.add((TextView) findViewById(resID));
        }

        return equationSigns;
    }

    private ArrayList<Button> getOptionsReference() {
        ArrayList<Button> options = new ArrayList<>();

        for (int i = 1; i <= optionAmount; i++) {
            String buttonID = "b_option" + i;
            int resID = getResources().getIdentifier(buttonID, "id", "com.example.okay_pc.myapplication");
            options.add((Button) findViewById(resID));
            options.get(i - 1).setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    optionNumberPressed(v);
                }
            });
        }

        return options;
    }

    private void equationNumberPressed(View v) {
        if (gameFinished) {
            return;
        }

        Button b = (Button) v;

        for (int i = 0; i < optionAmount; i++) {
            if (!options.get(i).isEnabled()) {
                options.get(i).setText(b.getText());
                options.get(i).setEnabled(true);
                b.setText("");
                b.setEnabled(false);
                break;
            }
        }
    }

    private void optionNumberPressed(View v) {
        if (gameFinished) {
            return;
        }

        Button b = (Button) v;

        for (int i = 0; i < currentEquationMembersAmount; i++) {
            if (!equationNumbers.get(i).isEnabled()) {
                equationNumbers.get(i).setText(b.getText());
                equationNumbers.get(i).setEnabled(true);
                b.setText("");
                b.setEnabled(false);
                break;
            }
        }

        if (isEquationFull()) {
            gameFinished = true;
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    gm.levelCompleted();
                    gameFinished = false;
                }
            }, 500);
            //gm.levelCompleted();
        }
    }

    private boolean isEquationFull() {
        for (int i = 0; i < currentEquationMembersAmount; i++) {
            if (!equationNumbers.get(i).isEnabled()) {
                return false;
            }
        }

        return true;
    }


}