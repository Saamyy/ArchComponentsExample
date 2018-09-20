package com.example.mahmoudsamy.retrofitpoc.data_source;

import android.arch.lifecycle.MutableLiveData;

import com.example.mahmoudsamy.retrofitpoc.Network.ApiConnection;
import com.example.mahmoudsamy.retrofitpoc.Network.ApiRetrofit;
import com.example.mahmoudsamy.retrofitpoc.model.GitHubRepo;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class DataSource {
    private Retrofit retrofit = new ApiConnection().getRetrofitCall();
    // Create a very simple REST adapter which points the GitHub API endpoint.
    private ApiRetrofit client = retrofit.create(ApiRetrofit.class);

    // Fetch a list of the Github repositories.
    private Call<List<GitHubRepo>> call =
            client.reposForUser("fs-opensource");


    public void getGitHubData(MutableLiveData<List<GitHubRepo>> usersList, MutableLiveData<String> errorMessage) {
        call.clone().enqueue(new Callback<List<GitHubRepo>>() {
            @Override
            public void onResponse(Call<List<GitHubRepo>> call, Response<List<GitHubRepo>> response) {
                // The network call was a success and we got a response
                // TODO: use the repository list and display it
                if (response.isSuccessful()) {

                    List<GitHubRepo> gitHubRepos = response.body();
                    usersList.postValue(gitHubRepos);
                } else {
                    errorMessage.postValue("haga 8alat haslt");
                }
            }

            @Override
            public void onFailure(Call<List<GitHubRepo>> call, Throwable t) {
                // the network call was a failure
                // TODO: handle error
//                Toast.makeText(GitHubClientsActivity.this, "error", Toast.LENGTH_SHORT).show();
                errorMessage.postValue("haga 8alat haslt");
            }
        });
    }

}
