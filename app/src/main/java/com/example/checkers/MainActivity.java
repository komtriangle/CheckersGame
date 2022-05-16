package com.example.checkers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.checkers.DAL.Data;
import com.example.checkers.DAL.DbProperties;
import com.example.checkers.DAL.Users.UserRepository;

public class MainActivity extends AppCompatActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new GameView(this));
        chooseActivity();
    }



    private  void chooseActivity(){
        Intent i;
        i = new Intent(this, LoginActivity.class);
        startActivity(i);
    }
}