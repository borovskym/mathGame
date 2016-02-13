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

    ProgressBar pBar; // daj private
    int pStatus = 0; // daj private
    private Handler handler; // Handler sa nikde neinicializuje, pozri Konstruktor
    //TODO: vytvorit premennu kde bude pocet sekund a aj setter

    //TODO: Vytvor konstruktor v ktorom zrobis inicializaciu handlera a ostatne veci ktore sa robia len raz

    public void stopTime() {
        // toto by malo zastavit thread v startTime namiesto toho to ani neviem co robi
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
        pBar.setProgress(0); // myslim, ze toto Ti staci len nastavit pStatus na 0, urob to cez setter ale
    }

    public void startTime() {
        pBar = (ProgressBar) findViewById(R.id.circularProgressbar); // toto ma byt v konstruktore
        while (pStatus < 1000) { // prerobit cez vzorec na pocet sekund
            pStatus += 1;

            handler.post(new Runnable() {

                @Override
                public void run() {
                    pBar.setProgress(pStatus);
                }
            });
            try {
                /*
                 * tymto sleepnes aj nas thread na ktorom bezi hra, skus to dat na osobitny thread
                 * a nastav ho ako daemon aby sa ukoncoval spolu s appkou lebo inak nam to bude crashovat
                 */
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        stopTime(); // toto by malo volat vyslat do GameMastra signal a ten by mal mat event listener ktory ho zachyti
    }

   /* public int getScoreValue(){
        //vrati aktualny pStatus
    }*/
}
