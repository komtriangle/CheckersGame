package com.example.checkers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity  implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        handleClicks();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.registerButton:
                boolean loginResult = login(v);
                if(loginResult){
                    moveToGame();
                }
                break;
            case  R.id.registerLink:
                moveToRegister();
            default:
                break;
        }
    }

    private boolean login(View v){
        return  true;
    }

    private  void moveToGame(){
        Intent i;
        i = new Intent(this, CheckersGameActivity.class);
        startActivity(i);
    }

    private  void moveToRegister(){
        Intent i;
        i = new Intent(this, RegisterActivity.class);
        startActivity(i);
    }

    private  void handleClicks(){
        Button registerButton = (Button) findViewById(R.id.registerButton);
        registerButton.setOnClickListener(this);

        TextView registerLink = (TextView) findViewById(R.id.registerLink);
        registerLink.setOnClickListener(this);
    }
}