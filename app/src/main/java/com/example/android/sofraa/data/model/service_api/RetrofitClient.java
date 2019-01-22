package com.example.android.sofraa.data.model.service_api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    public static Retrofit getShopInstance() {

        return new Retrofit.Builder()
                .baseUrl("http://ipda3.com/sofra/api/v1/restaurant/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static Retrofit getUserInstance() {

        return new Retrofit.Builder()
                .baseUrl("http://ipda3.com/sofra/api/v1/client/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }


    public static Retrofit general() {

        return new Retrofit.Builder()
                .baseUrl("http://ipda3.com/sofra/api/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

}
