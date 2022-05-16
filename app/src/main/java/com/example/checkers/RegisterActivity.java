package com.example.checkers;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

@RequiresApi(api = Build.VERSION_CODES.Q)
public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        handleClicks();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.registerButton:
                boolean registerResult = register(v);
                if(registerResult){
                    moveToGame();
                }
                break;
            case R.id.loginLink:
                moveToLogin();
            default:
                break;
        }
    }

    private boolean register(View v){
        return  true;
    }

    private  void moveToGame(){
        Intent i;
        i = new Intent(this, CheckersGameActivity.class);
        startActivity(i);
    }

    private  void moveToLogin(){
        Intent i;
        i = new Intent(this, LoginActivity.class);
        startActivity(i);
    }

    private  void handleClicks(){
        Button registerButton = (Button) findViewById(R.id.registerButton);
        registerButton.setOnClickListener(this);

        TextView loginLink = (TextView) findViewById(R.id.loginLink);
        loginLink.setOnClickListener(this);
    }
}