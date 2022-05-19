package com.example.checkers.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.checkers.API.GameAPI;
import com.example.checkers.ActivitiesRouter;
import com.example.checkers.Dialogs.EndGameDialog;
import com.example.checkers.GameView;
import com.example.checkers.Models.GameResult;
import com.example.checkers.Models.User;
import com.example.checkers.R;

public class CheckersGameActivity extends ActivityBase implements View.OnClickListener{

    private  String player1Name;
    private  String player2Name;
    private GameAPI gameAPI;
    LinearLayout gameLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkers_game);
        readPlayerNames();
        gameLayout = findViewById(R.id.gameLayout);
        GameView gameView = new GameView(this, player1Name, player2Name);
        gameLayout.addView(gameView);
        handleClicks();
        gameAPI = new GameAPI("http://213.178.155.140:4200", null);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.endFromGame:
                exitFromGame();
                break;
            default:
                break;
        }
    }

    private  void exitFromGame(){
        ActivitiesRouter.moveToMenu(this);
    }

    public   void endGame(byte winner){
        showEndGameDialog(winner);
        User user = getUserSharedPreference();
        if(user != null){
            GameResult gameResult = new GameResult(user.getId(), player1Name, player2Name, winner);
            gameAPI.SaveGame(gameResult);
        }

    }

    private  void handleClicks(){
        Button endGame = (Button) findViewById(R.id.endFromGame);
        endGame.setOnClickListener(this);

    }

    private  void readPlayerNames(){
            Bundle arguments = getIntent().getExtras();

        if(arguments!=null){
            player1Name = arguments.getString("player1Name").toString();
            player2Name = arguments.getString("player2Name").toString();

        }
    }

    public  void showEndGameDialog(byte winner){
       String winnerName = getWinnerName(winner);

        FragmentManager manager = getSupportFragmentManager();
        EndGameDialog dialog = new EndGameDialog(winnerName, this);
        dialog.show(manager, "");

    }

    private  String getWinnerName(byte winner){
        String winnerName;
        if(winner == 0){
            winnerName = player1Name;
        }
        else {
            winnerName = player2Name;
        }
        return  winnerName;
    }

}