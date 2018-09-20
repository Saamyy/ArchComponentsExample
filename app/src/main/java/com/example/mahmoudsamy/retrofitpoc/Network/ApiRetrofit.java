package com.example.mahmoudsamy.retrofitpoc.Network;

import com.example.mahmoudsamy.retrofitpoc.model.GitHubRepo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Mahmoud samy on 9/16/2017.
 */

public interface ApiRetrofit {
    @GET("/users/{user}/repos")
    Call<List<GitHubRepo>> reposForUser(@Path("user") String user);
}
