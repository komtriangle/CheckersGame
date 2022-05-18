package com.example.checkers;

import android.content.Intent;


import androidx.appcompat.app.AppCompatActivity;

import com.example.checkers.Activities.CheckersGameActivity;
import com.example.checkers.Activities.LoginActivity;
import com.example.checkers.Activities.MenuActivity;
import com.example.checkers.Activities.RegisterActivity;

public  class ActivitiesRouter {
    public  static   void moveToGame(AppCompatActivity activity, String player1Name, String player2Name){
        Intent i;
        i = new Intent(activity, CheckersGameActivity.class);
        i.putExtra("player1Name", player1Name);
        i.putExtra("player2Name", player2Name);
        activity.startActivity(i);
    }

    public static void moveToRegister(AppCompatActivity activity){
        Intent i;
        i = new Intent(activity, RegisterActivity.class);
        activity.startActivity(i);
    }

    public  static   void moveToLogin(AppCompatActivity activity){
        Intent i;
        i = new Intent(activity, LoginActivity.class);
        activity.startActivity(i);
    }

    public  static  void moveToMenu(AppCompatActivity activity){
        Intent i;
        i = new Intent(activity, MenuActivity.class);
        activity.startActivity(i);
    }
}
