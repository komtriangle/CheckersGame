package com.example.checkers.Dialogs;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.example.checkers.ActivitiesRouter;

public class EndGameDialog extends DialogFragment {

    private  String winnerName;
    private AppCompatActivity activity;


    public  EndGameDialog(String winnerName, AppCompatActivity activity){
        this.winnerName = winnerName;
        this.activity = activity;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        String title = "Игра завершена";
        String message = "Победил - " + winnerName;
        String button1String = "Перейти в меню";

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(title);  // заголовок
        builder.setMessage(message); // сообщение
        builder.setPositiveButton(button1String, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                ActivitiesRouter.moveToMenu(activity);
            }
        });
        builder.setCancelable(true);

        return builder.create();
    }
}
