package com.example.checkers;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class CheckersGameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new GameView(this));

    }
}