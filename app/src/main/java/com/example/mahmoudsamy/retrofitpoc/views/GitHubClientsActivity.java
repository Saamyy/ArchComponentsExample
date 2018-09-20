package com.example.mahmoudsamy.retrofitpoc.views;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.mahmoudsamy.retrofitpoc.R;
import com.example.mahmoudsamy.retrofitpoc.viewmodel.GitHubClientViewModel;

import java.util.List;

public class GitHubClientsActivity extends AppCompatActivity {

    ListView listView;
    GitHubClientViewModel gitHubClientViewModel;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.my_list);
        progressBar=findViewById(R.id.progressBar_cyclic);
        gitHubClientViewModel = ViewModelProviders.of(this).get(GitHubClientViewModel.class);
//        gitHubClientViewModel=new GitHubClientViewModel();
        getLifecycle().addObserver(gitHubClientViewModel);
        gitHubClientViewModel.getUsersNames()
                .observe(this, this::showUsers);

        gitHubClientViewModel.getErrorMessage()
                .observe(this, this::showError);

    }

    private void showError(String error) {
        Toast.makeText(GitHubClientsActivity.this, error, Toast.LENGTH_SHORT).show();

    }

    private void showUsers(List<String> users) {
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                GitHubClientsActivity.this,
                android.R.layout.simple_list_item_1,
                users);
        listView.setAdapter(arrayAdapter);
        handleViews();
    }

    @Override
    protected void onDestroy() {
        getLifecycle().removeObserver(gitHubClientViewModel);
        super.onDestroy();
        handleViews();
    }

    private void handleViews(){
        progressBar.setVisibility(View.GONE);
        listView.setVisibility(View.VISIBLE);
    }
}
