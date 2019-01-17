package io.nanodegree.andrea.popularmovies.presentation.detail;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import io.nanodegree.andrea.popularmovies.model.Movie;
import io.nanodegree.andrea.popularmovies.persistence.MovieDatabase;

/**
 * Created by Andrea Loddo (andrea@evenly.io) on 17.01.2019
 * for Evenly GmbH
 * <p>
 * Copyright (c) 2019 Evenly GmbH,
 * all rights reserved
 */
public class DetailViewModelFactory extends ViewModelProvider.AndroidViewModelFactory {
    private final MovieDatabase movieDatabase;
    private final Movie movie;

    public DetailViewModelFactory(@NonNull Application application, MovieDatabase movieDatabase, Movie movie) {
        super(application);
        this.movieDatabase = movieDatabase;
        this.movie = movie;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        //noinspection unchecked
        return (T) new DetailViewModel(movieDatabase, movie);
    }
}
