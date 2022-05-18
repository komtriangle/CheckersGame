package com.example.checkers.Activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.checkers.ActivitiesRouter;
import com.example.checkers.R;

public class MenuActivity extends AppCompatActivity  implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        handleClicks();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.newGame:
                newGame();
                break;
            case R.id.logOut:
                LogOut();
            default:
                break;
        }
    }

    private  void newGame(){
        //ActivitiesRouter.moveToGame(this);
        ChoosePlayerNames();


    }

    private  void LogOut(){
        resetUserInSharedPreference();
        ActivitiesRouter.moveToLogin(this);
    }

    private  void resetUserInSharedPreference(){
        SharedPreferences.Editor userShapeEditor = getUserSharedEditor();
        userShapeEditor.remove("USER_NAME");
        userShapeEditor.remove("ID");
    }

    private SharedPreferences.Editor getUserSharedEditor(){
        String preferenceName = "USER_PREFERENCE";
        SharedPreferences userSharedPreferences = getSharedPreferences(preferenceName, Context.MODE_PRIVATE);
        SharedPreferences.Editor userShapeEditor = userSharedPreferences.edit();
        return  userShapeEditor;
    }

    private  void handleClicks(){
        Button newGame = (Button) findViewById(R.id.newGame);
        newGame.setOnClickListener(this);

        Button gameHistory = (Button) findViewById(R.id.gamesHistory);
        gameHistory.setOnClickListener(this);

        Button logOut = (Button) findViewById(R.id.logOut);
        logOut.setOnClickListener(this);
    }

    private  void ChoosePlayerNames(){
        LayoutInflater li = LayoutInflater.from(this);
        View chooseNamesView = li.inflate(R.layout.choose_player_names, null);
        AlertDialog.Builder mDialogBuilder = new AlertDialog.Builder(this);
        mDialogBuilder.setView(chooseNamesView);
        mDialogBuilder
                .setCancelable(false)
                .setPositiveButton("Начать игру",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                boolean validateResult = validateChoosePlayerNames(chooseNamesView);
                                if(validateResult){
                                    handleChoosePlayerNames(chooseNamesView);
                                }
                            }
                        })
                .setNegativeButton("Отмена",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                dialog.cancel();
                            }
                        });

        AlertDialog alertDialog = mDialogBuilder.create();
        alertDialog.show();
    }


    private  boolean validateChoosePlayerNames(View view){
        EditText player1Name = (EditText)view.findViewById(R.id.player1Name);
        EditText player2Name = (EditText) view.findViewById(R.id.player1Name);
        return player1Name.length() > 0 && player2Name.length() > 0;
    }

    private  void handleChoosePlayerNames(View view){
        EditText player1NameText = (EditText)view.findViewById(R.id.player1Name);
        EditText player2NameText = (EditText) view.findViewById(R.id.player1Name);

        String player1Name = player1NameText.getText().toString();
        String player2Name = player2NameText.getText().toString();
        if(player1Name != null && player2Name != null){
            ActivitiesRouter.moveToGame(this, player1Name, player2Name);
        }
    }




}