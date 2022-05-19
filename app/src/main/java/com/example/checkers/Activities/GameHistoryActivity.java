package com.example.checkers.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.checkers.API.GameAPI;
import com.example.checkers.Activities.Interfaces.IGameHistoryActivity;
import com.example.checkers.ActivitiesRouter;
import com.example.checkers.Models.GameResult;
import com.example.checkers.Models.User;
import com.example.checkers.R;

import java.util.ArrayList;

public class GameHistoryActivity extends ActivityBase implements IGameHistoryActivity {

    private GameAPI gameAPI;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_history);
        gameAPI = new GameAPI("http://213.178.155.140:4200", this);
        getHistory();
    }

    @Override
    public void handleResultCallBack(ArrayList<GameResult> gameResults) {
        ListView countriesList = findViewById(R.id.gameResultsList);

        ArrayList<String> games = new ArrayList<String>();

        for(int i=0; i< gameResults.size();i++){
            GameResult game = gameResults.get(i);
            games.add(String.format("%s - %s. Победитель: %s",
                    game.getPlayer1Name(), game.getPlayer2Name(),
                    game.getWinner() ==0 ? game.getPlayer1Name() : game.getPlayer2Name()));
        }
        String []gamesArr = new String[games.size()];
        games.toArray(gamesArr);

        ArrayAdapter<String> adapter = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1, gamesArr);

       runOnUiThread(new Runnable() {
           @Override
           public void run() {
               countriesList.setAdapter(adapter);
           }
       });
    }

    private  void getHistory(){
        User user = getUserSharedPreference();
        if(user != null){
            gameAPI.getGameHistory(user.getId());
        }
        else{
            ActivitiesRouter.moveToMenu(this);
        }

    }
}