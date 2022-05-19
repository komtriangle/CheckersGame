package com.example.checkers.Activities;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.checkers.API.AuthAPI;
import com.example.checkers.Activities.Interfaces.IAuthActivity;
import com.example.checkers.ActivitiesRouter;
import com.example.checkers.Models.User;
import com.example.checkers.R;

public class LoginActivity extends ActivityBase  implements View.OnClickListener, IAuthActivity {

    private AuthAPI auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        auth = new AuthAPI("http://213.178.155.140:4200", this);
        handleClicks();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.registerButton:
                login();
                break;
            case  R.id.registerLink:
               ActivitiesRouter.moveToRegister(this);
               break;
            case  R.id.skipAuth:
                skipAuth();
            default:
                break;
        }
    }

    private void login(){
        resetIncorrectDataWarning();
        EditText userNameText = (EditText) findViewById(R.id.userName);
        String userName = userNameText.getText().toString();

        EditText passwordText = (EditText) findViewById(R.id.password);
        String password = passwordText.getText().toString();
        auth.login(userName, password);
    }

    private  void skipAuth(){
        ActivitiesRouter.moveToMenu(this);
    }

    public void handleResultCallBack(User user){
        if(user.getId() != -1){
            setUserToSharedPreference(user);
            ActivitiesRouter.moveToMenu(this);
        }
        else{
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    setIncorrectDataWarning();
                }
            });
        }

    }

    private void   setIncorrectDataWarning(){
        String warningMessage = "Неправильное имя пользователя или пароль";
        TextView incorrectDataWarning = (TextView) findViewById(R.id.incorrectDataWarn);
        incorrectDataWarning.setText(warningMessage);
        incorrectDataWarning.setTextColor(Color.RED);
    }

    private void   resetIncorrectDataWarning(){
        TextView incorrectDataWarning = (TextView) findViewById(R.id.incorrectDataWarn);
        incorrectDataWarning.setText("");
        incorrectDataWarning.setBackgroundColor(Color.WHITE);
    }



    private  void handleClicks(){
        Button registerButton = (Button) findViewById(R.id.registerButton);
        registerButton.setOnClickListener(this);

        TextView registerLink = (TextView) findViewById(R.id.registerLink);
        registerLink.setOnClickListener(this);

        TextView skipAuth = (TextView) findViewById(R.id.skipAuth);
        skipAuth.setOnClickListener(this);
     }



}