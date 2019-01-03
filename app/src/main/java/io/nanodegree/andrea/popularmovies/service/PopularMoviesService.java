package io.nanodegree.andrea.popularmovies.service;

import io.nanodegree.andrea.popularmovies.model.MovieContainer;
import io.nanodegree.andrea.popularmovies.model.ReviewsContainer;
import io.nanodegree.andrea.popularmovies.model.VideoContainer;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

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

    @GET("{id}/videos")
    Call<VideoContainer> getMovieVideos(@Path("id") String id);

    @GET("{id}/reviews")
    Call<ReviewsContainer> getMovieReviews(@Path("id") String id);
}
