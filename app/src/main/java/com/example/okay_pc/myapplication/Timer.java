package com.example.okay_pc.myapplication;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Handler;
import android.widget.ProgressBar;

/**
 * Akademia SOVY project
 * <p/>
 * Copyright - 2016
 * Created by Peter Varholak on 5. 2. 2016.
 */
public class Timer extends GameScreen{

    ProgressBar pBar;
    int pStatus = 0;
    private Handler handler;

    public void stopTime() {
            AlertDialog.Builder helpBuilder = new AlertDialog.Builder(this);
            helpBuilder.setTitle("Game over");
            helpBuilder.setMessage("Much wow such skill\nYour score: "+getScore());
            helpBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });

            AlertDialog helpDialog = helpBuilder.create();
            helpDialog.show();
    }

    public void rewind() {
        pBar.setProgress(0);
        startTime();
    }

    public void startTime() {
        pBar = (ProgressBar) findViewById(R.id.circularProgressbar);
        while (pStatus < 1000) {
            pStatus += 1;

            handler.post(new Runnable() {

                @Override
                public void run() {
                    pBar.setProgress(pStatus);
                }
            });
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        stopTime();
    }

   /* public int getScoreValue(){

    }*/
}
