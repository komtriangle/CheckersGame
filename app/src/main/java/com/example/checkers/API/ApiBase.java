package com.example.checkers.API;

import okhttp3.MediaType;

public abstract class ApiBase {
    protected  String apiUrl;
    protected MediaType MEDIA_TYPE_APP_JSON
            = MediaType.parse("application/json; charset=utf-8");
}
