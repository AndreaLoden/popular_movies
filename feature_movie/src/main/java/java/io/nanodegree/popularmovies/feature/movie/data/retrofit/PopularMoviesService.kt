package java.io.nanodegree.popularmovies.feature.movie.data.retrofit

import java.io.nanodegree.popularmovies.feature.movie.data.retrofit.response.MovieContainer

import retrofit2.Call
import retrofit2.http.GET

/**
 * Created by Andrea Loddo (andrea@evenly.io) on 20.12.2018
 * for Evenly GmbH
 *
 *
 * Copyright (c) 2018 Evenly GmbH,
 * all rights reserved
 */
interface PopularMoviesService {

    @GET("popular")
    suspend fun listPopularMovies(): MovieContainer

    @GET("top_rated")
    suspend fun listTopRatedMovies(): MovieContainer
}
