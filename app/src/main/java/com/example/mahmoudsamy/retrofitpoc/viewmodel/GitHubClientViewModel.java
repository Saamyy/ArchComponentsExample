package com.example.mahmoudsamy.retrofitpoc.viewmodel;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.OnLifecycleEvent;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;

import com.example.mahmoudsamy.retrofitpoc.Network.ApiConnection;
import com.example.mahmoudsamy.retrofitpoc.Network.ApiRetrofit;
import com.example.mahmoudsamy.retrofitpoc.data_source.DataSource;
import com.example.mahmoudsamy.retrofitpoc.model.GitHubRepo;
import com.example.mahmoudsamy.retrofitpoc.utils.Constants;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class GitHubClientViewModel extends ViewModel implements LifecycleObserver {

    private MutableLiveData<List<GitHubRepo>> usersList = new MutableLiveData<>();
    private MutableLiveData<String> errorMessage = new MutableLiveData<>();

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    private void executeNetworkOperations() {
        new DataSource().getGitHubData(usersList,errorMessage);
    }

//    public MutableLiveData<List<GitHubRepo>> getUsersList() {
//        return usersList;
//    }

    public MutableLiveData<String> getErrorMessage() {
        return errorMessage;
    }

    public LiveData<List<String>> getUsersNames() {
        return Transformations
                .map(usersList, input -> {
                    List<String> names=new ArrayList<>();
                    for (GitHubRepo object : input) {
                        names.add(object.getName());
                    }
                    return names;
                });
    }
}
