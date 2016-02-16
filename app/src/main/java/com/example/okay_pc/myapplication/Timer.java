package com.example.okay_pc.myapplication;

import android.os.Handler;
import android.widget.ProgressBar;

/**
 * Akademia SOVY project
 * <p/>
 * Copyright - 2016
 * Created by Peter Varholak on 5. 2. 2016.
 */
public class Timer extends GameScreen{

    private ProgressBar pBar; // daj private
    private int pStatus; // daj private
    private Handler handler; // Handler sa nikde neinicializuje, pozri Konstruktor
    private Thread timerThread;
    private int pTime;

    //TODO: vytvorit premennu kde bude pocet sekund a aj setter

    //TODO: Vytvor konstruktor v ktorom zrobis inicializaciu handlera a ostatne veci ktore sa robia len raz

    public Timer() {
        this.pBar = GameScreen.getTimer();
        this.pStatus = 0;
        this.handler =  new Handler();
        this.pBar = (ProgressBar) findViewById(R.id.circularProgressbar);
        this.timerThread = new Thread();
        this.pTime = 0;
    }

    private void setpTime(int pTime){ this.pTime = pTime; }

    private void setpStatus(int pStatus) {
        this.pStatus = pStatus;
    }

    public void stopTime() {
        // toto by malo zastavit thread v startTime namiesto toho to ani neviem co robi
        getScoreValue();
        timerThread.interrupt();
    }

    public void rewind() {
        setpStatus(0);
    }

    public void startTime() {
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
                if(!timerThread.interrupted())
                    timerThread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        stopTime(); // toto by malo volat vyslat do GameMastra signal a ten by mal mat event listener ktory ho zachyti
    }

    public int getScoreValue(){
        //vrati aktualny pStatus
        return pStatus;
    }
}
