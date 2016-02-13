package com.example.okay_pc.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    Button button1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button1 = (Button) findViewById(R.id.button_ng);
        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                changeActivity();
            }
        });
    }

    public void changeActivity() {
        Intent intent = new Intent(this, GameScreen.class);
        intent.putExtra("gameMode", GameMode.ADDITION);
        startActivity(intent);
    }
}
