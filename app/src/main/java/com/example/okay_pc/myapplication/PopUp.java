package com.example.okay_pc.myapplication;

import android.app.AlertDialog;
import android.content.DialogInterface;

/**
 * Created by Okay-PC on 12.2.2016.
 */
public class PopUp extends GameScreen{
   private  void showPopUp(){
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
}
