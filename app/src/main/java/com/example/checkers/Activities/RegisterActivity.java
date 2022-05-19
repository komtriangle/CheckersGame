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

public class RegisterActivity extends ActivityBase implements View.OnClickListener, IAuthActivity {


    private AuthAPI auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        auth = new AuthAPI("http://213.178.155.140:4200", this);
        handleClicks();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.registerButton:
                register(v);
                break;
            case R.id.loginLink:
               ActivitiesRouter.moveToLogin(this);
            default:
                break;
        }
    }

    private void register(View v){
        resetIncorrectDataWarning();
        EditText userNameText = (EditText) findViewById(R.id.userName);
        String userName = userNameText.getText().toString();

        EditText passwordText = (EditText) findViewById(R.id.password);
        String password = passwordText.getText().toString();

        if(userName.length() < 3){
            String warningMessage = "Имя пользователя должно содержать не меньше 3-х символов";
            setIncorrectDataWarning(warningMessage);
            return;
        }
        if(password.length() <6){
            String warningMessage = "Пароль должен  содержать не меньше 3-х символов";
            setIncorrectDataWarning(warningMessage);
            return;
        }

        auth.register(userName, password);
    }

    public void handleResultCallBack(User user) {
        if (user.getId() != -1) {
            ActivitiesRouter.moveToMenu(this);
        } else {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    String warningMessage = "Пользователь с таким именем уже существует";
                    setIncorrectDataWarning(warningMessage);
                }
            });
        }
    }
    private void   setIncorrectDataWarning(String warningMessage){
        TextView incorrectDataWarning = (TextView) findViewById(R.id.incorrectRegDataWarn);
        incorrectDataWarning.setText(warningMessage);
        incorrectDataWarning.setTextColor(Color.RED);
    }

    private void   resetIncorrectDataWarning(){
        TextView incorrectDataWarning = (TextView) findViewById(R.id.incorrectRegDataWarn);
        incorrectDataWarning.setText("");
        incorrectDataWarning.setBackgroundColor(Color.WHITE);
    }




    private  void handleClicks(){
        Button registerButton = (Button) findViewById(R.id.registerButton);
        registerButton.setOnClickListener(this);

        TextView loginLink = (TextView) findViewById(R.id.loginLink);
        loginLink.setOnClickListener(this);
    }

}