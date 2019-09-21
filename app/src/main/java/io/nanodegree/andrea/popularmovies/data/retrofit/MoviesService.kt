package io.nanodegree.andrea.popularmovies.data.retrofit

import retrofit2.http.GET
import retrofit2.http.Path
import io.nanodegree.andrea.popularmovies.data.model.ReviewsContainer
import io.nanodegree.andrea.popularmovies.data.model.VideoContainer
import io.nanodegree.andrea.popularmovies.data.retrofit.response.MovieContainer

interface MoviesService {

    @GET("popular")
    suspend fun listPopularMovies(): MovieContainer

    @GET("{id}/videos")
    suspend fun getMovieVideos(@Path("id") id: String): VideoContainer

    @GET("{id}/reviews")
    suspend fun getMovieReviews(@Path("id") id: String): ReviewsContainer
}
