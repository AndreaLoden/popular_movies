package io.nanodegree.andrea.popularmovies.presentation.detail;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import io.nanodegree.andrea.popularmovies.model.Movie;
import io.nanodegree.andrea.popularmovies.persistence.MovieDatabase;

/**
 * Created by Andrea Loddo (andrea@evenly.io) on 17.01.2019
 * for Evenly GmbH
 * <p>
 * Copyright (c) 2019 Evenly GmbH,
 * all rights reserved
 */
public class DetailViewModel extends ViewModel {

    public LiveData<Movie> movie;

    public DetailViewModel(MovieDatabase movieDatabase, Movie movie) {
        this.movie = movieDatabase.getMovieDao().getMovie(movie.id);
    }
}
