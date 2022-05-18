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

public class MainActivity extends AppCompatActivity  {

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

    private User getUserSharedPreference(){
        User user = null;
        String preferenceName = "USER_PREFERENCE";
        SharedPreferences userSharedPreferences = getSharedPreferences(preferenceName, Context.MODE_PRIVATE);
        if(userSharedPreferences.contains("USER_NAME") && userSharedPreferences.contains("ID")){
            String userName = userSharedPreferences.getString("USER_NAME", "");
            int id = userSharedPreferences.getInt("ID", -1);
            user = new User(id, userName);
        }
        return  user;

    }
}