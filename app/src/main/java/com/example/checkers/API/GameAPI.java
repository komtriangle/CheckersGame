package com.example.checkers.API;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.checkers.Activities.GameHistoryActivity;
import com.example.checkers.Activities.Interfaces.IGameHistoryActivity;
import com.example.checkers.Models.GameResult;
import com.example.checkers.Models.User;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.JsonReader;
import com.squareup.moshi.Types;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;
import okio.BufferedSource;

public class GameAPI extends  ApiBase{

    private IGameHistoryActivity activity;
    public GameAPI(String apiUrl, IGameHistoryActivity activity){
        this.apiUrl = apiUrl;
        this.activity = activity;
    }

    public  void SaveGame(GameResult gameResult){
        String postBody = createBody(gameResult);
        Request request = createPostRequest("/Game", postBody);


        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {

            }
        });
    }

    public  void getGameHistory(int userId){
        Type type = Types.newParameterizedType(List.class, String.class);
        JsonAdapter<List<String>> gameHistoryJsonAdapter = moshi.adapter(type);
        Request request = createGetRequest(String.format("/Game?userId=%d", userId));
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {

            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if(response.isSuccessful()){
                    ArrayList<GameResult> results = parseGameResults(response.body().source());
                    activity.handleResultCallBack(results);
                }
                else{

                }

            }
        });
    }

    private  ArrayList<GameResult> parseGameResults(BufferedSource source){
        JsonReader reader = JsonReader.of(source);
        ArrayList<GameResult> gameResults = new ArrayList<GameResult>();
        try {
            return readGameResults(reader);
        }
        catch (Exception ex){
            return  gameResults;
        }
    }
    public ArrayList<GameResult> readGameResults(JsonReader reader) throws IOException {
        ArrayList<GameResult> games = new ArrayList<>();

        reader.beginArray();
        while (reader.hasNext()) {
            games.add(readGameResult(reader));
        }
        reader.endArray();
        return games;
    }
    private  GameResult readGameResult(JsonReader reader) throws  IOException{
        int userId = -1;
        String player1Name = null;
        String player2Name = null;
        byte winner = 0;

        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("userId")) {
                userId = reader.nextInt();
            } else if (name.equals("player1Name")) {
                player1Name = reader.nextString();
            } else if (name.equals("player2Name")) {
                player2Name = reader.nextString();
            } else if(name.equals("winner")){
                winner = (byte)reader.nextInt();
            }
            else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return new GameResult(userId, player1Name, player2Name, winner);
    }


    private  String createBody(GameResult gameResult){
        String postBody = String.format(""+
              "{"+
                "\"userId\": %d,"+
                "\"player1Name\": \"%s\","+
                "\"player2Name\": \"%s\","+
                "\"winner\": %d"+
            "}"
                , gameResult.getUserId(), gameResult.getPlayer1Name(),
                gameResult.getPlayer2Name(), gameResult.getWinner());
        return  postBody;
    }
}
