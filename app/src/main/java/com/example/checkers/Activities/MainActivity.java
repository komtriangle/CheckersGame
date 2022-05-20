package com.example.checkers.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.checkers.ActivitiesRouter;
import com.example.checkers.Sugar.Entities.User;
import com.orm.SugarContext;

public class MainActivity extends AppCompatActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SugarContext.init(this);
        chooseActivity();
    }

    private  void chooseActivity(){
        User user = User.first(User.class);
        if(user == null){
            ActivitiesRouter.moveToLogin(this);
        }
        else{
            ActivitiesRouter.moveToMenu(this);
        }

    }


}