package io.nanodegree.andrea.popularmovies.service;

import java.io.nanodegree.popularmovies.feature.movie.data.retrofit.response.MovieContainer;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Andrea Loddo (andrea@evenly.io) on 20.12.2018
 * for Evenly GmbH
 * <p>
 * Copyright (c) 2018 Evenly GmbH,
 * all rights reserved
 */
public interface PopularMoviesService {

    @GET("popular")
    Call<MovieContainer> getListPopularMovies();

    @GET("top_rated")
    Call<MovieContainer> getListTopRatedMovies();

}
