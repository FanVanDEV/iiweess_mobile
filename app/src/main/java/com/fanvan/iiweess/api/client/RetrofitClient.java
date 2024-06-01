package com.fanvan.iiweess.api.client;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static final class RetrofitHolder {
        static final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://1ded-178-176-79-80.ngrok-free.app/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static Retrofit getRetrofitInstance() {
        return RetrofitHolder.retrofit;
    }
}