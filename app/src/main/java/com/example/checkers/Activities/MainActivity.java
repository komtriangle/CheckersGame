package com.example.checkers.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.checkers.Activities.CheckersGameActivity;
import com.example.checkers.Activities.LoginActivity;
import com.example.checkers.ActivitiesRouter;
import com.example.checkers.GameView;
import com.example.checkers.Models.User;

public class MainActivity extends ActivityBase  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        chooseActivity();
    }

    private  void chooseActivity(){
        User user = getUserSharedPreference();
        if(user == null){
            ActivitiesRouter.moveToLogin(this);
        }
        else{
            ActivitiesRouter.moveToMenu(this);
        }

    }


}