package com.example.okay_pc.myapplication;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.okay_pc.myapplication.enums.DialogOption;

/**
 * Created by Okay-PC on 12.2.2016.
 */
public class PopUp {

    private Activity activity;

    private DialogOption option;
    private String titleText;
    private String messageText;

    public PopUp(DialogOption option, String title, String message, Activity activity) {
        this.option = option;
        this.titleText = title;
        this.messageText = message;
        this.activity = activity;

        createDialog();
    }

    private void createDialog() {
        final Dialog dialog = new Dialog(activity);
        dialog.setContentView(R.layout.dialog_custom);

        TextView title = (TextView) dialog.findViewById(R.id.tv_title);
        TextView message = (TextView) dialog.findViewById(R.id.tv_message);
        Button back = (Button) dialog.findViewById(R.id.b_back);

        title.setText(titleText);
        message.setText(messageText);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (option == DialogOption.GAME_OVER) {
                    Intent intent = new Intent(activity, MainActivity.class);
                    activity.startActivity(intent);
                } else if (option == DialogOption.HIGH_SCORE || option == DialogOption.ABOUT) {
                    dialog.dismiss();
                }
            }
        });

        dialog.setCancelable(false);
        dialog.show();
    }
}
