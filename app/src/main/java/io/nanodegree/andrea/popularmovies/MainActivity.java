package io.nanodegree.andrea.popularmovies;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.List;

import io.nanodegree.andrea.popularmovies.api.GetMoviesTask;
import io.nanodegree.andrea.popularmovies.api.NetworkUtils;
import io.nanodegree.andrea.popularmovies.model.Movie;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        }
}
