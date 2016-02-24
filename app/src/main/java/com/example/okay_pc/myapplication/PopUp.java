package com.example.okay_pc.myapplication;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;

import com.example.okay_pc.myapplication.enums.GameMode;

/**
 * Created by Okay-PC on 12.2.2016.
 */
public class PopUp {

    private Activity activity;

    private String title;
    private String message;

    public PopUp(String title, Activity activity, int score) {
        this.activity = activity;
        this.title = title;
        this.message = "Your score is " + score;

        showPopUp();
    }

    private  void showPopUp(){
        AlertDialog.Builder helpBuilder = new AlertDialog.Builder(activity);
        helpBuilder.setTitle(title);
        helpBuilder.setMessage(message);
        helpBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(activity, MainActivity.class);
                activity.startActivity(intent);
            }
        });

        AlertDialog helpDialog = helpBuilder.create();
        helpDialog.show();
    }
}
