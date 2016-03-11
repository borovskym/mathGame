package com.example.okay_pc.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.okay_pc.myapplication.enums.DialogOption;
import com.example.okay_pc.myapplication.enums.GameMode;

public class MainActivity extends AppCompatActivity {

    private SaveManager gameSaver;
    private Button button1;
    private Button button2;
    private Button button3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupReferences();
        setupListeners();

        gameSaver = new SaveManager(this);
    }

    private void setupReferences() {
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
    }

    private void setupListeners() {
        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                button1Pressed((Button) v);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                button2Pressed((Button) v);
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                button3Pressed((Button) v);
            }
        });
    }

    private void button1Pressed(Button b) {
        if (b.getText() == getResources().getString(R.string.button_play)) {
            changeButtons("Play");
        } else if (b.getText() == getResources().getString(R.string.button_addition)) {
            changeActivity(GameMode.ADDITION);
        }
    }

    private void button2Pressed(Button b) {
        if (b.getText() == getResources().getString(R.string.button_high_score)) {
            displayHighScore();
        } else if (b.getText() == getResources().getString(R.string.button_multiplication)) {
            changeActivity(GameMode.MULTIPLICATION);
        }
    }

    private void button3Pressed(Button b) {
        if (b.getText() == getResources().getString(R.string.button_about)) {
            displayAbout();
        } else if (b.getText() == getResources().getString(R.string.button_back)) {
            changeButtons("Back");
        }
    }

    private void changeButtons(String type) {
        if (type.equals("Play")) {
            button1.setText(getResources().getString(R.string.button_addition));
            button2.setText(getResources().getString(R.string.button_multiplication));
            button3.setText(getResources().getString(R.string.button_back));
        } else if (type.equals("Back")) {
            button1.setText(getResources().getString(R.string.button_play));
            button2.setText(getResources().getString(R.string.button_high_score));
            button3.setText(getResources().getString(R.string.button_about));
        }

    }

    private void changeActivity(GameMode mode) {
        Intent intent = new Intent(this, GameScreen.class);
        intent.putExtra("gameMode", mode);
        startActivity(intent);
    }

    private void displayHighScore() {
        String dialogTitle = "HIGH SCORES";
        String dialogMessage = String.format("Addition: %d\nMultiplication: %d", gameSaver.getAdditionScore(), gameSaver.getMultiplicationScore());
        PopUp dialog = new PopUp(DialogOption.HIGH_SCORE, dialogTitle, dialogMessage, this);
    }

    private void displayAbout() {
        String dialogTitle = "ABOUT";
        String dialogMessage = "Created by:\nPeter Varhoľák\nMichal Borovský";
        PopUp dialog = new PopUp(DialogOption.ABOUT, dialogTitle, dialogMessage, this);
    }
}
