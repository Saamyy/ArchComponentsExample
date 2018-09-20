package com.example.mahmoudsamy.retrofitpoc.Network;

import com.example.mahmoudsamy.retrofitpoc.utils.Constants;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiConnection {

    private String API_BASE_URL = Constants.API_BASE_URL;

    private OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    public Retrofit getRetrofitCall() {
        Retrofit retrofit =
                new Retrofit.Builder()
                        .baseUrl(API_BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .client(httpClient.build())
                        .build();
        return retrofit;
    }


}
