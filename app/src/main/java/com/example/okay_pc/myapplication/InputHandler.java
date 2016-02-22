package com.example.okay_pc.myapplication;

import android.view.View;
import android.widget.Button;

import com.example.okay_pc.myapplication.interfaces.IInputObserver;
import com.example.okay_pc.myapplication.interfaces.IInputSubject;

import java.util.Vector;

/**
 * Akademia SOVY project
 * <p/>
 * Copyright - 2016
 * Created by Peter Varholak on 22. 2. 2016.
 */
public class InputHandler implements IInputSubject {

    private Vector<IInputObserver> observers;

    public InputHandler() {
        observers = new Vector<>();
    }

    @Override
    public void registerObserver(IInputObserver o) {
        observers.add(o);
    }

    @Override
    public void equationNumberPressed(View v) {
        Button b = (Button) v;

        for(IInputObserver observer : observers){
            observer.equationNumberPressed(b);
        }
    }

    @Override
    public void optionNumberPressed(View v) {
        Button b = (Button) v;

        for(IInputObserver observer : observers){
            observer.optionNumberPressed(b);
        }
    }
}
