package com.example.okay_pc.myapplication.interfaces;

import android.view.View;

/**
 * Akademia SOVY project
 * <p/>
 * Copyright - 2016
 * Created by Peter Varholak on 22. 2. 2016.
 */
public interface IInputSubject {
    void registerObserver(IInputObserver o);
    void equationNumberPressed(View v);
    void optionNumberPressed(View v);
}
