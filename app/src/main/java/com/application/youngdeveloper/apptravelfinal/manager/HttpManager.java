package com.application.youngdeveloper.apptravelfinal.manager;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HttpManager {

    public static String UrlImage = "http://202.28.94.32/2559/563020499-9/tgst/img/";
    public static String UrlPHP = "http://202.28.94.32/2559/563020499-9/tgst/php_android/";

    private static HttpManager instance;

    public static HttpManager getInstance() {
        if (instance == null)
            instance = new HttpManager();
        return instance;
    }

    private Context mContext;
    private ApiService service;

    private HttpManager() {
        mContext = Contextor.getInstance().getContext();

        //set type of date format
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .create();

        /**
         * Create Retrofit instance
         */

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(UrlPHP)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        service = retrofit.create(ApiService.class);
    }

    public ApiService getService(){
        return service;
    }

}

