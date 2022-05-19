package com.example.checkers.API;

import com.squareup.moshi.Moshi;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public abstract class ApiBase {

    protected  String apiUrl;
    protected MediaType MEDIA_TYPE_APP_JSON
            = MediaType.parse("application/json; charset=utf-8");
    protected Moshi moshi;
    protected OkHttpClient client;

    public  ApiBase(){
        moshi = new Moshi.Builder().build();
        client = new OkHttpClient();
    }



    protected Request createPostRequest(String query, String  body){
        Request request = new Request.Builder()
                .url(apiUrl+query)
                .post(RequestBody.create(MEDIA_TYPE_APP_JSON, body))
                .build();
        return  request;
    }

    protected  Request createGetRequest(String query){
        Request request = new Request.Builder()
                .url(apiUrl+query)
                .get()
                .build();
        return  request;
    }
}
