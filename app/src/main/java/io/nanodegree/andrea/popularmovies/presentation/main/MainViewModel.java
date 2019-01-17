package io.nanodegree.andrea.popularmovies.presentation.main;

import android.app.Application;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import io.nanodegree.andrea.popularmovies.model.Movie;
import io.nanodegree.andrea.popularmovies.persistence.MovieDatabase;

/**
 * Created by Andrea Loddo (andrea@evenly.io) on 17.01.2019
 * for Evenly GmbH
 * <p>
 * Copyright (c) 2019 Evenly GmbH,
 * all rights reserved
 */
public class MainViewModel extends AndroidViewModel {

    LiveData<List<Movie>> movieList;

    MovieDatabase movieDatabase;


    public MainViewModel(@NonNull Application application) {
        super(application);

        movieDatabase = MovieDatabase.getInstance(application);

        movieList = movieDatabase.getMovieDao().loadAllFavorites();
    }

    public LiveData<List<Movie>> getMovieList() {
        return movieList;
    }
}
