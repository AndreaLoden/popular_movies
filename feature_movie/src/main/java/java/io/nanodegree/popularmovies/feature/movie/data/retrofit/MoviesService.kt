package java.io.nanodegree.popularmovies.feature.movie.data.retrofit

import retrofit2.http.GET
import retrofit2.http.Path
import java.io.nanodegree.popularmovies.feature.movie.data.model.ReviewsContainer
import java.io.nanodegree.popularmovies.feature.movie.data.model.VideoContainer
import java.io.nanodegree.popularmovies.feature.movie.data.retrofit.response.MovieContainer

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
