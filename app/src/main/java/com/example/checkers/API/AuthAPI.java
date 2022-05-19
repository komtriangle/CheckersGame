package com.example.checkers.API;

import androidx.annotation.NonNull;

import com.example.checkers.Activities.Interfaces.IAuthActivity;
import com.example.checkers.Models.User;
import com.squareup.moshi.JsonAdapter;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;

public class AuthAPI extends  ApiBase {

    private IAuthActivity activity;
    public AuthAPI(String apiUrl, IAuthActivity activity) {
        super();
        this.apiUrl = apiUrl;
        this.activity = activity;
    }

    public  void login(String userName, String password){
        String postBody = createBoby(userName, password);

        Request request = createPostRequest("/Users/LogIn", postBody);
        JsonAdapter<User> userJsonAdapter = moshi.adapter(User.class);

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                activity.handleResultCallBack(new User(-1, null));;
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if(response.isSuccessful()){
                    User user = userJsonAdapter.fromJson(response.body().source());
                    activity.handleResultCallBack(user);
                }
                else{
                    activity.handleResultCallBack(new User(-1, null));
                }
            }
        });
    }

    public  void register(String userName, String password){
        String postBody = createBoby(userName, password);
        Request request = createPostRequest("/Users/Register", postBody);

        JsonAdapter<User> userJsonAdapter = moshi.adapter(User.class);

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                activity.handleResultCallBack(new User(-1, null));;
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if(response.isSuccessful()){
                    User user = userJsonAdapter.fromJson(response.body().source());
                    activity.handleResultCallBack(user);
                }
                else{
                    activity.handleResultCallBack(new User(-1, null));
                }
            }
        });
    }


    private  String createBoby(String userName, String password){
        String postBody = String.format(""
                + "{"+
                "\"userName\": \"%s\","+
                "\"password\": \"%s\""+
                "}", userName, password);
        return  postBody;
    }




}
