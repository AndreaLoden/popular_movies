package io.nanodegree.andrea.popularmovies.data.retrofit

import retrofit2.http.GET
import retrofit2.http.Path
import io.nanodegree.andrea.popularmovies.data.model.ReviewsContainer
import io.nanodegree.andrea.popularmovies.data.model.VideoContainer
import io.nanodegree.andrea.popularmovies.data.retrofit.response.MovieContainer

/**
 * Created by Andrea Loddo (andrea@evenly.io) on 20.12.2018
 * for Evenly GmbH
 *
 *
 * Copyright (c) 2018 Evenly GmbH,
 * all rights reserved
 */
interface MoviesService {

    @GET("popular")
    suspend fun listPopularMovies(): MovieContainer

    @GET("{id}/videos")
    suspend fun getMovieVideos(@Path("id") id: String): VideoContainer

    @GET("{id}/reviews")
    suspend fun getMovieReviews(@Path("id") id: String): ReviewsContainer
}
